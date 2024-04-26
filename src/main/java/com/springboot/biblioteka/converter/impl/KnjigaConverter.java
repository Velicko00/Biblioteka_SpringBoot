package com.springboot.biblioteka.converter.impl;

import com.springboot.biblioteka.converter.DtoEntityConverter;
import com.springboot.biblioteka.dto.KnjigaDto;
import com.springboot.biblioteka.entity.Knjiga;
import org.springframework.stereotype.Component;

@Component
public class KnjigaConverter implements DtoEntityConverter<KnjigaDto, Knjiga> {

    @Override
    public KnjigaDto toDto(Knjiga entity) {
        return new KnjigaDto(entity.getKnjigaID(), entity.getIsbn(), entity.getNaslov(),
                entity.getAutor(), entity.getZanr());
    }

    @Override
    public Knjiga toEntity(KnjigaDto dto) {
        return new Knjiga(dto.getKnjigaID(), dto.getIsbn(), dto.getNaslov(),
                dto.getAutor(), dto.getZanr());
    }
}

