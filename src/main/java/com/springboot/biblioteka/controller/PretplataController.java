package com.springboot.biblioteka.controller;

import com.springboot.biblioteka.dto.PretplataDto;
import com.springboot.biblioteka.service.PretplataService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pretplata")
public class PretplataController {
    private final PretplataService pretplataService;

    public PretplataController(PretplataService pretplataService) {
        this.pretplataService = pretplataService;
    }

    //kreiranje pretplate
    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody PretplataDto pretplata) throws Exception{
        PretplataDto pretplataDto = pretplataService.save(pretplata);
        return new ResponseEntity<>(pretplataDto, HttpStatus.CREATED);
    }

    //azuriranje pretplate
    @PutMapping
    public ResponseEntity<Object> update( @RequestBody PretplataDto pretplata) throws Exception{
        PretplataDto pretplataDto = pretplataService.update(pretplata);
        return new ResponseEntity<>(pretplataDto, HttpStatus.OK);
    }

    //prikaz svih pretplata
    @GetMapping
    public ResponseEntity<Object> getAll(){
        List<PretplataDto> pretplataDtos = pretplataService.getAll();
        return new ResponseEntity<>(pretplataDtos, HttpStatus.OK);
    }

    //stanje aktivne pretplate za ulogovanog korisnika
    @GetMapping("/stanje")
    public ResponseEntity<Object> getStanjePretlate(Authentication authentication)throws Exception{
        String korisnickoIme = authentication.getName();
        PretplataDto stanjePretplate = pretplataService.findStanjePretplate(korisnickoIme);
        return new ResponseEntity<>(stanjePretplate, HttpStatus.OK);
    }

    //brisanje pretplate
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestParam("pretplataID") Long pretplataID, @RequestParam("korisnikID") Long korisnikID) throws Exception{
        pretplataService.delete(pretplataID, korisnikID);
        return new ResponseEntity<>("Pretplata je obrisana!", HttpStatus.OK);
    }
}
