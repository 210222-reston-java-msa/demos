package com.revature.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
// Load balancing is done by Ribbon on the client side
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
// we remove the RequestMapping because the Gateway is handling all of our routing based on the uri of the service
//@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	private QuizRepository quizDao;

// We no longer need this because we will inject a RestTemplate Bean

//	@Autowired
//	private FlashcardRepository flashcardDao;
	
	// First we declare the RestTemplate as a bean to be autowired
	@Bean
	@LoadBalanced
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
	
	// localhost:8080/quiz/cards -- will return all flashcards used in this quiz
	@GetMapping("/cards")
	public ResponseEntity<List<Flashcard>> getCards() {
		// Use the RestTemplate to CALL another service
		List<Flashcard> all = this.restTemplate.getForObject("http://flashcard", List.class);
		
		if(all.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(all);
	}
	
	// Now, when we send a GET request to localhost:8080/quiz/cards we get the expected response
	
	
//	@GetMapping("/cards")
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
