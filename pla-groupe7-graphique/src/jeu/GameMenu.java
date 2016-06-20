package jeu;

import java.awt.Desktop;
import java.net.URI;
import java.net.URL;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameMenu extends Parent {
    public GameMenu(Stage window) {
    	
        VBox menu0 = new VBox(30);
        VBox menu1 = new VBox(30);
        VBox menu2 = new VBox(30);
        

        menu0.setTranslateX(700);
        menu0.setTranslateY(220);

        menu1.setTranslateX(700);
        menu1.setTranslateY(220);
        
        menu2.setTranslateX(700);
        menu2.setTranslateY(220);

        final int offset =500;

        menu1.setTranslateX(offset);
        menu2.setTranslateX(offset);
       
        
        MenuButton btnStart = new MenuButton("START");
        btnStart.setOnMouseClicked(event -> {
        	Group root = new Group();
    	    Scene scene = new Scene(root, 1700, 750, Color.GREEN);
    	    Partie p = new Partie(new InterfaceGraphique(), new JoueurConsoleZQSD("Toto", "automates1.xml"), new JoueurConsoleZQSD("Titi", "automates2.xml"),root);
    	   // Particule part = new Particule(Color.WHITE, 1000, 500, 1000, 1000, -100, 3500);
            root.getChildren().add(p);
         //   root.getChildren().add(part);
    		
    		//root.getTransforms().add(new Scale(0.82,0.82));
    		//root.getTransforms().add(new Rotate(-38));
    		p.requestFocus();
    		window.setScene(scene);
        	
        
        
        });
         
     


        MenuButton btnOptions = new MenuButton("OPTIONS");
        btnOptions.setOnMouseClicked(event -> {
            getChildren().add(menu1);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
            tt.setToX(menu0.getTranslateX() - offset);

            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.25), menu1);
            tt1.setToX(menu0.getTranslateX());

            tt.play();
            tt1.play();

            tt.setOnFinished(evt -> {
                getChildren().remove(menu0);
            });
        });
        
        MenuButton btnHelp = new MenuButton("HELP");
        btnHelp.setOnMouseClicked(event -> {
        	 
        	getChildren().add(menu2);
        	TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
            tt.setToX(menu0.getTranslateX() - offset);

            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.25), menu2);
            tt1.setToX(menu0.getTranslateX());

            tt.play();
            tt1.play();

            tt.setOnFinished(evt -> {
                getChildren().remove(menu0);
            });
        	
        });

        MenuButton btnExit = new MenuButton("EXIT");
        btnExit.setOnMouseClicked(event -> {
            System.exit(0);
        });
        
        
        
        
        
        MenuButton btnBack = new MenuButton("BACK");
        btnBack.setOnMouseClicked(event -> {
            getChildren().add(menu0);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu2);
            tt.setToX(menu2.getTranslateX() + offset);

            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.25), menu0);
            tt1.setToX(menu2.getTranslateX());

            tt.play();
            tt1.play();

            tt.setOnFinished(evt -> {
                
                getChildren().remove(menu2);
                
            });
        });
        
        MenuButton btnBack1 = new MenuButton("BACK");
        btnBack1.setOnMouseClicked(event -> {
            getChildren().add(menu0);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu1);
            tt.setToX(menu1.getTranslateX() + offset);

            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.25), menu0);
            tt1.setToX(menu1.getTranslateX());

            tt.play();
            tt1.play();

            tt.setOnFinished(evt -> {
                getChildren().remove(menu1);
                
                
            });
        });

        
        //On met du gros son
        MenuButton btnSound = new MenuButton("SOUND");
        btnSound.setOnMouseClicked(event -> {
            final URL resource = getClass().getResource("/images/epique.mp3");
            final Media media = new Media(resource.toString());
            final MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(Animation.INDEFINITE);
            mediaPlayer.play();
});
        
       /*MenuButton btnVideo = new MenuButton("VIDEO");
       btnVideo.setOnMouseClicked(event -> {
        	final File file = new File("/images/dub-back.mp4"); 
            final Media media = new Media(file.toURI().toString()); 
            final MediaPlayer mediaPlayer = new MediaPlayer(media); 
            final MediaView mediaView = new MediaView(mediaPlayer); 
            getChildren().add(mediaView);
            mediaPlayer.play();});*/
        
        
        
        
        MenuButton2 btnNotice = new MenuButton2("MANUEL UTILISATEUR");
        MenuButton2 btnNotice2 = new MenuButton2("MANUEL DEVELOPPEUR");
        
        btnNotice.setOnMouseClicked(event -> {
        	try {
				Desktop.getDesktop().browse(new URI("https://github.com/geourjoa/pla-groupe7-graphique/blob/master/pla-groupe7-graphique/manuel_de_jeu_attacks_on_behavior.pdf"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}});

        btnNotice2.setOnMouseClicked(event -> {
        	try {
				Desktop.getDesktop().browse(new URI("https://github.com/geourjoa/pla-groupe7-graphique/blob/master/pla-groupe7-graphique/manuel_de_developpement_attacks_on_behavior.pdf"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}});
        
        menu0.getChildren().addAll(btnStart, btnOptions, btnHelp,btnExit);
        menu1.getChildren().addAll(btnSound,btnBack1);
        menu2.getChildren().addAll(btnNotice,btnNotice2,btnBack);
        

        Rectangle fond = new Rectangle(1700, 750);
        fond.setFill(Color.RED);
        fond.setOpacity(0.1);

        getChildren().addAll( fond,menu0);
    }
}