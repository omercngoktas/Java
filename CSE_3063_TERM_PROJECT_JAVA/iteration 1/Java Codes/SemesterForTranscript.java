package com.company;

import java.util.HashMap;

class SemesterForTranscript extends Semester{
    private HashMap<Course,String> grades = new HashMap<Course,String>();
    private double semesterGpa;

    public SemesterForTranscript(int numberOfSemester, String status, HashMap<Course, String> grades, double semesterGpa) {
        super(numberOfSemester, status);
        this.grades = grades;
        this.semesterGpa = semesterGpa;
    }

    public HashMap<Course, String> getGrades() {
        return grades;
    }

    public void setGrades(HashMap<Course, String> grades) {
        this.grades = grades;
    }

    public double getSemesterGpa() {
        return semesterGpa;
    }

    public void setSemesterGpa(double semesterGpa) {
        this.semesterGpa = semesterGpa;
    }
}
