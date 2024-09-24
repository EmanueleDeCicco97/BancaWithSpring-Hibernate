package it.emanuele.banca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.emanuele.banca.model.ContoCorrente;
import it.emanuele.banca.model.UtenteBancario;
import it.emanuele.banca.repository.ContoCorrenteRepository;

import javax.transaction.Transactional;

@Service
public class ContoCorrenteServiceImpl implements ContoCorrenteService {

    @Autowired
    private ContoCorrenteRepository contoCorrenteRepository;

    @Override
    public List<ContoCorrente> findAllByUtentebancarioAndAttivoTrue(UtenteBancario utente) {
        return contoCorrenteRepository.findAllByUtentebancarioAndAttivoTrue(utente);
    }

    @Override
    public ContoCorrente getContiById(long id) {
        Optional<ContoCorrente> optional = contoCorrenteRepository.findById(id);
        ContoCorrente conto = optional.get();
        return conto;
    }

    @Transactional
    @Override
    public void salvaConto(ContoCorrente conto) {
        this.contoCorrenteRepository.save(conto);
    }

    @Transactional
    @Override
    public void eliminaConto(long id) {
        Optional<ContoCorrente> optional = contoCorrenteRepository.findById(id);
        if (optional.isPresent()) {
            ContoCorrente conto = optional.get();
            conto.setAttivo(false);
            contoCorrenteRepository.save(conto);
        }
    }
}
