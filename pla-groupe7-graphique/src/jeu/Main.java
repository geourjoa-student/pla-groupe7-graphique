package jeu;
	
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import jeu.InterfaceGraphique;
import jeu.JoueurConsoleZQSD;
import jeu.Particule;
import jeu.Partie;
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

	
	Stage window =new Stage();
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
	
	private class GameMenu extends Parent {
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
       //     final File file = new File("/images/epique.mp3"); 
            final Media media = new Media("/images/epique.mp3"); 
            final MediaPlayer mediaPlayer = new MediaPlayer(media); 
            mediaPlayer.play();});
            
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
					Desktop.getDesktop().browse(new URI("https://www.sharelatex.com/project/575ed3b7449bc1261704e180"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}});

            btnNotice2.setOnMouseClicked(event -> {
            	try {
					Desktop.getDesktop().browse(new URI("https://www.sharelatex.com/project/57652cb9ba15672702160a13"));
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

    private static class MenuButton extends StackPane {
        private Text text;

        public MenuButton(String nom) {
            text = new Text(nom);
            //text.setFont(text.getFont().font(30));
            text.setFill(Color.WHITE);
            text.setStyle("-fx-font: 30 defused ;");

            Rectangle fond = new Rectangle(200, 30);
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
    
    private static class MenuButton2 extends StackPane {
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
