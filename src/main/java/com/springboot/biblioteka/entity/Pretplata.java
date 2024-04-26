package com.springboot.biblioteka.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
@Entity
@Table(name = "pretplata")
public class Pretplata {
    @EmbeddedId
    private PretplataId id;

    @ManyToOne
    @MapsId("korisnikId")
    @JoinColumn(name = "korisnik_ID")
    private Korisnik korisnik;

    @PastOrPresent(message = "Datum pocetka mora biti u proslosti ili sadasnjosti!")
    @NotNull(message = "Datum pocetka je obavezno polje!")
    @Column(name = "datum_od")
    private LocalDate datumOd;

    @NotNull(message = "Datum zavrsetka je obavezno polje!")
    @Column (name = "datum_do")
    private LocalDate datumDo;

    public Pretplata(PretplataId id, Korisnik korisnik, LocalDate datumOd, LocalDate datumDo) {
        this.id = id;
        this.korisnik = korisnik;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
    }

    public Pretplata() {
    }

    public PretplataId getId() {
        return id;
    }

    public void setId(PretplataId id) {
        this.id = id;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
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
