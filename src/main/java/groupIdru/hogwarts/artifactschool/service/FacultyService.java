package groupIdru.hogwarts.artifactschool.service;

import groupIdru.hogwarts.artifactschool.model.Faculty;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
//
@Service
public class FacultyService {

    private final Map<Long, Faculty> faculties = new HashMap<>();
    private long lastId = 0;

    public Map<Long, Faculty> getAllFaculties() {
        return faculties;
    }

    public Faculty getFaculty(Long id) {
        return faculties.get(id);
    }

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(++lastId);
        faculties.put(lastId, faculty);
        return faculty;
    }

    public Faculty editFaculty(Faculty faculty) {
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty deleteFaculty(long id) {
        return faculties.remove(id);
    }

    public List<Faculty> getFacultyColor(String color) {
        return faculties.values().stream()
                .filter(e -> e.getColor().equals(color))
                .collect(Collectors.toList());
    }
}
