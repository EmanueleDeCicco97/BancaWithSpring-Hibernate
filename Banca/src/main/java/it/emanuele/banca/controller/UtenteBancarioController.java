package it.emanuele.banca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.emanuele.banca.model.UtenteBancario;
import it.emanuele.banca.service.ContoCorrenteService;
import it.emanuele.banca.service.UtenteBancarioService;

@Controller
public class UtenteBancarioController {

	@Autowired
	private UtenteBancarioService utenteService;
	@Autowired
	private ContoCorrenteService contoService;
	
	@GetMapping ( "/" )
	public String viewHomePage (Model model) {
		
		
		model.addAttribute("listaUtenti", utenteService.getAllUtenti());
		return "index";
		
	}
	
	@GetMapping ("/showNewUtenteForm")
	public String showNewUtenteForm(Model model) {
			
			UtenteBancario utente = new UtenteBancario ();
			
			model.addAttribute("utente", utente);
			
		
			model.addAttribute("listaUtenti", utenteService.getAllUtenti());
			return "nuovo_utente";
	}
	
	
	@PostMapping("/salvaUtente")
	public String salvaUtente(@ModelAttribute("Utente") UtenteBancario utente) {
	//	utente.getRuoli().setId(Long.parseLong(utente.getRuoli().getId()));
		utenteService.salvaUtente(utente);
	    return "redirect:/";
	}

	
	@GetMapping ("/showModificaUtenteForm/{id}")
	public String showModificaUtenteForm(@PathVariable ( value = "id") long id , Model model) {
		
		UtenteBancario utente = utenteService.getUtenteById(id);
		
		model.addAttribute("utente" , utente);
		return "modifica_utente";
		
		
	}
	
	@GetMapping ("/cancellazioneUtente/{id}")
	public String cancellaUtente(@PathVariable ( value = "id" ) long id , Model model) {
		
		this.utenteService.cancellaUtenteById(id);
		return "redirect:/";
	}
	
	
	@GetMapping ("/listaConti/{id}")
	public String listaConti(@PathVariable ( value = "id") long id , Model model) {
		
		
		
		//UtenteBancario utente = utenteService.getUtenteById(id);
		//ho creato una query di utente bancari così mi trova i conti solo di quella 
		//determinata persona
		model.addAttribute("utente" ,contoService.findAllContiByUtentebancario(utenteService.getUtenteById(id)));
		
		return "lista_conti";
		
		
	}
	
	
	
	
	
}
