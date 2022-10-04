package groupIdru.hogwarts.artifactschool.controller;

import groupIdru.hogwarts.artifactschool.model.Student;
import groupIdru.hogwarts.artifactschool.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
//
@RestController
@RequestMapping("students")
public class StudentController {

    private final StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public Map<Long, Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping
    public Student editStudent(@RequestBody Student student) {
        return studentService.editStudent(student);
    }

    @DeleteMapping("{id}")
    public Student deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

    @GetMapping("/age/{age}")
    public List<Student> getStudentsAge(@PathVariable int age) {
        return studentService.getStudentsAge(age);
    }

}
