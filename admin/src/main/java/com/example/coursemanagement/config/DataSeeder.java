package com.example.coursemanagement.config;
import com.example.coursemanagement.entity.Instructor;
import com.example.coursemanagement.entity.Student;
import com.example.coursemanagement.entity.User;
import com.example.coursemanagement.enums.UserRole;
import com.example.coursemanagement.repository.InstructorRepository;
import com.example.coursemanagement.repository.StudentRepository;
import com.example.coursemanagement.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@SuppressWarnings("null")
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final InstructorRepository instructorRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UserRepository userRepository,
                      InstructorRepository instructorRepository,
                      StudentRepository studentRepository,
                      PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.instructorRepository = instructorRepository;
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            System.out.println("No users found. Seeding initial data...");

            // Seed Instructor
            User instructorUser = User.builder()
                    .fullName("Admin Instructor")
                    .email("instructor@test.com")
                    .password(passwordEncoder.encode("password123"))
                    .role(UserRole.INSTRUCTOR)
                    .build();
            instructorUser = userRepository.save(instructorUser);
            instructorRepository.save(Instructor.builder().user(instructorUser).build());

            // Seed Student
            User studentUser = User.builder()
                    .fullName("John Student")
                    .email("student@test.com")
                    .password(passwordEncoder.encode("password123"))
                    .role(UserRole.STUDENT)
                    .build();
            studentUser = userRepository.save(studentUser);
            studentRepository.save(Student.builder().user(studentUser).build());

            System.out.println("Seeding completed!");
            System.out.println("Instructor Login: instructor@test.com / password123");
            System.out.println("Student Login: student@test.com / password123");
        }
    }
}
