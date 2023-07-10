package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println("System online....");

        Department department = new Department(null,null,null);
        department.createSemester("Fall");
        department.createCourse();
//        System.out.println(department.getSemesters());
//        System.out.println(department.getSemesters().get(3).getNumberOfSemester());












    }
}
