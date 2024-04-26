package com.springboot.biblioteka.converter.impl;

import com.springboot.biblioteka.converter.DtoEntityConverter;
import com.springboot.biblioteka.dto.KorisnikDto;
import com.springboot.biblioteka.dto.PretplataDto;
import com.springboot.biblioteka.entity.Korisnik;
import com.springboot.biblioteka.entity.Pretplata;
import com.springboot.biblioteka.entity.PretplataId;
import org.springframework.stereotype.Component;

@Component
public class PretplataConverter implements DtoEntityConverter <PretplataDto, Pretplata>{
    private final KorisnikConverter korisnikConverter;

    public PretplataConverter(KorisnikConverter korisnikConverter) {
        this.korisnikConverter = korisnikConverter;
    }

    @Override
    public PretplataDto toDto(Pretplata entity) {
        return new PretplataDto(entity.getId().getPretplataId(), korisnikConverter.toDto(entity.getKorisnik()),
                entity.getDatumOd(),entity.getDatumDo());
    }

    @Override
    public Pretplata toEntity(PretplataDto dto) {
        return new Pretplata(new PretplataId(dto.getPretplataID(),dto.getKorisnikDto().getKorisnikID()),
                korisnikConverter.toEntity(dto.getKorisnikDto()),
                dto.getDatumOd(),dto.getDatumDo());
    }
}
