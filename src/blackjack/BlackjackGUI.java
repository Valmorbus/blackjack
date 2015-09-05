package blackjack;

import java.awt.Canvas;

import com.sun.prism.paint.Color;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.*;

public class BlackjackGUI extends Application{
	
	public void table() {
		


	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int startingBet = 1000;
		
		launch(args);
		new Blackjack(startingBet);
		
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		StackPane root = new StackPane();
	
		Scene scene = new Scene(root, 800, 800);
		
		
	
		primaryStage.setTitle("BlackJack");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
}
