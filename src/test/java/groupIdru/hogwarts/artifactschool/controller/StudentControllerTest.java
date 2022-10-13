package groupIdru.hogwarts.artifactschool.controller;


import groupIdru.hogwarts.artifactschool.model.Faculty;
import groupIdru.hogwarts.artifactschool.model.Student;
import groupIdru.hogwarts.artifactschool.repositiries.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void getAllStudents() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port, String.class))
                .isNotNull();
    }

    @Test
    void getStudent() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "1", String.class))
                .isNotNull();
    }

    @Test
    void createStudent() {
        Student student = new Student();
        student.setName("fdgd");
        student.setAge(12);
        student.setFaculty(new Faculty());
        assertThat(this.restTemplate.postForObject("http://localhost:" + port, student, String.class)).isNotNull();
    }

    @Test
    void editStudent() {
        Student student = new Student();
        student.setName("fdgd");
        student.setAge(12);
        student.setFaculty(new Faculty());
        assertThat(this.restTemplate.postForObject("http://localhost:" + port, student, String.class))
                .isNotNull();
    }

    @Test
    void deleteStudent() {
        Student student = new Student();
        student.setName("fdgd");
        student.setAge(12);
        student.setFaculty(new Faculty());
        studentRepository.save(student);
        restTemplate.delete("http://localhost:" + port + "/" + student.getId());
        assertThat(studentRepository.findById(student.getId())).isEmpty();

    }

    @Test
    void findByAge() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port, String.class))
                .isNotNull();
    }

    @Test
    void testFindByAge() {
    }

    @Test
    void findFacultyStudent() {
    }

    @Test
    void downloadAvatar() {
    }

    @Test
    void testDownloadAvatar() {
    }

    @Test
    void uploadAvatarStudent() {
    }
}