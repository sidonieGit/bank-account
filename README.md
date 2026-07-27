# Bank Account API

A RESTful CRUD API for managing bank accounts, built with Spring Boot 4.1 and MongoDB.

---

## Tech Stack

| Layer | Technology |
|---|---|
| Runtime | Java 21 |
| Framework | Spring Boot 4.1 |
| Database | MongoDB 7.0 |
| Mapping | MapStruct 1.6 |
| Docs | SpringDoc OpenAPI (Swagger UI + Scalar) |
| Containerization | Docker + Docker Compose |

---

## Project Structure

```
src/main/java/com/example/bank_account/
├── account/
│   ├── api/            # Controllers and request/response DTOs
│   ├── application/    # Service interface, implementation, MapStruct mapper
│   ├── domain/         # Document entity, enums, domain exceptions
│   └── infrastructure/ # Repository, seeder, index config
└── shared/
    └── error/          # Global exception handler and error DTO
```

---

## API Reference

**Base URL:** `http://localhost:8080/api/v1/accounts`

### Endpoints

| Method | Path | Description | Status Codes |
|---|---|---|---|
| `POST` | `/` | Create a new account | 201, 400, 409 |
| `GET` | `/` | List accounts (filterable, paginated) | 200 |
| `GET` | `/{id}` | Get account by ID | 200, 404 |
| `PUT` | `/{id}` | Full update | 200, 400, 404 |
| `PATCH` | `/{id}` | Partial update (status change) | 200, 404 |
| `DELETE` | `/{id}` | Soft-delete (sets status to `CLOSED`) | 204, 404 |

### Create Account — `POST /`

```json
{
  "accountNumber": "FR1234567890",
  "ownerName": "Jane Doe",
  "balance": 1500.00,
  "currency": "EURO",
  "accountType": "SAVINGS"
}
```

`accountNumber` must match `^[A-Z]{2}[0-9]{10,16}$`.

### List Accounts — `GET /?status=ACTIVE&accountType=SAVINGS&page=0&size=10`

Query parameters:

| Param | Values | Required |
|---|---|---|
| `status` | `ACTIVE`, `SUSPENDED`, `CLOSED` | No |
| `accountType` | `CHECKING`, `SAVINGS` | No |
| `page` | integer (0-based) | No |
| `size` | integer | No |

### Account Response

```json
{
  "id": "64b1f...",
  "accountNumber": "FR1234567890",
  "ownerName": "Jane Doe",
  "balance": 1500.00,
  "currency": "EURO",
  "accountType": "SAVINGS",
  "status": "ACTIVE",
  "createdAt": "2024-01-15T10:30:00Z",
  "updatedAt": "2024-01-15T10:30:00Z"
}
```

### Enums

| Enum | Values |
|---|---|
| `AccountStatus` | `ACTIVE`, `SUSPENDED`, `CLOSED` |
| `AccountType` | `CHECKING`, `SAVINGS` |
| `Currency` | `EURO`, `USD`, `GBP` |

---

## API Documentation

Once the app is running, interactive docs are available at:

- **Swagger UI:** `http://localhost:8080/swagger-ui.html`
- **Scalar UI:** `http://localhost:8080/scalar-ui.html`

---

## Running the Project

### With Docker Compose (recommended)

```bash
docker compose up --build
```

Services started:

| Service | URL |
|---|---|
| Spring Boot API | http://localhost:8080 |
| Mongo Express (DB UI) | http://localhost:8081 (admin / admin) |
| MongoDB | localhost:27017 |

The database is seeded automatically with 20 sample accounts on first startup.

### Locally (without Docker)

Prerequisites: Java 21, Maven, MongoDB running on `localhost:27017`.

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

---

## Configuration Profiles

| Profile | MongoDB URI | Auto-index | Use case |
|---|---|---|---|
| `local` | `mongodb://localhost:27017/bank_account` | true | Local development |
| `docker` | `mongodb://mongo:27017/bank_account` | true | Docker Compose |
| `prod` | `$SPRING_DATA_MONGO_URI` (required) | false | Production |

Override the URI at runtime:

```bash
SPRING_DATA_MONGO_URI=mongodb://user:pass@host:27017/bank_account
```

---

## Health Check

```
GET http://localhost:8080/actuator/health
```

---

## Building the JAR

```bash
./mvnw package -DskipTests
```

Output: `target/bank-account-0.0.1-SNAPSHOT.jar`

---

## Docker Details

The image uses a two-stage build:

1. **Build stage** — `maven:3.9-eclipse-temurin-21-alpine` compiles and packages the JAR.
2. **Runtime stage** — `eclipse-temurin:21-jre-alpine` runs the JAR as a non-root user (`appuser`).

Build the image manually:

```bash
docker build -t bank-account .
```
