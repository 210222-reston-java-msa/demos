package com.example.json;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.SuperVillain;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONRequestHelper {

	public static void process(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException, IOException {
		
		System.out.println(request.getRequestURI());

		switch (request.getRequestURI()) {
		
		case "/HelloFrontController/mud.json":
			MudController.mudFinder(request, response);
			break;
		case "/HelloFrontController/fay.json":
			FayController.fayFinder(request, response);
			break;
			
		default:
			SuperVillain vill = new SuperVillain("?", "?", 0);
			response.getWriter().write(new ObjectMapper().writeValueAsString(vill));
		}
	}
}
