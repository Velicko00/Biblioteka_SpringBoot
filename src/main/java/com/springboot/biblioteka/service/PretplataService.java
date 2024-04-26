package com.springboot.biblioteka.service;

import com.springboot.biblioteka.dto.PretplataDto;

import java.util.List;

public interface PretplataService {
    PretplataDto save(PretplataDto pretplata) throws Exception;

    PretplataDto update(PretplataDto pretplata) throws Exception;

    List<PretplataDto> getAll();

    void delete(Long pretplataID, Long korisnikID) throws Exception;

    PretplataDto findStanjePretplate(String korisnickoIme) throws Exception;
}
