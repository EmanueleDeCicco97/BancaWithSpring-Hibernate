package it.emanuele.banca.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.emanuele.banca.model.ContoCorrente;
import it.emanuele.banca.model.UtenteBancario;


@Repository

public interface ContoCorrenteRepository extends JpaRepository<ContoCorrente, Long> {
    List<ContoCorrente> findAllByUtentebancarioAndAttivoTrue(UtenteBancario utente);

    Optional<ContoCorrente> findByNumeroConto(String numeroConto);
}
