import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Investment_Calc extends Application {
	
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
	  
    // Create a pane and set its properties
    GridPane pane = new GridPane();
    pane.setAlignment(Pos.TOP_LEFT);
    pane.setPadding(new Insets(5));
    pane.setHgap(5.5);
   
    // Place nodes in the pane
    TextField InvestmentA = new TextField();
    InvestmentA.setAlignment(Pos.BASELINE_RIGHT);
    pane.add(new Label("Investment Amount"), 0, 0);
    pane.add(InvestmentA, 1, 0);
    
    TextField Years = new TextField();
    pane.add(new Label("Years"), 0, 1); 
    pane.add(Years, 1, 1);
    Years.setAlignment(Pos.BASELINE_RIGHT);
    
    
    TextField Annual_Int = new TextField();
    pane.add(new Label("Annual Interest Rate"), 0, 2);
    pane.add(Annual_Int, 1, 2);
    Annual_Int.setAlignment(Pos.BASELINE_RIGHT);
    
    TextField Future_Val = new TextField();
    pane.add(new Label("Future Value"), 0, 3);
    pane.add(Future_Val, 1, 3);
    Future_Val.setAlignment(Pos.BASELINE_RIGHT);
    
    Button btCalc = new Button("Calculate");
    pane.add(btCalc, 1, 4);
    GridPane.setHalignment(btCalc, HPos.RIGHT);
    //Calculate The investments when pressing the button
    btCalc.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent e) {
        	double Investment = Double.parseDouble(InvestmentA.getText());
        	double Year = Double.parseDouble(Years.getText());
        	double AnnualInt = Double.parseDouble(Annual_Int.getText())/12;
        	Double Future =  Investment * Math.pow(1+ (AnnualInt/100), Year*12);
        	Future_Val.setPromptText(Double.toString(Future));
             }
         });
   
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane);
    primaryStage.setTitle("ShowGridPane"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }
  public static void main(String[] args) {
    launch(args);
  }
} 