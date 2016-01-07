package sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sample.model.Rifornimento;
import sample.model.Veicolo;
import sample.repo.VeicoloRepository;

@Controller
public class RifornimentoController {

	@Autowired
	protected VeicoloRepository vei;

	// @PreAuthorize("hasAuthority('USER')")
	@RequestMapping(value = "/user/rifornimento", method = RequestMethod.GET)
	public String rifornimento(@RequestParam(value = "id", required = true) long id, Model model) {
		System.out.println("Rifornimento -> GET");
		return "user@rifornimento";
	}

	// @PreAuthorize("hasAuthority('USER')")
	@RequestMapping(value = "/user/rifornimento/new", method = RequestMethod.GET)
	public String addRifornimento(@RequestParam(value = "veiId", required = true) long id, Model model) {
		System.out.println("Rifornimento -> Nuovo -> GET");
		Veicolo veicolo = vei.findOne(id);
		model.addAttribute("veicolo", veicolo);
		Rifornimento rifornimento = new Rifornimento();
		model.addAttribute("rifornimento", rifornimento);
		return "user@rifornimentoNewForm";
	}

}
