package com.springboot.biblioteka.service;

import com.springboot.biblioteka.dto.KorisnikDto;
import com.springboot.biblioteka.dto.LoginDto;
import com.springboot.biblioteka.dto.RegisterDto;

import java.util.List;

public interface KorisnikService {
    KorisnikDto save(KorisnikDto korisnikDto) throws Exception;

    List<KorisnikDto> getAll();

    void delete(Long id) throws Exception;

    KorisnikDto update(KorisnikDto korisnikDto)throws Exception;

    KorisnikDto login(LoginDto loginDto)throws Exception;

    List<KorisnikDto> getClanovi();

    KorisnikDto register(RegisterDto registerDto) throws Exception;
}
