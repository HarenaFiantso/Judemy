CREATE TABLE users
(
    user_id  SERIAL PRIMARY KEY,
    name     VARCHAR(100) NOT NULL,
    email    VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE courses
(
    course_id   SERIAL PRIMARY KEY,
    title       VARCHAR(200) NOT NULL,
    description TEXT,
    duration    INT
);

CREATE TABLE enrollments
(
    enrollment_id    SERIAL PRIMARY KEY,
    user_id          INT REFERENCES users (user_id),
    course_id        INT REFERENCES courses (course_id),
    enrollement_date TIMESTAMP   DEFAULT now(),
    status           VARCHAR(50) DEFAULT 'enrolled'
);

CREATE TABLE lessons
(
    lesson_id     SERIAL PRIMARY KEY,
    course_id     INT REFERENCES courses (course_id),
    title         VARCHAR(200) NOT NULL,
    description   TEXT,
    display_order INT
);

CREATE TABLE videos
(
    video_id  SERIAL PRIMARY KEY,
    lesson_id INT REFERENCES lessons (lesson_id),
    title     VARCHAR(200) NOT NULL,
    url       TEXT,
    duration  INT
);

CREATE TABLE quizzes
(
    quiz_id        SERIAL PRIMARY KEY,
    lesson_id      INT REFERENCES lessons (lesson_id),
    question       TEXT         NOT NULL,
    option1        VARCHAR(200) NOT NULL,
    option2        VARCHAR(200) NOT NULL,
    option3        VARCHAR(200),
    option4        VARCHAR(200),
    correct_answer INT
);