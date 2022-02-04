package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {

        JSONParser parser = new JSONParser();

        JSONObject input = (JSONObject) parser.parse(new FileReader("src/com/company/input.json"));

        String semesterKey = input.get("semester").toString();
        int numberOfStudentsForASemester =  Integer.parseInt((input.get("stdCountForSemester").toString()));
        int numberOfAdvisors = Integer.parseInt(input.get("numberofadvisors").toString());
        String useOldStudents_s = input.get("useOldStudents").toString();
        boolean useOldStudents = Boolean.parseBoolean(useOldStudents_s);
        int failProbabiltyPercentage = Integer.parseInt(input.get("failProbabiltyPercentage").toString());

        List<String> name_male_pool = (List<String>) input.get("name_male_pool");
        List<String> name_female_pool = (List<String>) input.get("name_female_pool");
        List<String> surname_pool = (List<String>) input.get("surname_pool");
        List<String> ranks = (List<String>) input.get("ranks");

        System.out.println("\nSystem online....");

        Department department = new Department();

        department.createSemester();
        System.out.println("Semesters initialized...");
        department.createCourses(input);
        System.out.println("Courses initialized...");
        department.createAdvisor(name_male_pool,name_female_pool,surname_pool,ranks,numberOfAdvisors);
        System.out.println("Advisors initialized...");

        if(useOldStudents == false){
            department.createStudent(numberOfStudentsForASemester,name_male_pool,name_female_pool,surname_pool,semesterKey);
            System.out.println("Students initialized...");
            department.startSimulation(failProbabiltyPercentage);
            department.system_output_students();
        }
        else {
            File students = new File("src/Students");
            System.out.println("Students loading...");
            department.usedStudentsTransfer(students);
        }


    }
}
