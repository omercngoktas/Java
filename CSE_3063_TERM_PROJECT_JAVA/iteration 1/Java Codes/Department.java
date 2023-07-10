package com.company;

import java.util.ArrayList;
import java.util.List;

class Department {
    private List<Advisor> advisors = new ArrayList<Advisor>();
    private List<Student> students = new ArrayList<Student>();
    private List<Course> courses = new ArrayList<Course>();
    private final List<Semester> semesters = new ArrayList<Semester>();


    public Department(List<Advisor> advisors, List<Student> students, List<Course> courses) {
        this.advisors = advisors;
        this.students = students;
        this.courses = courses;
    }

    public List<Semester> getSemesters() {
        return semesters;
    }

    public void setSemesters(Semester tempsemester) {
        this.semesters.add(tempsemester);
    }

    public List<Advisor> getAdvisors() {
        return advisors;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setAdvisors(Advisor advisor) {
        this.advisors.add(advisor);
    }

    public void setStudents(Student student) {
        this.students.add(student);
    }

    public void setCourses(Course course) {
        this.courses.add(course);
    }

    void createStudent(){

    }

    void createCourse(){

    }

    void createSemester(String status){
        if(status.equals("Fall")){
            for(int i=1;i<8;i+=2){
                Semester tempsemester = new Semester(i,"Fall");
                this.setSemesters(tempsemester);
            }
        }
        else if(status.equals("Spring")){
            for(int i=2;i<=8;i+=2){
                Semester tempsemester = new Semester(i,"Spring");
                this.setSemesters(tempsemester);
            }

        }


    }

    void createAdvisor(){

    }



}
