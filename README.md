# Royalty Platform

A full-stack platform designed to automate and manage digital streaming royalty splits for independent recording artists and producers. This monorepo contains both the Java-based backend API engine and the decoupled vanilla JavaScript client interface.

## 🚀 Vision
Independent music distribution requires transparent, accurate, and automated revenue splitting. This platform strips away manual spreadsheet calculations. By inputting total track revenue via the frontend dashboard, the system queries the backend database for agreed-upon producer splits and calculates exact payout figures, ensuring all collaborators are compensated accurately.

## 🏗️ Architecture (Monorepo)
This project is structured as a decoupled full-stack application:
* **`/backend`**: A headless REST API built with Java 21, Spring Boot 3.3.4, and PostgreSQL. Containerized via Docker.
* **`/frontend`**: A lightweight, zero-dependency Single Page Application (SPA) built with Vanilla HTML/JS and the Fetch API.

## ⚙️ Quick Start

### 1. Start the Backend Engine (Docker)
You only need Docker installed to run the API and Database.

The API will be available at `http://localhost:8080`.

### 2. Launch the Frontend Client
Because the client is built with Vanilla HTML/JS, there is no build step or node package installation required.
1. Navigate to the `frontend/` directory.
2. Open `index.html` in any modern web browser.

## 📡 API Reference
* `POST /api/tracks` - Registers new track metadata and percentage splits.
* `GET /api/tracks/{id}/payout?revenue={amount}` - Calculates the dynamic producer payout.

## 🛡️ Validation & Security
The API includes strict data validation using `Jakarta Validation` (preventing negative splits or empty titles) and a custom `@RestControllerAdvice` for clean error handling. Cross-Origin Resource Sharing (CORS) is configured to allow secure communication between the browser client and the containerized API.