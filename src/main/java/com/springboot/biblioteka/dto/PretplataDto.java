package com.springboot.biblioteka.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class PretplataDto {

    private Long pretplataID;

    private KorisnikDto korisnikDto;

    @NotNull(message = "Datum pocetka je obavezno polje!")
    private LocalDate datumOd;

    @NotNull(message = "Datum zavrsetka je obavezno polje!")
    private LocalDate datumDo;

    public PretplataDto(Long pretplataID, KorisnikDto korisnikDto, LocalDate datumOd, LocalDate datumDo) {
        this.pretplataID = pretplataID;
        this.korisnikDto = korisnikDto;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
    }

    public PretplataDto() {
    }

    public Long getPretplataID() {
        return pretplataID;
    }

    public void setPretplataID(Long pretplataID) {
        this.pretplataID = pretplataID;
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
}
