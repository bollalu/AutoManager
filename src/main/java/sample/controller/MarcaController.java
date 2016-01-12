package sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sample.model.Marca;
import sample.model.Marche;
import sample.repo.MarcaRepository;

@Controller
public class MarcaController {

	@Autowired
	protected MarcaRepository mar;
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/marche", method = RequestMethod.GET)
	public String marche(Model model) {
        System.out.println("Marche -> GET");
		return "admin@marche";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/marca", method = RequestMethod.GET)
	public String marca(@RequestParam(value = "id", required = true) long id,	Model model) {
        System.out.println("Marca -> GET");
		Marca marca = mar.findOne(id);
		model.addAttribute("marca", marca);
		return "admin@marcaEditForm";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/marca", method = RequestMethod.POST)
	public String marca(@ModelAttribute Marca marca, Model model) {
        System.out.println("Marca -> POST");
        try {
				mar.save(marca);
	    		return "redirect:/admin/marche";
			} catch (Exception e) {
	        	model.addAttribute("messaggio", "La marca " + marca.getDescrizione() + " é già presente");
		        System.out.println("Marca -> " + marcaJSON(marca.getId(), model));
				model.addAttribute("marca", marcaJSON(marca.getId(), model));
	        	return "admin@marcaEditForm";
        }        
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/marca/new", method = RequestMethod.GET)
	public String marca(Model model) {
        System.out.println("Marca -> Nuovo -> GET");
		Marca marca = new Marca();
		model.addAttribute("marca", marca);
		return "admin@marcaNewForm";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/marca/new", method = RequestMethod.POST)
	public String marcaPOST(@ModelAttribute Marca marca, Model model) {
        System.out.println("Marca -> Nuovo -> POST");
        try {
				mar.save(marca);
	    		return "redirect:/admin/marche";
			} catch (Exception e) {
	        	model.addAttribute("messaggio", "La marca " + marca.getDescrizione() + " é già presente");
	        	return "admin@marcaNewForm";
        }
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/marca/remove", method = RequestMethod.GET)
	public String marcaRemove(@RequestParam(value = "id", required = true) long id,	Model model) {
        System.out.println("Marca -> Remove -> GET");	
        mar.delete(id);
		return "redirect:/admin/marche";
	}	

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/json/marche", method = RequestMethod.GET)
	public @ResponseBody Marche marcheJSON(Model model) {
		return new Marche(mar.findAll());
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/json/marche/search", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Marche marcaJSON(@RequestParam(value = "q", required = true) String q, Model model) {
		return new Marche(mar.findByDescrizioneContainingIgnoreCase(q));
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/json/marca/{id}", method = RequestMethod.GET)
	public @ResponseBody Marca marcaJSON(@PathVariable long id, Model model) {
		return mar.findOne(id);
	}

}
