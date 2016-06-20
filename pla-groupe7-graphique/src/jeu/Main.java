package jeu;
	
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

import jeu.InterfaceGraphique;
import jeu.JoueurConsoleZQSD;
import jeu.Particule;
import jeu.Partie;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;






public class Main extends Application {

	
	static Stage window =new Stage();
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
        
        //Affichage de l'image
       // Image img = new Image("AttackOnBehavior.png");
        //final String imageURI = new File("/images/AttackOnBehavior.png").toURI().toString(); 
        final Image img = new Image("/images/AttackOnBehavior.png");
        ImageView imgView = new ImageView(img);     
        
        imgView.setScaleX(0.9);
        imgView.setScaleY(0.9);
        imgView.setX(-100);
        imgView.setY(-100);   
      
        
        rootMenu.getChildren().addAll(imgView,menu);
        
 
         
        
        window.setScene(sceneMenu);
        window.show();


    
       /*Partie p = new Partie(new InterfaceGraphique(), new JoueurConsoleZQSD("Toto", "automates1.xml"), new JoueurConsoleZQSD("Titi", "automates2.xml"));
        
        root.getChildren().add(p);
		
		root.getTransforms().add(new Scale(0.82,0.82));
		root.getTransforms().add(new Rotate(-38));
		 p.requestFocus();
		*/
		
		//root.setTranslateX(200);
		//root.setTranslateY(250);
        //window.setScene(scene);
        //window.show();
       
        
	}
    
    public static class MenuButton2 extends StackPane {
        private Text text;

        public MenuButton2(String nom) {
            text = new Text(nom);
            //text.setFont(text.getFont().font(30));
            text.setFill(Color.WHITE);
            text.setStyle("-fx-font: 30 defused ;");

            Rectangle fond = new Rectangle(350, 30);
            fond.setOpacity(0.4);
            fond.setFill(Color.BLACK);
            //Flou 
            fond.setEffect(new GaussianBlur(3.5));

            //setAlignment(Pos.CENTER_LEFT);
            
            getChildren().addAll(fond, text);

            setOnMouseEntered(event -> {
                fond.setTranslateX(10);
                text.setTranslateX(10);
                fond.setFill(Color.GREEN);
                text.setFill(Color.BLACK);
            });

            setOnMouseExited(event -> {
                fond.setTranslateX(0);
                text.setTranslateX(0);
                fond.setFill(Color.BLACK);
                text.setFill(Color.WHITE);
            });
            
            DropShadow drop = new DropShadow(50, Color.BLACK);
            drop.setInput(new Glow());

            setOnMousePressed(event -> setEffect(drop));
            setOnMouseReleased(event -> setEffect(null));
        }
    }
    

   
        
		/*while(!p.estTermine()){
			p.jouerTour();
		}*/
        
		
    
	

	
	public static void main(String[] args) {
	
		launch(args);
	
	}
}
