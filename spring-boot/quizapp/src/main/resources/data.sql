INSERT INTO question (question_title, option1, option2, option3, option4, correct_answer, difficulty_level, category)
SELECT 'What is the capital of France?', 'Berlin', 'Madrid', 'Paris', 'Rome', 'Paris', 'Easy', 'Geography'
WHERE NOT EXISTS (SELECT 1 FROM question WHERE question_title = 'What is the capital of France?');

INSERT INTO question (question_title, option1, option2, option3, option4, correct_answer, difficulty_level, category)
SELECT 'What is 2 + 2?', '3', '4', '5', '6', '4', 'Easy', 'Mathematics'
WHERE NOT EXISTS (SELECT 1 FROM question WHERE question_title = 'What is 2 + 2?');

INSERT INTO question (question_title, option1, option2, option3, option4, correct_answer, difficulty_level, category)
SELECT 'Who wrote "Hamlet"?', 'Charles Dickens', 'Mark Twain', 'William Shakespeare', 'Jane Austen', 'William Shakespeare', 'Medium', 'Literature'
WHERE NOT EXISTS (SELECT 1 FROM question WHERE question_title = 'Who wrote "Hamlet"?');

INSERT INTO question (question_title, option1, option2, option3, option4, correct_answer, difficulty_level, category)
SELECT 'What is the largest planet in our solar system?', 'Earth', 'Mars', 'Jupiter', 'Saturn', 'Jupiter', 'Medium', 'Astronomy'
WHERE NOT EXISTS (SELECT 1 FROM question WHERE question_title = 'What is the largest planet in our solar system?');

INSERT INTO question (question_title, option1, option2, option3, option4, correct_answer, difficulty_level, category)
SELECT 'What is the chemical symbol for water?', 'O2', 'H2O', 'CO2', 'HO', 'H2O', 'Easy', 'Chemistry'
WHERE NOT EXISTS (SELECT 1 FROM question WHERE question_title = 'What is the chemical symbol for water?');

INSERT INTO question (question_title, option1, option2, option3, option4, correct_answer, difficulty_level, category)
SELECT 'Which element has the atomic number 1?', 'Helium', 'Oxygen', 'Hydrogen', 'Nitrogen', 'Hydrogen', 'Easy', 'Chemistry'
WHERE NOT EXISTS (SELECT 1 FROM question WHERE question_title = 'Which element has the atomic number 1?');

INSERT INTO question (question_title, option1, option2, option3, option4, correct_answer, difficulty_level, category)
SELECT 'What year did World War II end?', '1943', '1944', '1945', '1946', '1945', 'Hard', 'History'
WHERE NOT EXISTS (SELECT 1 FROM question WHERE question_title = 'What year did World War II end?');

INSERT INTO question (question_title, option1, option2, option3, option4, correct_answer, difficulty_level, category)
SELECT 'Who painted the Mona Lisa?', 'Vincent Van Gogh', 'Pablo Picasso', 'Leonardo da Vinci', 'Claude Monet', 'Leonardo da Vinci', 'Medium', 'Art'
WHERE NOT EXISTS (SELECT 1 FROM question WHERE question_title = 'Who painted the Mona Lisa?');

INSERT INTO question (question_title, option1, option2, option3, option4, correct_answer, difficulty_level, category)
SELECT 'What is the smallest prime number?', '0', '1', '2', '3', '2', 'Easy', 'Mathematics'
WHERE NOT EXISTS (SELECT 1 FROM question WHERE question_title = 'What is the smallest prime number?');

INSERT INTO question (question_title, option1, option2, option3, option4, correct_answer, difficulty_level, category)
SELECT 'Who discovered penicillin?', 'Marie Curie', 'Albert Einstein', 'Alexander Fleming', 'Isaac Newton', 'Alexander Fleming', 'Medium', 'Science'
WHERE NOT EXISTS (SELECT 1 FROM question WHERE question_title = 'Who discovered penicillin?');

