<p align="center">
  <img src="https://img.icons8.com/external-flaticons-flat-flat-icons/128/external-course-online-education-flaticons-flat-flat-icons.png" alt="Logo" width="100"/>
</p>

<h1 align="center">📚 Course Management System</h1>

<p align="center">
  <strong>A production-ready RESTful API for managing courses, students, instructors, and enrollments.</strong>
</p>

<p align="center">
  <a href="https://coursemanagementsystem.up.railway.app/swagger-ui/index.html#/">
    <img src="https://img.shields.io/badge/🔴_Live_Demo-Railway-430098?style=for-the-badge" alt="Live Demo"/>
  </a>
  <a href="https://dark-firefly-415528.postman.co/workspace/Mobile~91c02dcf-ff60-4768-8af0-d7495dfd3a64/collection/40373101-9e608123-8ff2-4c04-80d4-320342f9baa6?action=share&source=copy-link&creator=40373101">
    <img src="https://img.shields.io/badge/Postman-Collection-FF6C37?style=for-the-badge&logo=postman&logoColor=white" alt="Postman"/>
  </a>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-ED8B00?style=flat-square&logo=openjdk&logoColor=white" alt="Java 17"/>
  <img src="https://img.shields.io/badge/Spring_Boot-3.3.5-6DB33F?style=flat-square&logo=spring-boot&logoColor=white" alt="Spring Boot"/>
  <img src="https://img.shields.io/badge/Spring_Security-JWT-6DB33F?style=flat-square&logo=spring-security&logoColor=white" alt="Spring Security"/>
  <img src="https://img.shields.io/badge/MySQL-8.0-4479A1?style=flat-square&logo=mysql&logoColor=white" alt="MySQL"/>
  <img src="https://img.shields.io/badge/Hibernate-JPA-59666C?style=flat-square&logo=hibernate&logoColor=white" alt="Hibernate"/>
  <img src="https://img.shields.io/badge/Maven-Build-C71A36?style=flat-square&logo=apache-maven&logoColor=white" alt="Maven"/>
  <img src="https://img.shields.io/badge/Swagger-OpenAPI_3-85EA2D?style=flat-square&logo=swagger&logoColor=black" alt="Swagger"/>
  <img src="https://img.shields.io/badge/Lombok-Latest-red?style=flat-square" alt="Lombok"/>
  <img src="https://img.shields.io/badge/Railway-Deployed-430098?style=flat-square&logo=railway&logoColor=white" alt="Railway"/>
</p>

---

