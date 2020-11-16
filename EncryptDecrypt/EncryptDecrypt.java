
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EncryptDecrypt extends Application {

    public static void main(String[] args) {
        Application.launch(args);
        //System.out.println(encryptDecrypt("Mohammad H Mansour" , 5));
        
    }
    
    private final TextField inputtext = new TextField();
    private final TextField rotation = new TextField();
    private final TextArea outputtext = new TextArea();

    private final Button encrypt = new Button("Encrypt");
    private final Button decrypt = new Button("Decrypt");

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create UI
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        gridPane.add(inputtext, 1, 0);
        gridPane.add(rotation, 1, 1);

        gridPane.add(outputtext, 1, 2);
        gridPane.add(encrypt, 1, 3);
        gridPane.add(decrypt, 1, 4);

        // Set properties for UI
        gridPane.setAlignment(Pos.CENTER);
        inputtext.setAlignment(Pos.BOTTOM_RIGHT);
        rotation.setAlignment(Pos.BOTTOM_RIGHT);
        GridPane.setHalignment(decrypt, HPos.RIGHT);
        GridPane.setHalignment(encrypt, HPos.RIGHT);

        // Process events
        encrypt.setOnAction(e -> encryption());
        decrypt.setOnAction(e -> decryption());

        // Create a scene and place it in the stage
        Scene scene = new Scene(gridPane, 400, 250);
        primaryStage.setTitle("LoanCalculator"); // Set title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    private void encryption() {
        String text = inputtext.getText();
        int rot = Character.toUpperCase(rotation.getText().charAt(0)) - 'A';
        outputtext.setText(encryptDecrypt(text.toUpperCase(),rot));
    }

    private void decryption() {
        String text = inputtext.getText();
        int rot = Character.toUpperCase(rotation.getText().charAt(0)) - 'A';
        outputtext.setText(encryptDecrypt(text.toUpperCase(),-rot));
    }
    
    // when rot is positive the method will encrypt the text
    // when rot is negative the method will decrypt the text 
    public static String encryptDecrypt(String s , int rot){
        String finalS = "";
        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            if(temp == ' '){
                finalS += ' ';
                continue;
            }
            if(((temp - 'A' <= ('Z' - 'A') - rot) && (rot >= 0)) || ((temp - 'A') >= -rot) && (rot < 0))
                temp += rot ;
                
            else if (((temp - 'A' > ('Z' - 'A') - rot) && (rot >=0)) || ((temp - 'A' < -rot) && (rot < 0)))
                    temp += (-rot / Math.abs(rot))*(('Z' - 'A') - Math.abs(rot) + 1);
                    
            finalS += temp ;
        }
        return finalS;
    }
    
}
