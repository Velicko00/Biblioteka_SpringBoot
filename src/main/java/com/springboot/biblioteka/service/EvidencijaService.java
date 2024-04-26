package com.springboot.biblioteka.service;

import com.springboot.biblioteka.dto.EvidencijaPozajmljivanjaDto;

import java.util.List;

public interface EvidencijaService {
    EvidencijaPozajmljivanjaDto save(EvidencijaPozajmljivanjaDto evidencijaPozajmljivanjaDto) throws Exception;

    List<EvidencijaPozajmljivanjaDto> getAll();

    void delete(Long pozajmicaID, Long knjigaID, Long korisnikID) throws Exception;

    EvidencijaPozajmljivanjaDto update(EvidencijaPozajmljivanjaDto evidencijaPozajmljivanjaDto)throws Exception;
}
