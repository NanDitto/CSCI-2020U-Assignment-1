import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Random;

public class Displaying_Three_Cards extends Application {
  @Override 
  public void start(Stage primaryStage) {
    
    Pane pane = new HBox(5);
    Image Card1 = new Image("file:///C:/Users/Nandor Gallo/workspace/Assignment Software Dev/bin/Cards/"+ChooseCard());
    pane.getChildren().add(new ImageView(Card1));
    
    Image Card2 = new Image("file:///C:/Users/Nandor Gallo/workspace/Assignment Software Dev/bin/Cards/"+ChooseCard());
    ImageView imageView2 = new ImageView(Card2);
    pane.getChildren().add(imageView2);   

    Image Card3 = new Image("file:///C:/Users/Nandor Gallo/workspace/Assignment Software Dev/bin/Cards/"+ChooseCard());
    ImageView imageView3 = new ImageView(Card3);
    pane.getChildren().add(imageView3);     
    
    
    Scene scene = new Scene(pane);
    primaryStage.setTitle("Display 3 Cards"); 
    primaryStage.setScene(scene); 
    primaryStage.show(); 
  }
  public static String ChooseCard(){
	  // Picks Random Card from an integer 1-54
	  Random rand = new Random();
	  // Add 1 to fix range from 0-53 -> 1-54
	  int card = rand.nextInt(54) + 1;
	  //Covert integer toString and add it with .png's extension
	  return Integer.toString(card) + ".png";
  }
  
  public static void main(String[] args) {
    launch(args);
  }
}
