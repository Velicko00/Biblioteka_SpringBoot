package com.springboot.biblioteka.repository;

import com.springboot.biblioteka.entity.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
    Optional<Korisnik> findByKorisnickoIme(String korisnickoIme);
    List<Korisnik> findByRola(String rola);
}
