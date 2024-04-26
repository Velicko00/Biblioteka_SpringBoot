package com.springboot.biblioteka.repository;

import com.springboot.biblioteka.entity.Pretplata;
import com.springboot.biblioteka.entity.PretplataId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PretplataRepository extends JpaRepository<Pretplata, PretplataId> {
    Optional<Pretplata> findByKorisnikKorisnikIDAndDatumDoAfter(Long korisnikID, LocalDate today);
}
