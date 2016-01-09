package sample.controller;

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

	@PreAuthorize("hasAuthority('USER')")
	@RequestMapping(value = "/user/rifornimento/new", method = RequestMethod.GET)
	public String addRifornimento(@RequestParam(value = "veiId", required = true) long id,
			@RequestParam(value = "msg", required = false) String msg, Model model) {
		System.out.println("Rifornimento -> Nuovo -> GET");
		model.addAttribute("messaggio", msg);
		Veicolo veicolo = vei.findOne(id);
		Rifornimento rifornimento = new Rifornimento();
		rifornimento.setVeicolo(veicolo);
		model.addAttribute("rifornimento", rifornimento);
		return "user@rifornimentoNewForm";
	}

	// @PreAuthorize("hasAuthority('USER')")
	@RequestMapping(value = "/user/rifornimento/new", method = RequestMethod.POST)
	public String saveRifornimento(@ModelAttribute Rifornimento rifornimento,
			@RequestParam(value = "veicolo.id", required = true) long id, Model model) {
		System.out.println("Rifornimento -> Nuovo -> POST");
		try {
			rifornimento.setData(new Date());
			rifornimento.setVeicolo(vei.findOne(id));
			rif.save(rifornimento);
			return "redirect:/user?msg=Rifornimento OK";
		} catch (Exception e) {
			// return "user@rifornimentoNewForm";
			return "redirect:/user?msg=" + e.getMessage();
		}
	}

}
