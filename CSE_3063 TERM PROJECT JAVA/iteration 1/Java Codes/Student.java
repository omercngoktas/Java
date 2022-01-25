package com.company;

import java.util.ArrayList;
import java.util.List;

class Student extends Person{
    private List<Course> activeCourses = new ArrayList<Course>();
    private List<Course> passiveCourses = new ArrayList<Course>();
    private Semester curentsemester;
    private Advisor advisor;
    private Transcript transcript;
    private StudentNumber studentNumber;


    public Student(Semester curentsemester, Advisor advisor) {
        this.curentsemester = curentsemester;
        this.advisor = advisor;
    }

    public List<Course> getActiveCourses() {
        return activeCourses;
    }

    public StudentNumber getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(StudentNumber studentNumber) {
        this.studentNumber = studentNumber;
    }

    public void setActiveCourses(List<Course> activeCourses) {
        this.activeCourses = activeCourses;
    }

    public List<Course> getPassiveCourses() {
        return passiveCourses;
    }

    public void setPassiveCourses(List<Course> passiveCourses) {
        this.passiveCourses = passiveCourses;
    }

    public Semester getCurentsemester() {
        return curentsemester;
    }

    public void setCurentsemester(Semester curentsemester) {
        this.curentsemester = curentsemester;
    }

    public Advisor getAdvisor() {
        return advisor;
    }

    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
    }

    public Transcript getTranscript() {
        return transcript;
    }

    public void setTranscript(Transcript transcript) {
        this.transcript = transcript;
    }
}
