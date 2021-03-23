package com.revature.repository;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.model.Hero;

// @Repository annotation indicates that this class provides a mechanism
// for CRUD operations of this particular object
@Repository("heroRepository")
@Transactional // This annotation tells spring how this class is associates with our DB sessions -- this transaction is happening 
// inside the JPA Entitymanager
public class HeroRepositoryImpl implements HeroRepository{
	
	
	private static Logger logger = Logger.getLogger(HeroRepositoryImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public HeroRepositoryImpl() {
		logger.trace("Injection session factory bean");
	}

	@Override
	public void save(Hero hero) {
		sessionFactory.getCurrentSession().save(hero);		
	}

	// this @ Suppress Warnings will NOT affect functionality of our code
	@SuppressWarnings("unchecked")
	@Override
	public List<Hero> findAll() {
		
		// Here we're using Criteria API (totally OOP focused, but you could use HQL , or NATIVE SQL
		return sessionFactory.getCurrentSession().createCriteria(Hero.class).list();
	}

	@Override
	public Hero findByName(String passedThruName) {
		try {
			return (Hero) sessionFactory.getCurrentSession().createCriteria(Hero.class).add(Restrictions.like("name", passedThruName))
					.list().get(0);
		} catch (IndexOutOfBoundsException e) {
			logger.debug(e);
			return null;
		}
	}
	
	

}
