package it.emanuele.banca.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import it.emanuele.banca.model.ContoCorrente;
import it.emanuele.banca.service.ContoCorrenteService;
import it.emanuele.banca.service.UtenteBancarioService;

import java.util.UUID;


@Controller
public class ContoCorrenteController {


    @Autowired
    private UtenteBancarioService utenteBancarioService;
    @Autowired
    private ContoCorrenteService contoCorrenteService;


    @GetMapping("/inserimentoConto")
    public String inserimentoConto(Model model) {
        ContoCorrente contoCorr = new ContoCorrente();
        model.addAttribute("conto", contoCorr);
        model.addAttribute("listaPersone", utenteBancarioService.getUtentiAttivi());
        return "inserimento_conto";
    }


    @PostMapping("/salvaConto")
    public String salvaConto(ContoCorrente contoCorrente, Model model) {
        // genero un numero conto random
        // randomUUID crea un numero di 36 caratteri, toString lo converte in stringa e substring prende i primi 8 caratteri
        contoCorrente.setNumeroConto("CC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        contoCorrenteService.salvaConto(contoCorrente);
        return "redirect:/";
    }

    @GetMapping("/versamento/{id}")
    public String versamento(@PathVariable(value = "id") long id, Model model) {
        ContoCorrente conto = contoCorrenteService.getContiById(id);
        model.addAttribute("conto", conto);
        return "versamento";
    }


    @PostMapping("/salvaVersamento")
    public String salvaVersamento(@ModelAttribute("ContoCorrente") ContoCorrente conto, HttpServletRequest request) {
        ContoCorrente contoEsistente = contoCorrenteService.getContiById(conto.getId());
        double numero = Double.parseDouble(request.getParameter("numero"));
        double somma = contoEsistente.getSaldo() + numero;
        contoEsistente.setSaldo(somma);

        contoCorrenteService.salvaConto(contoEsistente);
        return "redirect:/listaConti/" + conto.getUtentebancario().getId();
    }


    @GetMapping("/prelievo/{id}")
    public String prelievo(@PathVariable(value = "id") long id, Model model) {
        ContoCorrente conto = contoCorrenteService.getContiById(id);
        model.addAttribute("conto", conto);
        return "prelievo";
    }

    @PostMapping("/salvaPrelievo")
    public String salvaPrelievo(@ModelAttribute("ContoCorrente") ContoCorrente conto, HttpServletRequest request) {
        ContoCorrente contoEsistente = contoCorrenteService.getContiById(conto.getId());

        double numero = Double.parseDouble(request.getParameter("numero"));
        double somma = contoEsistente.getSaldo() - numero;

        contoEsistente.setSaldo(somma);

        contoCorrenteService.salvaConto(contoEsistente);

        return "redirect:/listaConti/" + conto.getUtentebancario().getId();
    }

    @GetMapping("/eliminaConto/{id}")
    public String eliminaConto(@PathVariable long id, Model model) {
        ContoCorrente conto = contoCorrenteService.getContiById(id);
        long idUtente = conto.getUtentebancario().getId();

        contoCorrenteService.eliminaConto(id);
        return "redirect:/listaConti/" + idUtente;
    }

}
