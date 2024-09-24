package it.emanuele.banca.service;

import java.util.List;

import it.emanuele.banca.model.UtenteBancario;


public interface UtenteBancarioService {
    List<UtenteBancario> getUtentiAttivi();

    void salvaUtente(UtenteBancario utente);

    UtenteBancario getUtenteById(long id);

    void cancellaUtente(long id);
}