INSERT INTO question (question_title, option1, option2, option3, option4, correct_answer, difficulty_level, category)
SELECT 'What is the capital of Japan?', 'Seoul', 'Beijing', 'Tokyo', 'Bangkok', 'Tokyo', 'Easy', 'Geography'
WHERE NOT EXISTS (SELECT 1 FROM question WHERE question_title = 'What is the capital of Japan?');

INSERT INTO question (question_title, option1, option2, option3, option4, correct_answer, difficulty_level, category)
SELECT 'Which planet is known as the Red Planet?', 'Venus', 'Mars', 'Jupiter', 'Mercury', 'Mars', 'Easy', 'Astronomy'
WHERE NOT EXISTS (SELECT 1 FROM question WHERE question_title = 'Which planet is known as the Red Planet?');

INSERT INTO question (question_title, option1, option2, option3, option4, correct_answer, difficulty_level, category)
SELECT 'Who wrote "The Great Gatsby"?', 'Ernest Hemingway', 'F. Scott Fitzgerald', 'J.D. Salinger', 'Mark Twain', 'F. Scott Fitzgerald', 'Medium', 'Literature'
WHERE NOT EXISTS (SELECT 1 FROM question WHERE question_title = 'Who wrote "The Great Gatsby?"');

INSERT INTO question (question_title, option1, option2, option3, option4, correct_answer, difficulty_level, category)
SELECT 'What is the square root of 64?', '6', '7', '8', '9', '8', 'Easy', 'Mathematics'
WHERE NOT EXISTS (SELECT 1 FROM question WHERE question_title = 'What is the square root of 64?');

INSERT INTO question (question_title, option1, option2, option3, option4, correct_answer, difficulty_level, category)
SELECT 'Who developed the theory of relativity?', 'Isaac Newton', 'Nikola Tesla', 'Albert Einstein', 'Galileo Galilei', 'Albert Einstein', 'Hard', 'Science'
WHERE NOT EXISTS (SELECT 1 FROM question WHERE question_title = 'Who developed the theory of relativity?');

INSERT INTO question (question_title, option1, option2, option3, option4, correct_answer, difficulty_level, category)
SELECT 'What is the capital of Australia?', 'Sydney', 'Melbourne', 'Canberra', 'Perth', 'Canberra', 'Easy', 'Geography'
WHERE NOT EXISTS (SELECT 1 FROM question WHERE question_title = 'What is the capital of Australia?');

INSERT INTO question (question_title, option1, option2, option3, option4, correct_answer, difficulty_level, category)
SELECT 'Who is known as the Father of Computers?', 'Alan Turing', 'Charles Babbage', 'Bill Gates', 'Steve Jobs', 'Charles Babbage', 'Medium', 'Technology'
WHERE NOT EXISTS (SELECT 1 FROM question WHERE question_title = 'Who is known as the Father of Computers?');

INSERT INTO question (question_title, option1, option2, option3, option4, correct_answer, difficulty_level, category)
SELECT 'What is the longest river in the world?', 'Amazon', 'Nile', 'Yangtze', 'Mississippi', 'Nile', 'Hard', 'Geography'
WHERE NOT EXISTS (SELECT 1 FROM question WHERE question_title = 'What is the longest river in the world?');

INSERT INTO question (question_title, option1, option2, option3, option4, correct_answer, difficulty_level, category)
SELECT 'What is the boiling point of water?', '90°C', '95°C', '100°C', '105°C', '100°C', 'Easy', 'Science'
WHERE NOT EXISTS (SELECT 1 FROM question WHERE question_title = 'What is the boiling point of water?');

INSERT INTO question (question_title, option1, option2, option3, option4, correct_answer, difficulty_level, category)
SELECT 'Which country is known as the Land of the Rising Sun?', 'China', 'South Korea', 'Japan', 'Thailand', 'Japan', 'Medium', 'Geography'
WHERE NOT EXISTS (SELECT 1 FROM question WHERE question_title = 'Which country is known as the Land of the Rising Sun?');