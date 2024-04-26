package com.springboot.biblioteka.dto;

import jakarta.validation.constraints.NotNull;

public class RegisterDto {

    @NotNull(message = "Korisnicko ime je obavezno polje!")
    private String korisnickoIme;

    @NotNull(message = "Lozinka je obavezno polje!")
    private String lozinka;

    @NotNull(message = "Ime je obavezno polje!")
    private String ime;

    private String email;

    private String vrstaClanarine;

    public RegisterDto(){

    }

    public RegisterDto( String korisnickoIme, String lozinka, String ime, String email, String vrstaClanarine) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.email = email;
        this.vrstaClanarine = vrstaClanarine;
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

    public String getVrstaClanarine() {
        return vrstaClanarine;
    }

    public void setVrstaClanarine(String vrstaClanarine) {
        this.vrstaClanarine = vrstaClanarine;
    }

}
