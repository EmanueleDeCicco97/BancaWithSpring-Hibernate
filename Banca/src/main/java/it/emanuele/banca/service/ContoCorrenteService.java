package it.emanuele.banca.service;

import java.util.List;

import it.emanuele.banca.model.ContoCorrente;
import it.emanuele.banca.model.UtenteBancario;

public interface ContoCorrenteService {
    void salvaConto(ContoCorrente conti);

    ContoCorrente getContiById(long id);

    List<ContoCorrente> findAllByUtentebancarioAndAttivoTrue(UtenteBancario utente);

    void eliminaConto(long id);

}
