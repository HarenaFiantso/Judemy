-- Mock data pour la table 'users'
INSERT INTO users (name, email, password)
VALUES ('Alice', 'alice@example.com', 'alice123'),
       ('Bob', 'bob@example.com', 'bob456'),
       ('Carol', 'carol@example.com', 'carol789'),
       ('David', 'david@example.com', 'david321'),
       ('Eve', 'eve@example.com', 'eve654'),
       ('Frank', 'frank@example.com', 'frank987'),
       ('Grace', 'grace@example.com', 'grace234'),
       ('Harry', 'harry@example.com', 'harry567'),
       ('Ivy', 'ivy@example.com', 'ivy890'),
       ('Jack', 'jack@example.com', 'jack123');

-- Mock data pour la table 'courses'
INSERT INTO courses (title, description, duration)
VALUES ('Introduction to Programming', 'Learn the basics of programming.', 30),
       ('Web Development Fundamentals', 'Get started with web development.', 45),
       ('Data Science Essentials', 'Explore the world of data science.', 60),
       ('Advanced Java Programming', 'Deep dive into Java programming concepts.', 60),
       ('Python for Beginners', 'Start your journey with Python programming.', 30),
       ('Machine Learning Fundamentals', 'Introduction to machine learning concepts.', 45),
       ('Networking Basics', 'Understanding computer networking fundamentals.', 30),
       ('Database Design Principles', 'Learn about designing efficient databases.', 45),
       ('Artificial Intelligence Basics', 'Introduction to AI and its applications.', 60),
       ('Graphic Design Fundamentals', 'Explore the world of graphic design.', 30);

-- Mock data pour la table 'enrollments'
INSERT INTO enrollments (user_id, course_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5),
       (5, 6),
       (6, 7),
       (7, 8);

-- Mock data pour la table 'lessons'
INSERT INTO lessons (course_id, title, description, display_order)
VALUES (1, 'Introduction to Programming Concepts', 'Understanding basic programming concepts.', 1),
       (1, 'Variables and Data Types', 'Learn about variables and data types in programming.', 2),
       (2, 'HTML and CSS Basics', 'Introduction to HTML and CSS.', 1),
       (2, 'JavaScript Fundamentals', 'Getting started with JavaScript programming.', 2),
       (3, 'Introduction to Data Science', 'An overview of data science.', 1),
       (3, 'Data Analysis Techniques', 'Exploring various data analysis methods.', 2),
       (4, 'Object-Oriented Programming', 'Understanding OOP concepts in Java.', 1),
       (5, 'Python Syntax and Basics', 'Getting familiar with Python syntax.', 1),
       (6, 'Machine Learning Algorithms', 'Overview of machine learning algorithms.', 1),
       (7, 'Introduction to Networking', 'Networking basics and protocols.', 1);

-- Mock data pour la table 'videos'
INSERT INTO videos (lesson_id, title, url, duration)
VALUES (1, 'Introduction Video', 'https://example.com/intro_video', 1200),
       (3, 'HTML Basics', 'https://example.com/html_basics', 900),
       (4, 'JavaScript Variables', 'https://example.com/js_variables', 1500),
       (5, 'Introduction to Data Science', 'https://example.com/intro_to_ds', 1800),
       (7, 'Object-Oriented Concepts', 'https://example.com/oop_concepts', 1200),
       (8, 'Python Variables and Data Types', 'https://example.com/python_vars', 900),
       (9, 'Decision Trees Explained', 'https://example.com/decision_trees', 1500),
       (10, 'Networking Protocols', 'https://example.com/networking_protocols', 1200);

-- Mock data pour la table 'quizzes'
INSERT INTO quizzes (lesson_id, question, option1, option2, option3, option4, correct_answer)
VALUES (2, 'What is a variable?', 'A container for storing data.', 'A type of loop.', 'A function definition.',
        'An HTML tag.', 1),
       (5, 'What is data science?', 'A field of study focused on analyzing data.', 'A type of programming language.',
        'A video game genre.', 'A form of visual art.', 1),
       (6, 'What is data analysis?', 'Extracting useful insights from data.', 'Writing code for web development.',
        'A way to format text.', 'A type of graph.', 1),
       (7, 'What is an object?', 'An instance of a class in OOP.', 'A programming language keyword.', 'A type of file.',
        'A data structure.', 1),
       (9, 'What is a decision tree?', 'A machine learning algorithm for classification.', 'A type of graph.',
        'A type of variable.', 'A method of encryption.', 1);
