package sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sample.model.Veicolo;
import sample.model.Veicoli;
import sample.repo.VeicoloRepository;

@Controller
public class VeicoloController {

	@Autowired
	protected VeicoloRepository ar;

	//@PreAuthorize("hasAuthority('USER')")
	//@RequestMapping(value = "/", method = RequestMethod.GET)
	//public String greeting(Model model) {
	//	return "articles";
	//}
	
	//@PreAuthorize("hasAuthority('USER')")
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String greeting(Model model) {
		return "admin@home";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/veicoli", method = RequestMethod.GET)
	public String veicoli(Model model) {
		return "admin@veicoli";
	}
	
	// http://docs.spring.io/autorepo/docs/spring-security/3.2.1.RELEASE/apidocs/org/springframework/security/access/expression/SecurityExpressionOperations.html
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/veicolo", method = RequestMethod.GET)
	public String veicolo(@RequestParam(value = "id", required = true) long id,	Model model) {
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(username);
		
		Veicolo veicolo = ar.findOne(id);
		model.addAttribute("veicolo", veicolo);
		return "admin@veicoloEditForm";
	}
	
	@RequestMapping(value = "/veicolo", method = RequestMethod.POST)
	public String veicolo(@ModelAttribute Veicolo veicolo, Model model) {
		ar.save(veicolo);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/veicolo/new", method = RequestMethod.GET)
	public String veicolo(Model model) {
		Veicolo veicolo = new Veicolo();
		model.addAttribute("veicolo", veicolo);
		return "admin@veicoloNewForm";
	}
	
	@RequestMapping(value = "/veicolo/new", method = RequestMethod.POST)
	public String veicoloPOST(@ModelAttribute Veicolo veicolo, Model model) {
		ar.save(veicolo);
		return "redirect:/";
	}

	@RequestMapping(value = "/json/veicoli", method = RequestMethod.GET)
	public @ResponseBody Veicoli veicoliJSON(Model model) {
		return new Veicoli(ar.findAll());
	}
	
	@RequestMapping(value = "/json/veicoli/search", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Veicoli veicoloJSON(@RequestParam(value = "q", required = true) String q, Model model) {
		return new Veicoli(ar.findByModelloContainingIgnoreCase(q));
	}

	@RequestMapping(value = "/json/veicolo/{id}", method = RequestMethod.GET)
	public @ResponseBody Veicolo veicoloJSON(@PathVariable long id, Model model) {
		return ar.findOne(id);
	}

}
