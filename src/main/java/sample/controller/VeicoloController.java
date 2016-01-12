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
import sample.model.Modelli;
import sample.model.Rifornimenti;
import sample.model.Veicoli;
import sample.repo.CarburanteRepository;
import sample.repo.MarcaRepository;
import sample.repo.ModelloRepository;
import sample.repo.RifornimentoRepository;
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
	@Autowired	
	protected RifornimentoRepository rir;	
	
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
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/veicolo", method = RequestMethod.GET)
	public String veicolo(@RequestParam(value = "id", required = true) long id,	Model model) {
        System.out.println("Veicolo -> GET");
        Veicolo veicolo = ver.findOne(id);
		model.addAttribute("veicolo", veicolo);
		model.addAttribute("modelli", mor.findAll());		
		model.addAttribute("marche", mar.findAll());
		model.addAttribute("carburanti", car.findAll());
		return "admin@veicoloEditForm";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/veicolo", method = RequestMethod.POST)
	public String veicolo(@ModelAttribute Veicolo veicolo, Model model) {
        System.out.println("Veicolo -> POST");
        try {
				ver.save(veicolo);
	    		return "redirect:/admin/veicoli";
			} catch (Exception e) {
	        	model.addAttribute("messaggio", "La targa " + veicolo.getTarga() + " é già utilizzata");
		        System.out.println("Veicolo -> " + veicoloJSONid(veicolo.getId(), model));
				model.addAttribute("veicolo", veicoloJSONid(veicolo.getId(), model));
				model.addAttribute("modelli", mor.findAll());
				model.addAttribute("marche", mar.findAll());				
				model.addAttribute("carburanti", car.findAll());
	        	return "admin@veicoloEditForm";
			}        
	}         

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/veicolo/new", method = RequestMethod.GET)
	public String veicolo(Model model) {
        System.out.println("Veicolo -> Nuovo -> GET");
		Veicolo veicolo = new Veicolo();
		model.addAttribute("veicolo", veicolo);
		model.addAttribute("modelli", mor.findAll());
		model.addAttribute("marche", mar.findAll());		
		model.addAttribute("carburanti", car.findAll());		
		return "admin@veicoloNewForm";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/veicolo/new", method = RequestMethod.POST)
	public String veicoloPOST(@ModelAttribute Veicolo veicolo, Model model) {
        System.out.println("Veicolo -> Nuovo -> POST");
        try {
				ver.save(veicolo);
	    		return "redirect:/admin/veicoli";
			} catch (Exception e) {
	        	model.addAttribute("messaggio", "La targa " + veicolo.getTarga() + " é già utilizzata");
				model.addAttribute("modelli", mor.findAll());
				model.addAttribute("marche", mar.findAll());				
				model.addAttribute("carburanti", car.findAll());				
	        	return "admin@veicoloNewForm";
			}        
	}         

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/veicolo/remove", method = RequestMethod.GET)
	public String veicoloRemove(@RequestParam(value = "id", required = true) long id,	Model model) {
        System.out.println("Veicolo -> Remove -> GET");	
        ver.delete(id);
		return "redirect:/admin/veicoli";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/json/veicoli", method = RequestMethod.GET)
	public @ResponseBody Veicoli veicoliJSON(Model model) {
		return new Veicoli(ver.findAll());
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/json/veicoli/search", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Veicoli veicoloJSONid(@RequestParam(value = "q", required = true) long q, Model model) {
		return new Veicoli(ver.findVeicoliById(q));
	}	
	
	@RequestMapping(value = "/json/veicoli/searchTarga", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Veicoli veicoloJSONtarga(@RequestParam(value = "q", required = true) String q, Model model) {
		return new Veicoli(ver.findVeicoliByTarga(q));
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/json/veicoli/searchCarburante", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Veicoli veicoloJSONcarburante(@RequestParam(value = "q", required = true) long q, Model model) {
		return new Veicoli(ver.findVeicoliByCarburanteId(q));
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/json/veicoli/searchModello", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Veicoli veicoloJSONmodello(@RequestParam(value = "q", required = true) long q, Model model) {
		return new Veicoli(ver.findVeicoliByModelloId(q));
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/json/veicoli/searchMarca", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Modelli veicoloJSONmarca(@RequestParam(value = "q", required = true) long q, Model model) {
		return new Modelli(mor.findModelloByMarcaId(q));
	}	

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/json/veicoli/searchRifornimento", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Rifornimenti rifornimentoJSONid(@RequestParam(value = "q", required = true) long q, Model model) {
		return new Rifornimenti(rir.findRifornimentoByVeicoloId(q));
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/json/veicolo/{id}", method = RequestMethod.GET)
	public @ResponseBody Veicolo veicoloJSON(@PathVariable long id, Model model) {
		return ver.findOne(id);
	}
}
