package com.springboot.biblioteka.converter.impl;

import com.springboot.biblioteka.converter.DtoEntityConverter;
import com.springboot.biblioteka.dto.EvidencijaPozajmljivanjaDto;
import com.springboot.biblioteka.entity.EvidencijaPozajmljivanja;
import com.springboot.biblioteka.entity.EvidencijaPozajmljivanjaId;
import org.springframework.stereotype.Component;

@Component
public class EvidencijaConverter implements DtoEntityConverter<EvidencijaPozajmljivanjaDto, EvidencijaPozajmljivanja> {
    private final KnjigaConverter knjigaConverter;
    private final KorisnikConverter korisnikConverter;

    public EvidencijaConverter(KnjigaConverter knjigaConverter, KorisnikConverter korisnikConverter) {
        this.knjigaConverter = knjigaConverter;
        this.korisnikConverter = korisnikConverter;
    }

    @Override
    public EvidencijaPozajmljivanjaDto toDto(EvidencijaPozajmljivanja entity) {
        return new EvidencijaPozajmljivanjaDto(entity.getId().getPozajmicaId(), knjigaConverter.toDto(entity.getKnjiga()),
                korisnikConverter.toDto(entity.getKorisnik()), entity.getDatumOd(), entity.getDatumDo(),
                entity.getKomentar());
    }

    @Override
    public EvidencijaPozajmljivanja toEntity(EvidencijaPozajmljivanjaDto dto) {
        return new EvidencijaPozajmljivanja(new EvidencijaPozajmljivanjaId(dto.getPozajmicaID(),
                dto.getKnjigaDto().getKnjigaID(), dto.getKorisnikDto().getKorisnikID()),
                knjigaConverter.toEntity(dto.getKnjigaDto()),
                korisnikConverter.toEntity(dto.getKorisnikDto()), dto.getDatumOd(), dto.getDatumDo(),
                dto.getKomentar());
    }
}
