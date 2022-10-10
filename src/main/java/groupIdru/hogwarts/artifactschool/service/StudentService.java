package groupIdru.hogwarts.artifactschool.service;

import groupIdru.hogwarts.artifactschool.Exception.EntityNotFoundException;
import groupIdru.hogwarts.artifactschool.model.Faculty;
import groupIdru.hogwarts.artifactschool.model.Student;
import groupIdru.hogwarts.artifactschool.repositiries.FacultyRepository;
import groupIdru.hogwarts.artifactschool.repositiries.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;

    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }


    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudent(long id) {
        return studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        studentRepository.deleteById(id);
    }

    public Collection<Student> findByAge(int age){
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(int minAge, int maxAge){
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    public Faculty findFaculty(Long id){
        return studentRepository.findById(id)
                .map(Student::getFaculty)
                .orElse(null);
        }

}
