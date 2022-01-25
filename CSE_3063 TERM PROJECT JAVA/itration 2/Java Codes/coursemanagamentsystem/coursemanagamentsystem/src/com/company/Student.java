package com.company;
import java.util.ArrayList;
import java.util.List;

class Student extends Person{
    private List<Course> activeCourses = new ArrayList<Course>();
    private List<Course> passiveCourses = new ArrayList<Course>();
    private Semester curentsemester;
    private Advisor advisor;
    private Transcript transcript;
    private StudentNumber studentNumber;
    private int credit;
    private RegistrationCart registrationCart;
    private List<String> log = new ArrayList<String>();





    public Student(Semester curentsemester, Advisor advisor) {
        this.curentsemester = curentsemester;
        this.advisor = advisor;
    }

    public Student(){

    }

    public List<String> getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log.add(log);
    }

    public RegistrationCart getRegistrationCart() {
        return registrationCart;
    }

    public void setRegistrationCart(RegistrationCart registrationCart) {
        this.registrationCart = registrationCart;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public List<Course> getActiveCourses() {
        return activeCourses;
    }

    public StudentNumber getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(StudentNumber studentNumber) {
        this.studentNumber = studentNumber;
    }

    public void setActiveCourses(Course course) {
        this.activeCourses.add(course);
    }

    public List<Course> getPassiveCourses() {
        return passiveCourses;
    }

    public void setPassiveCourses(Course course) {
        this.passiveCourses.add(course);
    }

    public Semester getCurentsemester() {
        return curentsemester;
    }

    public void setCurentsemester(Semester curentsemester) {
        this.curentsemester = curentsemester;
    }

    public Advisor getAdvisor() {
        return advisor;
    }

    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
    }

    public Transcript getTranscript() {
        return transcript;
    }

    public void setTranscript(Transcript transcript) {
        this.transcript = transcript;
    }

    public void editCart_passive(){
        for(var pascourse:this.getPassiveCourses()){
            Registration tempreg = new Registration(pascourse,this);
            this.registrationCart.setRegistrations(tempreg);
        }
    }

    public void editCart_Prereq(){
        List<Registration> willbeadded = new ArrayList<Registration>();
        List<Registration> willberemoved = new ArrayList<Registration>();
        for (var reg:this.registrationCart.getRegistrations()) { //regler içinde gez
            if(!reg.isApproved()){ //onaylanmayan regler
                willberemoved.add(reg);
                var curcourse = reg.getCourse();
                for (var curpreq:curcourse.getPrerequisiteCourses()) { //preqler içinde gez
                    Registration tempreq = new Registration(curpreq,this);
                    willbeadded.add(tempreq);
                }
            }
        }

        for (var adreg:willbeadded) {
            int c = 0;
            for(var frf : this.getRegistrationCart().getRegistrations()){
                if(frf.getCourse() == adreg.getCourse()){
                   c = 1;
                    break;
                }
            }

            if(c == 0){
                this.getRegistrationCart().setRegistrations(adreg);
                this.getRegistrationCart().setTotalCredit(adreg.getCourse().getCredit());
            }

            System.out.println(adreg.getCourse().getCourseID() + " reglere eklendi");

        }

        for (var remreg:willberemoved) {
            if(!this.getPassiveCourses().contains(remreg.getCourse())){
                this.setPassiveCourses(remreg.getCourse());
            }
            this.registrationCart.getRegistrations().remove(remreg);
            this.registrationCart.decTotalCredit(remreg.getCourse().getCredit());
            System.out.println(remreg.getCourse().getCourseID() + " reglerden çıkarıldı");
        }




//        for (var vv:this.registrationCart.getRegistrations()){
//            System.out.println(vv.getCourse().getCourseID());
//        }

    }

    public void takeTE(List<Course> list){
        List<Course> telist = new ArrayList<Course>();
        for(var searchc: list ){
            if(searchc.getCanTakenSemester().getNumberOfSemester() == -1){ //technical
                telist.add(searchc);
            }
        }


        List<Registration> willberemoved = new ArrayList<Registration>();
        List<Registration> willbeadded = new ArrayList<Registration>();
        for(var curreg: this.getRegistrationCart().getRegistrations()){
           var curcourse = curreg.getCourse();
           String courseID = curcourse.getCourseID();
           String firsttwo = courseID.substring(0,2);
            if(firsttwo.equals("TE")){ //technical elective
                int range = (telist.size());
                while(true){
                    int rand = (int)(Math.random()*range);
                    var selected = telist.get(rand);
                    System.out.println(selected.getCourseID() + " " + selected.getQuota());
                    if(selected.getQuota() != 0){
                        Registration tempreg = new Registration(selected,this);
                        willbeadded.add(tempreg);
                        willberemoved.add(curreg);
                        selected.setQuota(selected.getQuota()-1);
                        break;
                    }
                    else{
                        System.out.println("QUOTA PROBLEM FOR TE");
                        this.setLog("System didn't alllow " + selected.getCourseID() + " because of quota problem");
                        continue;
                    }

                }

            }
        }

        for(var gg:willberemoved)
        {
            this.getRegistrationCart().getRegistrations().remove(gg);
        }
        for(var gg:willbeadded)
        {
            this.getRegistrationCart().getRegistrations().add(gg);
        }

    }


    public void editCart_Credit(){
        while(this.getCredit()+2 < this.getRegistrationCart().getTotalCredit()){
            int siz = this.getRegistrationCart().getRegistrations().size()/2;
            if(!this.passiveCourses.contains(this.registrationCart.getRegistrations().get(siz).getCourse())){
                this.setPassiveCourses( this.getRegistrationCart().getRegistrations().get(siz).getCourse() );
            }

            System.out.println("The advisor didn't approve " + this.getRegistrationCart().getRegistrations().get(siz).getCourse().getCourseID() + " because of total credit");
            this.setLog("The advisor didn't approve " + this.getRegistrationCart().getRegistrations().get(siz).getCourse().getCourseID() + " because of total credit");
            this.getRegistrationCart().getRegistrations().remove(siz);
            this.getRegistrationCart().decTotalCredit(this.getRegistrationCart().getRegistrations().get(siz).getCourse().getCredit());
        }
        for(var pas:this.getPassiveCourses()){
            if(pas.getCredit() <= this.getCredit() - this.getRegistrationCart().getTotalCredit()){
                Registration tempreg = new Registration(pas,this);
                this.getRegistrationCart().setRegistrations(tempreg);
                this.getRegistrationCart().setTotalCredit(tempreg.getCourse().getCredit());
                System.out.println("In passive courses " + tempreg.getCourse().getCourseID() + " added to cart.");
            }
        }


    }

    public void calculateGrades(int failProbabiltyPercentage){
        for(int i = 0; i<this.activeCourses.size();i++){
            int range_1 = 100;
            int rand_1 = (int)(Math.random()*range_1);
            var currentcourse = this.activeCourses.get(i);
            if(rand_1<failProbabiltyPercentage){
                //kaldı
                System.out.println("failed -> " + currentcourse.getCourseID());
                if(!this.passiveCourses.contains(currentcourse)){
                    this.passiveCourses.add(currentcourse);
                }
                for(int j=0;j<this.transcript.getSemesters().size();j++){
                    var cursem = this.transcript.getSemesters().get(j);
                    if(cursem.getGrades().get(currentcourse) == null){}
                    else if(cursem.getGrades().get(currentcourse).equals("*")){ //not boş ise
                        cursem.setGrades(currentcourse,"F");
                    }
                }
            }else{
                if(this.passiveCourses.contains(currentcourse)){
                    this.getPassiveCourses().remove(currentcourse);
                }
                if(currentcourse.getCanTakenSemester().getNumberOfSemester()==-1){
                    currentcourse.setQuota(currentcourse.getQuota()+1);
                }

                int range = (100 - 50)+1;
                int rand = (int)(Math.random()*range)+50;
                System.out.println(rand);
                for(int j=0;j<this.transcript.getSemesters().size();j++){ //dönemler içinde gez
                    var cursem = this.transcript.getSemesters().get(j);
                    if(cursem.getGrades().get(currentcourse) == null){}
                    else if(cursem.getGrades().get(currentcourse).equals("*")){ //not boş ise
                        if(90<=rand && rand<=100){cursem.setGrades(currentcourse,"AA");}
                        if(85<=rand && rand<=89){cursem.setGrades(currentcourse,"BA");}
                        if(80<=rand && rand<=84){cursem.setGrades(currentcourse,"BB");}
                        if(75<=rand && rand<=79){cursem.setGrades(currentcourse,"CB");}
                        if(65<=rand && rand<=74){cursem.setGrades(currentcourse,"CC");}
                        if(55<=rand && rand<=64){cursem.setGrades(currentcourse,"DC");}
                        if(50<=rand && rand<=54){cursem.setGrades(currentcourse,"DD");}


                    }
                }
            }
        }
        this.activeCourses.clear();
    }
}


