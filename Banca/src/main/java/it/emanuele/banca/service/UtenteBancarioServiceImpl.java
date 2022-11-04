package it.emanuele.banca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.emanuele.banca.model.UtenteBancario;
import it.emanuele.banca.repository.UtenteBancarioRepository;

@Service
public class UtenteBancarioServiceImpl implements UtenteBancarioService{

	@Autowired
	private UtenteBancarioRepository utBan;
	
	@Override
	public List<UtenteBancario> getAllUtenti() {
		// TODO Auto-generated method stub
		return utBan.findAll();
		
	}

	@Override
	public void salvaUtente(UtenteBancario utente) {
		// TODO Auto-generated method stub
		
		this.utBan.save(utente);
	}

	@Override
	public UtenteBancario getUtenteById(long id) {
		// TODO Auto-generated method stub
		Optional<UtenteBancario> container = utBan.findById(id);
		UtenteBancario utente = null;
	
		utente = container.get();
		return utente;

	}

	@Override
	public void cancellaUtenteById(long id) {
		// TODO Auto-generated method stub
		this.utBan.deleteById(id);
	}

}
