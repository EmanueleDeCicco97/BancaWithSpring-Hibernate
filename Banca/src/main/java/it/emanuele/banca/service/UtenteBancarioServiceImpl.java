package it.emanuele.banca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.emanuele.banca.model.UtenteBancario;
import it.emanuele.banca.repository.UtenteBancarioRepository;

import javax.transaction.Transactional;

@Service
public class UtenteBancarioServiceImpl implements UtenteBancarioService {

    @Autowired
    private UtenteBancarioRepository utenteBancarioRepository;

    @Override
    public UtenteBancario getUtenteById(long id) {
        Optional<UtenteBancario> container = utenteBancarioRepository.findById(id);
        UtenteBancario utente = container.get();
        return utente;
    }

    @Override
    public List<UtenteBancario> getUtentiAttivi() {

        return utenteBancarioRepository.findByAttivoTrue();
    }

    @Transactional
    @Override
    public void salvaUtente(UtenteBancario utente) {
        utenteBancarioRepository.save(utente);
    }

    @Transactional
    @Override
    public void cancellaUtente(long id) {
        Optional<UtenteBancario> optional = utenteBancarioRepository.findById(id);
        if (optional.isPresent()) {
            UtenteBancario utente = optional.get();
            utente.setAttivo(false);
            utenteBancarioRepository.save(utente);
        }
    }
}