## 📖 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Architecture](#-architecture)
- [Database Schema (ERD)](#-database-schema-erd)
- [Project Structure](#-project-structure)
- [Getting Started](#-getting-started)
- [API Endpoints](#-api-endpoints)
- [Authentication & Security](#-authentication--security)
- [Error Handling](#-error-handling)
- [Testing](#-testing)
- [Deployment](#-deployment)
- [Links](#-links)

---

## 🌟 Overview

**Course Management System** is a full-stack backend REST API built with **Spring Boot 3** that enables educational institutions to manage their courses, students, instructors, and enrollments. The system provides secure JWT-based authentication, role-based access, file upload capabilities, and comprehensive API documentation via Swagger UI.

> 🚀 **Live Production:** [coursemanagementsystem.up.railway.app](https://coursemanagementsystem.up.railway.app/swagger-ui/index.html#/)

---

## ✨ Features

| Feature | Description |
|---------|-------------|
| 🔐 **JWT Authentication** | Stateless token-based auth with Bearer tokens (24h expiry) |
| 👥 **Role-Based Registration** | Register as `STUDENT` or `INSTRUCTOR` with separate profiles |
| 📚 **Course CRUD** | Create, read, update, and soft-delete courses with pagination |
| 📝 **Enrollment System** | Enroll/unenroll students from courses with duplicate detection |
| 🖼️ **Image Upload** | Profile pictures and course images via multipart form-data |
| 📄 **Swagger / OpenAPI 3** | Interactive API documentation with JWT auth support |
| ⚠️ **Global Error Handling** | Structured JSON error responses for all exceptions |
| ✅ **Bean Validation** | Request validation with detailed field-level error messages |
| 🌱 **Database Seeder** | Auto-seeds initial instructor and student accounts |
| 🧪 **Unit Testing** | JUnit 5 + Mockito tests for service layer |

---

## 🛠 Tech Stack

<table>
  <tr>
    <td align="center" width="100">
      <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" width="40" height="40" alt="Java"/>
      <br><strong>Java 17</strong>
    </td>
    <td align="center" width="100">
      <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" width="40" height="40" alt="Spring Boot"/>
      <br><strong>Spring Boot</strong>
    </td>
    <td align="center" width="100">
      <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mysql/mysql-original.svg" width="40" height="40" alt="MySQL"/>
      <br><strong>MySQL</strong>
    </td>
    <td align="center" width="100">
      <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/hibernate/hibernate-original.svg" width="40" height="40" alt="Hibernate"/>
      <br><strong>Hibernate</strong>
    </td>
    <td align="center" width="100">
      <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/maven/maven-original.svg" width="40" height="40" alt="Maven"/>
      <br><strong>Maven</strong>
    </td>
  </tr>
</table>

| Category | Technology |
|----------|-----------|
| **Language** | Java 17 |
| **Framework** | Spring Boot 3.3.5 |
| **Security** | Spring Security + JWT (jjwt 0.12.3) |
| **Database** | MySQL 8.0 |
| **ORM** | Hibernate / Spring Data JPA |
| **Validation** | Jakarta Bean Validation |
| **Documentation** | SpringDoc OpenAPI 3 (Swagger UI) |
| **Build Tool** | Apache Maven |
| **Boilerplate** | Lombok 1.18.38 |
| **Testing** | JUnit 5, Mockito |
| **Deployment** | Railway (Cloud) |

---

## 🏗 Architecture

The project follows a **Layered Architecture** pattern with clear separation of concerns:

```
┌──────────────────────────────────────────────────────┐
│                    CLIENT (Postman / Frontend)        │
└──────────────────────┬───────────────────────────────┘
                       │ HTTP Request
                       ▼
┌──────────────────────────────────────────────────────┐
│              🔒 JWT Authentication Filter             │
│         (Validates Bearer Token on every request)     │
└──────────────────────┬───────────────────────────────┘
                       │
                       ▼
┌──────────────────────────────────────────────────────┐
│               🎮 Controller Layer                     │
│  AuthController │ CourseController │ EnrollmentCtrl   │
│  StudentController │ InstructorController             │
└──────────────────────┬───────────────────────────────┘
                       │
                       ▼
┌──────────────────────────────────────────────────────┐
│               ⚙️ Service Layer                        │
│  AuthServiceImpl │ CourseServiceImpl                  │
│  EnrollmentServiceImpl │ StudentServiceImpl           │
│  InstructorServiceImpl │ FileStorageServiceImpl       │
└──────────────────────┬───────────────────────────────┘
                       │
                       ▼
┌──────────────────────────────────────────────────────┐
│            🗄️ Repository Layer (Spring Data JPA)      │
│  UserRepo │ CourseRepo │ StudentRepo │ InstructorRepo │
│  EnrollmentRepo                                       │
└──────────────────────┬───────────────────────────────┘
                       │
                       ▼
┌──────────────────────────────────────────────────────┐
│               🐬 MySQL Database                       │
│   users │ students │ instructors │ courses │ enrollments│
└──────────────────────────────────────────────────────┘
```

### Design Patterns Used

| Pattern | Usage |
|---------|-------|
| **Layered Architecture** | Controller → Service → Repository → Database |
| **DTO Pattern** | Request/Response DTOs to decouple API from entities |
| **Mapper Pattern** | Manual mappers for entity ↔ DTO conversion |
| **Builder Pattern** | Lombok `@Builder` on all entities and DTOs |
| **Repository Pattern** | Spring Data JPA repositories |
| **Global Exception Handler** | `@RestControllerAdvice` for centralized error handling |
| **Soft Delete** | Courses use a `deleted` flag instead of physical removal |

---

## 🗄 Database Schema (ERD)

```
┌───────────────┐       ┌───────────────┐       ┌───────────────┐
│     users     │       │   students    │       │  instructors  │
├───────────────┤       ├───────────────┤       ├───────────────┤
│ id (PK)       │──┐    │ id (PK)       │       │ id (PK)       │
│ full_name     │  │    │ user_id (FK)  │───┐   │ user_id (FK)  │──┐
│ email (UQ)    │  └────│               │   │   │               │  │
│ password      │       └───────┬───────┘   │   └───────┬───────┘  │
│ role          │               │           │           │          │
│ profile_image │               │           │           │          │
└───────────────┘               │           │           │          │
                                │           │           │          │
                    ┌───────────┘           │           │          │
                    │                       │           │          │
              ┌─────┴─────────┐    ┌────────┴──────────┘          │
              │  enrollments  │    │       courses                │
              ├───────────────┤    ├───────────────┐              │
              │ id (PK)       │    │ id (PK)       │              │
              │ student_id(FK)│────│ title          │              │
              │ course_id(FK) │────│ description    │              │
              │ enrollment_   │    │ instructor_id  │──────────────┘
              │   date        │    │ course_image   │
              └───────────────┘    │ is_deleted     │
               UQ(student_id,     │ created_at     │
                  course_id)      └────────────────┘
```

### Relationships

| Relationship | Type | Description |
|-------------|------|-------------|
| `User` ↔ `Student` | One-to-One | Each student has exactly one user account |
| `User` ↔ `Instructor` | One-to-One | Each instructor has exactly one user account |
| `Instructor` → `Course` | One-to-Many | An instructor can teach multiple courses |
| `Student` ↔ `Course` | Many-to-Many | Through `Enrollment` join entity |

---

## 📁 Project Structure

```
src/main/java/com/example/coursemanagement/
│
├── 📂 config/                        # Configuration & Infrastructure
│   ├── DataSeeder.java               # Seeds initial data on startup
│   ├── JwtAuthenticationFilter.java  # JWT token validation filter
│   ├── JwtUtil.java                  # JWT generation & parsing utility
│   ├── SecurityConfig.java           # Spring Security configuration
│   ├── SwaggerConfig.java            # OpenAPI / Swagger configuration
│   └── WebMvcConfig.java             # Static resource & CORS config
│
├── 📂 controller/                    # REST API Endpoints
│   ├── AuthController.java           # POST /api/auth/register, /login
│   ├── CourseController.java         # CRUD /api/courses
│   ├── EnrollmentController.java     # POST, GET, DELETE /api/enrollments
│   ├── InstructorController.java     # GET /api/instructors
│   └── StudentController.java        # GET /api/students
│
├── 📂 dto/                           # Data Transfer Objects
│   ├── AuthResponse.java             # Login/Register response
│   ├── CourseRequest.java            # Course creation/update input
│   ├── CourseResponse.java           # Course API output
│   ├── EnrollmentRequest.java        # Enrollment input
│   ├── EnrollmentResponse.java       # Enrollment output
│   ├── ErrorResponse.java            # Standard error JSON format
│   ├── InstructorResponse.java       # Instructor API output
│   ├── LoginRequest.java             # Login input
│   ├── RegisterRequest.java          # Registration input
│   ├── StudentResponse.java          # Student API output
│   └── ValidationErrorResponse.java  # Validation error with field details
│
├── 📂 entity/                        # JPA Entities (Database Models)
│   ├── Course.java                   # Course with soft-delete support
│   ├── Enrollment.java               # Student ↔ Course join entity
│   ├── Instructor.java               # Instructor profile
│   ├── Student.java                  # Student profile
│   ├── User.java                     # Core authentication entity
│   └── UserRole.java                 # STUDENT | INSTRUCTOR enum
│
├── 📂 exception/                     # Custom Exceptions
│   ├── ConflictException.java        # 409 — duplicates
│   ├── GlobalExceptionHandler.java   # Centralized error handling
│   ├── NotFoundException.java        # 404 — resource not found
│   └── UnauthorizedException.java    # 401 — bad credentials
│
├── 📂 mapper/                        # Entity ↔ DTO Mappers
│   ├── CourseMapper.java
│   ├── EnrollmentMapper.java
│   ├── InstructorMapper.java
│   ├── StudentMapper.java
│   └── UserMapper.java
│
├── 📂 repository/                    # Spring Data JPA Repositories
│   ├── CourseRepository.java
│   ├── EnrollmentRepository.java
│   ├── InstructorRepository.java
│   ├── StudentRepository.java
│   └── UserRepository.java
│
├── 📂 service/                       # Business Logic Interfaces
│   ├── AuthService.java
│   ├── CourseService.java
│   ├── EnrollmentService.java
│   ├── FileStorageService.java
│   ├── InstructorService.java
│   ├── StudentService.java
│   └── 📂 impl/                      # Service Implementations
│       ├── AuthServiceImpl.java
│       ├── CourseServiceImpl.java
│       ├── EnrollmentServiceImpl.java
│       ├── FileStorageServiceImpl.java
│       ├── InstructorServiceImpl.java
│       └── StudentServiceImpl.java
│
└── CourseManagementApplication.java  # Main entry point
```

---

## 🚀 Getting Started

### Prerequisites

| Tool | Version | Download |
|------|---------|----------|
| ☕ Java JDK | 17+ | [Download](https://adoptium.net/) |
| 🐬 MySQL | 8.0+ | [Download](https://dev.mysql.com/downloads/) |
| 📦 Maven | 3.8+ | [Download](https://maven.apache.org/download.cgi) |
| 📮 Postman | Latest | [Download](https://www.postman.com/downloads/) |

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/mohamedabdelstar06/Course-Management-System.git
cd Course-Management-System
```

### 2️⃣ Create the Database

```sql
CREATE DATABASE course_management_db;
```

### 3️⃣ Configure `application.properties`

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/course_management_db
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```

### 4️⃣ Run the Application

```bash
mvn spring-boot:run
```

The server will start on `http://localhost:8080`

### 5️⃣ Access Swagger UI

Open your browser and navigate to:

```
http://localhost:8080/swagger-ui/index.html
```

### 6️⃣ Default Accounts (Auto-Seeded)

On first startup, the `DataSeeder` automatically creates two test accounts:

| Role | Email | Password |
|------|-------|----------|
| 👨‍🏫 Instructor | `instructor@test.com` | `password123` |
| 👨‍🎓 Student | `student@test.com` | `password123` |

---

## 📡 API Endpoints

### 🔓 Authentication (Public — No Token Required)

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| `POST` | `/api/auth/register` | Register a new user | `multipart/form-data` |
| `POST` | `/api/auth/login` | Login & get JWT token | `application/json` |

<details>
<summary>📋 <strong>Register Example (form-data)</strong></summary>

| Field | Type | Required | Description |
|-------|------|----------|-------------|
| `fullName` | text | ✅ | User's full name |
| `email` | text | ✅ | Valid email address |
| `password` | text | ✅ | Min 8 characters |
| `role` | text | ✅ | `STUDENT` or `INSTRUCTOR` |
| `image` | file | ❌ | Profile picture |

</details>

<details>
<summary>📋 <strong>Login Example</strong></summary>

```json
{
  "email": "instructor@test.com",
  "password": "password123"
}
```

**Response:**
```json
{
  "userId": 1,
  "fullName": "Admin Instructor",
  "email": "instructor@test.com",
  "role": "INSTRUCTOR",
  "profileId": 1,
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "profileImage": null
}
```

</details>

---

### 📚 Courses (🔒 Token Required)

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/courses` | Create a new course (multipart) |
| `GET` | `/api/courses` | List all courses (paginated) |
| `GET` | `/api/courses/{id}` | Get course by ID |
| `PUT` | `/api/courses/{id}` | Update a course (multipart) |
| `DELETE` | `/api/courses/{id}` | Soft-delete a course |

> **Pagination:** `GET /api/courses?page=0&size=10&sort=title,asc`

<details>
<summary>📋 <strong>Create Course Example (form-data)</strong></summary>

| Field | Type | Required | Description |
|-------|------|----------|-------------|
| `title` | text | ✅ | Course title |
| `description` | text | ❌ | Course description |
| `instructorId` | text | ✅ | Instructor's profile ID |
| `image` | file | ❌ | Course cover image |

</details>

---

### 👨‍🎓 Students (🔒 Token Required)

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/students` | List all students |
| `GET` | `/api/students/{id}` | Get student by ID |

---

### 👨‍🏫 Instructors (🔒 Token Required)

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/instructors` | List all instructors |
| `GET` | `/api/instructors/{id}` | Get instructor by ID |

---

### 📝 Enrollments (🔒 Token Required)

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/enrollments` | Enroll student in a course |
| `GET` | `/api/enrollments/student/{studentId}` | Get enrollments by student |
| `DELETE` | `/api/enrollments/{id}` | Unenroll (delete enrollment) |

<details>
<summary>📋 <strong>Enrollment Example</strong></summary>

```json
{
  "studentId": 1,
  "courseId": 1
}
```

</details>

---

## 🔐 Authentication & Security

The system uses **JWT (JSON Web Token)** for stateless authentication:

```
┌─────────┐                        ┌─────────────┐
│  Client  │──── POST /api/auth/login ────▶│   Server    │
│          │                        │             │
│          │◀───── JWT Token ───────│             │
│          │                        │             │
│          │── GET /api/courses ──▶ │             │
│          │   Authorization:       │ Validate    │
│          │   Bearer <token>       │ JWT Token   │
│          │                        │             │
│          │◀──── 200 OK ──────────│             │
└─────────┘                        └─────────────┘
```

### How to Use in Postman

1. **Login** → `POST /api/auth/login` → Copy the `token` from response
2. Go to **Authorization** tab → Select **Bearer Token**
3. Paste the token → Send your request

### Security Rules

| Endpoint Pattern | Access |
|-----------------|--------|
| `POST /api/auth/**` | 🔓 Public |
| `GET /swagger-ui/**` | 🔓 Public |
| `GET /v3/api-docs/**` | 🔓 Public |
| `GET /uploads/**` | 🔓 Public |
| All other endpoints | 🔒 JWT Required |

---

## ⚠️ Error Handling

All errors return a consistent JSON structure:

```json
{
  "timestamp": "2026-07-09T16:00:00",
  "status": 404,
  "message": "Course not found with id: 99"
}
```

### Error Codes Reference

| Status | Type | Example |
|--------|------|---------|
| `400` | Validation Error | Missing required fields, invalid email format |
| `401` | Unauthorized | Missing or invalid JWT token |
| `403` | Forbidden | Insufficient permissions |
| `404` | Not Found | Resource doesn't exist |
| `409` | Conflict | Duplicate email, duplicate enrollment |
| `413` | Payload Too Large | Upload file exceeds size limit |
| `500` | Server Error | Unexpected internal error |

### Validation Errors (400) — Detailed Field Errors

```json
{
  "timestamp": "2026-07-09T16:00:00",
  "status": 400,
  "message": "Validation failed",
  "errors": [
    { "field": "email", "message": "Email must be a valid address" },
    { "field": "password", "message": "Password must be at least 8 characters" }
  ]
}
```

---

## 🧪 Testing

The project includes unit tests using **JUnit 5** and **Mockito** for the service layer.

### Run Tests

```bash
mvn test
```

### Test Coverage

| Test Class | Coverage |
|-----------|----------|
| `CourseServiceTest` | Course CRUD, soft-delete, pagination, not-found scenarios |
| `EnrollmentServiceTest` | Enroll, unenroll, duplicate detection, student validation |

---

## ☁️ Deployment

The application is deployed on **Railway** with:

- **Backend:** Spring Boot JAR on Railway
- **Database:** MySQL 8 on Railway

### Environment Variables (Railway)

| Variable | Description |
|----------|-------------|
| `spring.datasource.url` | MySQL connection URL |
| `spring.datasource.username` | DB username |
| `spring.datasource.password` | DB password |
| `app.jwt.secret` | Base64-encoded JWT secret |
| `app.jwt.expiration-ms` | Token expiry (default: 86400000 = 24h) |

---

## 🔗 Links

| Resource | URL |
|----------|-----|
| 🔴 **Live API (Swagger)** | [coursemanagementsystem.up.railway.app/swagger-ui](https://coursemanagementsystem.up.railway.app/swagger-ui/index.html#/) |
| 📮 **Postman Collection** | [Open in Postman](https://dark-firefly-415528.postman.co/workspace/Mobile~91c02dcf-ff60-4768-8af0-d7495dfd3a64/collection/40373101-9e608123-8ff2-4c04-80d4-320342f9baa6?action=share&source=copy-link&creator=40373101) |
| 💻 **GitHub Repository** | [github.com/mohamedabdelstar06/Course-Management-System](https://github.com/mohamedabdelstar06/Course-Management-System) |

---

## 👨‍💻 Author

**Mohamed Abdel Star**

[![GitHub](https://img.shields.io/badge/GitHub-mohamedabdelstar06-181717?style=for-the-badge&logo=github)](https://github.com/mohamedabdelstar06)

---

<p align="center">
  <sub>⭐ If you found this project helpful, please give it a star!</sub>
</p>
