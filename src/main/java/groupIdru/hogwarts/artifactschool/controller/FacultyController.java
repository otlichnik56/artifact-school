package groupIdru.hogwarts.artifactschool.controller;

import groupIdru.hogwarts.artifactschool.model.Faculty;
import groupIdru.hogwarts.artifactschool.model.Student;
import groupIdru.hogwarts.artifactschool.service.FacultyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("faculties")
public class FacultyController {

    private final FacultyService facultyService;
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }


    // домашка 4.5
    @GetMapping("/4.5/long_name_of_faculty")
    public Optional<String> getLongNameOfFaculty() {
        return facultyService.getLongNameOfFaculty();
    }
    @GetMapping("/4.5/integer_value")
    public Integer getIntegerValue() {
        return facultyService.getIntegerValue();
    }





    // до текущей домашки
    @GetMapping
    public Collection<Faculty> getAllFaculties() {
        return facultyService.getAllFaculties();
    }

    @GetMapping("{id}")
    public Faculty getFaculty(@PathVariable Long id) {
        return facultyService.getFaculty(id);
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @PutMapping
    public Faculty editFaculty(@RequestBody Faculty faculty) {
        return facultyService.editFaculty(faculty);
    }

    @DeleteMapping("id")
    public ResponseEntity deleteFaculty(@RequestParam Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("color")
    public Collection<Faculty> findByColor(@RequestParam String color) {
        return facultyService.findByColor(color);
    }

    @GetMapping("nameorcolor")
    public Collection<Faculty> findByColorOrName(@RequestParam String param) {
        return facultyService.findByColorOrName(param);
    }

    @GetMapping("{id}/students")
    public Collection<Student> findStudentsOfFaculty(@PathVariable long id) {
        return facultyService.findStudentsOfFaculty(id);
    }

}
