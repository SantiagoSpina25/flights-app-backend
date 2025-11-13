# ✈️ Flights App Backend

🌐 Available Languages:  
[English](README.md) | [Español](README.es.md)

**Flights App Backend** is a REST API built with **Spring Boot** that manages information about flights, airlines, airports, seats, and users.  
It is part of the **Flights App** project, a complete platform for managing flight reservations.

---

## 🚀 Technologies Used

- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA (Hibernate)**
- **Spring Security**
- **MySQL** (Connector/J)
- **Maven**
- **JWT (io.jsonwebtoken - jjwt)**
- **Lombok**
- **BCryptPasswordEncoder**
---
## ✨ Features & Functionality

### 🔐 Security & Authentication
- Secure authentication using **JWT**
- User roles: **Administrator** and **User**
- Protected routes based on authorization

### 🛫 Flight & Airline Management
- Full CRUD for **flights**, **users**, **airlines**, and **seats**
- Manual generation of **random seats** for a flight
- **Real-time seat reservation**
- **Automatic price calculation** based on seat class and the **distance** between origin and destination airports

### 💳 User Operations
- Users can **add balance** to their accounts
- **Seat purchasing** based on availability and class

### 🧭 Navigation & User Experience
- Clear and intuitive API behavior
- Custom **access denied** and **error** responses
- Detailed validation and error messages for each operation

---

## 📦 Dependencies

Main dependencies included in `pom.xml`:

- `spring-boot-starter-data-jpa` — Integration with Spring Data JPA and Hibernate for persistence.
- `spring-boot-starter-web` — Support for building web and REST applications (Spring MVC, embedded Tomcat).
- `spring-boot-devtools` — Development tools (auto-restart, live reload). (scope: runtime)
- `mysql-connector-j` — JDBC connector for MySQL. (scope: runtime) 
- `spring-boot-starter-test` — Testing dependencies for Spring. (scope: test)  
- `spring-boot-starter-validation` — Validation support using `javax.validation` / `jakarta.validation`.
- `io.jsonwebtoken:jjwt-api`, `jjwt-impl`, `jjwt-jackson` — JWT library used for authentication/authorization. 
- `spring-boot-starter-security` — Spring Security for securing the API.
- `lombok` — Reduces boilerplate code (getters/setters, constructors, builder, etc.). (annotationProcessor)

>Note: The pom.xml also includes the `spring-boot-maven-plugin`, used for packaging the project and running it with `mvn spring-boot:run` or `mvn package`.

---

## 🧱 Project Structure
The backend is structured into layers to maintain clean and modular code:
```
src/main/java/com/santiago/flightsapp/flights_app
│
├── controllers/
├── dto/
├── entities/
├── exceptions/
├── repositories/
├── security/
├── services/
├── utils/
└── FlightsAppApplication.java
```
---
## 🤝 Frontend Integration

If you want a smoother and more intuitive user experience, I invite you to use the frontend part of the project, where you can access all the features in a much more visual way.

