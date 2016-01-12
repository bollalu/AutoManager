package sample.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sample.model.Carburante;
import sample.model.Carburanti;

import sample.repo.CarburanteRepository;

@Controller
public class CarburanteController {

	@Autowired
	protected CarburanteRepository car;

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/carburanti", method = RequestMethod.GET)
	public String carburanti(Model model) {
        System.out.println("Carburanti -> GET");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nomeUtente = auth.getName();        
        log.info("User {}: Richiesta lista Carburanti", nomeUtente);
		return "admin@carburanti";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/carburante", method = RequestMethod.GET)
	public String carburante(@RequestParam(value = "id", required = true) long id,	Model model) {
        System.out.println("Carburante -> GET");
		Carburante carburante = car.findOne(id);
		model.addAttribute("carburante", carburante);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nomeUtente = auth.getName();
        log.info("User {}: Modifica Carburante-> {}", nomeUtente,carburante.getId() + ":" + carburante.getDescrizione());
		return "admin@carburanteEditForm";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/carburante", method = RequestMethod.POST)
	public String carburante(@ModelAttribute Carburante carburante, Model model) {
        System.out.println("Carburante -> POST");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nomeUtente = auth.getName();
        try {
				car.save(carburante);
		        log.info("User {}: Salvata Modifica Carburante-> {}", nomeUtente, carburante.getId() + ":" + carburante.getDescrizione());
	    		return "redirect:/admin/carburanti";
			} catch (Exception e) {
				model.addAttribute("messaggio", "Il carburante " + carburante.getDescrizione() + " é già presente");
		        System.out.println("Carburante -> " + carburanteJSON(carburante.getId(), model));
				model.addAttribute("carburante", carburanteJSON(carburante.getId(), model));
		        log.info("User {}: Non Salvata Modifica Carburante (duplicato) Carburante-> {}", nomeUtente, carburante.getId() + ":" + carburante.getDescrizione());
				return "admin@carburanteEditForm";
        }
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/carburante/new", method = RequestMethod.GET)
	public String carburnate(Model model) {
        System.out.println("Carburante -> Nuovo -> GET");
		Carburante carburante = new Carburante();
		model.addAttribute("carburante", carburante);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nomeUtente = auth.getName();
        log.info("User {}: Nuovo Carburante", nomeUtente);		
		return "admin@carburanteNewForm";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/carburante/new", method = RequestMethod.POST)
	public String carburantePOST(@ModelAttribute Carburante carburante, Model model) {
        System.out.println("Carburante -> Nuovo -> POST");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nomeUtente = auth.getName();
        try {
				car.save(carburante);
				log.info("User {}: Salvato Nuovo Carburante-> {}", nomeUtente,carburante.getId() + ":" + carburante.getDescrizione());				
	    		return "redirect:/admin/carburanti";
			} catch (Exception e) {
				model.addAttribute("messaggio", "Il carburante " + carburante.getDescrizione() + " é già presente");
				log.info("User {}: Non salvato Nuovo Carburante (duplicato) Carburante-> {}", nomeUtente, carburante.getDescrizione());
				return "admin@carburanteNewForm";
        } 
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/carburante/remove", method = RequestMethod.GET)
	public String carburanteRemove(@RequestParam(value = "id", required = true) long id,	Model model) {
        System.out.println("Carburante -> Remove -> GET");	
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nomeUtente = auth.getName();
        log.info("User {}: Eliminato Carburante-> {}", nomeUtente,carburanteJSON(id, model).getId() + ":" + carburanteJSON(id, model).getDescrizione());
        car.delete(id);
		return "redirect:/admin/carburanti";
	}	


	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/json/carburanti", method = RequestMethod.GET)
	public @ResponseBody Carburanti carburantiJSON(Model model) {
		return new Carburanti(car.findAll());
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/json/carburanti/search", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Carburanti carburanteJSON(@RequestParam(value = "q", required = true) String q, Model model) {
		return new Carburanti(car.findByDescrizioneContainingIgnoreCase(q));
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/json/carburante/{id}", method = RequestMethod.GET)
	public @ResponseBody Carburante carburanteJSON(@PathVariable long id, Model model) {
		return car.findOne(id);
	}

}
