package it.emanuele.banca.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.emanuele.banca.model.ContoCorrente;
import it.emanuele.banca.service.ContoCorrenteService;
import it.emanuele.banca.service.UtenteBancarioService;

@Controller
public class ContoCorrenteController {

	
	@Autowired
	private UtenteBancarioService utBan;
	
	@Autowired
	private ContoCorrenteService contRep;	
	
	
	
	
	@GetMapping("/inserimentoConto")
	public String inserimentoConto (Model model) {
		
		ContoCorrente contoCorr = new ContoCorrente();
		model.addAttribute("conto", contoCorr);
		model.addAttribute("listaPersone",utBan.getAllUtenti());
		
		return "inserimento_conto";
		
	}
	
	
	@PostMapping("/salvaConto")
	public String salvaConto (@ModelAttribute("ContoCorrente") ContoCorrente conto)
	{
		
		contRep.salvaConto(conto);
		return "redirect:/";
		
		
	}
	
	@GetMapping ("/versamento/{id}")
	public String versamento(@PathVariable ( value = "id") long id , Model model) {
		
		ContoCorrente conto = contRep.getContiById(id);	
		
		model.addAttribute("conto" , conto);
		return "versamento";
		
		
	}
	
	
	
	@PostMapping("/salvaVersamento")
	public String salvaVersamento (@ModelAttribute("ContoCorrente")ContoCorrente conto, HttpServletRequest request)
	{
		
		
		double numero = Double.parseDouble(request.getParameter("numero"));
		
		double somma = conto.getSaldo() + numero;
		
		conto.setSaldo(somma);
	
		System.out.println(conto.getSaldo());
		
		contRep.salvaConto(conto);
		return "redirect:/";
		
		
	}
	
	
	@GetMapping ("/prelievo/{id}")
	public String prelievo(@PathVariable ( value = "id") long id , Model model) {
		
		ContoCorrente conto = contRep.getContiById(id);	
		
		model.addAttribute("conto" , conto);
	
		return "prelievo";
		
		
	}
	
	@PostMapping("/salvaPrelievo")
	public String salvaPrelievo (@ModelAttribute("ContoCorrente")ContoCorrente conto, HttpServletRequest request)
	{
		
		//con il request mi prendo il valore di "numero" nel form 
		//e ci faccio i calcoli inserendoli nel saldo
		
		double numero = Double.parseDouble(request.getParameter("numero"));
		
		double somma = conto.getSaldo() - numero;
		
		conto.setSaldo(somma);
	
		System.out.println(conto.getSaldo());
		
		contRep.salvaConto(conto);
		return "redirect:/";
		
		
	}
}
