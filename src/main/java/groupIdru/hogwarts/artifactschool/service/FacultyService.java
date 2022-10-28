package groupIdru.hogwarts.artifactschool.service;

import groupIdru.hogwarts.artifactschool.Exception.EntityNotFoundException;
import groupIdru.hogwarts.artifactschool.model.Faculty;
import groupIdru.hogwarts.artifactschool.model.Student;
import groupIdru.hogwarts.artifactschool.repositiries.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Stream;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    // домашка 4.5
    public String getLongNameOfFaculty() {
        return facultyRepository.findAll().stream()
                                .max(Comparator.comparing(faculty -> faculty.getName().length()))
                                .toString();
    }
    public Integer getIntegerValue() {
        return Stream.iterate(1, a -> a +1)
                        .limit(1_000_000)
                        .reduce(0, Integer::sum);
    }





    // до текущей домашки
    public Collection<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    public Faculty getFaculty(long id) {
        return facultyRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        facultyRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> findByColor(String color) {
        return facultyRepository.findByColorIgnoreCase(color);
    }

    public Collection<Faculty> findByColorOrName(String name) {
        return facultyRepository.findByColorIgnoreCaseOrNameIgnoreCase(name, name);
    }

    public Collection<Student> findStudentsOfFaculty(long id) {
        return facultyRepository.findById(id)
                .map(Faculty::getStudents)
                .orElse(Collections.emptyList());
     }

}
