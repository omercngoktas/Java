package com.company;

import java.util.ArrayList;
import java.util.List;

public class RegistrationCart {
    private List<Registration> registrations = new ArrayList<Registration>();
    private boolean isApproved;
    private Advisor registeredBy;
    private int totalCredit;

    public RegistrationCart(){

    }


    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(Registration registration) {
        this.registrations.add(registration);
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    public int getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(int totalCredit) {
        this.totalCredit += totalCredit;
    }

    public void decTotalCredit(int credit){this.totalCredit -= credit;}

    public void resetTotalCredit(){
        this.totalCredit = 0;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public Advisor getRegisteredBy() {
        return registeredBy;
    }

    public void setRegisteredBy(Advisor registeredBy) {
        this.registeredBy = registeredBy;
    }
}
