import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Random;

public class PointsOnACircle extends Application {
	private double radius = 100;
	//Points On The Circle
	private Circle[] circle = { new Circle(40, 40, 5), new Circle(140, 40, 5), new Circle(60, 140, 5)};
	//Lines in the circle
	private Line line1 = new Line();
	private Line line2 = new Line();
	private Line line3 = new Line();
	//Text That follow points
	private Text[] text = { new Text(), new Text(), new Text() };
	private double[] angle = new double[3];
	
	@Override
	public void start(Stage primaryStage) {
		Pane pane = new Pane();
		Scene scene = new Scene(pane, 400, 250);
		primaryStage.setResizable(false);
		
		// Display Centered Circle
		double centerX = pane.getWidth() / 2.0;
	    double centerY = pane.getHeight() / 2.0;
	    Circle CenterCircle = new Circle(centerX, centerY, radius);
	    CenterCircle.setFill(null);
	    CenterCircle.setStroke(Color.BLACK);
	    
	    //Points On The Circle  Set Color
	    for (int i = 0; i < 3; i++) {
	    	circle[i].setStroke(Color.BLACK); 
			circle[i].setFill(Color.RED);
		}
	    //Randomly Set the angles on the circle
	    Random rand = new Random();
		for (int i = 0; i < 3; i++) {
			angle[i] = rand.nextInt(180);
			circle[i].setCenterX(centerX + radius * Math.cos(angle[i]));
	        circle[i].setCenterY(centerY + radius * Math.sin(angle[i]));
	        setLines();
		}
		
		pane.getChildren().addAll(CenterCircle,circle[0], circle[1], circle[2], line1, line2, line3, text[0], text[1], text[2]);
		// Create a scene and place it in the stage
		// Display the stage
		primaryStage.setTitle("Points on a circle"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); 
		
		//Functions For Dragging point on the circle
		circle[0].setOnMouseDragged((MouseEvent e) -> {
			Point2D redCenter = new Point2D(centerX, centerY);
	        Point2D mouse = new Point2D(e.getX(), e.getY());
	        Point2D centerToMouse = mouse.subtract(redCenter);
	        Point2D centerToNewPoint = centerToMouse.normalize().multiply(radius);
	        Point2D newPoint = centerToNewPoint.add(redCenter);
	        circle[0].setCenterX(newPoint.getX());
	        circle[0].setCenterY(newPoint.getY());
	        setLines();
	    });
		circle[1].setOnMouseDragged((MouseEvent e) -> {
	        Point2D redCenter = new Point2D(CenterCircle.getCenterX(), CenterCircle.getCenterY());
	        Point2D mouse = new Point2D(e.getX(), e.getY());
	        Point2D centerToMouse = mouse.subtract(redCenter);
	        Point2D centerToNewPoint = centerToMouse.normalize().multiply(radius);
	        Point2D newPoint = centerToNewPoint.add(redCenter);
	        circle[1].setCenterX(newPoint.getX());
	        circle[1].setCenterY(newPoint.getY());
	        setLines();
	    });
		circle[2].setOnMouseDragged((MouseEvent e) -> {
	        Point2D redCenter = new Point2D(CenterCircle.getCenterX(), CenterCircle.getCenterY());
	        Point2D mouse = new Point2D(e.getX(), e.getY());
	        Point2D centerToMouse = mouse.subtract(redCenter);
	        Point2D centerToNewPoint = centerToMouse.normalize().multiply(radius);
	        Point2D newPoint = centerToNewPoint.add(redCenter);
	        circle[2].setCenterX(newPoint.getX());
	        circle[2].setCenterY(newPoint.getY());
	        setLines();
	    });
	}
	private void setLines() {
		//Connect the lines on the circle
		line1.setStartX(circle[0].getCenterX());
		line1.setStartY(circle[0].getCenterY());
		line1.setEndX(circle[1].getCenterX());
		line1.setEndY(circle[1].getCenterY());
		
		line2.setStartX(circle[0].getCenterX());
		line2.setStartY(circle[0].getCenterY());
		line2.setEndX(circle[2].getCenterX());
		line2.setEndY(circle[2].getCenterY());
		
		line3.setStartX(circle[1].getCenterX());
		line3.setStartY(circle[1].getCenterY());
		line3.setEndX(circle[2].getCenterX());
		line3.setEndY(circle[2].getCenterY());
		
		//length of each line to the points
		double a = new Point2D(circle[2].getCenterX(), circle[2].getCenterY()).distance(circle[1].getCenterX(),circle[1].getCenterY());
		double b = new Point2D(circle[2].getCenterX(), circle[2].getCenterY()).distance(circle[0].getCenterX(),circle[0].getCenterY());
		double c = new Point2D(circle[1].getCenterX(), circle[1].getCenterY()).distance(circle[0].getCenterX(),circle[0].getCenterY());
		//Calculate angle
		angle[0] = Math.acos((a * a - b * b - c * c) / (-2 * b * c));
		angle[1] = Math.acos((b * b - a * a - c * c) / (-2 * a * c));
		angle[2] = Math.acos((c * c - b * b - a * a) / (-2 * a * b));
		//Set textboxes with the points
		for (int i = 0; i < 3; i++) {
			text[i].setX(circle[i].getCenterX());
			text[i].setY(circle[i].getCenterY() - 10);
			text[i].setText(String.format("%.0f", Math.toDegrees(angle[i])));
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}