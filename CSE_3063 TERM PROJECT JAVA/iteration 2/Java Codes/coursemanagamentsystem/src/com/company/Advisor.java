package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class Advisor extends Person {
    private String rank;
    private List<Student> advisee = new ArrayList<Student>();
    private List<Registration> registrationRequests = new ArrayList<Registration>();

    public Advisor() {

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

    public void setAdvisee(Student student) {
        this.advisee.add(student);
    }

    public List<Registration> getRegistrationRequests() {
        return registrationRequests;
    }

    public void setRegistrationRequests(List<Registration> registrationRequests) {
        this.registrationRequests = registrationRequests;
    }

    int checkRegistrationCart(Student student, int failProbabiltyPercentage){
        System.out.println("------------------------------------------");
        System.out.println(student.getName() + " " + student.getSurname() + " " + student.getCurent ().getNumberOfSemester());
        for (int i = 0;i<student.getRegistrationCart().getRegistrations().size();i++) { // reg cart içersinde gez
            var curreg = student.getRegistrationCart().getRegistrations().get(i); //şu an ki registration
            var curcourse = curreg.getCourse(); //şu an ki reg içerisindeki kurs
            System.out.println(student.getRegistrationCart().getRegistrations().get(i).getCourse().getCourseID());
        }
//       --------------------------------------------------------------------------------------------


        System.out.println( "Total Credit: " + student.getRegistrationCart().getTotalCredit());
        System.out.println("-------------------------------------");
        //--------------------------------------------------------------------------




        //--------------------------------------------------------------------------
        var notTakenList = checkPrerequisites(student); //prereq kontrolü
        if(!notTakenList.isEmpty()){ //prereqi alınmamış dersler var ----------
            System.out.println("PREREQ PROBLEM "+ notTakenList.get(0).getCourseID() +" for " + student.getName() + " " + student.getSurname());
            return 2;
        }else{
            student.getRegistrationCart().setApproved(true);
        }

        //----------------------------------------------------------------------
        if(student.getCredit()+2 < student.getRegistrationCart().getTotalCredit()){ //kredi yetersiz
            System.out.println("Credit is not enough " + student.getCredit() + " " + student.getRegistrationCart().getTotalCredit());
            return 1;
        }
        //----------------------------------------------------------------

        if(student.getRegistrationCart().isApproved()) { //cart onay aldı
            for (int c = 0; c < student.getRegistrationCart().getRegistrations().size(); c++) { //kontroller tamam. cart içinde dön
                //onayları ver
                student.getRegistrationCart().getRegistrations().get(c).setApproved(true);
                student.getRegistrationCart().getRegistrations().get(c).setRegisteredBy(this);
                student.getRegistrationCart().setRegisteredBy(this);
            }
            return 0;
        }







        return -1;

    }


    List<Course> checkPrerequisites(Student student){
        List<Course> nottakenPres = new ArrayList<Course>();
        var registrationCart = student.getRegistrationCart();
        var registrations = registrationCart.getRegistrations();
        for(int i = 0; i < registrations.size(); i++){ //sepet içerisindeki regler içinde gez
            var curreg=registrations.get(i); //şu an ki registration
            var curcourse = curreg.getCourse(); // şu an ki registration içerisindeki kurs
            if(!curcourse.getPrerequisiteCourses().isEmpty()){ //kursun ön koşulu var ise;
                for(int t = 0; t<curcourse.getPrerequisiteCourses().size(); t++){ //prereq kadar dön
                    var curprereq =  curcourse.getPrerequisiteCourses().get(t);
                    List<Course> totalc = new ArrayList<>();
                    List<String> totalG = new ArrayList<>();

                    for(int j = 0; j<student.getTranscript().getSemesters().size() ;j++) { //transkript içinde dön
                        var currentseason = student.getTranscript().getSemesters().get(j); //transkript içindeki dönem
                        for(int u=0;u<currentseason.getGrades().keySet().stream().toList().size();u++){
                            if(currentseason.getGrades().keySet().stream().toList().get(u) == curprereq){
                                totalc.add(currentseason.getGrades().keySet().stream().toList().get(u));//kursu al totale ekle
                                totalG.add(currentseason.getGrades().values().stream().toList().get(u)); // notu al totale ekle
                            }

                        }
                    }


                    if(totalG.contains("AA") || totalG.contains("BA") || totalG.contains("BB") || totalG.contains("CB") || totalG.contains("CC")
                    || totalG.contains("DC") || totalG.contains("DD")){
                        //dersi almıştır ve kalmamıştır.
                        curreg.setApproved(true);
                        System.out.println("/");
                    }
                    else {
                        System.out.println("The advisor didn't allow " + curcourse.getCourseID() + " because student failed prereq. " + curprereq.getCourseID());
                        student.setLog("The advisor didn't allow " + curcourse.getCourseID() + " because student failed prereq. " + curprereq.getCourseID());
                        nottakenPres.add(curprereq);
                        curreg.setApproved(false);
                    }




                }

            }else{
                curreg.setApproved(true);
            }

        }
        return nottakenPres;
    }

    void checkTimeSchedule(Student student){

    }
}
