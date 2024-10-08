package it.emanuele.banca.controller;

import it.emanuele.banca.model.ContoCorrente;
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

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class UtenteBancarioController {

    @Autowired
    private UtenteBancarioService utenteService;
    @Autowired
    private ContoCorrenteService contoService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listaUtenti", utenteService.getUtentiAttivi());
        return "index";
    }

    @GetMapping("/showNewUtenteForm")
    public String showNewUtenteForm(Model model) {
        UtenteBancario utente = new UtenteBancario();
        model.addAttribute("utente", utente);
        model.addAttribute("listaUtenti", utenteService.getUtentiAttivi());
        return "nuovo_utente";
    }

    @GetMapping("/showModificaUtenteForm/{id}")
    public String showModificaUtenteForm(@PathVariable(value = "id") long id, Model model) {
        UtenteBancario utente = utenteService.getUtenteById(id);
        model.addAttribute("utente", utente);
        return "modifica_utente";
    }

    @PostMapping("/salvaUtente")
    public String salvaUtente(@ModelAttribute("utente") UtenteBancario utente, Model model) {

        Date dataNascita = utente.getDataNascita();
        // calcolo l'anno di nascita
        int annoNascita = dataNascita.getYear() + 1900; // getYear() restituisce l'anno a partire dal 1900

        // controllo che l'anno di nascita non deve essere maggiore del 2006
        if (annoNascita > (LocalDate.now().getYear() - 18)) {
            model.addAttribute("errorMessage", "L'utente deve avere almeno 18 anni.");
            return "nuovo_utente";
        }

        // controllo per persone troppo vecchie non deve essere minore del 1904
        if (annoNascita < (LocalDate.now().getYear() - 120)) {
            model.addAttribute("errorMessage", "L'utente non può avere più di 120 anni.");
            return "nuovo_utente";
        }
        utenteService.salvaUtente(utente);
        return "redirect:/";
    }

    @GetMapping("/cancellazioneUtente/{id}")
    public String cancellaUtente(@PathVariable(value = "id") long id, Model model) {

        UtenteBancario utente = utenteService.getUtenteById(id);
        List<ContoCorrente> contiAssociati = contoService.findAllByUtentebancarioAndAttivoTrue(utente);
        if (!contiAssociati.isEmpty()) {
            model.addAttribute("errorMessage", "L'utente ha conti attivi e non può essere cancellato.");
            model.addAttribute("listaUtenti", utenteService.getUtentiAttivi());
            return "index";
        }
        utenteService.cancellaUtente(id);
        return "redirect:/";
    }

    @GetMapping("/listaConti/{id}")
    public String listaConti(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("utente", contoService.findAllByUtentebancarioAndAttivoTrue(utenteService.getUtenteById(id)));
        return "lista_conti";
    }
}
