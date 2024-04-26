package com.springboot.biblioteka.service.impl;

import com.springboot.biblioteka.converter.impl.KorisnikConverter;
import com.springboot.biblioteka.dto.KorisnikDto;
import com.springboot.biblioteka.dto.LoginDto;
import com.springboot.biblioteka.dto.RegisterDto;
import com.springboot.biblioteka.entity.Korisnik;
import com.springboot.biblioteka.repository.KorisnikRepository;
import com.springboot.biblioteka.service.KorisnikService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class KorisnikServiceImpl implements KorisnikService {

    private final KorisnikRepository korisnikRepository;
    private final KorisnikConverter korisnikConverter;
    private final BCryptPasswordEncoder passwordEncoder;

    public KorisnikServiceImpl(KorisnikRepository korisnikRepository, KorisnikConverter korisnikConverter, BCryptPasswordEncoder passwordEncoder) {
        this.korisnikRepository = korisnikRepository;
        this.korisnikConverter = korisnikConverter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public KorisnikDto save(KorisnikDto korisnikDto) throws Exception {
        Korisnik korisnik = korisnikConverter.toEntity(korisnikDto);
        korisnik.setLozinka(passwordEncoder.encode(korisnik.getLozinka())); //enkriptovanje lozinke
        return korisnikConverter.toDto(korisnikRepository.save(korisnik));
    }

    @Override
    @Transactional
    public KorisnikDto update(KorisnikDto korisnikDto) throws Exception {
        //provera da li korisnik postoji
        Korisnik existingK = korisnikRepository.findById(korisnikDto.getKorisnikID())
                .orElseThrow(() -> new Exception("Korisnik sa zadatim ID nije pronađen."));
        if (!passwordEncoder.matches(korisnikDto.getLozinka(), existingK.getLozinka()) && !korisnikDto.getLozinka().equals(existingK.getLozinka())) {
            //ako je izmenjena lozinka ponovna enkripcija
            korisnikDto.setLozinka(passwordEncoder.encode(korisnikDto.getLozinka()));
        } else {
            //ako nije izmenjena lozinka ostaje ista
            korisnikDto.setLozinka(existingK.getLozinka());
        }
        Korisnik korisnik = korisnikConverter.toEntity(korisnikDto);
        return korisnikConverter.toDto(korisnikRepository.save(korisnik));
    }

    @Override
    public KorisnikDto login(LoginDto loginDto) throws Exception {
        //provera da li korisnicko ime postoji
        Korisnik korisnik = korisnikRepository.findByKorisnickoIme(loginDto.getKorisnickoIme())
                .orElseThrow(() -> new Exception("Korisnicko ime ne postoji!"));
        String lozinka = loginDto.getLozinka();
        String encodedPassword = korisnik.getLozinka();
        //provera da li se lozinka poklapa
        if (passwordEncoder.matches(lozinka, encodedPassword)) {
            return korisnikConverter.toDto(korisnik);
        } else {
            throw new Exception("Pogresna lozinka!");
        }

    }

    @Override
    public List<KorisnikDto> getClanovi() {
        return korisnikRepository.findByRola("clan").stream().map(korisnikConverter::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public KorisnikDto register(RegisterDto registerDto) throws Exception {
        //provera da li je korisnicko ime zauzeto
        Optional<Korisnik> existingK = korisnikRepository.findByKorisnickoIme(registerDto.getKorisnickoIme());
        if (existingK.isPresent()) {
            throw new Exception("Korisnicko ime je zauzeto");
        }

        Korisnik korisnik = new Korisnik(-1L, registerDto.getKorisnickoIme(), passwordEncoder.encode(registerDto.getLozinka()),
                registerDto.getIme(), registerDto.getEmail(), "clan", registerDto.getVrstaClanarine(), true);
        return korisnikConverter.toDto(korisnikRepository.save(korisnik));
    }

    @Override
    public List<KorisnikDto> getAll() {
        return korisnikRepository.findAll().stream().map(korisnikConverter::toDto).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        //provera da li korisnik sa zadatim id postoji
        Korisnik korisnik = korisnikRepository.findById(id)
                .orElseThrow(() -> new Exception("Korisnik sa zadatim ID nije pronađen."));

        korisnikRepository.delete(korisnik);
    }

}
