package groupIdru.hogwarts.artifactschool.repositiries;

import groupIdru.hogwarts.artifactschool.model.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

// домашка 3.5
public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    Avatar findByStudentId(Long studentId);
}
