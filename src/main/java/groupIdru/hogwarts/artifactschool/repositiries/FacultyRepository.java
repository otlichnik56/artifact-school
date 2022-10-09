package groupIdru.hogwarts.artifactschool.repositiries;

import groupIdru.hogwarts.artifactschool.model.Faculty;

import groupIdru.hogwarts.artifactschool.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;
//
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Faculty findByNameIgnoreCase(String name);
    Collection<Faculty> findByColor(String color);

    Collection<Faculty> findByColorIgnoreCaseOrNameIgnoreCase(String param);

    Faculty findByStudentsContainsIgnoreCase(Optional<Student> student);

}
