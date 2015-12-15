package sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RifornimentoController {
	
	//@PreAuthorize("hasAuthority('USER')")
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String home(Model model) {
		return "user@Home";
	}
}
