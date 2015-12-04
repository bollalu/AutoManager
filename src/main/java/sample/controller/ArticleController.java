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

import sample.model.Article;
import sample.model.Articles;
import sample.repo.ArticleRepository;

@Controller
public class ArticleController {

	@Autowired
	protected ArticleRepository ar;

	//@PreAuthorize("hasAuthority('USER')")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String greeting(Model model) {
		return "articles";
	}

	// http://docs.spring.io/autorepo/docs/spring-security/3.2.1.RELEASE/apidocs/org/springframework/security/access/expression/SecurityExpressionOperations.html
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/article", method = RequestMethod.GET)
	public String article(@RequestParam(value = "id", required = true) long id,
			Model model) {
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(username);
		
		Article article = ar.findOne(id);
		model.addAttribute("article", article);
		return "articleform";
	}
	
	@RequestMapping(value = "/article", method = RequestMethod.POST)
	public String article(@ModelAttribute Article article, Model model) {
		ar.save(article);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/article/new", method = RequestMethod.GET)
	public String article(Model model) {
		Article article = new Article();
		model.addAttribute("article", article);
		return "articleform";
	}
	
	@RequestMapping(value = "/article/new", method = RequestMethod.POST)
	public String articlePOST(@ModelAttribute Article article, Model model) {
		ar.save(article);
		return "redirect:/";
	}

	@RequestMapping(value = "/json/articles", method = RequestMethod.GET)
	public @ResponseBody Articles articlesJSON(Model model) {
		return new Articles(ar.findAll());
	}
	
	@RequestMapping(value = "/json/articles/search", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Articles articleJSON(@RequestParam(value = "q", required = true) String q, Model model) {
		return new Articles(ar.findByTitleContainingIgnoreCase(q));
	}

	@RequestMapping(value = "/json/article/{id}", method = RequestMethod.GET)
	public @ResponseBody Article articleJSON(@PathVariable long id, Model model) {
		return ar.findOne(id);
	}

}
