package it.emanuele.banca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.emanuele.banca.model.ContoCorrente;
import it.emanuele.banca.model.UtenteBancario;
import it.emanuele.banca.repository.ContoCorrenteRepository;
@Service
public class ContoCorrenteServiceImpl implements ContoCorrenteService{

	
	@Autowired
	private ContoCorrenteRepository contRep;	
	
	@Override
	public List<ContoCorrente> getAllConti() {
		// TODO Auto-generated method stub
		return contRep.findAll();
	}

	@Override
	public void salvaConto(ContoCorrente conti) {
		// TODO Auto-generated method stub
		this.contRep.save(conti);
		
	}

	@Override
	public List<ContoCorrente> findAllContiByUtentebancario(UtenteBancario utente) {
		// TODO Auto-generated method stub
		
		return contRep.findAllContiByUtentebancario(utente);
		//java capisce che stai facendo un find all my l'attributo
		//della classe (in questo caso utentebancario
	}

	@Override
	public ContoCorrente getContiById(long id) {
		Optional<ContoCorrente> optional = contRep.findById(id);
		ContoCorrente conto = null;
	
			
			conto = optional.get();


		return conto;
	}

}
