package groupIdru.hogwarts.artifactschool.service;

import groupIdru.hogwarts.artifactschool.Exception.EntityNotFoundException;
import groupIdru.hogwarts.artifactschool.model.Faculty;
import groupIdru.hogwarts.artifactschool.model.Student;
import groupIdru.hogwarts.artifactschool.repositiries.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    private final FacultyRepository studentRepository;

    public FacultyService(FacultyRepository facultyRepository, FacultyRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    public Collection<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    public Faculty getFaculty(long id) {
        return facultyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        facultyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> findByColor(String color) {
        return facultyRepository.findByColor(color);
    }

    public Collection<Faculty> findByColorOrName(String param) {
        return facultyRepository.findByColorIgnoreCaseOrNameIgnoreCase(param);
    }

    public Collection<Student> findStudentsOfFaculty(String name) {
        Faculty faculty = facultyRepository.findByNameIgnoreCase(name);
        return studentRepository.findByFacultyContainsIgnoreCase(faculty);
    }

}
