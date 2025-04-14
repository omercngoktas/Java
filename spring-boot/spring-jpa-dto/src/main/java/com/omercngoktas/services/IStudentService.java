package com.omercngoktas.services;

import java.util.List;

import com.omercngoktas.dto.DtoStudent;
import com.omercngoktas.dto.DtoStudentIU;

public interface IStudentService {
    
    public List<DtoStudent> getStudentList();

    public DtoStudent getStudentById(Integer id);

    public DtoStudent addStudent(DtoStudentIU dtoStudentIU);

    public void deleteStudent(Integer id);

    public DtoStudent updateStudent(Integer id, DtoStudentIU dtoStudentIU);
}
