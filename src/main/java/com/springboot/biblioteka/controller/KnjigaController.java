package com.springboot.biblioteka.controller;

import com.springboot.biblioteka.dto.KnjigaDto;
import com.springboot.biblioteka.service.KnjigaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/knjiga")
public class KnjigaController {
    private final KnjigaService knjigaService;
    public KnjigaController(KnjigaService knjigaService) {
        this.knjigaService = knjigaService;
    }

    //kreiranje knjiga
    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody KnjigaDto knjiga) throws Exception{
        knjiga.setKnjigaID(-1L);
        KnjigaDto knjigaDto = knjigaService.save(knjiga);
        return new ResponseEntity<>(knjigaDto, HttpStatus.CREATED);
    }

    //azuriranje knjiga
    @PutMapping
    public ResponseEntity<Object> update( @RequestBody KnjigaDto knjiga) throws Exception{
        KnjigaDto knjigaDto = knjigaService.update(knjiga);
        return new ResponseEntity<>(knjigaDto, HttpStatus.OK);
    }

    //prikaz svih primeraka knjiga
    @GetMapping
    public ResponseEntity<Object> getAll(){
        List<KnjigaDto> knjigaDtos = knjigaService.getAll();
        return new ResponseEntity<>(knjigaDtos, HttpStatus.OK);
    }

    //prikaz kolicina svih dostupnih knjiga
    @GetMapping("/kolicina")
    public ResponseEntity<Map<String,Long>> getKolicinaDostupnih(){
        Map<String,Long> kolicinaDostupnih = knjigaService.findKolicinaDostupnih();
        return new ResponseEntity<>(kolicinaDostupnih, HttpStatus.OK);
    }

    //prikaz svih dostupnih knjiga
    @GetMapping("/dostupne")
    public ResponseEntity<Object> getDostupneKnjige(){
        List<KnjigaDto> dostupneKnjige = knjigaService.findDostupneKnjige();
        return new ResponseEntity<>(dostupneKnjige, HttpStatus.OK);
    }

    //prikaz svih pozajmljenih knjiga koje ulogovani korisnik duguje
    @GetMapping("/pozajmljene")
    public ResponseEntity<Object> getPozajmljeneKnjige(Authentication authentication)throws Exception{
        String korisnickoIme = authentication.getName();
        List<KnjigaDto> pozajmljeneKnjige = knjigaService.findPozajmljeneKnjige(korisnickoIme);
        return new ResponseEntity<>(pozajmljeneKnjige, HttpStatus.OK);
    }

    //brisanje knjiga
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) throws Exception{
        knjigaService.delete(id);
        return new ResponseEntity<>("Knjiga je obrisana!", HttpStatus.OK);
    }
}
