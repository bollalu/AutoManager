package sample.controller;

import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class HomeController {
	
	//@PreAuthorize("hasAuthority('USER')")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {		
		return "/login";
	}
	
	//@PreAuthorize("hasAuthority('USER')")
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String userHome(Model model) {
		return "user@home";
	}
	
}
