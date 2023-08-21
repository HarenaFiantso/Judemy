# Judemy Project

Judemy is an e-learning platform designed to help users learn and teach a variety of topics. This repository contains
the backend code for the Judemy project.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
- [Contributing](#contributing)

## Overview

Judemy is a platform that provides a wide range of educational resources, including video lessons, quizzes, and
interactive content. Users can sign up, browse courses, watch lessons, take quizzes, and track their progress. Teachers
can create and manage courses, add lessons, and assess students' performance.

This repository contains the backend code responsible for managing users, courses, lessons, and quizzes.

## Features

- Course creation, management, and enrollment
- Lesson and video management
- Quiz creation and assessment
- Progress tracking for users
- ...

## Getting Started

### Prerequisites

- Java
- Spring Boot
- PostgreSQL
- Maven

### Installation

1. Clone the repository:

   ```shell
   git clone https://github.com/HarenaFiantso/Judemy.git
   ```
2. Configure the database settings in database configuration folder (Credentials java class):

```java
public class Credentials {
    public static final String DATABASE_URL = "jdbc:postgresql://localhost/judemy";
    public static final String DATABASE_USER = "YourName";
    public static final String DATABASE_PASSWORD = "YourPassword";
}
```

3. Build the project:

```shell
    mvn clean install
```

4. Run the application ✅️

**Access the Judemy API by sending HTTP requests to the appropriate endpoints.
Use API documentation or tools like Postman to interact with the API.
Visit the frontend repository (link) to access the user interface.**

# Contributing

Contributions to the Judemy project are welcome! If you find a bug or want to add a new feature, please follow these
steps:

- Fork the repository
- Create a new branch
- Make your changes and commit them
- Push to the branch
- Submit a pull request.

#### **©️Fiantso Harena - Judemy 2023**