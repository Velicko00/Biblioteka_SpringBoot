package com.springboot.biblioteka.service.impl;

import com.springboot.biblioteka.converter.impl.KnjigaConverter;
import com.springboot.biblioteka.dto.KnjigaDto;
import com.springboot.biblioteka.entity.EvidencijaPozajmljivanja;
import com.springboot.biblioteka.entity.Knjiga;
import com.springboot.biblioteka.entity.Korisnik;
import com.springboot.biblioteka.repository.EvidencijaRepository;
import com.springboot.biblioteka.repository.KnjigaRepository;
import com.springboot.biblioteka.repository.KorisnikRepository;
import com.springboot.biblioteka.service.KnjigaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class KnjigaServiceImpl implements KnjigaService {
    private final KnjigaRepository knjigaRepository;
    private final KnjigaConverter knjigaConverter;
    private final EvidencijaRepository evidencijaRepository;
    private final KorisnikRepository korisnikRepository;

    public KnjigaServiceImpl(KnjigaRepository knjigaRepository, KnjigaConverter knjigaConverter, EvidencijaRepository evidencijaRepository, KorisnikRepository korisnikRepository) {
        this.knjigaRepository = knjigaRepository;
        this.knjigaConverter = knjigaConverter;
        this.evidencijaRepository = evidencijaRepository;
        this.korisnikRepository = korisnikRepository;
    }

    @Override
    @Transactional
    public KnjigaDto save(KnjigaDto knjigaDto) throws Exception {
        Knjiga knjiga = knjigaConverter.toEntity(knjigaDto);
        return knjigaConverter.toDto(knjigaRepository.save(knjiga));
    }

    @Override
    public List<KnjigaDto> getAll() {
        return knjigaRepository.findAll().stream().map(knjigaConverter::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        //provera da li knjiga sa zadatim id postoji
        Knjiga knjiga = knjigaRepository.findById(id)
                .orElseThrow(() -> new Exception("Knjiga sa zadatim ID nije pronađena."));

        knjigaRepository.delete(knjiga);
    }

    @Override
    @Transactional
    public KnjigaDto update(KnjigaDto knjigaDto) throws Exception {
        //provera da li knjiga postoji
        Knjiga existingK = knjigaRepository.findById(knjigaDto.getKnjigaID())
                .orElseThrow(() -> new Exception("Knjiga sa zadatim ID nije pronađena."));

        Knjiga knjiga = knjigaConverter.toEntity(knjigaDto);
        return knjigaConverter.toDto(knjigaRepository.save(knjiga));
    }

    @Override
    public Map<String, Long> findKolicinaDostupnih() {
        //vrati trenutno aktuelne evidencije pozajmica
        LocalDate today = LocalDate.now();
        List<EvidencijaPozajmljivanja> trenutnoPozajmljene = evidencijaRepository.findByDatumOdBeforeAndDatumDoAfter(today, today);

        //vrati IDs trenutno pozajmljenih knjiga
        Set<Long> trenutnoPozajmljeneIds = trenutnoPozajmljene.stream()
                .map(evidencija -> evidencija.getKnjiga().getKnjigaID())
                .collect(Collectors.toSet());

        //vrati sve knjige
        List<Knjiga> sveKnjige = knjigaRepository.findAll();

        //filtriraj one primerke knjiga koje nisu trenutno pozajmljene,
        //grupisi po ISBN i za svaku izbroji koliko ima dostupnih primeraka
        Map<String, Long> kolicinaDostupnih = sveKnjige.stream()
                .filter(knjiga -> !trenutnoPozajmljeneIds.contains(knjiga.getKnjigaID()))
                .collect(Collectors.groupingBy(Knjiga::getIsbn, Collectors.counting()));
        return kolicinaDostupnih;
    }

    @Override
    public List<KnjigaDto> findDostupneKnjige() {
        //vrati trenutno aktuelne evidencije pozajmica
        LocalDate today = LocalDate.now();
        List<EvidencijaPozajmljivanja> trenutnoPozajmljene = evidencijaRepository.findByDatumOdBeforeAndDatumDoAfter(today, today);

        //vrati IDs trenutno pozajmljenih knjiga
        Set<Long> trenutnoPozajmljeneIds = trenutnoPozajmljene.stream()
                .map(evidencija -> evidencija.getKnjiga().getKnjigaID())
                .collect(Collectors.toSet());

        //vrati sve knjige
        List<Knjiga> sveKnjige = knjigaRepository.findAll();

        //filtriraj one primerke knjiga koje nisu trenutno pozajmljene
        List<KnjigaDto> dostupneKnjige = sveKnjige.stream().map(knjigaConverter::toDto)
                .filter(knjiga -> !trenutnoPozajmljeneIds.contains(knjiga.getKnjigaID()))
                .collect(Collectors.toList());
        return dostupneKnjige;
    }

    @Override
    public List<KnjigaDto> findPozajmljeneKnjige(String korisnickoIme) throws Exception {
        //provera da li korisnik postoji
        Korisnik korisnik = korisnikRepository.findByKorisnickoIme(korisnickoIme)
                .orElseThrow(() -> new Exception("Korisnik nije pronađen"));

        //vrati trenutno aktuelne evidencije pozajmica za odredjenog korisnika
        Long korisnikID = korisnik.getKorisnikID();
        LocalDate today = LocalDate.now();
        List<EvidencijaPozajmljivanja> trenutnoPozajmljene = evidencijaRepository.findByKorisnikKorisnikIDAndDatumDoAfter(korisnikID, today);

        //vrati trenutno pozajmljene knjige za odrednjenog korisnika
        List<KnjigaDto> pozajmljeneKnjige = trenutnoPozajmljene.stream()
                .map(evidencija -> {
                    Knjiga knjiga = knjigaRepository.findById(evidencija.getKnjiga().getKnjigaID()).orElse(null);
                    if (knjiga != null) {
                        return knjigaConverter.toDto(knjiga);
                    }
                    return null;
                })
                .filter(Objects::nonNull).collect(Collectors.toList());
        return pozajmljeneKnjige;
    }
}
