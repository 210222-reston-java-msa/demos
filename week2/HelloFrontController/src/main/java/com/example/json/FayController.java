package com.example.json;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.SuperVillain;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FayController {

	
	public static void fayFinder(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException, IOException {
		
		
		SuperVillain fay = new SuperVillain("David Fay", "Sith Lord", 250_000);
		
		response.getWriter().write(new ObjectMapper().writeValueAsString(fay));
	}
}
