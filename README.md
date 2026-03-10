# futures-tracker
Portfolio project. A Futures trading tracker/journal that allows the end user to upload csv files of their trading transaction and see account details like P&amp;L, Win %, P%L per day, and more.

# Futures Tracker

A backend REST API for uploading and tracking futures trades using CSV exports from brokerage platforms.

This project allows users to upload trade history, store it in a database, and retrieve trade data through API endpoints.

---

## Features

- Upload CSV trade history
- Automatic CSV parsing
- Trade data storage in PostgreSQL
- REST API for retrieving trade records
- Date/time parsing for trade events
- Clean backend architecture using service and repository layers

---

## Tech Stack

Backend:
- Java
- Spring Boot
- Maven

Database:
- PostgreSQL
- Flyway database migrations

Tools:
- Docker
- Postman
- Git / GitHub

---

## Project Architecture

Controller -> Service -> Repository -> Database


### Controller
Handles REST API endpoints.

### Service
Contains business logic and CSV parsing.

### Repository
Handles database interaction using Spring Data JPA.

### Database
PostgreSQL database managed with Flyway migrations.

---

## API Endpoints

### Upload Trades

POST /api/trades/upload

Uploads a CSV file containing trade data.

Example usage with Postman:

Body → form-data

Key: `file`  
Value: upload CSV file

---

### Get All Trades

GET /api/trades

Returns all stored trade records.

Example Response:

```json
[
{
"id": 1,
"name": "Micro WTI Crude Oil APR 26",
"symbol": "MCLJ6",
"side": "Sell",
"status": "Filled",
"placedTime": "2026-02-27T12:26:12"
}
]
```

## Running the project

### Start PostgreSQL with Docker
docker run --name futures-db \
-e POSTGRES_DB=futures \
-e POSTGRES_USER=futuresuser \
-e POSTGRES_PASSWORD=futurespass \
-p 5432:5432 \
-d postgres:16

### Start the application

mvn spring-boot:run

Server runs on: "http://localhost:8080" (This will change)

### Example Workflow

1 Upload trade CSV through API \
2 CSV data is parsed \
3 Trades are stored in PostgreSQL \
4 Data can be retrieved via REST endpoints

### Future Improvements

- Trade profit/loss calculation
- Trade performance analytics
- Trade filtering by symbol and date
- Frontend dashboard for trade visualization
- Deployment with Docker Compose

## Author
Benjamin Roberson
Computer Science student focused on backend development, systems, and financial technology tools.
