INSERT INTO TOPIC (name) VALUES ('Java'), ('SQL');

INSERT INTO FLASHCARD (question, answer, name, difficulty, topic_id) VALUES
(
	'What is the root interface of the Collections API?',
	'Iterable is at the top of the Collection Heirarchy, but many consider the Collection interface to be the root.',
	'Collections Root Interface',
	1,
	1
);

INSERT INTO FLASHCARD (question, answer, name, difficulty, topic_id) VALUES
(
	'What are the 5 SQL Sublanguages?',
	'Data Definition Language, Data Manipulation Language, Data Query Language, Data Control Language, and Transaction Control Language.',
	'SQL Sublanguages',
	1,
	2
);

--INSERT INTO QUIZ (grade, name) VALUES
--(
--	100,
--	'Basic Quiz'
--);
--
--INSERT INTO QUIZ_CARDS (quiz_id, cards_id) VALUES (1, 1), (1, 2);
