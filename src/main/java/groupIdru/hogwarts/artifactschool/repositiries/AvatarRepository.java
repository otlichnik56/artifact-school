package groupIdru.hogwarts.artifactschool.repositiries;

import groupIdru.hogwarts.artifactschool.model.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// домашка 3.5
public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    Optional<Avatar> findByStudentId(Long studentId);
}
