package groupIdru.hogwarts.artifactschool.service;

import groupIdru.hogwarts.artifactschool.Exception.EntityNotFoundException;
import groupIdru.hogwarts.artifactschool.model.Faculty;
import groupIdru.hogwarts.artifactschool.model.Student;
import groupIdru.hogwarts.artifactschool.repositiries.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class StudentService {

    Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    // домашка 4.5
    public List<Student> getStudentsToLatter(String start) {
        return studentRepository.findAll();
    }
    public Double getAvgAge() {
        return 10.5;
    }





    // до текущей домашки
    public Integer getNumberOfAllStudents() {
        logger.info("Was invoked method for get number of all students");
        Integer number = studentRepository.getNumberOfAllStudents();
        logger.debug("Hogwarts has " + number + " students");
        return number;
    }
    public Double getAvgAgeOfAllStudents() {
        logger.info("Was invoked method for get avg age of all students");
        Double avgAge = studentRepository.getAvgAgeOfAllStudents();
        logger.debug("Avg age of all students is  " + avgAge);
        return avgAge;
    }
    public List<Student> getFiveLastStudents() {
        logger.info("Was invoked method for get five last students");
        return studentRepository.getFiveLastStudents();
    }

    public Collection<Student> getAllStudents() {
        logger.info("Was invoked method for get all students");
        return studentRepository.findAll();
    }

    public Student getStudent(long id) {
        logger.info("Was invoked method for get student");
        logger.error("There is not student with id = " + id);
        return studentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Student createStudent(Student student) {
        logger.info("Was invoked method for create student");
        return studentRepository.save(student);
    }

    public Student editStudent(Student student) {
        logger.info("Was invoked method for edit student");
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.info("Was invoked method for delete student");
        logger.error("There is not student with id = " + id);
        studentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        studentRepository.deleteById(id);
    }

    public Collection<Student> findByAge(int age){
        logger.info("Was invoked method for find de age students");
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(int minAge, int maxAge){
        logger.info("Was invoked method for find by age between students");
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    public Faculty findFaculty(Long id){
        logger.info("Was invoked method for find by faculty student");
        return studentRepository.findById(id)
                .map(Student::getFaculty)
                .orElse(null);
        }

}
