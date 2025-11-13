# ✈️ Flights App Backend

**Flights App Backend** es una API REST desarrollada con **Spring Boot** que gestiona información de vuelos, aerolíneas, aeropuertos, asientos y usuarios.  
Forma parte del proyecto **Flights App**, una aplicación completa para la gestión de reservas de vuelos.

---

## 🚀 Tecnologías utilizadas

- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA (Hibernate)**
- **Spring Security**
- **MySQL** (Connector/J)
- **Maven** (Build tool)
- **JWT (io.jsonwebtoken - jjwt)**
- **Lombok**
- **BCryptPasswordEncoder**

---

## 📦 Dependencias

A continuación las dependencias principales incluidas en el `pom.xml` del proyecto y una breve descripción de su uso:

- `spring-boot-starter-data-jpa` — Integración con Spring Data JPA y Hibernate para persistencia.  
- `spring-boot-starter-web` — Soporte para construir aplicaciones web y REST (Spring MVC, Tomcat embebido).  
- `spring-boot-devtools` — Herramientas para desarrollo (auto-restart, live reload). (scope: runtime)  
- `mysql-connector-j` — Connector JDBC para MySQL. (scope: runtime)  
- `spring-boot-starter-test` — Dependencias para testing con Spring (scope: test).  
- `spring-boot-starter-validation` — Soporte para validación con `javax.validation` / `jakarta.validation`.  
- `io.jsonwebtoken:jjwt-api`, `jjwt-impl`, `jjwt-jackson` — Biblioteca JWT utilizada para autenticación/autorization.  
- `spring-boot-starter-security` — Spring Security para asegurar la API.  
- `lombok` — Reducción de boilerplate (getters/setters, constructores, builder, etc.) (annotationProcessor).

> **Nota:** El `pom.xml` también incluye el plugin `spring-boot-maven-plugin` para empaquetado y ejecución del proyecto con `mvn spring-boot:run` o `mvn package`.

---

## 🧱 Arquitectura del proyecto

El backend está estructurado en capas para mantener un código limpio y modular:

```
src/main/java/com/santiago/flightsapp/flights_app
│
├── controllers/      # Controladores REST
├── dto/              # Objetos de transferencia de datos
├── entities/         # Entidades JPA (tablas de la base de datos)
├── exceptions/       # Manejo de errores y respuestas
├── repositories/     # Repositorios Spring Data JPA
├── security/         # Configuración y utilidades de seguridad (JWT, filtros)
├── services/         # Lógica de negocio (servicios)
├── utils/            # Utilidades y helpers reutilizables
└── FlightsAppApplication.java  # Clase principal de la aplicación

```

---

## 🗃️ Entidades

| Entidad | Descripción |
|----------|-------------|
| **User** | Representa a un usuario del sistema. |
| **Flight** | Contiene información de un vuelo (origen, destino, fecha, etc.). |
| **Seat** | Define los asientos en un vuelo con su precio y disponibilidad.|
| **Airline** | Representa una aerolínea. |
| **Airport** | Define los aeropuertos de origen y destino. |
| **Country / Continent** | Referencias geográficas asociadas a los aeropuertos. |

---

## ⚙️ Configuración de la base de datos

Configura tu archivo `application.properties` con tus credenciales de MySQL.

