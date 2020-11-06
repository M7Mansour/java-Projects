
import java.util.Scanner;
public class Project1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        int actualSize = 0 ;
        double[] array = createArray(1000);
        do {
            menu( );
            System.out.print("\nPlease enter your choice : ");
            char choice = input.next().charAt(0);
            if (Character.toLowerCase(choice) == 'i') {
                System.out.print("\nPlease enter an item : ");
                double addedNumber = inputItem( );
                actualSize += insert(array, actualSize , addedNumber);
                System.out.println("\nNumber of items in the array is " + actualSize);
            }
            else if (Character.toLowerCase(choice) == 'r') {
                    System.out.print("\nPlease enter an item : ");
                    double deletedNumber = inputItem( );
                    if (findItem(array , actualSize , deletedNumber) == -1) 
                        System.out.printf("\nitem %.0f does not exist!\n" , deletedNumber);
                    else {
                        actualSize += removeItem(array , actualSize , deletedNumber);
                        System.out.println("\nNumber of items in the array is " + actualSize);
                    }
            }
            else if (Character.toLowerCase(choice) == 'f'){
                System.out.print("\nPlease enter an item : ");
                double foundedNumber = inputItem( );
                if (findItem(array , actualSize , foundedNumber) == -1) 
                        System.out.println("\nitem " + (int)foundedNumber + " does not exist!");
                else System.out.println("\nitem " + (int)foundedNumber + " is found at position " +
                        (findItem(array , actualSize , foundedNumber) + 1));
            }
            else if (Character.toLowerCase(choice) == 'd'){
                System.out.print("\nPlease enter the starting index (1..." + actualSize + ") : ");
                int index = input.nextInt();
                if (index > actualSize)
                    System.out.println("\nInvalid index :( ");
                else displayItems(array , actualSize , index - 1);
            }
            else if (Character.toLowerCase(choice) == 'q'){
                System.out.println("\nThanks for using :V :)");
                System.exit(0);
            }
            else System.out.println("\nInvalid choice try again :) ");
                
        } while (true);
        
        }
    
    // create an array
    public static double[ ] createArray(int maxsize){
        double[] array = new double[maxsize] ;
        return array ;
    }
    
    //display the menu
    public static void menu( ){
        System.out.println("\ni- Insert item \nr- Remove item \nf- Find an item \nd- Display items \nq- Quit the program");
    }
    
    //returns the position of the item if exist
    public static int findItem(double[ ] store,int datalength, double item){
        int low = 0 ;
        int high = datalength - 1 ;
        int mid = 0 ;
        while (low <= high){
            mid = (low + high) / 2 ;
            if (store[mid] == item)
                return mid ;
            else if (store[mid] < item)
                low = mid + 1 ;
            else if (store[mid] > item)
                high = mid - 1 ;
        }
        return -1 ;
    }
    
    //returns the proper position of a new item
    public static int findPosition(double[ ] store, int datalength, double item){
        int low = 0 ;
        int high = datalength - 1 ;
        int mid = 0 ;
        while (low <= high){
            mid = (low + high) / 2 ;
            if (store[mid] == item)
                return (mid + 1) ;
            else if (store[mid] < item)
                low = mid + 1 ;
            else high = mid - 1 ;
        }
        return low ;
    }
    
    //shift items to the left starting at a given index
    public static void shiftDataLeft(double[ ] store, int datalength, int index){
        for (int i = index ; i < datalength ; i++)
            store[i] = store[i + 1] ;
    }
    
    //shift items to the right starting at a given index
    public static void shiftDataRight(double[ ] store, int datalength, int index){
        for (int i = datalength  ; i > index ; i--)
            store[i] = store[i - 1] ;
    }
    
    // add an item in the proper position
    public static int insert(double[ ] store, int datalength, double item){
        int position = findPosition(store , datalength, item);
        shiftDataRight(store , datalength, position);
        store[position] = item ;
        return 1 ;
    }
    
    // delete an item from the array
    public static int removeItem(double[ ] store, int datalength, double item){
        int position = findItem(store , datalength, item);
        shiftDataLeft(store , datalength, position);
        return -1 ;
    }
    
    //input the item from the keyboard
    public static double inputItem( ){
        Scanner input = new Scanner(System.in);
        return input.nextDouble();
    }
    
    //shows the items in the list starting at a given index
    public static void displayItems(double[ ] store, int datalength, int index){
        System.out.println();
        for (int i = index ; i < datalength ; i++)
            System.out.print(store[i] + " ");
        System.out.println();
    }
}
