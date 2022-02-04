package com.company;

class Semester {
    private int numberOfSemester;
    private String status;

    public Semester() {

    }


    public Semester(int numberOfSemester, String status) {
        this.numberOfSemester = numberOfSemester;
        this.status = status;
    }

    public int getNumberOfSemester() {
        return numberOfSemester;
    }

    public void setNumberOfSemester(int numberOfSemester) {
        this.numberOfSemester = numberOfSemester;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
