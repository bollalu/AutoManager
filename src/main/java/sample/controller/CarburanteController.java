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

import sample.model.Carburante;
import sample.model.Carburanti;
import sample.model.Marca;
import sample.model.Marche;
import sample.repo.CarburanteRepository;

@Controller
public class CarburanteController {

	@Autowired
	protected CarburanteRepository cr;

	
	//@PreAuthorize("hasAuthority('USER')")
	/*@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String greeting(Model model) {
        System.out.println("Admin -> GET");
		return "admin@home";
	}
	*/
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/carburanti", method = RequestMethod.GET)
	public String carburanti(Model model) {
        System.out.println("Carburanti -> GET");
		return "admin@carburanti";
	}
	
	// http://docs.spring.io/autorepo/docs/spring-security/3.2.1.RELEASE/apidocs/org/springframework/security/access/expression/SecurityExpressionOperations.html
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/carburante", method = RequestMethod.GET)
	public String carburante(@RequestParam(value = "id", required = true) long id,	Model model) {
        System.out.println("Carburante -> GET");
		Carburante carburante = cr.findOne(id);
		model.addAttribute("carburante", carburante);
		return "admin@carburanteEditForm";
	}
	
	@RequestMapping(value = "/admin/carburante", method = RequestMethod.POST)
	public String carburante(@ModelAttribute Carburante carburante, Model model) {
        System.out.println("Carburante -> POST");
        System.out.println("Esiste ? -> " + cr.findCarburanteByDescrizione(carburante.getDescrizione()).toString());
        if (cr.findCarburanteByDescrizione(carburante.getDescrizione()).toString().length()<5){
        	cr.save(carburante);
    		return "redirect:/admin/carburanti";
        }else{
        	model.addAttribute("messaggio", "Il carburante " + carburante.getDescrizione() + " é già presente");
        	return "admin@carburanteEditForm";
        }
	}
	
	@RequestMapping(value = "/admin/carburante/new", method = RequestMethod.GET)
	public String carburnate(Model model) {
        System.out.println("Carburante -> Nuovo -> GET");
		Carburante carburante = new Carburante();
		model.addAttribute("carburante", carburante);
		return "admin@carburanteNewForm";
	}
	
	@RequestMapping(value = "/admin/carburante/new", method = RequestMethod.POST)
	public String carburantePOST(@ModelAttribute Carburante carburante, Model model) {
        System.out.println("Carburante -> Nuovo -> POST");
        System.out.println("Esiste ? -> " + cr.findCarburanteByDescrizione(carburante.getDescrizione()).iterator().hasNext());     
        if (cr.findCarburanteByDescrizione(carburante.getDescrizione()).toString().length()<5){
        	cr.save(carburante);
    		return "redirect:/admin/carburanti";
        }else{
        	model.addAttribute("messaggio", "Il carburante " + carburante.getDescrizione() + " é già presente");
        	return "admin@carburanteNewForm";
        }
	}
	
	@RequestMapping(value = "/admin/carburante/remove", method = RequestMethod.GET)
	public String carburanteRemove(@RequestParam(value = "id", required = true) long id,	Model model) {
        System.out.println("Carburante -> Remove -> GET");	
        cr.delete(id);
		return "redirect:/admin/carburanti";
	}	


	@RequestMapping(value = "/json/carburanti", method = RequestMethod.GET)
	public @ResponseBody Carburanti carburantiJSON(Model model) {
		return new Carburanti(cr.findAll());
	}
	
	@RequestMapping(value = "/json/carburanti/search", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Carburanti carburanteJSON(@RequestParam(value = "q", required = true) String q, Model model) {
		return new Carburanti(cr.findByDescrizioneContainingIgnoreCase(q));
	}

	@RequestMapping(value = "/json/carburante/{id}", method = RequestMethod.GET)
	public @ResponseBody Carburante carburanteJSON(@PathVariable long id, Model model) {
		return cr.findOne(id);
	}

}
