package sample.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sample.model.UsersList;
import sample.model.Authorities;
import sample.model.Ruolo;
import sample.model.Users;
import sample.repo.AuthoritiesRepository;
import sample.repo.RuoloRepository;
import sample.repo.UsersRepository;

@Controller
public class UserController {

	@Autowired
	protected UsersRepository usr;
	@Autowired
	protected AuthoritiesRepository aur;
	@Autowired
	protected RuoloRepository rur;
	
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
	
*/	
	
	@RequestMapping(value = "/admin/users/new", method = RequestMethod.GET)
	public String users(Model model) {
        System.out.println("Users -> Nuovo -> GET");
		Users users = new Users();
		model.addAttribute("users", users);
		model.addAttribute("ruoli", rur.findAll());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nomeUtente = auth.getName();
        log.info("User {}: Nuovo Utente", nomeUtente);	
		return "admin@usersNewForm";
	}
	
	@RequestMapping(value = "/admin/users/new", method = RequestMethod.POST)
	public String usersPOST(@ModelAttribute Users users, @RequestParam(value="ruolo", required=true) String ruolo, Model model) {
        System.out.println("Users -> Nuovo -> POST");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nomeUtente = auth.getName();
        try {
        		usr.save(users);	
        		Authorities authorities = new Authorities();
				authorities.setUsername(users.getUsername());
				authorities.setAuthority(ruolo);
			    aur.save(authorities);				
				log.info("User {}: Salvato Nuovo User-> {}", nomeUtente, users.getUsername());				
	    		return "redirect:/admin/users";
			} catch (Exception e) {
				model.addAttribute("messaggio", "L'utente " + users.getUsername() + " é già presente");
				log.info("User {}: Non salvato Nuovo Utente (duplicato) Utente-> {}", nomeUtente, users.getUsername());
				return "admin@usersNewForm";
			}
	}
	
	
	@RequestMapping(value = "/admin/users/remove", method = RequestMethod.GET)
	public String usersRemove(@RequestParam(value = "id", required = true) String username,	Model model) {
        System.out.println("Users -> Remove -> GET");	
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nomeUtente = auth.getName();
        log.info("User {}: Eliminato Utente-> {}", nomeUtente, username);
        //aur.delete(username);        
        usr.delete(username);
		return "redirect:/admin/carburanti";
	}
	
	@RequestMapping(value = "/json/users", method = RequestMethod.GET)
	public @ResponseBody UsersList userslistJSON(Model model) {
		return new UsersList(usr.findAll());
	}
	
/*
	@RequestMapping(value = "/json/users/search", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody UsersList usersListJSON(@RequestParam(value = "q", required = true) long id, Model model) {
		return new UsersList(usr.findCarburanteById(id));
	}
	
	*/
}
