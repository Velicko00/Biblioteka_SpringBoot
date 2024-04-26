package com.springboot.biblioteka.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

@Entity
@Table(name = "evidencija_pozajmljivanja")
public class EvidencijaPozajmljivanja {
    @EmbeddedId
    private EvidencijaPozajmljivanjaId id;

    @ManyToOne
    @MapsId("knjigaId")
    @JoinColumn(name = "knjiga_ID")
    private Knjiga knjiga;

    @ManyToOne
    @MapsId("korisnikId")
    @JoinColumn(name = "korisnik_ID")
    private Korisnik korisnik;

    @PastOrPresent(message = "Datum pocetka mora biti u proslosti ili sadasnjosti!")
    @NotNull(message = "Datum pocetka je obavezno polje!")
    @Column (name = "datum_od")
    private LocalDate datumOd;

    @NotNull(message = "Datum zavrsetka je obavezno polje!")
    @Column (name = "datum_do")
    private LocalDate datumDo;

    @Column (name = "komentar")
    private String komentar;

    public EvidencijaPozajmljivanja() {
    }

    public EvidencijaPozajmljivanja(EvidencijaPozajmljivanjaId id, Knjiga knjiga, Korisnik korisnik, LocalDate datumOd, LocalDate datumDo, String komentar) {
        this.id = id;
        this.knjiga = knjiga;
        this.korisnik = korisnik;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.komentar = komentar;
    }

    public EvidencijaPozajmljivanjaId getId() {
        return id;
    }

    public void setId(EvidencijaPozajmljivanjaId id) {
        this.id = id;
    }

    public Knjiga getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
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

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }
}
