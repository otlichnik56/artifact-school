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
import java.util.OptionalDouble;
import java.util.stream.Collectors;


@Service
public class StudentService {

    private final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final Object flag = new Object();
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    // домашка 4.6
    public void getAllStudentsParallelStreams() {
        printStudentOfParallelStreams(studentRepository.findById(1L).get());

        Thread oneThread = new Thread(() -> {
            printStudentOfParallelStreams(studentRepository.findById(3L).get());
            printStudentOfParallelStreams(studentRepository.findById(4L).get());
        });
        oneThread.start();

        Thread twoThread = new Thread(() -> {
            printStudentOfParallelStreams(studentRepository.findById(5L).get());
            printStudentOfParallelStreams(studentRepository.findById(6L).get());
        });
        twoThread.start();

        printStudentOfParallelStreams(studentRepository.findById(2L).get());
        oneThread.interrupt();
        twoThread.interrupt();
    }
    public void printStudentOfParallelStreams(Student student) {
        System.out.println(student.getId() + " " + student.getName());
    }

    public void getAllStudentsSynchronizedStreams() {
        printStudentOfSynchronizedStreams(studentRepository.findById(1L).get());
        printStudentOfSynchronizedStreams(studentRepository.findById(2L).get());

        Thread oneThread = new Thread(() -> {
            synchronized (flag) {
                printStudentOfSynchronizedStreams(studentRepository.findById(3L).get());
                printStudentOfSynchronizedStreams(studentRepository.findById(4L).get());
            }
        });
        oneThread.start();

        Thread twoThread = new Thread(() -> {
            synchronized (flag) {
                printStudentOfSynchronizedStreams(studentRepository.findById(5L).get());
                printStudentOfSynchronizedStreams(studentRepository.findById(6L).get());
            }
        });
        twoThread.start();

    }
    public void printStudentOfSynchronizedStreams(Student student) {
        System.out.println(student.getId() + " " + student.getName());
    }





    // домашка 4.5
    public List<String> getStudentsToLatter(String start) {
        logger.info("Was invoked method for get students to the latter " + start);
        return studentRepository.findAll().stream()
                .map(student -> student.getName().toUpperCase())
                .sorted()
                .filter(s -> s.startsWith(start.toUpperCase()))
                .collect(Collectors.toList());
    }
    public OptionalDouble getAvgAge() {
        logger.info("Was invoked method for get avg age of all students");
        return (studentRepository.findAll().stream()
                .map(Student::getAge))
                .mapToInt(e -> e)
                .average();
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
