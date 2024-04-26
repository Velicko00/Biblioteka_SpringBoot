package com.springboot.biblioteka.controller;

import com.springboot.biblioteka.dto.EvidencijaPozajmljivanjaDto;
import com.springboot.biblioteka.service.EvidencijaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evidencija")
public class EvidencijaController {
    private final EvidencijaService evidencijaService;
    public EvidencijaController(EvidencijaService evidencijaService) {
        this.evidencijaService = evidencijaService;
    }

    //kreiranje evidencije pozajmljivanja
    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody EvidencijaPozajmljivanjaDto evidencija) throws Exception{
        EvidencijaPozajmljivanjaDto evidencijaPozajmljivanjaDto = evidencijaService.save(evidencija);
        return new ResponseEntity<>(evidencijaPozajmljivanjaDto, HttpStatus.CREATED);
    }

    //azuriranje evidencije pozajmljivanja
    @PutMapping
    public ResponseEntity<Object> update( @RequestBody EvidencijaPozajmljivanjaDto evidencija) throws Exception{
        EvidencijaPozajmljivanjaDto evidencijaPozajmljivanjaDto = evidencijaService.update(evidencija);
        return new ResponseEntity<>(evidencijaPozajmljivanjaDto, HttpStatus.OK);
    }

    //prikaz svih evidencija pozajmljivanja
    @GetMapping
    public ResponseEntity<Object> getAll(){
        List<EvidencijaPozajmljivanjaDto> evidencijaPozajmljivanjaDtos = evidencijaService.getAll();
        return new ResponseEntity<>(evidencijaPozajmljivanjaDtos, HttpStatus.OK);
    }

    //brisanje evidencije pozajmljivanja
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestParam("pozajmicaID") Long pozajmicaID, @RequestParam("knjigaID") Long knjigaID, @RequestParam("korisnikID") Long korisnikID) throws Exception{
        evidencijaService.delete(pozajmicaID, knjigaID, korisnikID);
        return new ResponseEntity<>("Evidencija pozajmljivanja je obrisana!", HttpStatus.OK);
    }
}
