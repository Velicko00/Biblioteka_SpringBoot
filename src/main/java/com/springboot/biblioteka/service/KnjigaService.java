package com.springboot.biblioteka.service;

import com.springboot.biblioteka.dto.KnjigaDto;

import java.util.List;
import java.util.Map;

public interface KnjigaService {
    KnjigaDto save(KnjigaDto knjigaDto) throws Exception;

    List<KnjigaDto> getAll();

    void delete(Long id) throws Exception;

    KnjigaDto update(KnjigaDto knjigaDto)throws Exception;

    Map<String, Long> findKolicinaDostupnih();

    List<KnjigaDto> findDostupneKnjige();

    List<KnjigaDto> findPozajmljeneKnjige(String korisnickoIme) throws Exception;
}
