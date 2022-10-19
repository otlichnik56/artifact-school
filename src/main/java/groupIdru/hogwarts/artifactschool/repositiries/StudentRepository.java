package groupIdru.hogwarts.artifactschool.repositiries;

import groupIdru.hogwarts.artifactschool.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // домашка 4.1
    @Query(value = "SELECT COUNT(*) FROM students", nativeQuery = true)
    Integer getNumberOfAllStudents();

    @Query(value = "SELECT AVG(age) FROM students", nativeQuery = true)
    Double getAvgAgeOfAllStudents();

    @Query(value = "SELECT * FROM students ORDER BY id OFFSET((SELECT COUNT(*) FROM students) - 5)", nativeQuery = true)
    List<Student> getFiveLastStudents();


    // домашка 3.5
    Collection<Student> findByAge(int age);

    Collection<Student> findByAgeBetween(int minAge, int maxAge);


}
