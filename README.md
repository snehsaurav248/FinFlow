
```md
#  Finance Dashboard Backend

##  Overview

This project is a Spring Boot backend application for managing financial data.  
It provides secure APIs for:

- User authentication (JWT-based)
- Role-based access control
- Financial record management (income & expenses)
- Dashboard analytics (summary, category, trends)

---

##  Tech Stack

- Java 21  
- Spring Boot  
- Spring Security  
- JWT (JSON Web Token)  
- Spring Data JPA (Hibernate)  
- MySQL  
- Maven  

---

##  Project Structure

```

src/main/java/com/finance/dashboard/

config/        → Security & JWT configuration
controller/    → REST API controllers
dto/           → Request & response models
entity/        → Database entities
repository/    → Data access layer (JPA)
service/       → Interfaces
service/impl/  → Business logic implementation
security/      → Authentication & user details
exception/     → Global exception handling
util/          → Utility classes
enums/         → Enum types (RoleType, RecordType)

```

---

##  Authentication & Authorization

### Authentication

- Uses JWT-based authentication
- User logs in with email & password
- Password is verified using BCrypt
- A JWT token is generated and returned

### Authorization

- Role-based access:
  - ADMIN
  - ANALYST
  - VIEWER
- Controlled using `@PreAuthorize`

---

##  Request Flow

1. Client sends request  
2. JWT token is passed in header  
3. JwtFilter validates token  
4. User is authenticated  
5. Role is checked (if required)  
6. Controller handles request  
7. Service executes business logic  
8. Repository interacts with database  
9. Response is returned  

---

##  Database Design

### User Table

- id  
- name  
- email (unique)  
- password (hashed)  
- role  
- isActive  
- createdAt  

---

### FinancialRecord Table

- id  
- amount  
- type (INCOME / EXPENSE)  
- category  
- date  
- description  
- user_id (foreign key)  
- createdAt  

---

##  API Endpoints

###  Auth APIs (2)

```

POST /api/auth/register
POST /api/auth/login

```

---

###  User APIs (5) — ADMIN only

```

GET    /api/users
GET    /api/users/{id}
POST   /api/users
PUT    /api/users/{id}
DELETE /api/users/{id}

```

---

###  Financial Record APIs (4)

```

POST   /api/records
GET    /api/records
PUT    /api/records/{id}
DELETE /api/records/{id}

```

---

###  Dashboard APIs (3)

```

GET /api/dashboard/summary
GET /api/dashboard/category
GET /api/dashboard/monthly

```

---

##  Business Logic

### Summary API

- Calculates:
  - Total Income  
  - Total Expense  
  - Balance = Income - Expense  

---

### Category API

- Groups expenses by category  
- Returns total per category  

---

### Monthly API

- Groups records by month  
- Returns income & expense per month  

---

##  Security Configuration

- Stateless session management  
- JWT-based authentication  
- Public endpoints:
```

/api/auth/**

```
- All other endpoints require authentication  

---

##  Validation

- DTOs use:
- `@NotNull`
- `@NotBlank`
- `@DecimalMin`
- Global exception handler handles errors  

---

##  Testing

APIs tested using:

- Postman  
- cURL  

### Covered:

- Authentication  
- Role-based access  
- CRUD operations  
- Dashboard analytics  
- Validation  

---

##  Common Issues & Fixes

| Issue | Cause | Fix |
|------|------|-----|
| 403 Forbidden | Role mismatch | Assign correct role |
| Invalid credentials | Wrong password | Use plain password |
| Missing field error | Validation | Send required fields |
| Null values | Field mismatch | Fix DTO |
| JWT error | Missing header | Add Authorization |

---

##  Run the Application

```

mvn spring-boot:run

```

App runs on:

```

[http://localhost:8080](http://localhost:8080)

```

---

##  Key Features

- JWT Authentication  
- Role-based Authorization  
- Financial CRUD APIs  
- Dashboard analytics  
- MySQL persistence  
- Clean architecture  

---

##  Future Enhancements

- Pagination & filtering  
- Swagger API docs  
- Frontend (React)  
- Reports export  
- Notifications  

---

##  Summary

This project demonstrates:

- Backend system design  
- Secure API development  
- Database integration  
- Business logic implementation  
- End-to-end testing  
```
