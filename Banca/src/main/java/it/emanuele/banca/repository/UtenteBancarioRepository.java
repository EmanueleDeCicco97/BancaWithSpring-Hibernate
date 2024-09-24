package it.emanuele.banca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.emanuele.banca.model.UtenteBancario;

import java.util.List;

@Repository

public interface UtenteBancarioRepository extends JpaRepository<UtenteBancario, Long> {

    List<UtenteBancario> findByAttivoTrue();
}
