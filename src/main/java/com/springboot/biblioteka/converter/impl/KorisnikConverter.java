package com.springboot.biblioteka.converter.impl;

import com.springboot.biblioteka.converter.DtoEntityConverter;
import com.springboot.biblioteka.dto.KorisnikDto;
import com.springboot.biblioteka.entity.Korisnik;
import org.springframework.stereotype.Component;

@Component
public class KorisnikConverter implements DtoEntityConverter<KorisnikDto,Korisnik>{

    @Override
    public KorisnikDto toDto(Korisnik entity) {
        return new KorisnikDto(entity.getKorisnikID(), entity.getKorisnickoIme(), entity.getLozinka(),
                entity.getIme(), entity.getEmail(), entity.getRola(), entity.getVrstaClanarine(), entity.getAktivan());
    }

    @Override
    public Korisnik toEntity(KorisnikDto dto) {
        return new Korisnik(dto.getKorisnikID(), dto.getKorisnickoIme(), dto.getLozinka(),
                dto.getIme(), dto.getEmail(), dto.getRola(), dto.getVrstaClanarine(), dto.getAktivan());
    }
}
