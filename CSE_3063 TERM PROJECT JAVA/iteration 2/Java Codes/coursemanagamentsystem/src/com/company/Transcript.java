package com.company;

import java.util.ArrayList;
import java.util.List;

class Transcript {

    private List<SemesterForTranscript> semesters = new ArrayList<SemesterForTranscript>();
    private double cumGPA;

    public Transcript() {

    }

    public List<SemesterForTranscript> getSemesters() {
        return semesters;
    }

    public void setSemesters(SemesterForTranscript semesters) {
        this.semesters.add(semesters);
    }

    public double getCumGPA() {
        return cumGPA;
    }

    public void setCumGPA(double cumGPA) {
        this.cumGPA = cumGPA;
    }
}
