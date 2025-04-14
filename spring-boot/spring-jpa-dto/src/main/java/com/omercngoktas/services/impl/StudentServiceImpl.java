package com.omercngoktas.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omercngoktas.dto.DtoStudent;
import com.omercngoktas.dto.DtoStudentIU;
import com.omercngoktas.entities.Student;
import com.omercngoktas.repository.StudentRepository;
import com.omercngoktas.services.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public DtoStudent addStudent(DtoStudentIU dtoStudentIU) {
        Student student = dtoStudentIUToStudent(dtoStudentIU);
        Student dbStudent = studentRepository.save(student);
        return studentToDtoStudent(dbStudent);
    }

    @Override
    public void deleteStudent(Integer id) {
        Student student = findStudentById(id);
        if(student != null) {
            studentRepository.delete(student);
        } else {
            return;
        }
    }

    @Override
    public DtoStudent getStudentById(Integer id) {
        return studentToDtoStudent(findStudentById(id));
    }

    @Override
    public List<DtoStudent> getStudentList() {
        List<Student> studentList = studentRepository.findAll();
        List<DtoStudent> dtoStudentList = new ArrayList<>();
        
        for(Student student : studentList) {
            dtoStudentList.add(studentToDtoStudent(student));
        }

        return dtoStudentList;
    }

    @Override
    public DtoStudent updateStudent(Integer id, DtoStudentIU dtoStudentIU) {
        Student student = findStudentById(id);
        if(student != null) {
            student.setFirstName(dtoStudentIU.getFirstName());
            student.setLastName(dtoStudentIU.getLastName());
            student.setEmail(dtoStudentIU.getEmail());
            student.setDateOfBirth(dtoStudentIU.getDateOfBirth());
            return studentToDtoStudent(studentRepository.save(student));
        } else {
            return null;
        }
    }

    private Student dtoStudentIUToStudent(DtoStudentIU dtoStudentIU) {
        Student student = new Student(); 
        BeanUtils.copyProperties(dtoStudentIU, student);
        return student;
    }

    private DtoStudent studentToDtoStudent(Student student) {
        if(student == null) {
            return null;
        }
        DtoStudent dtoStudent = new DtoStudent();
        BeanUtils.copyProperties(student, dtoStudent);
        return dtoStudent;
    }

    private Student findStudentById(Integer id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(optionalStudent.isPresent()) {
            return optionalStudent.get();
        }
        return null;
    }
}
