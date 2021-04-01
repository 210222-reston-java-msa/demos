package com.revature.events;

import java.time.LocalDateTime;

import com.revature.models.Flashcard;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode @ToString
public class FlashcardChangeEvent {

	private Flashcard flashcard;
	private Operation operation;
	private LocalDateTime timestamp;
	
	public FlashcardChangeEvent(Flashcard flashcard2, Operation create, LocalDateTime now) {
		// TODO Auto-generated constructor stub
	}
	public Flashcard getFlashcard() {
		return flashcard;
	}
	public void setFlashcard(Flashcard flashcard) {
		this.flashcard = flashcard;
	}
	public Operation getOperation() {
		return operation;
	}
	public void setOperation(Operation operation) {
		this.operation = operation;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
