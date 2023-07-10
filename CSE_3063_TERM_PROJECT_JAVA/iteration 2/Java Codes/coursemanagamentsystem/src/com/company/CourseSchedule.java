package com.company;

import java.util.HashMap;

class CourseSchedule {
    private HashMap<String, String> courseTime = new HashMap<String, String>();

    public CourseSchedule() {

    }

    public HashMap<String, String> getCourseSchedule() {
        return courseTime;
    }

    public void setCourseSchedule(String day, String time) {
        this.courseTime.put(day,time);
    }
}
