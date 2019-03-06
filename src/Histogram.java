import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Histogram extends Application {
	private String alphabet[] = { "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    private int lettercount[] = new int[26];
    private BorderPane pane = new BorderPane();
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    // Create a border pane 
    HBox hBox = new HBox(); 
    hBox.setAlignment(Pos.BASELINE_CENTER);
    Label lbl = new Label("Filename");
    TextField text = new TextField();
    Button view = new Button("View");
    hBox.getChildren().addAll(lbl,text,view);

    // Place nodes in the pane
    pane.setBottom(hBox);
    //When pressing enter on the textbox
    text.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        	public void handle(ActionEvent e) {
        	String FILE_LOCATION = text.getText();
        	try {
				countLetters(FILE_LOCATION);
			} catch (FileNotFoundException e1) {
				
				e1.printStackTrace();
			}
        	DisplayHistogram();
             }
         });
   
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane,550,300);
    primaryStage.setTitle("Histogram"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }
  private void countLetters(String location) throws FileNotFoundException
  {
	  //Input Strings Read from the file
	  java.io.File file = new java.io.File(location);
	    // Create a Scanner for the file
	  Scanner input = new Scanner(file);
	  while (input.hasNext()) {
		  String word = input.next().toLowerCase();
		  //Count The number of letters in the string
		  for ( int i = 0; i < word.length(); i++ ) {
		         char ch=  word.charAt(i);
		         //convert Char to an integer Value
		         int value = (int) ch;
		         //a-z # values
		         if (value >= 97 && value <= 122){
		        	 lettercount[ch-'a']++;
		        }
		    }
	    }
	 
  	input.close();
  }
  private void DisplayHistogram()
  {
	  int StartX = 0;
	  //Display the 26 letters in the array
	  for (int i = 0; i < 26; i++) { 
		//Multiply the length by 10 to increase the size, allow the graph to be proportional
        Rectangle rectangle = new Rectangle(StartX+=20,300 - lettercount[i]*5 -50 ,15,lettercount[i]*5);
        Text letters = new Text(StartX+4,300-30,alphabet[i]);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(Color.WHITE);            
        pane.getChildren().addAll(rectangle,letters);
	     }
  }

  public static void main(String[] args) {
    launch(args);
  }
} 