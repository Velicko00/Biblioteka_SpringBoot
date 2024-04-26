package com.springboot.biblioteka.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public class EvidencijaPozajmljivanjaDto {
    private Long pozajmicaID;

    @NotNull(message = "Knjiga je obavezno polje!")
    private KnjigaDto knjigaDto;

    @NotNull(message = "Korisnik je obavezno polje!")
    private KorisnikDto korisnikDto;

    @PastOrPresent(message = "Datum pocetka mora biti u proslosti ili sadasnjosti!")
    @NotNull(message = "Datum pocetka je obavezno polje!")
    private LocalDate datumOd;

    //@Future(message = "Datum zavrsetka mora biti u buducnosti!")
    @NotNull(message = "Datum zavrsetka je obavezno polje!")
    private LocalDate datumDo;

    private String komentar;

    public EvidencijaPozajmljivanjaDto() {
    }

    public EvidencijaPozajmljivanjaDto(Long pozajmicaID, KnjigaDto knjigaDto, KorisnikDto korisnikDto, LocalDate datumOd, LocalDate datumDo, String komentar) {
        this.pozajmicaID = pozajmicaID;
        this.knjigaDto = knjigaDto;
        this.korisnikDto = korisnikDto;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.komentar = komentar;
    }

    public Long getPozajmicaID() {
        return pozajmicaID;
    }

    public void setPozajmicaID(Long pozajmicaID) {
        this.pozajmicaID = pozajmicaID;
    }

    public KnjigaDto getKnjigaDto() {
        return knjigaDto;
    }

    public void setKnjigaDto(KnjigaDto knjigaDto) {
        this.knjigaDto = knjigaDto;
    }

    public KorisnikDto getKorisnikDto() {
        return korisnikDto;
    }

    public void setKorisnikDto(KorisnikDto korisnikDto) {
        this.korisnikDto = korisnikDto;
    }

    public LocalDate getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(LocalDate datumOd) {
        this.datumOd = datumOd;
    }

    public LocalDate getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(LocalDate datumDo) {
        this.datumDo = datumDo;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }
}
