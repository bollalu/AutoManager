package sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sample.model.AuthoritiesList;
import sample.repo.AuthoritiesRepository;

@Controller
public class AuthoritiesController {

	@Autowired
	protected AuthoritiesRepository aur;

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/authorities", method = RequestMethod.GET)
	public String carburanti(Model model) {
        System.out.println("Autorizzazioni -> GET");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nomeUtente = auth.getName();       
        log.info("User {}: Richiesta lista Autorizzazioni", nomeUtente);
		return "admin@authorities";
	}

	/*
	// http://docs.spring.io/autorepo/docs/spring-security/3.2.1.RELEASE/apidocs/org/springframework/security/access/expression/SecurityExpressionOperations.html
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
	
	@RequestMapping(value = "/admin/carburante/remove", method = RequestMethod.GET)
	public String carburanteRemove(@RequestParam(value = "id", required = true) long id,	Model model) {
        System.out.println("Carburante -> Remove -> GET");	
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nomeUtente = auth.getName();
        log.info("User {}: Eliminato Carburante-> {}", nomeUtente,carburanteJSON(id, model).getId() + ":" + carburanteJSON(id, model).getDescrizione());
        car.delete(id);
		return "redirect:/admin/carburanti";
	}	

*/
	@RequestMapping(value = "/json/authorities", method = RequestMethod.GET)
	public @ResponseBody AuthoritiesList authoritieslistJSON(Model model) {
		return new AuthoritiesList(aur.findAll());
	}


	@RequestMapping(value = "/json/authorities/search", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody AuthoritiesList authoritiesListJSON(@RequestParam(value = "q", required = true) String q, Model model) {
		return new AuthoritiesList(aur.findAuthoritiesByUsername(q));
	}
	
	@RequestMapping(value = "/json/authoritiesAut/search", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody AuthoritiesList authoritiesListJSONaut(@RequestParam(value = "q", required = true) String q, Model model) {
		return new AuthoritiesList(aur.findAuthoritiesByAuthority(q));
	}		

/*

	@RequestMapping(value = "/json/carburante/{id}", method = RequestMethod.GET)
	public @ResponseBody Carburante carburanteJSON(@PathVariable long id, Model model) {
		return car.findOne(id);
	}
*/
}
