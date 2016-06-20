package jeu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;






public class Main extends Application {

	
	static Stage window = new Stage();
	GameMenu menu = new GameMenu(window);
	@Override
    public void start(Stage primaryStage)  {
		
		
		window = primaryStage;
		window.setTitle("ATTACK ON BEHAVIOR");
        
		
	    //1er scene
	   
        Pane rootMenu = new Pane();
        rootMenu.setPrefSize(1750, 750);
        
        menu = new GameMenu(primaryStage);
        menu.setVisible(true);
        
        Scene sceneMenu = new Scene(rootMenu, 1700, 750);
        
        final Image img = new Image("/images/AttackOnBehavior.png");
        ImageView imgView = new ImageView(img);     
        imgView.setScaleX(0.9);
        imgView.setScaleY(0.9);
        imgView.setX(-100);
        imgView.setY(-100);   
        rootMenu.getChildren().addAll(imgView,menu); 
        window.setScene(sceneMenu);
        window.show();
        
	}   

	public static void main(String[] args) {
	
		launch(args);
	
	}
}
