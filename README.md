# Bajaj Finserv Health API – Acropolis Campus Hiring (May 2026)

REST API that processes an input data array and returns classified results.

## Tech Stack

- **Java 17** + **Spring Boot 3.2.5**
- Maven build system
- Docker support

## API Endpoints

| Method | Route  | Description                        |
|--------|--------|------------------------------------|
| POST   | `/api` | Process data array, returns result |
| GET    | `/api` | Returns `{ "operation_code": 1 }`  |

### POST /api – Request

```json
{
  "data": ["A", "1", "334", "4", "R"]
}
```

### POST /api – Response

```json
{
  "is_success": true,
  "user_id": "neha_gupta_22032006",
  "email": "nehagupta230802@acropolis.in",
  "roll_number": "0827IT231091",
  "numbers": ["1", "334", "4"],
  "alphabets": ["A", "R"],
  "highest_lowercase_alphabet": ["r"],
  "even_numbers": ["334", "4"],
  "odd_numbers": ["1"],
  "special_characters": [],
  "sum_of_numbers": 339,
  "alternating_case_concat": "Ra"
}
```

## Run Locally

```bash
# Build & run
mvn spring-boot:run

# Run tests
mvn test
```

## Deploy

### Render (recommended)

1. Push this repo to GitHub
2. Create a **New Web Service** on [render.com](https://render.com)
3. Connect your GitHub repo
4. Render auto-detects Java and builds with Maven
5. Set environment: **Docker** or **Native** runtime
6. The app reads `PORT` from environment automatically

### Railway

1. Push to GitHub
2. Create a new project on [railway.app](https://railway.app)
3. Connect your repo – Railway auto-detects the Dockerfile
4. Deploy!

### Docker (any provider)

```bash
docker build -t bajaj-api .
docker run -p 8080:8080 bajaj-api
```
