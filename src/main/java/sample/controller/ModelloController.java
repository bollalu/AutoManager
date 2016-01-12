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

import sample.model.Modello;
import sample.model.Marca;
import sample.model.Modelli;
import sample.repo.MarcaRepository;
import sample.repo.ModelloRepository;

@Controller
public class ModelloController {

	@Autowired
	protected ModelloRepository mor;
	@Autowired	
	protected MarcaRepository mar;
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/modelli", method = RequestMethod.GET)
	public String modelli(Model model) {
        System.out.println("Modelli -> GET");
		return "admin@modelli";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/modello", method = RequestMethod.GET)
	public String modello(@RequestParam(value = "id", required = true) long id,	Model model) {
        System.out.println("Modello -> GET");
		Modello modello = mor.findOne(id);
		model.addAttribute("modello", modello);
		model.addAttribute("marche", mar.findAll());
        System.out.println(mar.findAll());		
		return "admin@modelloEditForm";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/modello", method = RequestMethod.POST)
	public String modello(@ModelAttribute Modello modello, Model model) {
        System.out.println("Modello -> POST");
        try {
				mor.save(modello);
	    		return "redirect:/admin/modelli";
			} catch (Exception e) {
	        	model.addAttribute("messaggio", "Il modello " + modello.getDescrizione() + " é già presente");
		        System.out.println("Modello -> " + modelloJSON(modello.getId(), model));
				model.addAttribute("modello", modelloJSON(modello.getId(), model));
				model.addAttribute("marche", mar.findAll());
	        	return "admin@modelloEditForm";
			}        
	}        

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/modello/new", method = RequestMethod.GET)
	public String modello(Model model) {
        System.out.println("Modello -> Nuovo -> GET");
        Modello modello = new Modello();
		model.addAttribute("modello", modello);
		model.addAttribute("marche", mar.findAll());	
		return "admin@modelloNewForm";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/modello/new", method = RequestMethod.POST)
	public String modelloPOST(@ModelAttribute Modello modello, Model model) {
        System.out.println("Modello -> Nuovo -> POST");
        try {
				mor.save(modello);
	    		return "redirect:/admin/modelli";
			} catch (Exception e) {
	        	model.addAttribute("messaggio", "Il modello " + modello.getDescrizione() + " é già presente");
				model.addAttribute("marche", mar.findAll());
	        	return "admin@modelloNewForm";
			}
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/modello/remove", method = RequestMethod.GET)
	public String modelloRemove(@RequestParam(value = "id", required = true) long id,	Model model) {
        System.out.println("Modello -> Remove -> GET");	
        mor.delete(id);
		return "redirect:/admin/modelli";
	}	

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/json/modelli", method = RequestMethod.GET)
	public @ResponseBody Modelli modelliJSON(Model model) {
		return new Modelli(mor.findAll());
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/json/modelli/search", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Modelli modelloJSON(@RequestParam(value = "q", required = true) String q, Model model) {
        System.out.println("Ricerca GENERICA");
		return new Modelli(mor.findByDescrizioneContainingIgnoreCase(q));
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/json/modelli/marca", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Modelli modelloJSONmarca(@RequestParam(value = "q", required = true) Marca q, Model model) {
        System.out.println("Ricerca tramite MARCA");
		return new Modelli(mor.findModelloByMarca(q));
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/json/modelli/marcaId", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Modelli modelloJSONmarcaId(@RequestParam(value = "q", required = true) long q, Model model) {
        System.out.println("Ricerca tramite MARCA");
		return new Modelli(mor.findModelloByMarcaId(q));
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/json/modello/{id}", method = RequestMethod.GET)
	public @ResponseBody Modello modelloJSON(@PathVariable long id, Model model) {
        System.out.println("Ricerca tramite ID");
		return mor.findOne(id);
	}

}
