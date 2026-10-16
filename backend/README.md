# Royalty-API (Backend Service)

The core calculation and data persistence engine for the Royalty Platform. It exposes a RESTful API to manage track metadata and dynamically calculate producer payouts based on relational PostgreSQL data.

## 🛠️ Tech Stack
* **Language:** Java 21
* **Framework:** Spring Boot 3.3.4
* **Database:** PostgreSQL 15 (JPA / Hibernate)
* **Infrastructure:** Docker, Docker Compose, Maven

## ⚙️ Architecture
The codebase strictly follows the Controller-Service-Repository pattern:
* **Controllers:** Handle HTTP routing, CORS configuration (`@CrossOrigin`), and DTO mapping.
* **Services:** Contain the core business logic (fetching splits and calculating revenue payouts).
* **Repositories:** Spring Data JPA interfaces handling PostgreSQL transactions.
* **Exception Handling:** Global `@RestControllerAdvice` intercepts validation failures and returns sanitized HTTP 400 JSON arrays.

## 📡 API Specification

### 1. Get All Tracks
Retrieves the complete catalog of registered tracks.
* **Request:** `GET /api/tracks`
* **Response:** `200 OK` (Array of Track Objects)

### 2. Register a New Track
Validates and saves track metadata and producer split percentages.
* **Request:** `POST /api/tracks`
* **Body:**
```json
{
  "title": "SURFER BOY", 
  "primaryArtist": "ARES.thereal", 
  "producerSplitPercentage": 50.0
}
Response: 200 OK (Returns the saved object with database id)

3. Calculate Royalty Payout
Calculates the exact producer payout based on total streaming revenue and the saved database split.

Request: GET /api/tracks/{id}/payout?revenue={amount}

Example: GET /api/tracks/3/payout?revenue=9000

Response: 200 OK

JSON
{
  "Total Revenue": "$9000.00",
  "Producer Payout": "$2250.00"
}
```

## 🐳 Container Management
To view real-time logs of the API container:

``` docker-compose logs -f api ```

To shut down the database and API networks:

``` docker-compose down ```