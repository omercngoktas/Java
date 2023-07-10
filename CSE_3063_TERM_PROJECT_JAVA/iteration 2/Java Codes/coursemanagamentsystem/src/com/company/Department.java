package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Department {
    private List<Advisor> advisors = new ArrayList<Advisor>();
    private List<Student> students = new ArrayList<Student>();
    private List<Course> courses = new ArrayList<Course>();
    private final List<Semester> semesters = new ArrayList<Semester>();

    public Department() {

    }


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

    void createStudent(int noofstdforasem, List<String> name_male_pool,List<String> name_female_pool,List<String> surname_pool, String status){

        int stdcounter= 0;
        int year = 2023;

        List<Semester> tempsemester = new ArrayList<Semester>();

        for(int g = 0; g<this.semesters.size(); g++){
            if(this.semesters.get(g).getStatus().equals(status)){
                    tempsemester.add(this.semesters.get(g));
            }
        }

        for(int semestercount = 0; semestercount<4; semestercount++){
            var cursem = tempsemester.get(semestercount);
            for (int i = 0; i < noofstdforasem; i++){
                stdcounter += 1;
                Student student = new Student(cursem,null);
                int genderRandom = (int) (Math.random() * 2) ;

                if(genderRandom == 0){
                    int range = (name_male_pool.size()-1);
                    String random_name = name_male_pool.get((int) (Math.random() * range) + 1);
                    student.setName(random_name);

                }
                else {
                    int range = (name_female_pool.size()-1);
                    String random_name = name_female_pool.get((int) (Math.random() * range) + 1);
                    student.setName(random_name);

                }

                int range = (surname_pool.size()-1);
                String random_surname = surname_pool.get((int) (Math.random() * range) + 1);
                student.setSurname(random_surname);

                StudentNumber stnum = new StudentNumber(stdcounter, year);
                int yearint = (stnum.getYear());
                String yearstr = Integer.toString((stnum.getYear()));
                String lastTwoIndex = yearstr.substring(2);

                String strnum = "";
                if(stnum.getNumber() < 10){ strnum = "00" + stnum.getNumber();}
                else if(stnum.getNumber() < 100){ strnum = "0" + stnum.getNumber();}
                else{strnum = Integer.toString(stnum.getNumber());}

                stnum.setStudentNumber("1501" + lastTwoIndex + strnum);
                student.setStudentNumber(stnum);

                int currentSemester = cursem.getNumberOfSemester();
                if(currentSemester == 1){student.setCredit(31);}
                else if(currentSemester == 2 || currentSemester == 3 || currentSemester == 6 || currentSemester == 8)
                {student.setCredit(30);}
                else if(currentSemester == 4){student.setCredit(29);}
                else if(currentSemester == 5){student.setCredit(39);}
                else if(currentSemester == 7){student.setCredit(41);}

                int rangeforadvisor = (this.advisors.size()-1);
                student.setAdvisor(this.advisors.get((int) (Math.random() * rangeforadvisor) + 1));
                this.advisors.get((int) (Math.random() * rangeforadvisor) + 1).setAdvisee(student);

                Transcript temptr = new Transcript();
                for(int u = 0; u<8; u++){
                    SemesterForTranscript tempsemtr = new SemesterForTranscript();
                    tempsemtr.setNumberOfSemester(u+1);
                    temptr.setSemesters(tempsemtr);
                }
                student.setTranscript(temptr);
                this.setStudents(student);
            }
            year -= 1;
        }
    }

    void startSimulation(int failProbabiltyPercentage){

        for(int startSem = 8;startSem>0;startSem--) {

            List<Student> templist = new ArrayList<Student>(); //geçici student listesi

            for (int i = 0; i < this.students.size(); i++) { //students içinde dön
                var currentiter = this.students.get(i); //şu anki student
                if (currentiter.getCurentsemester().getNumberOfSemester() == startSem) { //eğer aradığımız dönemde ise
                    templist.add(currentiter); //geçici listeye ekle
                }
            }
            
            if(templist.isEmpty()){ //boşsa başa dön
                continue;
            }

            for (int j = 0; j<templist.size();j++){

                var currentstudent = templist.get(j); // şu an ki student
                try{
                    currentstudent.getRegistrationCart().getRegistrations().clear();
                    currentstudent.setRegistrationCart(null);
                }catch(Exception e){

                }

                RegistrationCart regCart = new RegistrationCart();
                currentstudent.setRegistrationCart(regCart);

                for(int sno = 0; sno<8; sno++) { //en baş dönemden başla, öğr. dönemine kadar git.
                    currentstudent.getRegistrationCart().getRegistrations().clear();
                    currentstudent.getRegistrationCart().resetTotalCredit();
                    if (sno + 1 == 1) {
                        currentstudent.setCredit(31);
                    } else if (sno + 1 == 2 || sno + 1 == 3 || sno + 1 == 6 || sno + 1 == 8) {
                        currentstudent.setCredit(30);
                    } else if (sno + 1 == 4) {
                        currentstudent.setCredit(29);
                    } else if (sno + 1 == 5) {
                        currentstudent.setCredit(39);
                    } else if (sno + 1 == 7) {
                        currentstudent.setCredit(41);
                    }


                    if(!currentstudent.getPassiveCourses().isEmpty()){ //passive kursu varsa
                        System.out.println("-" + currentstudent.getPassiveCourses());
                        for(var passivecourse: currentstudent.getPassiveCourses()){
                            System.out.println("##");
                            Registration tempreg = new Registration(passivecourse,currentstudent);
                            currentstudent.getRegistrationCart().setRegistrations(tempreg); //passive kursu reg oluştur ve carta ekle
                            currentstudent.getRegistrationCart().setTotalCredit(tempreg.getCourse().getCredit());
                        }
                    }

                    for (int g = 0; g < this.courses.size(); g++) { //kurslar içinde gez
                        if (this.courses.get(g).getCanTakenSemester().getNumberOfSemester() == sno + 1) { //eğer kurs ilgilendiğimiz dönemde ise
                            Registration tempreg = new Registration(this.courses.get(g), currentstudent); //registration oluştur
                            currentstudent.getRegistrationCart().setRegistrations(tempreg); //registrationu öğrencinin sepetine ekle
                            currentstudent.getRegistrationCart().setTotalCredit(tempreg.getCourse().getCredit()); //kredi topla
                        }

                    } //tüm registrationlar oluştu. şimdi sırada kontroller var.

                    currentstudent.takeTE(this.courses); //if technical elective exists, take random.

                    System.out.println("\n");
                    System.out.println("---------> " + (sno + 1));
                    while (true){

                        int key = currentstudent.getAdvisor().checkRegistrationCart(currentstudent, failProbabiltyPercentage); //advisora kontrol için gönderildi.
                        if (key == 2) { //prerequisite sorunu var ise

//                            System.out.println("here///////->");
                            currentstudent.editCart_Prereq();
                            continue;

                        }else if (key == 1){ //kredi yetersiz
                            currentstudent.editCart_Credit();
                            continue;

                        }else if (key == 0) { //cart onaylandı ise
                        for (int x = 0; x < currentstudent.getRegistrationCart().getRegistrations().size(); x++) { //reg içinde gez
                            var currentreg = currentstudent.getRegistrationCart().getRegistrations().get(x);//reg
                            if (currentreg.isApproved()) { //reg onaylandı ise
                                currentstudent.setActiveCourses(currentreg.getCourse()); //öğrencinin aktif kurslarına ekle
                                for (int y = 0; y < currentstudent.getTranscript().getSemesters().size(); y++) { //öğrencinin transkript dönemlerinde gez
                                    var cursem = currentstudent.getTranscript().getSemesters().get(y); //dönem
                                    if (cursem.getNumberOfSemester() == sno + 1) { //ilgili transkrpt dönemi bulundu ise
                                        cursem.setGrades(currentreg.getCourse(), "*");
//                                        System.out.println(cursem.getGrades().keySet().stream().toList().get(0).getCourseID());
                                    }
                                }
                            }
                        }
                        currentstudent.calculateGrades(failProbabiltyPercentage); //not hesaplaması yap
                        break;
                    }

                }
                    if (sno + 1 == currentstudent.getCurentsemester().getNumberOfSemester()) { //öğr. dönemine geldiysek duruyoruz.
                        break;
                    }

                }
            }
        }
    }

    void createCourses(JSONObject input){
        JSONArray courses = (JSONArray)  input.get("courses"); //jsondan course parsed
        JSONArray te_courses = (JSONArray) input.get("technicelElectiveCourses"); //te courses parse

        for (int i = 0; i<courses.size(); i++){ //json kursları içinde gez
            JSONObject course = (JSONObject) courses.get(i); //objeye dönüştür
            String courseID = course.get("courseID").toString(); //kurs id al
            int credit = Integer.parseInt(course.get("credit").toString()); //kurs kredi al
            int canTakenSemester = Integer.parseInt(course.get("canTakenSemester").toString()); //kurs dönemini al
            int k = 0;

            for (int j = 0; j < this.semesters.size(); j++){ //dönemi ara
                if (this.semesters.get(j).getNumberOfSemester() == canTakenSemester){
                    k = j; //dönemin indexini bul eşleştir
                    break;
                }
            }

            Course tempcourse = new Course(courseID,credit,this.semesters.get(k)); //kurs oluştu
            CourseSchedule plan = new CourseSchedule(); //schedule oluştur
            JSONArray coursePlans = (JSONArray) course.get("courseProgram"); //kurs program çek

            for(int u = 0; u < coursePlans.size(); u++){ //kursun programının günleri arasında gez
                JSONObject ob = (JSONObject) coursePlans.get(u); //gün ve saati objeye çevir
                var f1 = ob.keySet().toString(); //günü stringe dönüştür
                var f2 = ob.values().toString(); //saati stringe dönüştür
                var day = f1.substring(1,f1.length()-1); //günü tube dan ayır
                var time = f2.substring(1,f2.length()-1); //saati tube dan ayır
                plan.setCourseSchedule(day,time); //course schedule set
            }
            tempcourse.setCourseProgram(plan); //schedule u kursa ekle
            this.courses.add(tempcourse); //kursu departman kurslar listesine ekle
        }

        for (int i = 0; i<te_courses.size(); i++) { //json kursları içinde gez
            JSONObject te_course = (JSONObject) te_courses.get(i); //objeye dönüştür
            String courseID = te_course.get("courseID").toString(); //kus id al
            int credit = Integer.parseInt(te_course.get("credit").toString()); //kurs kredi al
            Semester tempsem = new Semester();
            tempsem.setNumberOfSemester(-1);
            tempsem.setStatus("TE");

            Course tempcourse = new Course(courseID,credit,tempsem); //kurs oluştu
            CourseSchedule plan = new CourseSchedule(); //schedule oluştur
            JSONObject coursePlans = (JSONObject) te_course.get("courseProgram"); //kurs program çek

            var f1 = coursePlans.keySet().toString(); //günü stringe dönüştür
            var f2 = coursePlans.values().toString(); //saati stringe dönüştür
            var day = f1.substring(1,f1.length()-1); //günü tube dan ayır
            var time = f2.substring(1,f2.length()-1); //saati tube dan ayır

            plan.setCourseSchedule(day,time); //course schedule set
            tempcourse.setCourseProgram(plan); //schedule u kursa ekle
            String quota_ =  input.get("technicalElectivesQuotaRate").toString();
            String std_ = input.get("stdCountForSemester").toString();
            int std = Integer.parseInt(std_);
            double per = Double.parseDouble(quota_);
            int quota = (int) (per * std);
            tempcourse.setQuota(quota);
            this.courses.add(tempcourse); //kursu departman kurslar listesine ekle
        }

        for(int i = 0; i<courses.size(); i++) { //json kurs listesinde dön
            JSONObject course_ = (JSONObject) courses.get(i); //kursu json objeye çevir
            if(!course_.get("prerequisite").toString().equals("-")) { //eğer preqreqi varsa
                JSONArray prerequisites = (JSONArray) course_.get("prerequisite"); //prereq listesini json arraya çevir
                int len = prerequisites.size(); //kaç prereqi olduğunu al
                for(int h = 0; h < len; h++) { //prereqi kadar dön
                    var prereq = prerequisites.get(h).toString(); //prereq stringi
                    for(int f = 0; f < this.courses.size(); f++){ //bu sefer genel kurs listesinde dön
                        if(this.courses.get(f).getCourseID().equals(course_.get("courseID"))){ //set edilecek course bul
                            for(int x = 0; x < this.courses.size(); x++){ // tekrar kurs listesinde dön ama bu sefer
                                // prereq olarak bulunan kursları, ekledğimiz kurslar arasında bulmak için döneceğiz
                                if(this.courses.get(x).getCourseID().equals(prereq)){ //prereq bulundu
                                    this.courses.get(f).setPrerequisiteCourses(this.courses.get(x)); // prereq eklendi
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    void createSemester(){
        for(int i = 1; i < 9; i += 1){
            if(i %2 == 0){
                Semester tempsemester = new Semester(i, "Spring");
                this.setSemesters(tempsemester);
            }
            else{
                Semester tempsemester = new Semester(i, "Fall");
                this.setSemesters(tempsemester);
            }
        }
    }

    void createAdvisor(List<String> name_male_pool,List<String> name_female_pool,List<String> surname_pool,List<String> ranks, int numberOfAdvisors){

        for (int i = 0; i < numberOfAdvisors; i++){
            Advisor advisor = new Advisor();

            int genderRandom = (int) (Math.random() * 2) ;

            if(genderRandom == 0){
                int range = (name_male_pool.size()-1);
                String random_name = name_male_pool.get((int) (Math.random() * range));
                advisor.setName(random_name);

            }
            else {
                int range = (name_female_pool.size()-1);
                String random_name = name_female_pool.get((int) (Math.random() * range));
                advisor.setName(random_name);

            }

            int range = (surname_pool.size()-1);
            String random_surname = surname_pool.get((int) (Math.random() * range));
            advisor.setSurname(random_surname);

            int range2 = (ranks.size()-1);
            String random_rank = ranks.get((int) (Math.random() * range2));
            advisor.setRank(random_rank);

            this.advisors.add(advisor);

        }
    }

    List<File> listFolder(final File folder) {
        List<File> folderList = new ArrayList<File>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFolder(fileEntry);
            }
            else {
                  folderList.add(fileEntry);
            }
        }
        return folderList;
    }

    void usedStudentsTransfer(File input) throws IOException, ParseException {
        var list = listFolder(input);

        for(var curstufile: list){
            JSONParser parser = new JSONParser();
            JSONObject curstuobj = (JSONObject) parser.parse(new FileReader(curstufile));
            int cursemno = Integer.parseInt(curstuobj.get("studentSemester").toString());
            Student tempstd = new Student();

            for(var searchsem : this.semesters){
                if(searchsem.getNumberOfSemester() == cursemno){
                    tempstd.setCurentsemester(searchsem);
                }
            }

            tempstd.setName(curstuobj.get("firstName").toString());
            tempstd.setSurname(curstuobj.get("lastName").toString());
            StudentNumber tempnum = new StudentNumber(0,0);
            tempnum.setStudentNumber(curstuobj.get("student_id").toString());
            tempstd.setStudentNumber(tempnum);

            JSONArray arr = (JSONArray) curstuobj.get("transcript");

            for(var cursem : arr){
                JSONObject cursem_ = (JSONObject) cursem;
            }
        }
    }

    void system_output_students(){
        for (var student:this.students){
            JSONObject studentDetails = new JSONObject();
            studentDetails.put("firstName", student.getName());
            studentDetails.put("lastName", student.getSurname());
            studentDetails.put("student_id", student.getStudentNumber().getStudentNumber());
            studentDetails.put("studentSemester", student.getCurentsemester().getNumberOfSemester());
            JSONArray logs = new JSONArray();

            for(var curlog_: student.getLog()){
               logs.add(curlog_);
            }

            studentDetails.put("log",logs);
            JSONArray transkript = new JSONArray();

            for(var cursem:student.getTranscript().getSemesters()){
                JSONObject semester = new JSONObject();
                for(int i=0; i<cursem.getGrades().size();i++){
                    var course = cursem.getGrades().keySet().stream().toList().get(i);
                    var grade = cursem.getGrades().values().stream().toList().get(i);
                    semester.put(course.getCourseID() , grade);
                }
                transkript.add(semester);
            }
            studentDetails.put("transcript",transkript);
            try (FileWriter file = new FileWriter("src/Students/"+student.getStudentNumber().getStudentNumber()+".json")) {
                file.write(studentDetails.toJSONString());
                file.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}