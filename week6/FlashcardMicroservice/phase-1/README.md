# Phase 1: Separate Monolith by Domain

In this phase we will break apart a monolithic application into multiple smaller services that together form a single microservice application. The application in use is the Quiz Application that has Flashcards for studying different topics, and quizzes that are tied to specific flashcards. The original monolithic application has tight coupling between the Flashcards and Quizzes, so in this phase we will overcome that using RestTemplate for interservice communication.

The end result of this phase will be provided for comparison.

## Step 1: Create New Spring Boot Projects

We will need to create 2 Spring Boot Projects, one named quiz-service and the other flashcard-service. They will use the same Spring Start Dependencies as our initial project, which are listed below.

* H2 Database (could be Oracle/PostgreSQL Driver instead)
* Spring Web
* Spring Data JPA
* Lombok
* Spring Boot Actuator
* Spring Boot DevTools

## Step 2: Copy Packages

Now we will copy the corresponding contents from the original quiz-app into each of the quiz-service and flashcard-service.

Copy the following files into quiz-service:
* QuizController.java
* Difficulty.java
* Flashcard.java
* Quiz.java
* Topic.java
* FlashcardRepository.java
* QuizRepository.java
* application.properties

Note: We specifically **do not** include the data.sql file in the quiz-service.

Copy the following files into flashcard-service:
* FlashcardController.java
* Difficulty.java
* Flashcard.java
* Quiz.java
* Topic.java
* FlashcardRepository.java
* TopicRepository.java
* application.properties
* data.sql

Note: We *do* have some duplicated code because the `QuizController` has a `/quiz/cards` endpoint that is also able to retrieve all flashcards. This means that the quiz-service must also have model classes for the Flashcards. These don't necessarily need to match the models in the flashcard-service. But in this case, they do.

## Step 3: Modify Configuration

In the `application.properties` files, change the beginning of the configuration to match as shown below.

quiz-service:
```properties
server.port=8080
spring.application.name=quiz-service
```

flashcard-service
```properties
server.port=8081
spring.application.name=flashcard-service
```

In the `data.sql` file in the flashcard-service, remove the INSERT statements for the `quiz` and `quiz_cards` tables, as they are no longer applicable.

## Step 4: Communicate with RestTemplate

At this point the services should function almost perfectly. The only issue is now the services are not properly synchronized. We can send a GET request to `localhost:8081/flashcard` and obtain our flashcards, but the request to `localhost:8080/quiz/cards` no longer works.

To resolve this, we allow the quiz-service to send a request to the flashcard-service to obtain the same information. So we can now remove the `FlashcardRepository` from the quiz-service.
We accomplish this with RestTemplate. Update the `QuizController` in the quiz-service according to the following code snippet.

```java
  // Declare the RestTemplate as a bean to be autowired
  // This could be xml, java, or annotation config
  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Autowired
  private RestTemplate restTemplate;

  @GetMapping("/cards")
  public ResponseEntity<List<Flashcard>> getCards() {
    // Use the RestTemplate to call another service
    List<Flashcard> all = this.restTemplate.getForObject("http://localhost:8081/flashcard", List.class);

    if(all.isEmpty()) {
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.ok(all);
  }
```

Now when we send a GET request to `localhost:8080/quiz/cards` we get the expected response.

## Summary

We have extracted smaller services from our monolithic application and turned them into a microservices application. From there, we created interservice communication through the use of RestTemplate.

The next issue to tackle in Phase 2 is keeping track of our many different services using Service Discovery and API Gateway with Consul and Spring Cloud Gateway.
