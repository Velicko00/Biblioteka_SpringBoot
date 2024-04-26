package com.springboot.biblioteka.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "knjiga")
public class Knjiga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "knjiga_ID")
    private Long knjigaID;

    @NotNull(message = "Isbn je obavezno polje!")
    @Column (name = "isbn")
    private String isbn;

    @NotNull(message = "Naslov je obavezno polje!")
    @Column (name = "naslov")
    private String naslov;

    @NotNull(message = "Autor je obavezno polje!")
    @Column (name = "autor")
    private String autor;

    @Enumerated(EnumType.STRING)
    @Column(name = "zanr", columnDefinition = "ENUM('roman', 'triler', 'naucna_fantastika', 'edukativno', 'biografija', 'poezija', 'horor', 'ljubavni')")
    private Zanr zanr;

    public Knjiga() {
    }

    public Knjiga(Long knjigaID, String isbn, String naslov, String autor, Zanr zanr) {
        this.knjigaID = knjigaID;
        this.isbn = isbn;
        this.naslov = naslov;
        this.autor = autor;
        this.zanr = zanr;
    }

    public Long getKnjigaID() {
        return knjigaID;
    }

    public void setKnjigaID(Long knjigaID) {
        this.knjigaID = knjigaID;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Zanr getZanr() {
        return zanr;
    }

    public void setZanr(Zanr zanr) {
        this.zanr = zanr;
    }
}
