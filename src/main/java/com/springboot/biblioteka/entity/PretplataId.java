package com.springboot.biblioteka.entity;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class PretplataId {
    private Long pretplataId;
    private Long korisnikId;

    public PretplataId(Long pretplataId, Long korisnikId) {
        this.pretplataId = pretplataId;
        this.korisnikId = korisnikId;
    }

    public PretplataId() {
    }

    public Long getPretplataId() {
        return pretplataId;
    }

    public void setPretplataId(Long pretplataId) {
        this.pretplataId = pretplataId;
    }

    public Long getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Long korisnikId) {
        this.korisnikId = korisnikId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PretplataId that)) return false;
        return Objects.equals(pretplataId, that.pretplataId) && Objects.equals(korisnikId, that.korisnikId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pretplataId, korisnikId);
    }
}
