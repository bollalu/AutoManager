package sample.controller;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sample.model.Rifornimento;
import sample.model.Veicolo;
import sample.repo.RifornimentoRepository;
import sample.repo.VeicoloRepository;

@Controller
public class RifornimentoController {

	@Autowired
	protected VeicoloRepository vei;

	@Autowired
	protected RifornimentoRepository rif;

	// @PreAuthorize("hasAuthority('USER')")
	@RequestMapping(value = "/user/rifornimento", method = RequestMethod.GET)
	public String rifornimento(@RequestParam(value = "id", required = true) long id, Model model) {
		System.out.println("Rifornimento -> GET");
		return "user@rifornimento";
	}

	@PreAuthorize("hasAuthority('USER')")
	@RequestMapping(value = "/user/rifornimento/new", method = RequestMethod.GET)
	public String addRifornimento(@RequestParam(value = "veiId", required = true) long id, Model model) {
		System.out.println("Rifornimento -> Nuovo -> GET");
		Veicolo veicolo = vei.findOne(id);
		Rifornimento rifornimento = new Rifornimento();
		rifornimento.setVeicolo(veicolo);
		//rif.save(rifornimento);
		model.addAttribute("rifornimento", rifornimento);
		return "user@rifornimentoNewForm";
	}

	//@PreAuthorize("hasAuthority('USER')")
	@RequestMapping(value = "/user/rifornimento/new", method = RequestMethod.POST)
	public String saveRifornimento(@ModelAttribute Rifornimento rifornimento, @RequestParam(value = "veicolo.id", required = true) long id,Model model) {
		System.out.println("Rifornimento -> Nuovo -> POST");
		try {
			rifornimento.setData(new Date());
			rifornimento.setVeicolo(vei.findOne(id));
			rif.save(rifornimento);
			return "redirect:/user";
			//return "user@home";
		} catch (Exception e) {
			model.addAttribute("messaggio", e.getMessage());
			return "user@rifornimentoNewForm";
		}
	}

}
