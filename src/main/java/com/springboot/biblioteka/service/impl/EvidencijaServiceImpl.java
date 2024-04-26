package com.springboot.biblioteka.service.impl;

import com.springboot.biblioteka.converter.impl.EvidencijaConverter;
import com.springboot.biblioteka.dto.EvidencijaPozajmljivanjaDto;
import com.springboot.biblioteka.entity.*;
import com.springboot.biblioteka.repository.EvidencijaRepository;
import com.springboot.biblioteka.repository.KnjigaRepository;
import com.springboot.biblioteka.repository.KorisnikRepository;
import com.springboot.biblioteka.repository.PretplataRepository;
import com.springboot.biblioteka.service.EvidencijaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class EvidencijaServiceImpl implements EvidencijaService {

    private final EvidencijaConverter evidencijaConverter;
    private final EvidencijaRepository evidencijaRepository;
    private final KnjigaRepository knjigaRepository;
    private final KorisnikRepository korisnikRepository;
    private final PretplataRepository pretplataRepository;

    public EvidencijaServiceImpl(EvidencijaConverter evidencijaConverter, EvidencijaRepository evidencijaRepository, KnjigaRepository knjigaRepository, KorisnikRepository korisnikRepository, PretplataRepository pretplataRepository) {
        this.evidencijaConverter = evidencijaConverter;
        this.evidencijaRepository = evidencijaRepository;
        this.knjigaRepository = knjigaRepository;
        this.korisnikRepository = korisnikRepository;
        this.pretplataRepository = pretplataRepository;
    }

    @Override
    @Transactional
    public EvidencijaPozajmljivanjaDto save(EvidencijaPozajmljivanjaDto evidencijaPozajmljivanjaDto) throws Exception {
        EvidencijaPozajmljivanja evidencija = evidencijaConverter.toEntity(evidencijaPozajmljivanjaDto);

        //provera da li knjiga postoji
        Optional<Knjiga> knjiga = knjigaRepository.findById(evidencija.getKnjiga().getKnjigaID());
        if (knjiga.isEmpty()) throw new Exception("Knjiga ne postoji!");

        //vrati trenutno pozajmljene knjige
        LocalDate today = LocalDate.now();
        List<EvidencijaPozajmljivanja> trenutnoPozajmljene = evidencijaRepository.findByDatumOdBeforeAndDatumDoAfter(today, today);
        Set<Long> trenutnoPozajmljeneIds = trenutnoPozajmljene.stream()
                .map(e -> e.getKnjiga().getKnjigaID())
                .collect(Collectors.toSet());
        //provera da li se trazena knjiga nalazi medju trenutno pozajmljenim knjigama
        if (trenutnoPozajmljeneIds.contains(knjiga.get().getKnjigaID()))
            throw new Exception("Knjiga trenutno nije dostupna!");

        //provera da li korisnik postoji
        Optional<Korisnik> korisnik = korisnikRepository.findById(evidencija.getKorisnik().getKorisnikID());
        if (korisnik.isEmpty()) throw new Exception("Korisnik ne postoji!");

        //provera da li je korisnik pretplacen
        Optional<Pretplata> trenutnoStanje = pretplataRepository.findByKorisnikKorisnikIDAndDatumDoAfter(korisnik.get().getKorisnikID(), today);
        if (trenutnoStanje.isEmpty()) throw new Exception("Korisnik nije pretplacen");

        return evidencijaConverter.toDto(evidencijaRepository.save(evidencija));
    }

    @Override
    @Transactional
    public EvidencijaPozajmljivanjaDto update(EvidencijaPozajmljivanjaDto evidencijaPozajmljivanjaDto) throws Exception {
        //provera da li evidencija pozajmljivanja postoji
        EvidencijaPozajmljivanja existingE = evidencijaRepository.findById(
                        new EvidencijaPozajmljivanjaId(evidencijaPozajmljivanjaDto.getPozajmicaID(), evidencijaPozajmljivanjaDto.getKnjigaDto().getKnjigaID(), evidencijaPozajmljivanjaDto.getKorisnikDto().getKorisnikID()))
                .orElseThrow(() -> new Exception("Evidencija pozajmljivanja sa zadatim ID nije pronađena."));
        EvidencijaPozajmljivanja evidencija = evidencijaConverter.toEntity(evidencijaPozajmljivanjaDto);

        //provera da li knjiga postoji
        Optional<Knjiga> knjiga = knjigaRepository.findById(evidencija.getKnjiga().getKnjigaID());
        if (knjiga.isEmpty()) throw new Exception("Knjiga ne postoji!");

        //provera da li korisnik postoji
        Optional<Korisnik> korisnik = korisnikRepository.findById(evidencija.getKorisnik().getKorisnikID());
        if (korisnik.isEmpty()) throw new Exception("Korisnik ne postoji!");

        return evidencijaConverter.toDto(evidencijaRepository.save(evidencija));
    }

    @Override
    public List<EvidencijaPozajmljivanjaDto> getAll() {
        return evidencijaRepository.findAll().stream().map(evidencijaConverter::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long pozajmicaID, Long knjigaID, Long korisnikID) throws Exception {
        //provera da li evidencija pozajmljivanja postoji
        EvidencijaPozajmljivanja existingE = evidencijaRepository.findById(
                        new EvidencijaPozajmljivanjaId(pozajmicaID, knjigaID, korisnikID))
                .orElseThrow(() -> new Exception("Evidencija pozajmljivanja sa zadatim ID nije pronađena."));

        evidencijaRepository.delete(existingE);
    }
}
