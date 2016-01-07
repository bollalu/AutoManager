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
import sample.model.Users;
import sample.model.UsersList;
import sample.repo.UsersRepository;

@Controller
public class UsersController {

	@Autowired
	protected UsersRepository usr;

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/users", method = RequestMethod.GET)
	public String carburanti(Model model) {
        System.out.println("Utenti -> GET");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nomeUtente = auth.getName();       
        log.info("User {}: Richiesta lista Utenti", nomeUtente);
		return "admin@users";
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
	
*/
	
	@RequestMapping(value = "/admin/users/remove", method = RequestMethod.GET)
	public String usersRemove(@RequestParam(value = "id", required = true) long id,	Model model) {
        System.out.println("Users -> Remove -> GET");	
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nomeUtente = auth.getName();
        log.info("User {}: Eliminato Carburante-> {}", nomeUtente, usersListJSON(id, model).getUsers());
        usr.delete(id);
		return "redirect:/admin/carburanti";
	}	

	@RequestMapping(value = "/json/users", method = RequestMethod.GET)
	public @ResponseBody UsersList userslistJSON(Model model) {
		return new UsersList(usr.findAll());
	}

	@RequestMapping(value = "/json/users/search", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody UsersList usersListJSON(@RequestParam(value = "q", required = true) long id, Model model) {
		return new UsersList(usr.findCarburanteById(id));
	}
}
