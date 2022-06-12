package model.service;

import model.bean.Student;

import java.util.List;
import java.util.Map;

public interface IStudentService {
    List<Student> getAllStudent();
    Student findById(int id );
    Map<String, String> save(Student student);
    Boolean delete(int id);
    void edit(Student student);
}
