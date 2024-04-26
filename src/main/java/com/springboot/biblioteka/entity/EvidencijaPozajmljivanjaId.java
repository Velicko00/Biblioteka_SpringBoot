package com.springboot.biblioteka.entity;

import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class EvidencijaPozajmljivanjaId{

    private Long pozajmicaId;
    private Long knjigaId;
    private Long korisnikId;

    public EvidencijaPozajmljivanjaId() {
    }

    public EvidencijaPozajmljivanjaId(Long pozajmicaId, Long knjigaId, Long korisnikId) {
        this.pozajmicaId = pozajmicaId;
        this.knjigaId = knjigaId;
        this.korisnikId = korisnikId;
    }

    public Long getKnjigaId() {
        return knjigaId;
    }

    public void setKnjigaId(Long knjigaId) {
        this.knjigaId = knjigaId;
    }

    public Long getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Long korisnikId) {
        this.korisnikId = korisnikId;
    }

    public Long getPozajmicaId() {
        return pozajmicaId;
    }

    public void setPozajmicaId(Long pozajmicaId) {
        this.pozajmicaId = pozajmicaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EvidencijaPozajmljivanjaId that)) return false;
        return Objects.equals(pozajmicaId, that.pozajmicaId) && Objects.equals(knjigaId, that.knjigaId) && Objects.equals(korisnikId, that.korisnikId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pozajmicaId, knjigaId, korisnikId);
    }
}
