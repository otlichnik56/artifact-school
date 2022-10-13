package groupIdru.hogwarts.artifactschool.controller;

import groupIdru.hogwarts.artifactschool.repositiries.FacultyRepository;
import groupIdru.hogwarts.artifactschool.service.FacultyService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
class FacultyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepository facultyRepository;

    @SpyBean
    private FacultyService facultyService;

    @InjectMocks
    private FacultyController facultyController;


    @Test
    void getAllFaculties() {
    }

    @Test
    void getFaculty() {
    }

    @Test
    void createFaculty() {
    }

    @Test
    void editFaculty() {
    }

    @Test
    void deleteFaculty() {
    }

    @Test
    void findByColor() {
    }

    @Test
    void findByColorOrName() {
    }

    @Test
    void findStudentsOfFaculty() {
    }
}