Ejemplo mínimo recomendado:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/flightsdb
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
server.port=8080
```

---

## 🧩 Ejecución del proyecto

1. Clona el repositorio:
   ```bash
   git clone https://github.com/SantiagoSpina25/flights-app-backend.git
   ```
2. Abre el proyecto en **Visual Studio Code** (o tu IDE preferido).
3. Ejecuta la aplicación desde el botón de **Run Spring Boot** o mediante terminal:
   ```bash
   mvn spring-boot:run
   ```
   o empaqueta y ejecuta el jar:
   ```bash
   mvn package
   java -jar target/flights-app-1.0.0.jar
   ```
4. La API se ejecutará por defecto en:
   ```
   http://localhost:8080
   ```

---

## 🌐 Endpoints
>⚠️ Nota: Todos los endpoints deben comenzar con el prefijo `/api` (excepto el login).
Ejemplo: para listar todos los usuarios, la ruta completa sería `/api/users`.
---
### 👤 Usuarios (`UserController`)
| Método | Endpoint | Descripción |
|---------|-----------|-------------|
| `GET` | `/users` | Lista todos los usuarios |
| `GET` | `/users/{id}` | Lista un usuario por ID |
| `POST` | `/users/register` | Registra un nuevo usuario |
| `POST` | `/login` |  Realiza el login (username y password) |
| `POST` | `/users/addBalance` | Agrega saldo a un usuario |
| `DELETE` | `/users/{id}` | Elimina un usuario |

**Body (POST /users/register)**
```json
{
    "username": "admin",
    "password": "admin",
    "admin": true //Opcional (por defecto false)
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
### 👤 Aerolineas (`AirlineController`)
| Método | Endpoint | Descripción |
|---------|-----------|-------------|
| `GET` | `/airlines` | Lista todos las aerolineas |
| `GET` | `/airlines/{id}` | Lista una aerolinea por ID |
| `POST` | `/airlines` | Registra una nueva aerolinea |
| `DELETE` | `/airlines/{id}` | Elimina una aerolinea |

**Body (POST /airlines)**
```json
{
    "name": "Air France",
    "description": "Aerolínea nacional de Francia, reconocida por su servicio y red global"
}
```
---
### ✈️ Vuelos (`FlightController`)
| Método | Endpoint | Descripción |
|---------|-----------|-------------|
| `GET` | `/flights` | Obtiene todos los vuelos |
| `GET` | `/flights/{id}` | Obtiene un vuelo por ID |
| `POST` | `/flights` | Crea un nuevo vuelo |
| `POST` | `/flights/createSeats` | Crea un numero de asientos aleatorios para el vuelo indicado |
| `DELETE` | `/flights/{id}` | Elimina un vuelo |

**Body (POST /flights)**
```json
{
    "id": "LUF111",
    "date": "2025-11-11", //Opcional (por defecto la fecha del sistema)
    "hour": "20:02:47", //Opcional (por defecto la hora del sistema)
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
### 💺 Asientos (`SeatController`)
| Método | Endpoint | Descripción |
|---------|-----------|-------------|
| `GET` | `/seats` | Lista todos los asientos |
| `GET` | `/seats/{id}` | Obtiene un asiento por ID |
| `POST` | `/seats` | Crea un nuevo asiento |
| `POST` | `/seats/book` | Reserva un asiento en un vuelo para un usuario |
| `DELETE` | `/seats/{id}` | Elimina un asiento |

**Body (POST /seats)**
```json
{ 
    "number": "111A",
    "classType": "FIRST_CLASS", //Opcional (por defecto ECONOMY)
    "status": "AVAILABLE", //Opcional (por defecto: AVAILABLE)
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
### 💺 Aeropuertos (`AirportController`)
| Método | Endpoint | Descripción |
|---------|-----------|-------------|
| `GET` | `/airports` | Lista todos los aeropuertos |
| `GET` | `/airports/{id}` | Obtiene un aeropuerto por ID |

---

## 🧠 Ejemplo de petición

**Reservar un asiento en un vuelo**
```http
POST /seats/book
Content-Type: application/json

{
  "userId": 1,
  "seatId": 40
}
```

**Respuesta (ejemplo)**
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

## 🔐 Seguridad — Spring Security + JWT

La API está protegida con Spring Security y requiere un Bearer token (JWT) para acceder a casi todos los endpoints. Sólo las rutas de autenticación están abiertas:

* `POST /login` → para obtener el JWT (autenticación).

* `POST /api/users/register` → para registrar un nuevo usuario.

⚠️ Todas las demás rutas requieren el header `Authorization: Bearer <token>`.

**Ejemplo de login**
```json
POST /login

{
  "username": "admin",
  "password": "admin"
}
```

**Respuesta esperada (login exitoso)**
```json
{
    "message": "inicio de sesion exitoso",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTc2Mjg5MDU3OSwiYXV0aG9yaXRpZXMiOiJbe1wiYXV0aG9yaXR5XCI6XCJST0xFX0FETUlOXCJ9LHtcImF1dGhvcml0eVwiOlwiUk9MRV9VU0VSXCJ9XSIsImlkIjoxLCJpYXQiOjE3NjI4ODY5Nzl9.wcBZTV1cO3TfNxviVw63Nt2oSQm0_G2tlR8xEuZgz_Y",
    "username": "admin"
}
```

**Respuesta esperada (error en el login)**
```json
{
    "message": "Error en el inicio de sesion. Username o password incorrectos",
    "error": "Bad credentials"
}
```

### Uso del token en las peticiones protegidas

Una vez obtenido el token, debe incluirse en el encabezado Authorization de cada petición protegida:

```http
GET /api/flights
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4...

```

**Respuesta esperada en el caso de error**
```json
{
    "error": "Invalid JWT",
    "message": "JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted."
}
```

---

## 👨‍💻 Autor

**Desarrollado por [Santiago Spina](https://github.com/SantiagoSpina25)**   
💡 Proyecto personal para practicar desarrollo fullstack con Spring Boot + React.

---

## 🛡️ Licencia

Este proyecto se distribuye bajo la licencia **MIT**.  
Puedes usarlo, modificarlo y compartirlo libremente.
