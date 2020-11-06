
import java.util.Scanner;
import java.io.*;
public class TextFormatter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        int noOfStudents;
        double tuitionRate;
        Scanner inFile = new Scanner(new FileReader("InputData.txt"));
        PrintWriter outFile = new PrintWriter("sDataOut.out");
        noOfStudents = inFile.nextInt();
        tuitionRate = inFile.nextDouble();
        Student[] studentList = new Student[noOfStudents];
        for (int i = 0; i < studentList.length; i++)
            studentList[i] = new Student();
        getStudentData(inFile, studentList);
        printGradeReports(outFile, studentList, tuitionRate);
        inFile.close();
        outFile.close();
    }
    
    public static void getStudentData(Scanner inFile , Student[] studentlist) throws IOException {
        for (int i = 0; i < studentlist.length ; i++) {
            studentlist[i].setName(inFile.next(), inFile.next());
            studentlist[i].setStudentId(inFile.nextInt());
            boolean t = inFile.next().charAt(0) == 'Y' ;
            studentlist[i].isTuitionPaid(t);
            int numberOfCourses = inFile.nextInt();
            studentlist[i].setNumberOfCourses(numberOfCourses);
            Course[] coursesEnrolled = new Course[numberOfCourses];
            for (int k = 0; k < coursesEnrolled.length; k++)
            coursesEnrolled[k] = new Course();
            char[] grades = new char[numberOfCourses];
            for (int j = 0; j < numberOfCourses ; j++) {
                coursesEnrolled[j].setCourseName(inFile.next());
                coursesEnrolled[j].setCourseNumber(inFile.next());
                coursesEnrolled[j].setCourseCredits(inFile.nextInt());
                grades[j] = inFile.next().charAt(0);
            }
            studentlist[i].setCoursesEnrolled(coursesEnrolled, grades);
        }
    }
    public static void printGradeReports(PrintWriter outFile , Student[] studentlist , double tuitionRate){
        for (int i = 0; i < studentlist.length ; i++) {
            outFile.println("Student Name : " + studentlist[i].getFirstName() + " " + studentlist[i].getLastName());
            outFile.println("Student ID : " + studentlist[i].getStudentId());
            outFile.println("Number of courses enrolled : " + studentlist[i].getNumberOfCourses());
            outFile.printf("%-13s%-16s%-12s%-7s\n" , "Course No" , "Course Name" , "Credits" , "Grade");
            if(studentlist[i].getIsTuitionPaid()){
                for (int j = 0; j < studentlist[i].getNumberOfCourses(); j++) {
                    outFile.printf("%-13s%-18s%-12s%-7s\n" , studentlist[i].getCourse(j).getCourseNumber() , studentlist[i].getCourse(j).getCourseName() , studentlist[i].getCourse(j).getCourseCredits() , studentlist[i].getGrade(j));
                }
                outFile.println("\nTotal number of credit hours : " + studentlist[i].getHoursEnrolled());
                outFile.print("Midsemester GPA : ");
                outFile.printf("%.2f\n", studentlist[i].getGpa());
            }
            else {
                for (int j = 0; j < studentlist[i].getNumberOfCourses(); j++) {
                    outFile.printf("%-13s%-18s%-12s%-7s\n" , studentlist[i].getCourse(j).getCourseNumber() , studentlist[i].getCourse(j).getCourseName() , studentlist[i].getCourse(j).getCourseCredits() , "***");
                }
                outFile.println("\nTotal number of credit hours : " + studentlist[i].getHoursEnrolled());
                outFile.println("*** Grades are being held for not paying the tuition. ***");
                outFile.println("Amount Due : " + studentlist[i].billingAmount(tuitionRate));
            }
            outFile.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n");
        }
    }
}
class Person {
    private String firstName ;
    private String lastName ;
    
    public Person (){
        //this("person","Name");
    }
    
    public Person (String firstName , String lastName){
        this.firstName = firstName ;
        this.lastName = lastName ;
    }
    
    @Override
    public String toString(){
        return firstName + " " + lastName ;
    }
    
    public void setName(String firstName , String lastName){
        this.firstName = firstName ;
        this.lastName = lastName ;
    }
    
    public String getFirstName(){
        return firstName ;
    }
    
    public String getLastName(){
        return lastName ;
    }
}

class Student extends Person {
    private int sId ;
    private int numberOfCourses ;
    private boolean isTuitionPaid ;
    Course[] coursesEnrolled = new Course[10];
    char[] courseGrades = new char[10];
    
    public Student (){
        
    }
    
