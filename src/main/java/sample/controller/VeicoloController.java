package sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
import sample.repo.CarburanteRepository;
import sample.repo.MarcaRepository;
import sample.repo.ModelloRepository;
import sample.repo.VeicoloRepository;

@Controller
public class VeicoloController {

	@Autowired
	protected VeicoloRepository ver;
	@Autowired	
	protected CarburanteRepository car;	
	@Autowired	
	protected MarcaRepository mar;
	@Autowired	
	protected ModelloRepository mor;
	
	//@PreAuthorize("hasAuthority('USER')")
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String greeting(Model model) {
        System.out.println("Admin -> GET");
		return "admin@home";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/veicoli", method = RequestMethod.GET)
	public String veicoli(Model model) {
        System.out.println("Veicoli -> GET");
		return "admin@veicoli";
	}
	
	// http://docs.spring.io/autorepo/docs/spring-security/3.2.1.RELEASE/apidocs/org/springframework/security/access/expression/SecurityExpressionOperations.html
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/veicolo", method = RequestMethod.GET)
	public String veicolo(@RequestParam(value = "id", required = true) long id,	Model model) {
        System.out.println("Veicolo -> GET");	
		Veicolo veicolo = ver.findOne(id);
		model.addAttribute("veicolo", veicolo);
		model.addAttribute("marche", mar.findAll());
		model.addAttribute("carburanti", car.findAll());
		model.addAttribute("modelli", mor.findAll());
		return "admin@veicoloEditForm";
	}
	
	@RequestMapping(value = "/admin/veicolo", method = RequestMethod.POST)
	public String veicolo(@ModelAttribute Veicolo veicolo, Model model) {
        System.out.println("Veicolo -> POST");
		ver.save(veicolo);
		return "redirect:/admin/veicoli";
	}
	
	@RequestMapping(value = "/admin/veicolo/new", method = RequestMethod.GET)
	public String veicolo(Model model) {
        System.out.println("Veicolo -> Nuovo -> GET");
		Veicolo veicolo = new Veicolo();
		model.addAttribute("veicolo", veicolo);
		model.addAttribute("carburanti", car.findAll());
		model.addAttribute("marche", mar.findAll());
		model.addAttribute("modelli", mor.findAll());
		return "admin@veicoloNewForm";
	}
	
	@RequestMapping(value = "/admin/veicolo/new", method = RequestMethod.POST)
	public String veicoloPOST(@ModelAttribute Veicolo veicolo, Model model) {
        System.out.println("Veicolo -> Nuovo -> POST");		
		ver.save(veicolo);
		return "redirect:/admin/veicoli";
	}

	@RequestMapping(value = "/admin/veicolo/remove", method = RequestMethod.GET)
	public String veicoloRemove(@RequestParam(value = "id", required = true) long id,	Model model) {
        System.out.println("Veicolo -> Remove -> GET");	
        ver.delete(id);
		return "redirect:/admin/veicoli";
	}
	
	@RequestMapping(value = "/json/veicoli", method = RequestMethod.GET)
	public @ResponseBody Veicoli veicoliJSON(Model model) {
		return new Veicoli(ver.findAll());
	}
	
	/*@RequestMapping(value = "/json/veicoli/search", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Veicoli veicoloJSON(@RequestParam(value = "q", required = true) String q, Model model) {
		return new Veicoli(ver.findByModelloContainingIgnoreCase(q));
	}*/

	@RequestMapping(value = "/json/veicoli/searchCarburante", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Veicoli veicoloJSONcarburante(@RequestParam(value = "q", required = true) long q, Model model) {
		return new Veicoli(ver.findByCarburante(q));
	}

	@RequestMapping(value = "/json/veicoli/searchModello", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Veicoli veicoloJSONmodello(@RequestParam(value = "q", required = true) long q, Model model) {
		return new Veicoli(ver.findByModello(q));
	}
	
	@RequestMapping(value = "/json/veicoli/searchMarca", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Veicoli veicoloJSONmarca(@RequestParam(value = "q", required = true) long q, Model model) {
		return new Veicoli(ver.findByMarca(q));
	}	
	
	
	@RequestMapping(value = "/json/veicolo/{id}", method = RequestMethod.GET)
	public @ResponseBody Veicolo veicoloJSON(@PathVariable long id, Model model) {
		return ver.findOne(id);
	}
}
