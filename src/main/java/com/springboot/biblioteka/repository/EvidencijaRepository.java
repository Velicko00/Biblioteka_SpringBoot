package com.springboot.biblioteka.repository;

import com.springboot.biblioteka.entity.EvidencijaPozajmljivanja;
import com.springboot.biblioteka.entity.EvidencijaPozajmljivanjaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EvidencijaRepository extends JpaRepository<EvidencijaPozajmljivanja, EvidencijaPozajmljivanjaId> {
    List<EvidencijaPozajmljivanja> findByDatumOdBeforeAndDatumDoAfter(LocalDate datum1, LocalDate datum2);
    List<EvidencijaPozajmljivanja> findByKorisnikKorisnikIDAndDatumDoAfter(Long korisnikID, LocalDate date);
}
