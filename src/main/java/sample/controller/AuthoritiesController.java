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

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/json/authorities", method = RequestMethod.GET)
	public @ResponseBody AuthoritiesList authoritieslistJSON(Model model) {
		return new AuthoritiesList(aur.findAll());
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/json/authorities/search", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody AuthoritiesList authoritiesListJSON(@RequestParam(value = "q", required = true) String q, Model model) {
		return new AuthoritiesList(aur.findAuthoritiesByUsername(q));
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/json/authoritiesAut/search", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody AuthoritiesList authoritiesListJSONaut(@RequestParam(value = "q", required = true) String q, Model model) {
		return new AuthoritiesList(aur.findAuthoritiesByAuthority(q));
	}		
}
