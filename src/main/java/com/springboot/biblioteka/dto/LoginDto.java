package com.springboot.biblioteka.dto;

import jakarta.validation.constraints.NotNull;

public class LoginDto {
    @NotNull(message = "Korisnicko ime je obavezno polje!")
    private String korisnickoIme;

    @NotNull(message = "Lozinka je obavezno polje!")
    private String lozinka;

    public LoginDto(String korisnickoIme, String lozinka) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    public LoginDto() {
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

    @Override
    public String toString() {
        return "LoginDto{" +
                "korisnickoIme='" + korisnickoIme + '\'' +
                ", lozinka='" + lozinka + '\'' +
                '}';
    }
}
