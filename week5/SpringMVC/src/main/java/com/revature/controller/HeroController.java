package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.revature.ajax.ClientMessage;
import com.revature.model.Hero;

public interface HeroController {
	
	// When we call register hero, it will return a Client Message
	// Here we SEND a post request and ADD something to the DB, and we recieve a message back
	ClientMessage registerHero(Hero hero);
	
	// Here we return the Hero object we were looking for
	Hero findHero(Hero hero, HttpServletRequest request);
	
	List<Hero> findAllHeroes();

	
}
