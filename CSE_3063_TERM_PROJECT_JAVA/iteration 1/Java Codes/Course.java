package com.company;

import java.util.ArrayList;
import java.util.List;

class Course {
    private String courseID;
    private int credit;
    private Course prequisite;
    private Semester canTakenSemester;
    private CourseSchedule courseProgram;
    private List<Student> registeredStudents = new ArrayList<Student>();

    public Course(String courseID, int credit, Course prequisite, Semester canTakenSemester) {
        this.courseID = courseID;
        this.credit = credit;
        this.prequisite = prequisite;
        this.canTakenSemester = canTakenSemester;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public Course getPrequisite() {
        return prequisite;
    }

    public void setPrequisite(Course prequisite) {
        this.prequisite = prequisite;
    }

    public Semester getCanTakenSemester() {
        return canTakenSemester;
    }

    public void setCanTakenSemester(Semester canTakenSemester) {
        this.canTakenSemester = canTakenSemester;
    }

    public CourseSchedule getCourseProgram() {
        return courseProgram;
    }

    public void setCourseProgram(CourseSchedule courseProgram) {
        this.courseProgram = courseProgram;
    }

    public List<Student> getRegisteredStudents() {
        return registeredStudents;
    }

    public void setRegisteredStudents(List<Student> registeredStudents) {
        this.registeredStudents = registeredStudents;
    }
}
