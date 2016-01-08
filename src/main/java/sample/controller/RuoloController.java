package sample.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sample.model.Ruolo;
import sample.model.Ruoli;

import sample.repo.RuoloRepository;

@Controller
public class RuoloController {

	@Autowired
	protected RuoloRepository rur;

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/ruoli", method = RequestMethod.GET)
	public String ruoli(Model model) {
        System.out.println("Ruoli -> GET");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nomeUtente = auth.getName();        
        log.info("User {}: Richiesta lista Ruoli", nomeUtente);
		return "admin@ruoli";
	}
	
	// http://docs.spring.io/autorepo/docs/spring-security/3.2.1.RELEASE/apidocs/org/springframework/security/access/expression/SecurityExpressionOperations.html
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/ruolo", method = RequestMethod.GET)
	public String ruolo(@RequestParam(value = "id", required = true) long id,	Model model) {
        System.out.println("Ruolo -> GET");
		Ruolo ruolo = rur.findOne(id);
		model.addAttribute("ruolo", ruolo);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nomeUtente = auth.getName();
        log.info("User {}: Modifica Ruolo-> {}", nomeUtente, ruolo.getId() + ":" + ruolo.getDescrizione());
		return "admin@ruoloEditForm";
	}
	
	@RequestMapping(value = "/admin/ruolo", method = RequestMethod.POST)
	public String ruolo(@ModelAttribute Ruolo ruolo, Model model) {
        System.out.println("Ruolo -> POST");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nomeUtente = auth.getName();
        try {
				rur.save(ruolo);
		        log.info("User {}: Salvata Modifica Ruolo-> {}", nomeUtente, ruolo.getId() + ":" + ruolo.getDescrizione());
	    		return "redirect:/admin/ruoli";
			} catch (Exception e) {
				model.addAttribute("messaggio", "Il ruolo " + ruolo.getDescrizione() + " é già presente");
		        System.out.println("Ruolo -> " + ruoloJSON(ruolo.getId(), model));
				model.addAttribute("ruolo", ruoloJSON(ruolo.getId(), model));
		        log.info("User {}: Non Salvata Modifica Ruolo (duplicato) Ruolo-> {}", nomeUtente, ruolo.getId() + ":" + ruolo.getDescrizione());
				return "admin@ruoloEditForm";
        }
	}
	
	@RequestMapping(value = "/admin/ruolo/new", method = RequestMethod.GET)
	public String ruolo(Model model) {
        System.out.println("Ruolo -> Nuovo -> GET");
		Ruolo ruolo = new Ruolo();
		model.addAttribute("ruolo", ruolo);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nomeUtente = auth.getName();
        log.info("User {}: Nuovo Ruolo", nomeUtente);		
		return "admin@ruoloNewForm";
	}
	
	@RequestMapping(value = "/admin/ruolo/new", method = RequestMethod.POST)
	public String ruoloPOST(@ModelAttribute Ruolo ruolo, Model model) {
        System.out.println("Ruolo -> Nuovo -> POST");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nomeUtente = auth.getName();
        try {
				rur.save(ruolo);
				log.info("User {}: Salvato Nuovo Ruolo-> {}", nomeUtente, ruolo.getId() + ":" + ruolo.getDescrizione());				
	    		return "redirect:/admin/ruoli";
			} catch (Exception e) {
				model.addAttribute("messaggio", "Il ruolo " + ruolo.getDescrizione() + " é già presente");
				log.info("User {}: Non salvato Nuovo Ruolo (duplicato) Ruolo-> {}", nomeUtente, ruolo.getDescrizione());
				return "admin@ruoloNewForm";
        }
	}
	
	@RequestMapping(value = "/admin/ruolo/remove", method = RequestMethod.GET)
	public String ruoloRemove(@RequestParam(value = "id", required = true) long id,	Model model) {
        System.out.println("Ruolo -> Remove -> GET");	
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nomeUtente = auth.getName();
        log.info("User {}: Eliminato Ruolo-> {}", nomeUtente, ruoloJSON(id, model).getId() + ":" + ruoloJSON(id, model).getDescrizione());
        rur.delete(id);
		return "redirect:/admin/ruoli";
	}	


	@RequestMapping(value = "/json/ruoli", method = RequestMethod.GET)
	public @ResponseBody Ruoli ruoliJSON(Model model) {
		return new Ruoli(rur.findAll());
	}
	
	@RequestMapping(value = "/json/ruoli/search", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Ruoli ruoloJSON(@RequestParam(value = "q", required = true) String q, Model model) {
		return new Ruoli(rur.findByDescrizioneContainingIgnoreCase(q));
	}

	@RequestMapping(value = "/json/ruolo/{id}", method = RequestMethod.GET)
	public @ResponseBody Ruolo ruoloJSON(@PathVariable long id, Model model) {
		return rur.findOne(id);
	}

}
