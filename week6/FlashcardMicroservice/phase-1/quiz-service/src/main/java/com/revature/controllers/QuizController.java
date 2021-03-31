package com.revature.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.revature.models.Flashcard;
import com.revature.models.Quiz;
import com.revature.repositories.QuizRepository;

@RestController
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	private QuizRepository quizDao;

	// We can comment this out because we will inject a RestTemplate Bean
//	@Autowired
//	private FlashcardRepository flashcardDao;
	
	
	// First, declare the RestTemplate as a bean to be autowired
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@GetMapping
	public ResponseEntity<List<Quiz>> findAll() {
		List<Quiz> all = quizDao.findAll();
		
		if(all.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(all);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Quiz> findById(@PathVariable("id") int id) {
		Optional<Quiz> optional = quizDao.findById(id);
		
		if(optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		}
		
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<Quiz> insert(@RequestBody Quiz quiz) {
		int id = quiz.getId();
		
		if(id != 0) {
			return ResponseEntity.badRequest().build();
		}
		
		quizDao.save(quiz);
		return ResponseEntity.status(201).body(quiz);
	}

	
	@GetMapping("/cards")
	public ResponseEntity<List<Flashcard>> getCards() {
		
		// Quiz Service is running on port 8080
		// Flashcard Service is running on port 8081
		
		// use the RestTemplate to call ANOTHER service -- flashcard service is on 8081
		List<Flashcard> all = this.restTemplate.getForObject("http://localhost:8081/flashcard", List.class);
		
		if(all.isEmpty()) {
		return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(all);
		
	}
	
	
	
//	@GetMapping("/cards") // we want to call on the flashcard-service while both services are running
//	public ResponseEntity<List<Flashcard>> getCards() {
//		List<Flashcard> all = flashcardDao.findAll();
//		
//		if(all.isEmpty()) {
//			return ResponseEntity.noContent().build();
//		}
//		
//		return ResponseEntity.ok(all);
//	}
}
