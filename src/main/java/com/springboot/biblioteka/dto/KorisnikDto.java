package com.springboot.biblioteka.dto;

import jakarta.validation.constraints.NotNull;

public class KorisnikDto {
    private Long korisnikID;

    @NotNull(message = "Korisnicko ime je obavezno polje!")
    private String korisnickoIme;

    @NotNull(message = "Lozinka je obavezno polje!")
    private String lozinka;

    @NotNull(message = "Ime je obavezno polje!")
    private String ime;

    private String email;

    @NotNull(message = "Rola je obavezno polje!")
    private String rola;

    private String vrstaClanarine;

    @NotNull(message = "Aktivan je obavezno polje!")
    private Boolean aktivan;

    public KorisnikDto(){

    }

    public KorisnikDto(Long korisnikID, String korisnickoIme, String lozinka, String ime, String email, String rola, String vrstaClanarine, Boolean aktivan) {
        this.korisnikID = korisnikID;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.email = email;
        this.rola = rola;
        this.vrstaClanarine = vrstaClanarine;
        this.aktivan = aktivan;
    }

    public Long getKorisnikID() {
        return korisnikID;
    }

    public void setKorisnikID(Long korisnikID) {
        this.korisnikID = korisnikID;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRola() {
        return rola;
    }

    public void setRola(String rola) {
        this.rola = rola;
    }

    public String getVrstaClanarine() {
        return vrstaClanarine;
    }

    public void setVrstaClanarine(String vrstaClanarine) {
        this.vrstaClanarine = vrstaClanarine;
    }

    public Boolean getAktivan() { return aktivan; }

    public void setAktivan(Boolean aktivan) { this.aktivan = aktivan; }
}
