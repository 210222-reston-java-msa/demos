package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.revature.util.ClientMessageUtil.*;

import com.revature.ajax.ClientMessage;
import com.revature.model.Hero;
import com.revature.service.HeroService;

@Controller("heroController")
@CrossOrigin(origins = "http://localhost:4200")
public class HeroControllerImpl implements HeroController{
	
	@Autowired
	private HeroService heroService;
	
	/*
	 * @RequestMapping (Get or Post) tells specifies the URL at which we can trigger these methods
	 * 
	 * @ResponseBody marshals our POJOs into JSON with Jackson (since we have the dependency)
	 * 
	 * @RequestBody UN-marshals the JSON into a Java POJO
	 * 
	 */
	@PostMapping("/register") // localhost:8080/SpringMVC/register
	public @ResponseBody ClientMessage registerHero(@RequestBody Hero hero) {
		// rememebr that our registerHero method returns a boolean, so we can use a ternary operator
		return (heroService.registerHero(hero)) ? REGISTRATION_SUCCESSFUL : SOMETHING_WRONG;
	}

	@PostMapping("/findHero")
	public @ResponseBody Hero findHero(@RequestBody Hero hero, HttpServletRequest request) {
		request.getSession();
		return heroService.getHero(hero.getName());
	}

	@GetMapping("/findAllHeroes") // localhost:8080/SpringMVC/findAllHeroes
	public @ResponseBody List<Hero> findAllHeroes() {
		return heroService.getAllHeroes();
	}

}
