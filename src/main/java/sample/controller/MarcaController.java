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
	protected MarcaRepository ar;

	
	//@PreAuthorize("hasAuthority('USER')")
	/*@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String greeting(Model model) {
        System.out.println("Admin -> GET");
		return "admin@home";
	}*/
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/marche", method = RequestMethod.GET)
	public String marche(Model model) {
        System.out.println("Marche -> GET");
		return "admin@marche";
	}
	
	// http://docs.spring.io/autorepo/docs/spring-security/3.2.1.RELEASE/apidocs/org/springframework/security/access/expression/SecurityExpressionOperations.html
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/marca", method = RequestMethod.GET)
	public String marca(@RequestParam(value = "id", required = true) long id,	Model model) {
        System.out.println("Marca -> GET");
		Marca marca = ar.findOne(id);
		model.addAttribute("marca", marca);
		return "admin@marcaEditForm";
	}
	
	@RequestMapping(value = "/admin/marca", method = RequestMethod.POST)
	public String marca(@ModelAttribute Marca marca, Model model) {
        System.out.println("Marca -> POST");
        System.out.println("Esiste ? -> " + ar.findMarcaByDescrizione(marca.getDescrizione()).toString());     
        if (ar.findMarcaByDescrizione(marca.getDescrizione()).toString().length()<5){
        	ar.save(marca);
    		return "redirect:/admin/marche";
        }else{
        	model.addAttribute("messaggio", "La marca " + marca.getDescrizione() + " é già presente");
        	return "admin@marcaNewForm";
        }

	}
	
	@RequestMapping(value = "/admin/marca/new", method = RequestMethod.GET)
	public String marca(Model model) {
        System.out.println("Marca -> Nuovo -> GET");
		Marca marca = new Marca();
		model.addAttribute("marca", marca);
		return "admin@marcaNewForm";
	}
	
	@RequestMapping(value = "/admin/marca/new", method = RequestMethod.POST)
	public String marcaPOST(@ModelAttribute Marca marca, Model model) {
        System.out.println("Marca -> Nuovo -> POST");
		ar.save(marca);
		return "redirect:/admin/marche";
	}
	
	@RequestMapping(value = "/admin/marca/remove", method = RequestMethod.GET)
	public String marcaRemove(@RequestParam(value = "id", required = true) long id,	Model model) {
        System.out.println("Marca -> Remove -> GET");	
        ar.delete(id);
		return "redirect:/admin/marche";
	}	

	@RequestMapping(value = "/json/marche", method = RequestMethod.GET)
	public @ResponseBody Marche marcheJSON(Model model) {
		return new Marche(ar.findAll());
	}
	
	@RequestMapping(value = "/json/marche/search", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Marche marcaJSON(@RequestParam(value = "q", required = true) String q, Model model) {
		return new Marche(ar.findByDescrizioneContainingIgnoreCase(q));
	}

	@RequestMapping(value = "/json/marca/{id}", method = RequestMethod.GET)
	public @ResponseBody Marca marcaJSON(@PathVariable long id, Model model) {
		return ar.findOne(id);
	}

}
