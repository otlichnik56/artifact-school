package groupIdru.hogwarts.artifactschool.controller;


import groupIdru.hogwarts.artifactschool.model.Faculty;
import groupIdru.hogwarts.artifactschool.model.Student;
import groupIdru.hogwarts.artifactschool.repositiries.StudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.mock.web.MockMultipartFile;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private StudentRepository studentRepository;

    @Mock
    private MockMultipartFile mockMultipartFile;

    @Test
    void getAllStudents() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students", String.class))
                .isNotNull();
    }

    @Test
    void getStudent() {
        long id = 1;
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/" + id, String.class))
                .isEqualTo(studentRepository.findById(id).toString());
    }

    @Test
    void createStudent() {
        Student student = new Student();
        student.setName("fdgd");
        student.setAge(12);
        student.setFaculty(new Faculty());
        assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/students", student, String.class))
                .isNotNull();
    }

    @Test
    void editStudent() {
        Student student = new Student();
        student.setName("fdgd");
        student.setAge(12);
        student.setFaculty(new Faculty());
        assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/students", student, String.class))
                .isNotNull();
    }

    @Test
    void deleteStudent() {
        Student student = new Student();
        student.setName("fdgd");
        student.setAge(12);
        student.setFaculty(new Faculty());
        studentRepository.save(student);
        restTemplate.delete("http://localhost:" + port + "/students/" + student.getId());
        assertThat(studentRepository.findById(student.getId())).isEmpty();
    }

    @Test
    void findByAge() {
        int age = 10;
        assertThat(this.restTemplate.getForObject("http://localhost:" + port+ "/students" + "?age="
                                                    + age, String.class))
                .isEqualTo(studentRepository.findByAge(age).toString());
    }

    @Test
    void testFindBetweenByAge() {
        int minAge = 10;
        int maxAge = 12;
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/sort" + "?minAge="
                                                    + minAge + "&maxAge=" + maxAge, String.class))
                .isEqualTo(studentRepository.findByAgeBetween(minAge, maxAge).toString());
    }

    @Test
    void findFacultyStudent() {
        long id = 1;
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/"
                                                    + id + "/faculty", String.class))
                .isEqualTo(studentRepository.findById(id).map(Student::getFaculty).toString());
    }

    @Test
    void testDownloadAvatar() {
        long id = 1;
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students"
                                                    + id + "/avatar", String.class))
                .isNotNull();
    }

    @Test
    void testDownloadAvatarData() {
        long id = 1;
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students"
                                                    + id + "/avatar/data", String.class))
                .isNotNull();
    }

    @Test
    void uploadAvatarStudent() {
        long id = 1;
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students"
                + id + "/avatar?avatar=" + mockMultipartFile, String.class))
                .isNotNull();
    }

}