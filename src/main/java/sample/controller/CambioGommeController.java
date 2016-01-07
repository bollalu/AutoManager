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

import sample.model.CambioGomme;
import sample.model.Rifornimento;
import sample.model.Veicolo;
import sample.repo.CambioGommeRepository;
import sample.repo.VeicoloRepository;

@Controller
public class CambioGommeController {
	@Autowired
	protected VeicoloRepository vei;
	@Autowired
	protected CambioGommeRepository cgo;
	
	@PreAuthorize("hasAuthority('USER')")
	@RequestMapping(value = "/user/cambioGomme/new", method = RequestMethod.GET)
	public String addCambioGomme(@RequestParam(value = "veiId", required = true) long id,@RequestParam(value = "msg", required = false) String msg, Model model) {
		System.out.println("cambioGomme -> Nuovo -> GET");
		model.addAttribute("messaggio", msg);
		Veicolo veicolo = vei.findOne(id);
		CambioGomme cambioGomme = new CambioGomme();
		cambioGomme.setVeicolo(veicolo);
		model.addAttribute("cambioGomme", cambioGomme);
		return "user@cambioGommeNewForm";
	}

	//@PreAuthorize("hasAuthority('USER')")
	@RequestMapping(value = "/user/cambioGomme/new", method = RequestMethod.POST)
	public String saveCambioGomme(@ModelAttribute CambioGomme cambioGomme, @RequestParam(value = "veicolo.id", required = true) long id,Model model) {
		System.out.println("cambioGomme -> Nuovo -> POST");
		try {
			cambioGomme.setData(new Date());
			cambioGomme.setVeicolo(vei.findOne(id));
			//cgo.save(cambioGomme);
			return "redirect:/user?msg=Cambio gomme OK";
		} catch (Exception e) {
			//return "user@rifornimentoNewForm";
			return "redirect:/user?msg="+e.getMessage();
		}
	}

	
}
