package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.CrimeDao;
import com.revature.dao.SuperPrisonDao;
import com.revature.dao.SuperVillainDao;
import com.revature.models.Crime;
import com.revature.models.SuperPrison;
import com.revature.models.SuperVillain;

/**
 * Servlet implementation class HibernateServlet
 */
public class HibernateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static SuperVillainDao svdao = new SuperVillainDao();

    public HibernateServlet() {
        
//        initialValues();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
			// we will select a supervillain by name
			System.out.println("We are in the doGet Method");
			System.out.println("\n About to select by name:");
			
			System.out.println(svdao.selectByName("Joker"));
			PrintWriter pw = response.getWriter();
			pw.println("<html><h1>Hello World</h1></html>");
			// checking for entities stored in the first level cache
			System.out.println(svdao.selectByName("Joker"));
		
		
	}

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("In doPost");
		
		System.out.println("\n About to select All");
		
		System.out.println(svdao.selectAll());
	
		// checking for entities stored in the first level cache
		SuperVillain joker = svdao.selectByName("Joker");
		
		response.setContentType("application/json");
		
		response.getWriter().write(new ObjectMapper().writeValueAsString(joker.toString()));
		
		HttpSession session = request.getSession();
		
		session.setAttribute("villain", joker);
		

	}
	
	
	
	public static void initialValues() {

		// Create new Crime objs
		Crime c1 = new Crime("Arson", "setting something ablaze");
		Crime c2 = new Crime("Freeze", "covering a whole city in ice");
		Crime c3 = new Crime("Time Manipulation", "freeze time, rob banks");

		// create a crime dao
		CrimeDao cDao = new CrimeDao();

		// use the crimeDao to insert the crimes
		cDao.insert(c1);
		cDao.insert(c2);
		cDao.insert(c3);

		// build a list of these crimes
		List<Crime> crimes = new ArrayList<Crime>();
		crimes.add(c1);
		crimes.add(c2);
		crimes.add(c3);

		// Create superPrison
		SuperPrison sp1 = new SuperPrison("Azkaban", "England");

		// I'll instantiate some super villains
		SuperVillain sv1 = new SuperVillain("Joker", "Evilness", 100000, crimes, sp1);

		// Create SuperVillainDao
		SuperVillainDao svdao = new SuperVillainDao();
		svdao.insert(sv1);

		// Create a SuperPrisonDao here
		SuperPrisonDao spdao = new SuperPrisonDao();

		// create a list of all villains
		List<SuperVillain> villains = new ArrayList<SuperVillain>();
		villains.add(sv1);

		// send the list of villains to the prison
		sp1.setVillList(villains);

		// insert the prison into the DB.
		spdao.insert(sp1);

	}

}
