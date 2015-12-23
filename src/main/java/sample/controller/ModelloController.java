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

import sample.model.Modello;
import sample.model.Modelli;
import sample.repo.MarcaRepository;
import sample.repo.ModelloRepository;

@Controller
public class ModelloController {

	@Autowired
	protected ModelloRepository mor;
	@Autowired	
	protected MarcaRepository mar;

	
	//@PreAuthorize("hasAuthority('USER')")
	/*@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String greeting(Model model) {
        System.out.println("Admin -> GET");
		return "admin@home";
	}*/
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/modelli", method = RequestMethod.GET)
	public String modelli(Model model) {
        System.out.println("Modelli -> GET");
		return "admin@modelli";
	}
	
	// http://docs.spring.io/autorepo/docs/spring-security/3.2.1.RELEASE/apidocs/org/springframework/security/access/expression/SecurityExpressionOperations.html
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/modello", method = RequestMethod.GET)
	public String modello(@RequestParam(value = "id", required = true) long id,	Model model) {
        System.out.println("Modello -> GET");
		Modello modello = mor.findOne(id);
		model.addAttribute("modello", modello);
		model.addAttribute("marche", mar.findAll());	
		return "admin@modelloEditForm";
	}
	
	@RequestMapping(value = "/admin/modello", method = RequestMethod.POST)
	public String modello(@ModelAttribute Modello modello, Model model) {
        System.out.println("Modello -> POST");
        //System.out.println("Esiste ? -> " + ar.findMarcaByDescrizione(marca.getDescrizione()).toString());
        //if (ar.findMarcaByDescrizione(marca.getDescrizione()).toString().length()<5){
        	mor.save(modello);
    		return "redirect:/admin/modelli";
        /*}else{
        	model.addAttribute("messaggio", "La marca " + marca.getDescrizione() + " é già presente");
        	return "admin@marcaEditForm";
        }*/
	}
	
	@RequestMapping(value = "/admin/modello/new", method = RequestMethod.GET)
	public String modello(Model model) {
        System.out.println("Modello -> Nuovo -> GET");
        Modello modello = new Modello();
		model.addAttribute("modello", modello);
		model.addAttribute("marche", mar.findAll());	
		return "admin@modelloNewForm";
	}
	
	@RequestMapping(value = "/admin/modello/new", method = RequestMethod.POST)
	public String modelloPOST(@ModelAttribute Modello modello, Model model) {
        System.out.println("Modello -> Nuovo -> POST");
        System.out.println("Esiste ? -> " + mor.findModelloByDescrizione(modello.getDescrizione()).iterator().hasNext());     
        if (mor.findModelloByDescrizione(modello.getDescrizione()).toString().length()<5){
        	mor.save(modello);
    		return "redirect:/admin/modelli";
        }else{
        	model.addAttribute("messaggio", "Il modello " + modello.getDescrizione() + " é già presente");
        	return "admin@modelloNewForm";
        }
	}
	
	@RequestMapping(value = "/admin/modello/remove", method = RequestMethod.GET)
	public String modelloRemove(@RequestParam(value = "id", required = true) long id,	Model model) {
        System.out.println("Modello -> Remove -> GET");	
        mor.delete(id);
		return "redirect:/admin/modelli";
	}	

	@RequestMapping(value = "/json/modelli", method = RequestMethod.GET)
	public @ResponseBody Modelli modelliJSON(Model model) {
		return new Modelli(mor.findAll());
	}
	
	@RequestMapping(value = "/json/modelli/search", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Modelli modelloJSON(@RequestParam(value = "q", required = true) String q, Model model) {
        System.out.println("Ricerca GENERICA");
		return new Modelli(mor.findByDescrizioneContainingIgnoreCase(q));
	}

	@RequestMapping(value = "/json/modelli/marca", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Modelli modelloJSONmarca(@RequestParam(value = "q", required = true) long q, Model model) {
        System.out.println("Ricerca tramite MARCA");
		return new Modelli(mor.findModelloByMarca(q));
	}
	
	@RequestMapping(value = "/json/modello/{id}", method = RequestMethod.GET)
	public @ResponseBody Modello modelloJSON(@PathVariable long id, Model model) {
        System.out.println("Ricerca tramite ID");
		return mor.findOne(id);
	}

}