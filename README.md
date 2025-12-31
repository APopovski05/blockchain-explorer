# Blockchain Explorer (Angular + Spring Boot)

A multi-page full-stack Blockchain Explorer web application built with Angular (standalone)
for the frontend and Spring Boot for the backend.

The application allows users to browse blocks, view block details and related transactions,
search transactions, and maintain a local watchlist of smart contract addresses.

---

## Features

### Frontend (Angular)
- Dashboard showing the latest block
- Blocks page with sortable list of blocks
- Block Details page (/blocks/:blockNumber)
- Transactions search page:
  - All transactions
  - Search by transaction hash
  - Search by block number
  - Search by address (from/to)
- Watchlist page:
  - Add/remove watched contract addresses
  - Data persisted in browser localStorage
- Client-side routing between all pages
- Responsive dark-themed UI

### Backend (Spring Boot API)
- GET /api/blocks/latest
- GET /api/blocks
- GET /api/blocks/{blockNumber}
- GET /api/blocks/{blockNumber}/transactions
- GET /api/transactions
- GET /api/transactions/{txHash}
- GET /api/transactions?blockNumber={number}
- GET /api/transactions?address={address}

All API endpoints are protected with an API key header.

---

## Project Structure

Internet Programming Project
- Backend/
  - blockchain-explorer-api (Spring Boot application)
- Frontend/
  - blockchain-explorer-web (Angular application)

---


## How to Run the Project Locally

### Backend (Spring Boot)

Open a terminal and navigate to the backend project:

cd Backend/blockchain-explorer-api

Run the application:

./mvnw spring-boot:run

The backend will run on:
http://localhost:8080

---

### Frontend (Angular)

Open a terminal and navigate to the frontend project:

cd Frontend/blockchain-explorer-web

Install dependencies:

npm install

Run the Angular app:

ng serve

The frontend will run on:
http://localhost:4200

---

## API Key Configuration

All backend endpoints require the following HTTP header:

X-API-Key: blockchain-explorer-2025-secure-key

The Angular frontend includes an HTTP interceptor that automatically attaches this header.

---

## How to Test the Application

1. Dashboard
   - Open http://localhost:4200
   - Verify the latest block is displayed

2. Blocks
   - Navigate to /blocks
   - Verify blocks list loads
   - Click a block number to open Block Details

3. Block Details
   - Verify block metadata is displayed
   - Verify transactions list (or empty message)

4. Transactions
   - Navigate to /transactions
   - Test:
     - All transactions
     - Search by Tx Hash (e.g. 0xtx123)
     - Search by Block Number (e.g. 1)
     - Search by Address (e.g. 0xfrom1)

5. Watchlist
   - Navigate to /watchlist
   - Add a contract address
   - Refresh the page and verify persistence

---

## Technologies Used

Frontend:
- Angular (standalone)
- TypeScript
- SCSS

Backend:
- Spring Boot
- Java
- JPA / Hibernate

---

## Author

Andrej Popovski  
Internet Programming Project
