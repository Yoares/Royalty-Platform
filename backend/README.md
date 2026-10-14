# Royalty Split API - Architecture & Documentation

## Overview
The Royalty Split API is a Spring Boot application designed to log recording sessions, track song releases, and manage royalty split percentages between primary artists and producers.

## Tech Stack
*   **Language:** Java 21 (LTS)
*   **Framework:** Spring Boot 3.x
*   **Database:** PostgreSQL 17 (via Docker)
*   **ORM:** Spring Data JPA / Hibernate
*   **Build Tool:** Maven

## System Architecture
The application follows a standard **N-Tier (Layered) Architecture**, which separates concerns into distinct packages:

```text
HTTP Request 
   │
   ▼
[ Controller Layer ]  --> Handles HTTP routing, JSON parsing, and HTTP status codes.
   │
   ▼
[ Service Layer ]     --> (Planned) Handles business logic, mathematical royalty calculations.
   │
   ▼
[ Repository Layer ]  --> Interfaces with the database using Spring Data JPA.
   │
   ▼
[ Database ]          --> PostgreSQL container.
```

## Project Directory Structure
```text
src/main/java/com/example/demo/
├── DemoApplication.java          # Main entry point
├── model/                        # Domain entities
│   └── Track.java
├── repository/                   # Data access interfaces
│   └── TrackRepository.java
└── controller/                   # REST API endpoints
    └── TrackController.java
```

## Domain Model: `Track`
Maps to the `track` table in PostgreSQL.

| Field | Type | Description | constraints |
| :--- | :--- | :--- | :--- |
| `id` | `Long` | Primary Key | Auto-incremented |
| `title` | `String` | Name of the track | |
| `primaryArtist` | `String` | Main artist name | |
| `producerSplitPercentage`| `Double` | Producer's cut (0.0 to 100.0) | |

## API Endpoints Specification

### 1. Create a Track
*   **Endpoint:** `POST /api/tracks`
*   **Payload:**
    ```json
    {
      "title": "Song Name",
      "primaryArtist": "Artist Name",
      "producerSplitPercentage": 15.0
    }
    ```
*   **Response:** `200 OK` with the created database record (including the generated ID).

### 2. Get All Tracks
*   **Endpoint:** `GET /api/tracks`
*   **Response:** `200 OK` with a JSON array of all tracks.