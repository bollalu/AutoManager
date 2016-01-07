package sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sample.repo.MarcaRepository;
import sample.repo.VeicoloRepository;

@Controller
public class HomeController {
	
	@Autowired
	protected VeicoloRepository vei;
	
	//@PreAuthorize("hasAuthority('USER')")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {		
		return "/login";
	}
	
	//@PreAuthorize("hasAuthority('USER')")
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String userHome(@RequestParam(value = "msg", required = false) String msg,Model model) {
		model.addAttribute("veicoli", vei.findAll());	
		model.addAttribute("messaggio", msg);
		return "user@home";
	}
	
}
