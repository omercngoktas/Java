package com.company;

import java.util.HashMap;

class SemesterForTranscript extends Semester{
    private HashMap<Course,String> grades = new HashMap<Course,String>();
    private double semesterGpa;
    private int numberofSemester;


    public SemesterForTranscript() {
        super();


    }

    public HashMap<Course, String> getGrades() {
        return grades;
    }

    public void setGrades(Course course, String grade) {
        this.grades.put(course,grade);
    }


    public double getSemesterGpa() {
        return semesterGpa;
    }

    public void setSemesterGpa(double semesterGpa) {
        this.semesterGpa = semesterGpa;
    }
}
