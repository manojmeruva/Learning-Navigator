
# Learning-Navigator

## Overview

The Learning Navigator is a RESTful service built using **Spring Boot** to manage student exam registration in a Learning Management System (LMS). This service handles CRUD operations for students, subjects, and exams, ensuring that students can register for exams only after enrolling in the relevant subjects. The system uses **MySQL** for data persistence and follows clean architecture principles, emphasizing **SOLID** design.

## Features

1. **Student Management**: 
   - Create and manage students.
   - Enroll students in subjects.
   - Register students for exams.

2. **Subject Management**:
   - Create and manage subjects.
   - Retrieve all students enrolled in a subject.

3. **Exam Management**:
   - Create and manage exams.
   - Register students for exams.
   - Retrieve all students registered for a particular exam.

4. **Error Handling**:
   - Centralized exception handling for common errors (404 Not Found, 400 Bad Request).
   - Graceful handling of missing entities (student, subject, or exam).

5. **Validation**:
   - Students must be enrolled in a subject before they can register for the corresponding exam.

## System Architecture

The application follows a layered architecture using **Spring Boot** with clear separation of concerns:
- **Controllers**: Handle HTTP requests and delegate business logic to services.
- **Services**: Contain the business logic, interact with the repository layer, and ensure validation.
- **Repositories**: Interact with the MySQL database to persist and retrieve data.

The design of the application adheres to **SOLID principles** to ensure code maintainability and scalability.

## Technologies Used

- **Spring Boot** - for building the RESTful API
- **MySQL** - for data persistence
- **Spring Data JPA** - for database interaction and ORM
- **MockMvc and Mockito** - for unit testing
- **Gradle** - for dependency management and building the project

## API Endpoints

### 1. **Student Endpoints**
Manage students, enroll them in subjects, and register them for exams.

- **POST** `/students`  
  Create a new student.
  ```json
  Request Body:
  {
      "name": "John Doe"
  }
  ```

- **GET** `/students/{studentId}`  
  Get details of a specific student.
  
- **POST** `/students/{studentId}/subjects/{subjectId}`  
  Enroll a student in a specific subject.
  
- **POST** `/students/{studentId}/exams/{examId}`  
  Register a student for a specific exam (only allowed if they are enrolled in the corresponding subject).

### 2. **Subject Endpoints**
Manage subjects and retrieve students enrolled in specific subjects.

- **POST** `/subjects`  
  Create a new subject.
  ```json
  Request Body:
  {
      "name": "Mathematics"
  }
  ```

- **GET** `/subjects/{subjectId}`  
  Get details of a specific subject.
  
- **GET** `/subjects/{subjectId}/students`  
  Get a list of students enrolled in a specific subject.

### 3. **Exam Endpoints**
Manage exams and retrieve students registered for specific exams.

- **POST** `/exams`  
  Create a new exam for a subject.
  ```json
  Request Body:
  {
      "subject": {
          "subjectId": 1
      }
  }
  ```

- **GET** `/exams/{examId}`  
  Get details of a specific exam.

- **GET** `/exams/{examId}/students`  
  Get a list of students registered for a specific exam.

### Error Handling

- **404 Not Found**: When entities (students, subjects, exams) are not found.
- **400 Bad Request**: For validation failures, such as attempting to register for an exam without enrolling in the corresponding subject.
  
## Database Schema

The application uses **MySQL** as the persistence layer. Below is the entity relationship:

- **Student**
  - Fields: `registrationId`, `name`
  - Relationships:
    - Many-to-Many: `subjects` (a student can enroll in multiple subjects)
    - Many-to-Many: `exams` (a student can register for multiple exams)
  
- **Subject**
  - Fields: `subjectId`, `name`
  - Relationships:
    - Many-to-Many: `students` (a subject can have multiple students enrolled)

- **Exam**
  - Fields: `examId`
  - Relationships:
    - Many-to-One: `subject` (an exam belongs to a specific subject)
    - Many-to-Many: `students` (an exam can have multiple students registered)

## Running the Application

1. Clone the repository:
   ```bash
   git clone https://github.com/manojmeruva/Learning-Navigator.git
   ```

2. Configure the **MySQL** database in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/LMS
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   ```

3. Build and run the application:
  ./gradlew build - build app
   ./gradlew bootrun - run app

5. Access the API via `http://localhost:8080/`.

## Unit Testing

Unit tests are written using **JUnit**, **MockMvc**, and **Mockito** to ensure high code quality and correct behavior of the services.

To run the tests:
./gradlew test

### Key Unit Tests:
- **StudentServiceTest**: Tests creating a student, enrolling them in a subject, and registering them for exams.
