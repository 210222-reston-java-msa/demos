package com.example.json;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.SuperVillain;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MudController {

	
	public static void mudFinder(HttpServletRequest request, HttpServletResponse response)
			throws JsonProcessingException, IOException {
		
		SuperVillain muddy = new SuperVillain("Mud Man", "Mud Armor", 100_000);
		
		response.getWriter().write(new ObjectMapper().writeValueAsString(muddy));
		
	}
}
