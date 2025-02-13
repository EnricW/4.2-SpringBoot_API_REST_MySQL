# CRUD Exercise with MySQL 🍊

## Description

This project demonstrates a basic implementation of a REST API with Spring Boot using Maven. The API allows managing a "Fruit" entity in a MySQL database following the MVC pattern.

## Technical Requirements

- Java JDK 11 or higher
- Maven
- Spring Boot (latest stable version)
- IDE (IntelliJ IDEA, Eclipse, VS Code, etc.)
- Postman for testing

## Configuration

### Dependencies

- Spring Boot DevTools
- Spring Web
- Spring Data JPA
- MySQL Driver

## Project Structure

The project is structured following the MVC pattern with the following packages:

```
cat.itacademy.s04.t02.n01.controllers  → Contains REST controllers
cat.itacademy.s04.t02.n01.model        → Contains entity classes
cat.itacademy.s04.t02.n01.services     → Contains business logic
cat.itacademy.s04.t02.n01.repository   → Contains persistence interfaces
cat.itacademy.s04.t02.n01.exception    → Contains global exception handling
```

## API Endpoints

### 1. Add a fruit

```http
POST /fruit/add
```

- Body (JSON):
  ```json
  {
    "name": "Apple",
    "weight": 150
  }
  ```
- Returns: Details of the created fruit

### 2. Update a fruit

```http
PUT /fruit/update
```

- Body (JSON):
  ```json
  {
    "id": 1,
    "name": "Banana",
    "weight": 200
  }
  ```
- Returns: Updated fruit

### 3. Delete a fruit

```http
DELETE /fruit/delete/{id}
```

- Parameter: `id` of the fruit to delete
- Returns: Confirmation message

### 4. Get a fruit by ID

```http
GET /fruit/getOne/{id}
```

- Parameter: `id` of the fruit
- Returns: Fruit details

### 5. Get all fruits

```http
GET /fruit/getAll
```

- Returns: List of all fruits

## Error Handling

The application implements a `GlobalExceptionHandler` to handle exceptions centrally.

- If the fruit ID does not exist, it returns `404 Not Found`.
- If the request is incorrect, it returns `400 Bad Request`.
- Internal server errors are handled with `500 Internal Server Error`.

## Testing with Postman

### Environment Setup

1. Create an environment named "Project Maven"
2. Configure variables:
   - Server: `http://localhost`
   - Port: `8080`

### Test Endpoints

1. Add a fruit:
   - `{{Server}}:{{Port}}/fruit/add`
2. Update a fruit:
   - `{{Server}}:{{Port}}/fruit/update`
3. Delete a fruit:
   - `{{Server}}:{{Port}}/fruit/delete/{id}`
4. Get a fruit by ID:
   - `{{Server}}:{{Port}}/fruit/getOne/{id}`
5. Get all fruits:
   - `{{Server}}:{{Port}}/fruit/getAll`
