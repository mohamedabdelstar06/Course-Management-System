Remove-Item -Recurse -Force .git
git init
git config user.name "mohamedabdelstar06"
git config user.email "mohamedabdelstar06@users.noreply.github.com"
git branch -M main

# 1
git add pom.xml .gitignore src/main/resources/application.properties
git commit -m "chore: project initialization and dependencies setup"

# 2
git add src/main/java/com/example/coursemanagement/CourseManagementApplication.java
git commit -m "chore: main spring boot application class"

# 3
git add src/main/java/com/example/coursemanagement/entity/UserRole.java
git commit -m "feat: define UserRole enumeration"

# 4
git add src/main/java/com/example/coursemanagement/entity/User.java
git commit -m "feat: implement base User entity for authentication"

# 5
git add src/main/java/com/example/coursemanagement/entity/Student.java
git commit -m "feat: implement Student domain entity"

# 6
git add src/main/java/com/example/coursemanagement/entity/Instructor.java
git commit -m "feat: implement Instructor domain entity"

# 7
git add src/main/java/com/example/coursemanagement/entity/Course.java
git commit -m "feat: implement Course entity with relationships"

# 8
git add src/main/java/com/example/coursemanagement/entity/Enrollment.java
git commit -m "feat: implement Enrollment entity linking students and courses"

# 9
git add src/main/java/com/example/coursemanagement/repository/UserRepository.java
git commit -m "feat: add UserRepository for user data access"

# 10
git add src/main/java/com/example/coursemanagement/repository/CourseRepository.java
git commit -m "feat: add CourseRepository with custom queries"

# 11
git add src/main/java/com/example/coursemanagement/repository/StudentRepository.java src/main/java/com/example/coursemanagement/repository/InstructorRepository.java src/main/java/com/example/coursemanagement/repository/EnrollmentRepository.java
git commit -m "feat: add remaining Spring Data JPA repositories"

# 12
git add src/main/java/com/example/coursemanagement/exception/NotFoundException.java src/main/java/com/example/coursemanagement/exception/ConflictException.java src/main/java/com/example/coursemanagement/exception/UnauthorizedException.java
git commit -m "feat: create custom business exceptions"

# 13
git add src/main/java/com/example/coursemanagement/dto/ErrorResponse.java src/main/java/com/example/coursemanagement/dto/ValidationErrorResponse.java
git commit -m "feat: define structured error response models"

# 14
git add src/main/java/com/example/coursemanagement/exception/GlobalExceptionHandler.java
git commit -m "feat: implement global exception handler for REST APIs"

# 15
git add src/main/java/com/example/coursemanagement/dto/LoginRequest.java src/main/java/com/example/coursemanagement/dto/RegisterRequest.java src/main/java/com/example/coursemanagement/dto/AuthResponse.java
git commit -m "feat: add authentication and registration DTOs"

# 16
git add src/main/java/com/example/coursemanagement/dto/CourseRequest.java src/main/java/com/example/coursemanagement/dto/CourseResponse.java src/main/java/com/example/coursemanagement/dto/StudentResponse.java src/main/java/com/example/coursemanagement/dto/InstructorResponse.java src/main/java/com/example/coursemanagement/dto/EnrollmentRequest.java src/main/java/com/example/coursemanagement/dto/EnrollmentResponse.java
git commit -m "feat: add business logic DTOs"

# 17
git add src/main/java/com/example/coursemanagement/mapper/
git commit -m "feat: implement object mappers for entity-DTO conversion"

# 18
git add src/main/java/com/example/coursemanagement/service/AuthService.java src/main/java/com/example/coursemanagement/service/impl/AuthServiceImpl.java
git commit -m "feat: implement authentication and user registration logic"

# 19
git add src/main/java/com/example/coursemanagement/service/FileStorageService.java src/main/java/com/example/coursemanagement/service/impl/FileStorageServiceImpl.java
git commit -m "feat: implement local file storage service for image uploads"

# 20
git add src/main/java/com/example/coursemanagement/service/CourseService.java src/main/java/com/example/coursemanagement/service/impl/CourseServiceImpl.java
git commit -m "feat: implement course management and media processing logic"

# 21
git add src/main/java/com/example/coursemanagement/service/StudentService.java src/main/java/com/example/coursemanagement/service/impl/StudentServiceImpl.java src/main/java/com/example/coursemanagement/service/InstructorService.java src/main/java/com/example/coursemanagement/service/impl/InstructorServiceImpl.java
git commit -m "feat: implement student and instructor services"

# 22
git add src/main/java/com/example/coursemanagement/service/EnrollmentService.java src/main/java/com/example/coursemanagement/service/impl/EnrollmentServiceImpl.java
git commit -m "feat: implement course enrollment logic"

# 23
git add src/main/java/com/example/coursemanagement/config/JwtUtil.java src/main/java/com/example/coursemanagement/config/JwtAuthenticationFilter.java src/main/java/com/example/coursemanagement/config/SecurityConfig.java
git commit -m "feat: integrate JWT authentication and Spring Security"

# 24
git add src/main/java/com/example/coursemanagement/config/SwaggerConfig.java src/main/java/com/example/coursemanagement/config/WebMvcConfig.java
git commit -m "feat: configure OpenAPI documentation and static resource handlers"

# 25
git add src/main/java/com/example/coursemanagement/config/DataSeeder.java
git commit -m "feat: implement database seeder for initial data"

# 26
git add src/main/java/com/example/coursemanagement/controller/
git commit -m "feat: expose REST API endpoints in controllers"

# 27
git add src/test/
git commit -m "test: add JUnit and Mockito unit tests for service layer"

# Ensure any leftover files are added
git add .
git commit -m "chore: final adjustments and cleanup"

git remote add origin https://github.com/mohamedabdelstar06/Course-Management-System.git
git push -u origin main --force
