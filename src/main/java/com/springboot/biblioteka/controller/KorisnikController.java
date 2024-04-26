package com.springboot.biblioteka.controller;

import com.springboot.biblioteka.dto.KorisnikDto;
import com.springboot.biblioteka.dto.LoginDto;
import com.springboot.biblioteka.dto.RegisterDto;
import com.springboot.biblioteka.service.KorisnikService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/korisnik")
public class KorisnikController {
    private final KorisnikService korisnikService;

    public KorisnikController(KorisnikService korisnikService) {
        this.korisnikService = korisnikService;
    }

    //kreiranje korisnika od strane radnika
    @PostMapping
    public ResponseEntity<Object> save( @Valid @RequestBody KorisnikDto korisnik) throws Exception{
        korisnik.setKorisnikID(-1L);
        KorisnikDto korisnikDto = korisnikService.save(korisnik);
        return new ResponseEntity<>(korisnikDto, HttpStatus.CREATED);
    }

    //registrovanje korisnika
    @PostMapping("/register")
    public ResponseEntity<Object> save( @Valid @RequestBody RegisterDto registerDto) throws Exception{
        KorisnikDto korisnikDto = korisnikService.register(registerDto);
        return new ResponseEntity<>(korisnikDto, HttpStatus.CREATED);
    }

    //login korisnika
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDto login) throws Exception{
        KorisnikDto korisnikDto = korisnikService.login(login);
        return new ResponseEntity<>(korisnikDto, HttpStatus.OK);
    }

    //azuriranje korisnika
    @PutMapping
    public ResponseEntity<Object> update( @RequestBody KorisnikDto korisnik) throws Exception{
        KorisnikDto korisnikDto = korisnikService.update(korisnik);
        return new ResponseEntity<>(korisnikDto, HttpStatus.OK);
    }

    //prikaz svih korisnika
    @GetMapping
    public ResponseEntity<Object> getAll(){
        List<KorisnikDto> korisnikDtos = korisnikService.getAll();
        return new ResponseEntity<>(korisnikDtos, HttpStatus.OK);
    }

    //prikaz svih clanova biblioteke
    @GetMapping("/clanovi")
    public ResponseEntity<Object> getClanovi(){
        List<KorisnikDto> korisnikDtos = korisnikService.getClanovi();
        return new ResponseEntity<>(korisnikDtos, HttpStatus.OK);
    }

    //brisanje korisnika
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) throws Exception{
        korisnikService.delete(id);
        return new ResponseEntity<>("Korisnik obrisan!", HttpStatus.OK);
    }
}
