package com.company;

import java.util.ArrayList;
import java.util.List;

class Advisor extends Person {
    private String rank;
    private List<Student> advisee = new ArrayList<Student>();
    private List<Registration> registrationRequests = new ArrayList<Registration>();

    public Advisor(String rank) {
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public List<Student> getAdvisee() {
        return advisee;
    }

    public void setAdvisee(List<Student> advisee) {
        this.advisee = advisee;
    }

    public List<Registration> getRegistrationRequests() {
        return registrationRequests;
    }

    public void setRegistrationRequests(List<Registration> registrationRequests) {
        this.registrationRequests = registrationRequests;
    }
}
