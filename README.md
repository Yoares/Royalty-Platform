# Royalty Platform (Full-Stack Monorepo)

A decoupled, full-stack application designed to automate and manage digital streaming royalty splits for independent recording artists and producers. 

This monorepo contains both the headless Java/Spring Boot backend engine and the reactive Vanilla JavaScript frontend client.

## 🏗️ Architecture overview

The platform utilizes a strict separation of concerns, communicating securely via REST and CORS:

* **`/backend`**: A headless REST API built with Java 21, Spring Boot 3.3.4, and PostgreSQL. Containerized via Docker for reliable deployment. [Read the Backend Docs](./backend/README.md)
* **`/frontend`**: A lightweight Single Page Application (SPA) built with Vanilla HTML/JS and the Fetch API for zero-dependency client-side rendering. [Read the Frontend Docs](./frontend/README.md)
* **`USER_GUIDE.md`**: A step-by-step manual on how to use the dashboard workflow.

## 🚀 Quick Start

To spin up the entire platform locally, you only need Docker and a web browser.

### 1. Boot the Backend Services
Navigate to the backend directory and spin up the API and PostgreSQL containers:
bash
cd backend
docker-compose up -d --build

The API will boot and listen on `http://localhost:8080`.

### 2. Launch the Client Dashboard
Because the client is pure Vanilla JS/HTML, there is no Node.js build step.
1. Navigate to the `frontend/` directory.
2. Open `index.html` directly in any modern web browser.
3. The UI will immediately connect to the Docker network.