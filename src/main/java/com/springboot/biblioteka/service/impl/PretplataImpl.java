package com.springboot.biblioteka.service.impl;

import com.springboot.biblioteka.converter.impl.PretplataConverter;
import com.springboot.biblioteka.dto.PretplataDto;
import com.springboot.biblioteka.entity.*;
import com.springboot.biblioteka.repository.KorisnikRepository;
import com.springboot.biblioteka.repository.PretplataRepository;
import com.springboot.biblioteka.service.PretplataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PretplataImpl implements PretplataService {

    private final PretplataConverter pretplataConverter;
    private final PretplataRepository pretplataRepository;
    private final KorisnikRepository korisnikRepository;

    public PretplataImpl(PretplataConverter pretplataConverter, PretplataRepository pretplataRepository, KorisnikRepository korisnikRepository) {
        this.pretplataConverter = pretplataConverter;
        this.pretplataRepository = pretplataRepository;
        this.korisnikRepository = korisnikRepository;
    }

    @Override
    @Transactional
    public PretplataDto save(PretplataDto pretplataDto) throws Exception {
        Pretplata pretplata = pretplataConverter.toEntity(pretplataDto);
        //provera da li korisnik postoji
        Optional<Korisnik> korisnik = korisnikRepository.findById(pretplata.getKorisnik().getKorisnikID());
        if (korisnik.isEmpty()) throw new Exception("Korisnik ne postoji!");

        return pretplataConverter.toDto(pretplataRepository.save(pretplata));
    }

    @Override
    @Transactional
    public PretplataDto update(PretplataDto pretplataDto) throws Exception{
        //provera da li pretplata postoji
        Pretplata existingP = pretplataRepository.findById(
                        new PretplataId(pretplataDto.getPretplataID(), pretplataDto.getKorisnikDto().getKorisnikID()))
                .orElseThrow(() -> new Exception("Pretplata sa zadatim ID nije pronađena."));
        Pretplata pretplata = pretplataConverter.toEntity(pretplataDto);

        //provera da li korisnik postoji
        Optional<Korisnik> korisnik = korisnikRepository.findById(pretplata.getKorisnik().getKorisnikID());
        if (korisnik.isEmpty()) throw new Exception("Korisnik ne postoji!");

        return pretplataConverter.toDto(pretplataRepository.save(pretplata));
    }

    @Override
    public List<PretplataDto> getAll() {
        return pretplataRepository.findAll().stream().map(pretplataConverter::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long pretplataID, Long korisnikID) throws Exception{
        //provera da li pretplata postoji
        Pretplata existingP = pretplataRepository.findById(new PretplataId(pretplataID,korisnikID))
                .orElseThrow(() -> new Exception("Pretplata sa zadatim ID nije pronađena."));

        pretplataRepository.delete(existingP);
    }

    @Override
    public PretplataDto findStanjePretplate(String korisnickoIme) throws Exception {
        //provera da li korisnik postoji
        Korisnik korisnik = korisnikRepository.findByKorisnickoIme(korisnickoIme)
                .orElseThrow(() -> new Exception("Korisnik nije pronađen"));

        //vrati trenutno stanje pretplate za odredjenog korisnika
        Long korisnikID = korisnik.getKorisnikID();
        LocalDate today = LocalDate.now();
        Pretplata trenutnoStanje = pretplataRepository.findByKorisnikKorisnikIDAndDatumDoAfter(korisnikID, today)
                .orElseThrow(() -> new Exception("Korisnik nije pretplacen"));
        return pretplataConverter.toDto(trenutnoStanje);
    }
}
