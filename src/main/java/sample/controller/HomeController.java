package sample.controller;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sample.model.Rifornimenti;
import sample.model.Rifornimento;
import sample.model.Veicoli;
import sample.model.Veicolo;
import sample.repo.RifornimentoRepository;
import sample.repo.VeicoloRepository;

@Controller
public class HomeController {

	@Autowired
	protected VeicoloRepository vei;

	@Autowired
	protected RifornimentoRepository rif;

	// @PreAuthorize("hasAuthority('USER')")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		return "/login";
	}

	// @PreAuthorize("hasAuthority('USER')")
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String userHome(@RequestParam(value = "msg", required = false) String msg, Model model) {
		Iterable<Veicolo> veicoli = vei.findAll();
		Iterator<Veicolo> veicoloIter = veicoli.iterator();
		while (veicoloIter.hasNext()) {
			Veicolo v = veicoloIter.next();
			v.setRifornimenti((ArrayList<Rifornimento>) rif.findRifornimentoByVeicoloId(v.getId()));;
		}
		model.addAttribute("veicoli", veicoli);
		model.addAttribute("messaggio", msg);

		return "user@home";
	}

}