* Frontend repository: [flights-app frontend](https://github.com/SantiagoSpina25/flights-app-frontend)

* Both projects must share the same **CORS configuration** and **base URL**.
---
## 🗃️ Entities

| Entity | Description |
|--------|-------------|
| User | Represents a system user |
| Flight | Flight information |
| Seat | Seats for each flight |
| Airline | Represents an airline |
| Airport | Origin/destination airports |
| Country / Continent | Geographic references |

---
## ⚙️ Database Configuration

Configure your `application.properties` file with your MySQL credentials.

Recommended minimal example:

```
spring.datasource.url=jdbc:mysql://localhost:3306/flightsdb
spring.datasource.username=your_user
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

server.port=8080
```

---
## 🧩 Running the Project

1. Clone the repository:
   ```bash
   git clone https://github.com/SantiagoSpina25/flights-app-backend.git
   ```
2. Open the proyect in **Visual Studio Code** (or your favorite IDE).
3. Run the application from the **Run Spring Boot** button or via terminal:
   ```bash
   mvn spring-boot:run
   ```
   or package and run the jar::
   ```bash
   mvn package
   java -jar target/flights-app-1.0.0.jar
   ```
4. The API will run by default in:
   ```
   http://localhost:8080
   ```
---

## 🌐 Endpoints
>⚠️ Note: All endpoints must start with the `/api` prefix (except login).
Example: to list all users, the full route would be`/api/users`.
---
### 👤 Users (`UserController`)
| Method | Endpoint | Description |
|---------|-----------|-------------|
| `GET` | `/users` | Lists all users |
| `GET` | `/users/{id}` | Retrieves a user by ID |
| `POST` | `/users/register` | Registers a new user |
| `POST` | `/login` |  Performs login (username and password) |
| `POST` | `/users/addBalance` | Adds balance to a user |
| `DELETE` | `/users/{id}` | Deletes a user |

**Body (POST /users/register)**
```json
{
    "username": "admin",
    "password": "admin",
    "admin": true //Optional (default: false)
}
```

**Body (POST /login)**
```json
{
    "username": "admin",
    "password": "admin"
}
```

**Body (POST /users/addBalance)**
```json
{
    "id": "1",
    "balance": 1000
}
```
---
### 👤 Airlines (`AirlineController`)
| Method | Endpoint | Description |
|---------|-----------|-------------|
| `GET` | `/airlines` | Lists all airlines |
| `GET` | `/airlines/{id}` | Retrieves an airline by ID |
| `POST` | `/airlines` | Creates a new airline |
| `DELETE` | `/airlines/{id}` | Deletes an airline |

**Body (POST /airlines)**
```json
{
    "name": "Air France",
    "description": "National airline of France, known for its service and global network"
}
```
---
### ✈️ Flights (`FlightController`)
| Method | Endpoint | Description |
|---------|-----------|-------------|
| `GET` | `/flights` | Lists all flights |
| `GET` | `/flights/{id}` | Retrieves a flight by ID |
| `POST` | `/flights` | Creates a new flight |
| `POST` | `/flights/createSeats` | Creates a random number of seats for the specified flight |
| `DELETE` | `/flights/{id}` | Deletes a flight |

**Body (POST /flights)**
```json
{
    "id": "LUF111",
    "date": "2025-11-11", //Optional (default: system date)
    "hour": "20:02:47", //Optional (default: system time)
    "originAirportId": 10,
    "destinationAirportId": 11,
    "airlineId": 3
}
```

**Body (POST /flights/createSeats)**
```json
{
    "flightId": "AFR010",
    "numberOfSeats": 5
}
```
---
### 💺 Seats (`SeatController`)
| Method | Endpoint | Description |
|---------|-----------|-------------|
| `GET` | `/seats` | Lists all seats |
| `GET` | `/seats/{id}` | Retrieves a seat by ID |
| `POST` | `/seats` | Creates a new seat |
| `POST` | `/seats/book` | Books a seat on a flight for a user |
| `DELETE` | `/seats/{id}` | Deletes a seat |

**Body (POST /seats)**
```json
{ 
    "number": "111A",
    "classType": "FIRST_CLASS", //Optional (default: ECONOMY)
    "status": "AVAILABLE", //Optional (default: AVAILABLE)
    "flightId": "EMI001"
}
```

**Body (POST /seats/book)**
```json
{
    "flightId": "AFR010",
    "numberOfSeats": 5
}
```
---
### 💺 Airports (`AirportController`)
| Method | Endpoint | Description |
|---------|-----------|-------------|
| `GET` | `/airports` | Lists all airports |
| `GET` | `/airports/{id}` | Retrieves an airport by ID |

---

## 🧠 Request Example

**Book a seat on a flight**
```http
POST /seats/book
Content-Type: application/json

{
  "userId": 1,
  "seatId": 40
}
```

**Response (example)**
```json
{
    "id": 40,
    "number": "16B",
    "status": "SOLD",
    "classType": "ECONOMY",
    "price": 1600.0,
    "flightId": "EMI001"
}
```

## 🔐 Security — Spring Security + JWT

The API is secured with Spring Security and requires a Bearer token (JWT) to access almost all endpoints. Only authentication routes are public:

* `POST /login` → to obtain the JWT (authentication).

* `POST /api/users/register` → to register a new user.

⚠️ All other routes require the `Authorization: Bearer <token>` header.

**Login example**
```json
POST /login

{
  "username": "admin",
  "password": "admin"
}
```

**Expected response (successful login)**
```json
{
    "message": "successful login",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTc2Mjg5MDU3OSwiYXV0aG9yaXRpZXMiOiJbe1wiYXV0aG9yaXR5XCI6XCJST0xFX0FETUlOXCJ9LHtcImF1dGhvcml0eVwiOlwiUk9MRV9VU0VSXCJ9XSIsImlkIjoxLCJpYXQiOjE3NjI4ODY5Nzl9.wcBZTV1cO3TfNxviVw63Nt2oSQm0_G2tlR8xEuZgz_Y",
    "username": "admin"
}
```

**Expected response (login error)**
```json
{
    "message": "Login error. Incorrect username or password",
    "error": "Bad credentials"
}
```

### Using the token in protected requests

Once the token is obtained, it must be included in the Authorization header of each protected request:

```http
GET /api/flights
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4...

```

**Expected response in case of error**
```json
{
    "error": "Invalid JWT",
    "message": "JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted."
}
```

---

## 👨‍💻 Author

**Developed by [Santiago Spina](https://github.com/SantiagoSpina25)**   
💡 Personal project to practice full-stack development with Spring Boot + React.

---

## 🛡️ License

This project is distributed under the **MIT** license.  
You are free to use, modify, and share it.
