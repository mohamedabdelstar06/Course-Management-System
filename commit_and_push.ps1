Add-Content .gitignore "`nuploads/"
git add .gitignore
git commit -m "chore: ignore uploads directory"

git add pom.xml src/main/resources/application.properties
git commit -m "chore: upgrade Lombok, configure maven-compiler-plugin, and set valid JWT secret"

git add src/main/java/com/example/coursemanagement/service/FileStorageService.java
git add src/main/java/com/example/coursemanagement/service/impl/FileStorageServiceImpl.java
git commit -m "feat: implement FileStorageService for local image uploads"

git add src/main/java/com/example/coursemanagement/entity/
git add src/main/java/com/example/coursemanagement/dto/
git commit -m "feat: add course image field to entities and request/response DTOs"

git add src/main/java/com/example/coursemanagement/mapper/
git commit -m "feat: map courseImage property in DTO mappers"

git add src/main/java/com/example/coursemanagement/repository/
git commit -m "feat: update repositories for enhanced queries"

git add src/main/java/com/example/coursemanagement/service/
git commit -m "feat: integrate file upload logic into CourseService and handle IDE warnings"

git add src/main/java/com/example/coursemanagement/controller/
git commit -m "feat: update CourseController to accept multipart forms and improve DELETE response"

git add src/main/java/com/example/coursemanagement/config/WebMvcConfig.java
git add src/main/java/com/example/coursemanagement/config/SwaggerConfig.java
git commit -m "feat: add WebMvcConfig for static resources and Swagger OpenAPI config"

git add src/main/java/com/example/coursemanagement/config/DataSeeder.java
git commit -m "feat: add DataSeeder to populate initial admin and student accounts"

git add src/main/java/com/example/coursemanagement/config/JwtAuthenticationFilter.java
git add src/main/java/com/example/coursemanagement/config/SecurityConfig.java
git commit -m "fix: resolve strict null safety warnings in security configuration"

git add src/main/java/com/example/coursemanagement/exception/GlobalExceptionHandler.java
git commit -m "fix: resolve potential NullPointerException in GlobalExceptionHandler type checking"

git add src/test/
git commit -m "test: suppress null safety IDE warnings in unit tests"

# Ensure all remaining files are added (just in case)
git add .
git commit -m "chore: final cleanup and optimizations"

git branch -M main
git remote remove origin
git remote add origin git@github.com:mohamedabdelstar06/Course-Management-System.git
git push -u origin main
