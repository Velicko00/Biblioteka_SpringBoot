package com.springboot.biblioteka.dto;

import com.springboot.biblioteka.entity.Zanr;
import jakarta.validation.constraints.NotNull;

public class KnjigaDto {
    private Long knjigaID;

    @NotNull(message = "Isbn je obavezno polje!")
    private String isbn;

    @NotNull(message = "Naslov je obavezno polje!")
    private String naslov;

    @NotNull(message = "Autor je obavezno polje!")
    private String autor;

    private Zanr zanr;

    public KnjigaDto() {
    }

    public KnjigaDto(Long knjigaID, String isbn, String naslov, String autor, Zanr zanr) {
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