    public void setInfo(String firstName , String lastName , int sId , boolean isTuitionPaid ,int numberOfCourses , Course[] coursesEnrolled , char[] courseGrades){
        super.setName(firstName, lastName);
        this.sId = sId ;
        this.isTuitionPaid = isTuitionPaid ;
        this.numberOfCourses = numberOfCourses ;
        for (int i = 0; i < this.coursesEnrolled.length ; i++) {
            this.coursesEnrolled[i] = coursesEnrolled[i] ;
        }
        for (int i = 0; i < this.courseGrades.length ; i++) {
            this.courseGrades[i] = courseGrades[i] ;
        }
        sortCourses();
    }
    public void setStudentId(int sId){
        this.sId = sId ;
    }
    public void setNumberOfCourses(int numberOfCourses){
        this.numberOfCourses = numberOfCourses ;
    }
    public void isTuitionPaid(boolean isTuitionPaid){
        this.isTuitionPaid = isTuitionPaid ;
    }
    public void setCoursesEnrolled(Course[] coursesEnrolled , char[] courseGrades){
        for (int i = 0; i < coursesEnrolled.length ; i++) {
            this.coursesEnrolled[i] = coursesEnrolled[i] ;
        }
        for (int i = 0; i < courseGrades.length ; i++) {
            this.courseGrades[i] = courseGrades[i] ;
        }
        sortCourses();
    }
    @Override 
    public String toString(){
        return "" + getStudentId() + getNumberOfCourses() + getIsTuitionPaid() + getHoursEnrolled() + getGpa() ;
    }
    public int getStudentId(){
        return sId ;
    }
    public int getNumberOfCourses(){
        return numberOfCourses ;
    }
    public boolean getIsTuitionPaid(){
        return isTuitionPaid ;
    }
    public Course getCourse(int course){
        return coursesEnrolled[course] ;
    }
    public char getGrade(int chars){
        return courseGrades[chars] ;
    }
    public int getHoursEnrolled(){
        int hours = 0 ;
        for (int i = 0; i < coursesEnrolled.length ; i++) {
            if(!(coursesEnrolled[i] == null))
            hours += coursesEnrolled[i].getCourseCredits();
        }
        return hours ;
    }
    public double getGpa(){
        double sum = 0 ;
        int count = 0 ;
        for (int i = 0; i < courseGrades.length ; i++) {
            if(!(courseGrades[i] == '\u0000')){
                switch (courseGrades[i]) {
                    case 'A':
                        sum += 4 ; count++;
                        break;
                    case 'B':
                        sum += 3 ; count++;
                        break;
                    case 'C':
                        sum += 2 ; count++;
                        break;
                    case 'D':
                        sum += 1 ; count++;
                        break;
                    default:
                        sum += 0 ; count++;
                        break;
                }
            }
                
        }
        return sum / count ;
    }
    public double billingAmount(double tuitionRate){
        return getHoursEnrolled() * tuitionRate ;
    }
    private void sortCourses(){
        for (int i = 0; i < coursesEnrolled.length - 1 && coursesEnrolled[i] != null ; i++) {
            String currentMax = coursesEnrolled[i].getCourseName() ;
            Course currentM = coursesEnrolled[i];
            int currentMaxIndex = i ;
            for (int j = i+1 ; j < coursesEnrolled.length && coursesEnrolled[j] != null ; j++) {
                if(bigerThan(coursesEnrolled[j].getCourseName() , currentMax)){
                    currentMax = coursesEnrolled[j].getCourseName() ;
                    currentM = coursesEnrolled[j] ;
                    currentMaxIndex = j ;
                }
            }
            if(currentMaxIndex != i ){
                coursesEnrolled[currentMaxIndex] = coursesEnrolled[i] ;
                coursesEnrolled[i] = currentM ;
                char temp = courseGrades[i] ;
                courseGrades[i] = courseGrades[currentMaxIndex];
                courseGrades[currentMaxIndex] = temp ;
            }
        }
    }
    private boolean bigerThan(String s1 , String s2){
        for (int i = 0; i < s1.length() - 1 ; i++) {
            if (s1.charAt(i) < s2.charAt(i))
                return true ;
            else if (s1.charAt(i) > s2.charAt(i))
                return false ;
        }
        return false ;
    }
}

class Course extends Student{
    private String courseName ;
    private String courseNo ;
    private int courseCredits ;
    
    public Course (){
        this("courseName","courseNo",0);
    }
    
    public Course (String courseName , String courseNo , int courseCredits){
        this.courseName = courseName ;
        this.courseNo = courseNo ;
        this.courseCredits = courseCredits ;
    }
    
    public void setCourseInfo(String courseName , String courseNo , int courseCredits){
        this.courseName = courseName ;
        this.courseNo = courseNo ;
        this.courseCredits = courseCredits ;
    }
    
    public void setCourseName(String courseName){
        this.courseName = courseName ;
    }
    
    public void setCourseNumber(String courseNo){
        this.courseNo = courseNo ;
    }
    
    public void setCourseCredits(int courseCredits){
        this.courseCredits = courseCredits ;
    }
    
    @Override
    public String toString(){
        return courseName + " " + courseNo + " " + courseCredits ;
    }
    
    public String getCourseName(){
        return courseName ;
    }
    
    public String getCourseNumber(){
        return courseNo ;
    }
    
    public int getCourseCredits(){
        return courseCredits ;
    }
    
    public void copyCourseInfo(Course course){
        course.setCourseInfo(courseName, courseNo, courseCredits);
    }
}