package com.company;

import java.util.HashMap;

class CourseSchedule {
    private HashMap<String, String> courseTime = new HashMap<String, String>();

    public CourseSchedule(HashMap<String, String> courseSchedule) {
        this.courseTime = courseSchedule;
    }

    public HashMap<String, String> getCourseSchedule() {
        return courseTime;
    }

    public void setCourseSchedule(HashMap<String, String> courseSchedule) {
        this.courseTime = courseSchedule;
    }
}
