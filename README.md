
## Database Configuration
Please find the database schema at db_schema.sql
Set up PostgreSQL and configure the connection in `application.properties`.

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/library
spring.datasource.username=yourUsername
spring.datasource.password=yourPassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```
## Project Structure

```plaintext
src/
├── main/
│   └── java/
│       └── library/
│           ├── controller/
│           │   └── LibraryController.java
│           ├── dto/
│           │   └── BorrowRequest.java
│           ├── exception/
│           │   └── CustomExceptionHandler.java
│           ├── model/
│           │   ├── Book.java
│           │   └── Borrower.java
│           ├── repo/
│           │   ├── BookRepository.java
│           │   └── BorrowerRepository.java
│           ├── service/
│           │   └── LibraryService.java
│           └── validation/
│               └── AlphabeticValidator.java
└── LibraryApplication.java
```

## API Endpoints

For seek of easy testing, please find the postman json collection (library.postman_collection.json).

- **POST** `/api/library/borrowers`
  - **Description**: Register a new borrower.
  - **Request Body**:
    ```json
    {
      "name": "John Doe",
      "email": "john.doe@example.com"
    }
    ```
  - **Response**: `201 Created` on success.

- **POST** `/api/library/books`
  - **Description**: Register a new book.
  - **Request Body**:
    ```json
    {
      "isbn": "978-3-16-148410-0",
      "title": "Sample Book",
      "author": "Author Name"
    }
    ```
  - **Response**: `201 Created` on success.

- **GET** `/api/library/books`
  - **Description**: Get a list of all books.
  - **Response**: `200 OK` with list of books.

- **POST** `/api/library/borrow`
  - **Description**: Borrow a book.
  - **Request Body**:
    ```json
    {
      "borrowerId": 1,
      "bookId": 101
    }
    ```
  - **Response**: `200 OK` with success message or `400 Bad Request` on failure.

- **POST** `/api/library/return`
  - **Description**: Return a borrowed book.
  - **Request Body**:
    ```json
    {
      "borrowerId": 1,
      "bookId": 101
    }
    ```
  - **Response**: `200 OK` with success message or `400 Bad Request` on failure.

  ## Run In Docker
  This docker file and docker-compose.yml in the dokcer folder will build the images and copy the library.jar, to build the docker images Run docker compose build to build the image.


  
