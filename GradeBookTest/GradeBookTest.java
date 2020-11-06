
import java.util.Scanner;
public class GradeBookTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter course name : ");
        String cName = input.nextLine();
        System.out.print("\nEnter number of students : ");
        int nStudent = input.nextInt();
        System.out.print("\nEnter number of exams : ");
        int nExam = input.nextInt();
        GradeBook myGradeBook = new GradeBook(cName , nStudent , nExam);
        for (int i = 0; i < nStudent ; i++) {
            System.out.print("\nEnter student " + (i + 1) + " grades : ");
            for (int j = 0; j < nExam ; j++) {
                int x = input.nextInt();
                myGradeBook.setGrade(x , i , j);
            }
        }
        System.out.printf("Welcome to the grade book for%n%s%n%n" , myGradeBook.getCourseName());
        myGradeBook.processGrades();
    }
    
}

class GradeBook{
    private final String cName ;
    private final int nStudent ;
    private final int nExam ;
    private int[][] grades ;
    public GradeBook(String cName , int nStudent , int nExam){
        this.cName = cName ;
        this.nExam = nExam ;
        this.nStudent = nStudent ;
        grades = new int[nStudent][nExam];
        for (int i = 0; i < nStudent; i++) 
            for (int j = 0; j < nExam; j++)
                grades[0][0] = -1 ;
    }
    
    public void setGrade(int grade , int sNumber ,int eNumber){
        grades[sNumber][eNumber] = grade ;
    } 
    public String getCourseName(){
        return cName;
    }
    private double average(int sNumber){
        double sum = 0 ;
        for (int i = 0 ; i < nExam ; i++) {
            sum += grades[sNumber][i] ;
        }
        return sum / nExam ;
    }
    private int lowestGrade(){
        int min = 101 ;
        for (int i = 0; i < nStudent ; i++) 
            for (int j = 0; j < nExam ; j++) 
                if (grades[i][j] < min)
                    min = grades[i][j];
        return min ;
    }
    private int highestGrade(){
        int max = -1 ;
        for (int i = 0; i < nStudent ; i++) 
            for (int j = 0; j < nExam ; j++) 
                if (grades[i][j] > max)
                    max = grades[i][j];
        return max ;
    }
    private int[] gradeDistribution(){
        int[] x = new int[11];
        for (int i = 0; i < nStudent ; i++) 
            for (int j = 0; j < nExam ; j++)
            	x[grades[i][j] / 10] += 1 ;
        return x ;
    }
    public void processGrades(){
        System.out.println("The grades are : ");
        System.out.print("            ");
        for (int i = 0; i < nExam; i++) {
            System.out.print("Test " + (i + 1) + "     ");
        }
        System.out.println("Average ");
        for (int i = 0; i < nStudent ; i++) {
            System.out.printf("Student %-2d" , (i+1));
            for (int j = 0; j < nExam; j++) {
                System.out.printf("%8d" , grades[i][j]);
            }
            System.out.printf("%14.2f\n" , average(i));
        }
        System.out.println("");
        System.out.println("Lowest grade in the grade book is : " + lowestGrade());
        System.out.println("Highest grade in the grade book is : " + highestGrade());
        System.out.println("");
        System.out.println("Overall grade distribution : ");
        int[] x = gradeDistribution();
        System.out.print("00-09 : ");
        for (int i = 0; i < x[0] ; i++)
            System.out.print("*");
        System.out.println("");
        System.out.print("10-19 : ");
        for (int i = 0; i < x[1] ; i++)
            System.out.print("*");
        System.out.println("");
        System.out.print("20-29 : ");
        for (int i = 0; i < x[2] ; i++)
            System.out.print("*");
        System.out.println("");
        System.out.print("30-39 : ");
        for (int i = 0; i < x[3] ; i++)
            System.out.print("*");
        System.out.println("");
        System.out.print("40-49 : ");
        for (int i = 0; i < x[4] ; i++)
            System.out.print("*");
        System.out.println("");
        System.out.print("50-59 : ");
        for (int i = 0; i < x[5] ; i++)
            System.out.print("*");
        System.out.println("");
        System.out.print("60-69 : ");
        for (int i = 0; i < x[6] ; i++)
            System.out.print("*");
        System.out.println("");
        System.out.print("70-79 : ");
        for (int i = 0; i < x[7] ; i++)
            System.out.print("*");
        System.out.println("");
        System.out.print("80-89 : ");
        for (int i = 0; i < x[8] ; i++)
            System.out.print("*");
        System.out.println("");
        System.out.print("90-99 : ");
        for (int i = 0; i < x[9] ; i++)
            System.out.print("*");
        System.out.println("");
        System.out.print("100   : ");
        for (int i = 0; i < x[10] ; i++)
            System.out.print("*");
        System.out.println("");
    }
} 