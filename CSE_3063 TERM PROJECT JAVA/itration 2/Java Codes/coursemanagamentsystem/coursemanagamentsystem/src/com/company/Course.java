package com.company;

import java.util.ArrayList;
import java.util.List;

class Course {
    private String courseID;
    private int credit;
    private List<Course> prerequisiteCourses = new ArrayList<Course>();
    private Semester canTakenSemester;
    private CourseSchedule courseProgram;
    private List<Student> registeredStudents = new ArrayList<Student>();
    private int quota = 0;

    public Course(String courseID, int credit, Semester canTakenSemester) {
        this.courseID = courseID;
        this.credit = credit;
        this.canTakenSemester = canTakenSemester;
    }

    public Course() {

    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
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

    public List<Course> getPrerequisiteCourses() {
        return prerequisiteCourses;
    }

    public void setPrerequisiteCourses(Course prerequisiteCourses) {
        this.prerequisiteCourses.add(prerequisiteCourses);
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

    public void setRegisteredStudents(Student registeredStudent) {
        this.registeredStudents.add(registeredStudent);
    }
}
