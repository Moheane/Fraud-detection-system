# Fraud Detection System

 **Fraud Detection System** built with Spring Boot. It processes categorized transaction events in real-time, applies multiple fraud detection rules, flags suspicious transactions, and stores results for analysis via REST APIs.

---

## Overview

This service is designed to detect fraudulent transactions by evaluating them against a set of configurable business rules. Flagged transactions are stored separately for easy analyst review and case management.



---

## Tech Stack

- **Backend**: Java 26 + Spring Boot 4
- **Database**: PostgreSQL
- **Build Tool**: Maven
- **Containerization**: Docker + Docker Compose
- **Security**: Spring Security + JWT

---

## Quick Start

##### LOCAL DEVELOPMENT 

docker-compose up -d --build

#### PRODUCTION
docker-compose -f docker-compose.prod.yml up --build -d

#### Configure database
Configure database
Update application.yml with your local PostgreSQL credentials
Or use the provided docker-compose.yml

#### STEP BY STEP GUIDE
```bash
# 1. Clone the repository
git clone <your-repo-url>
cd fraud-detection-app

# 2. Build and start the application
docker-compose up --build

# 3. Run in background (Recommended)
docker-compose up -d --build

# 4. View application logs
docker-compose logs -f fraud-app

# 5. Stop the services
docker-compose down

the application runs at port 8080

```


---
## Test
#### Run all tests
.\mvnw clean test

##### Run all Controller tests
.\mvnw test -Dtest=*ControllerTest

##### Run all Service tests
.\mvnw test -Dtest=*ServiceTest

## API Endpoints

```markdown
## API Endpoints

### Authentication

| Method | Endpoint                    | Description                    | Request Body     |
|--------|-----------------------------|--------------------------------|------------------|
| POST   | `/api/auth/register/user`   | Register a new user            | `AuthRequest`    |
| POST   | `/api/auth/login`           | Login user                     | `AuthRequest`    |

> **Note**: Full interactive API documentation will be available at `/swagger-ui.html` once the application is running.
