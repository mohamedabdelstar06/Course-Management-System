<p align="center">
  <img src="https://img.icons8.com/external-flaticons-flat-flat-icons/128/external-course-online-education-flaticons-flat-flat-icons.png" alt="Logo" width="100"/>
</p>

<h1 align="center">📚 Course Management System</h1>

<p align="center">
  <strong>A RESTful API for managing courses, students, instructors, and enrollments — built with Spring Boot 3.</strong>
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
  <img src="https://img.shields.io/badge/Java-17-ED8B00?style=flat-square&logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring_Boot-3.3.5-6DB33F?style=flat-square&logo=spring-boot&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring_Security-JWT-6DB33F?style=flat-square&logo=spring-security&logoColor=white"/>
  <img src="https://img.shields.io/badge/MySQL-8.0-4479A1?style=flat-square&logo=mysql&logoColor=white"/>
  <img src="https://img.shields.io/badge/Swagger-OpenAPI_3-85EA2D?style=flat-square&logo=swagger&logoColor=black"/>
  <img src="https://img.shields.io/badge/Railway-Deployed-430098?style=flat-square&logo=railway&logoColor=white"/>
</p>

---

## ✨ Features

- 🔐 **JWT Authentication** — Stateless Bearer token auth (24h expiry)
- 👥 **Role-Based Registration** — Register as `STUDENT` or `INSTRUCTOR`
- 📚 **Course CRUD** — Create, read, update, and soft-delete with pagination
- 📝 **Enrollment System** — Enroll/unenroll with duplicate detection
- 🖼️ **Image Upload** — Profile pictures & course images via multipart
- 📄 **Swagger UI** — Interactive API documentation with JWT support
- ⚠️ **Global Error Handling** — Structured JSON errors for every endpoint
- 🌱 **Auto Seeder** — Pre-built test accounts on first startup
- 🧪 **Unit Tests** — JUnit 5 + Mockito for the service layer

---

## 🛠 Tech Stack

| Category | Technology |
|----------|-----------|
| **Language** | Java 17 |
| **Framework** | Spring Boot 3.3.5 |
| **Security** | Spring Security + JWT (jjwt 0.12.3) |
| **Database** | MySQL 8.0 + Hibernate / Spring Data JPA |
| **Validation** | Jakarta Bean Validation |
| **Docs** | SpringDoc OpenAPI 3 (Swagger UI) |
| **Build** | Apache Maven |
| **Testing** | JUnit 5, Mockito |
| **Deploy** | Railway (Cloud) |

---

## 🏗 Architecture

```
Client (Postman / Frontend)
        │
        ▼
🔒 JWT Authentication Filter
        │
        ▼
🎮 Controllers ──▶ ⚙️ Services ──▶ 🗄️ Repositories ──▶ 🐬 MySQL
```

**Design patterns:** Layered Architecture · DTO Pattern · Builder Pattern · Repository Pattern · Global Exception Handler · Soft Delete

---

## 🗄 Database Schema

```
users (1)──(1) students ──(M) enrollments (M)──(1) courses (M)──(1) instructors (1)──(1) users
```

| Entity | Key Fields |
|--------|-----------|
| **User** | id, fullName, email, password, role, profileImage |
| **Student** | id, userId (FK) |
| **Instructor** | id, userId (FK) |
| **Course** | id, title, description, instructorId (FK), courseImage, isDeleted, createdAt |
| **Enrollment** | id, studentId (FK), courseId (FK), enrollmentDate · UQ(student, course) |

---

## 🚀 Getting Started

```bash
# 1. Clone
git clone https://github.com/mohamedabdelstar06/Course-Management-System.git
cd Course-Management-System

# 2. Create DB
mysql -u root -p -e "CREATE DATABASE course_management_db;"

# 3. Configure src/main/resources/application.properties
#    Set your DB url, username, password

# 4. Run
mvn spring-boot:run

# 5. Open Swagger UI
#    http://localhost:8080/swagger-ui/index.html
```

### Default Accounts (Auto-Seeded)

| Role | Email | Password |
|------|-------|----------|
| 👨‍🏫 Instructor | `instructor@test.com` | `password123` |
| 👨‍🎓 Student | `student@test.com` | `password123` |

---

## 📡 API Endpoints

### 🔓 Authentication (Public)

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/auth/register` | Register (multipart/form-data) |
| `POST` | `/api/auth/login` | Login & get JWT token |

### 📚 Courses (🔒 Token Required)

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/courses` | Create course (multipart) |
| `GET` | `/api/courses` | List all (paginated: `?page=0&size=10`) |
| `GET` | `/api/courses/{id}` | Get by ID |
| `PUT` | `/api/courses/{id}` | Update course (multipart) |
| `DELETE` | `/api/courses/{id}` | Soft-delete |

### 👥 Students & Instructors (🔒 Token Required)

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/students` | List all students |
| `GET` | `/api/students/{id}` | Get student by ID |
| `GET` | `/api/instructors` | List all instructors |
| `GET` | `/api/instructors/{id}` | Get instructor by ID |

### 📝 Enrollments (🔒 Token Required)

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/enrollments` | Enroll student in course |
| `GET` | `/api/enrollments/student/{studentId}` | Get by student |
| `DELETE` | `/api/enrollments/{id}` | Unenroll |

---

## 🔐 Authentication

1. **Login** → `POST /api/auth/login` → Copy `token` from response
2. In Postman → **Authorization** tab → **Bearer Token** → Paste token
3. All non-auth endpoints require this token

| Endpoint Pattern | Access |
|-----------------|--------|
| `/api/auth/**` | 🔓 Public |
| `/swagger-ui/**`, `/v3/api-docs/**` | 🔓 Public |
| Everything else | 🔒 JWT Required |

---

## ⚠️ Error Handling

Every error returns structured JSON:

```json
{
  "timestamp": "2026-07-09T16:00:00",
  "status": 404,
  "message": "Course not found with id: 99"
}
```

| Status | Meaning |
|--------|---------|
| `400` | Validation / bad request |
| `401` | Missing or invalid JWT |
| `403` | Insufficient permissions |
| `404` | Resource not found |
| `409` | Duplicate (email / enrollment) |
| `500` | Unexpected server error |

---

## 🧪 Testing

```bash
mvn test
```

Covers: Course CRUD, soft-delete, pagination, enrollment, duplicate detection, not-found scenarios.

---

## 🔗 Links

| Resource | URL |
|----------|-----|
| 🔴 **Live API** | [Swagger UI](https://coursemanagementsystem.up.railway.app/swagger-ui/index.html#/) |
| 📮 **Postman** | [Collection](https://dark-firefly-415528.postman.co/workspace/Mobile~91c02dcf-ff60-4768-8af0-d7495dfd3a64/collection/40373101-9e608123-8ff2-4c04-80d4-320342f9baa6?action=share&source=copy-link&creator=40373101) |
| 💻 **GitHub** | [Repository](https://github.com/mohamedabdelstar06/Course-Management-System) |

---

## 👨‍💻 Author

**Mohamed Abdel Star**

[![GitHub](https://img.shields.io/badge/GitHub-mohamedabdelstar06-181717?style=for-the-badge&logo=github)](https://github.com/mohamedabdelstar06)

---

<p align="center">⭐ If you found this project helpful, please give it a star!</p>
