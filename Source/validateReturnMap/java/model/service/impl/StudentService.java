package model.service.impl;

import model.bean.Student;
import model.repository.IStudentRepository;
import model.repository.impl.StudentRepository;
import model.service.IStudentService;
import util.Validation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentService implements IStudentService {
    // gọi repository
    IStudentRepository studentRepository = new StudentRepository();
    @Override
    public List<Student> getAllStudent() {

        return studentRepository.getAllStudent();
    }

    @Override
    public Student findById(int id) {
        return null;
    }

    @Override
    public Map<String, String> save(Student student) {
        // cần kiểm tra dự liệu
        // +nếu mà ok => gọi repository để lưu.
        Map<String, String> map = new HashMap<>();
        if (student.getPoint()<0){
            map.put("point","Diem phai lon 0");
        }else if (student.getPoint()>10){
            map.put("point","Diem phai nho hon 10");
        }
        if(!Validation.checkEmail(student.getEmail())){
            map.put("email", "Email không đúng định dạng");
        }
        if (map.isEmpty()){
            studentRepository.save(student);
        }

       return map;

    }

    @Override
    public Boolean delete(int id) {
        return studentRepository.delete(id);
    }

    @Override
    public void edit(Student student) {

    }

}
