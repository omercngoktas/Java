package com.omercngoktas.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omercngoktas.controller.IStudentController;
import com.omercngoktas.dto.DtoStudent;
import com.omercngoktas.dto.DtoStudentIU;
import com.omercngoktas.services.IStudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/rest/api/student")
public class StudentControllerImpl implements IStudentController {

    @Autowired
    private IStudentService studentService;

    @Override
    @PostMapping(path = "/add")
    public DtoStudent addStudent(@RequestBody @Valid DtoStudentIU dtoStudentIU) {
        return studentService.addStudent(dtoStudentIU);
    }

    @Override
    @DeleteMapping(path = "/delete/{id}")
    public void deleteStudent(@PathVariable(name = "id", required = true) Integer id) {
        studentService.deleteStudent(id);
    }

    @Override
    @GetMapping(path = "/list/{id}")
    public DtoStudent getStudentById(@PathVariable(name = "id", required = true) Integer id) {
        return studentService.getStudentById(id);
    }

    @Override
    @GetMapping(path = "/list")
    public List<DtoStudent> getStudentList() {
        return studentService.getStudentList();
    }

    @Override
    @PutMapping(path = "/update/{id}")
    public DtoStudent updateStudent(@PathVariable(name = "id", required = true) Integer id, @RequestBody DtoStudentIU dtoStudentIU) {
        return studentService.updateStudent(id, dtoStudentIU);
    }

}
