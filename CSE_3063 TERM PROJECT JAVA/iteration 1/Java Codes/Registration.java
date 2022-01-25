package com.company;

class Registration {
    private Course course ;
    private Student student;
    private boolean isApproved;
    private Advisor registeredBy;
    private String description;

    public Registration(Course course, Student student) {
        this.course = course;
        this.student = student;
    }

    public Advisor getRegisteredBy() {
        return registeredBy;
    }

    public void setRegisteredBy(Advisor registeredBy) {
        this.registeredBy = registeredBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}
