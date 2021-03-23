package com.revature.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.ajax.ClientMessage;
import com.revature.model.Hero;
import com.revature.repository.HeroRepository;

@Service("heroService")
public class HeroServiceImpl implements HeroService{
	
	private Logger logger = Logger.getLogger(HeroServiceImpl.class);
	
	@Autowired
	private HeroRepository heroRepository;

	public HeroServiceImpl() {
		logger.trace("Injection using Autowired Hero Repository in HeroServiceImpl");
	}
	
	@Override
	public boolean registerHero(Hero hero) {
		heroRepository.save(hero);
		return hero.getId() != 0;
	}
	
	@Override
	public List<Hero> getAllHeroes() {
		return heroRepository.findAll();
	}

	@Override
	public Hero getHero(String name) {
		return heroRepository.findByName(name);
	}

}
