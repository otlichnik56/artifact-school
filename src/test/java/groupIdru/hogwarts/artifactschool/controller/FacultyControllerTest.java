package groupIdru.hogwarts.artifactschool.controller;

import groupIdru.hogwarts.artifactschool.model.Faculty;
import groupIdru.hogwarts.artifactschool.model.Student;
import groupIdru.hogwarts.artifactschool.repositiries.FacultyRepository;
import groupIdru.hogwarts.artifactschool.service.FacultyService;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    Long id = 1L;
    String name = "Faculty";
    String color = "blue";
    JSONObject studentObject;
    Faculty faculty;
    Collection<Faculty> faculties;
    Collection<Student> students;


    @BeforeEach
    void setUp() {
        studentObject.put("name", name);
        studentObject.put("color", color);
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);
        faculties.add(faculty);
    }

    @Test
    void testGetAllFaculties() throws Exception {
        when(facultyRepository.findAll()).thenReturn((List<Faculty>) faculties);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculties")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpectAll();
    }

    @Test
    void testGetFaculty() throws Exception {
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculties/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("id"))
                .andExpect(jsonPath("$.name").value("name"))
                .andExpect(jsonPath("$.color").value("color"));
    }

    @Test
    void testCreateFaculty() throws Exception {
        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculties")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("id"))
                .andExpect(jsonPath("$.name").value("name"))
                .andExpect(jsonPath("$.color").value("color"));
    }

    @Test
    void testEditFaculty() throws Exception {
        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculties")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("id"))
                .andExpect(jsonPath("$.name").value("name"))
                .andExpect(jsonPath("$.color").value("color"));
    }

    @Test
    void testDeleteFaculty() throws Exception {
        facultyRepository.deleteById(1L);
        Assertions.assertNull(faculties);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/faculties", (Object) null));
    }

    @Test
    void testFindByColor() throws Exception {
        when(facultyRepository.findByColorIgnoreCase(any(String.class))).thenReturn(faculties);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculties?color=" + color)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpectAll();
    }

    @Test
    void testFindByColorOrName() throws Exception {
        when(facultyRepository.findByColorIgnoreCaseOrNameIgnoreCase(any(String.class), any(String.class)))
                .thenReturn(faculties);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculties?nameorcolor=" + color)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpectAll();
    }

    @Test
    void testFindStudentsOfFaculty() throws Exception {
        when(facultyRepository.findById(any(Long.class))
                .map(Faculty::getStudents)
                .orElse(Collections.emptyList())).thenReturn(students);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("1/students")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpectAll();
    }

}