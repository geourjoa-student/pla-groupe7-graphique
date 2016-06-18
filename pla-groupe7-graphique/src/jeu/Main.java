package jeu;
	
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;





public class Main extends Application {


	@Override
    public void start(Stage primaryStage) {
		
		
		
		primaryStage.setTitle("ATTACK ON BEHAVIOR");
        Group root = new Group();
        Scene scene = new Scene(root, 1368, 690, Color.FORESTGREEN);
      /*  InterfaceGraphique inter = new InterfaceGraphique();
		int tab[][] = { 
				{1,1,1,0,1,0,1,0,0,1,1,0,1,1},
				{1,1,1,3,3,3,0,1,1,1,0,1,1,1},
				{1,1,0,3,3,0,6,1,1,0,1,1,1,0},
				{1,1,1,1,1,1,1,1,0,1,2,1,1,0},
				{1,0,1,1,0,1,1,1,4,1,2,1,0,0},
				{1,1,1,1,1,1,1,1,0,1,2,1,0,0},
				{0,1,1,1,1,1,1,1,1,0,1,1,1,1},
				{1,1,0,1,1,1,1,4,0,1,1,1,0,1},
				{1,1,1,0,1,1,1,0,1,2,1,2,1,1},
				{1,1,5,0,1,1,0,1,1,1,1,1,0,1},
				{0,1,1,1,0,1,6,1,2,1,1,0,1,1},
				{1,3,3,1,0,0,1,1,1,1,0,1,1,1},
				{1,3,3,3,0,0,0,1,1,0,5,1,1,1},
				{0,3,3,3,0,1,0,1,0,1,1,1,1,1}
				
			
		};
		
		
		root.getChildren().add(inter);
		
		root.getTransforms().add(new Scale(1,1));
        primaryStage.setScene(scene);
        primaryStage.show();
        
       // inter.afficherMap(tab);
        inter.requestFocus();
        */
        
        //InterfaceGraphique i = new InterfaceGraphique();
        
        Partie p = new Partie(new InterfaceGraphique(), new JoueurConsoleZQSD("Toto", "automates1.xml"), new JoueurConsoleZQSD("Titi", "automates2.xml"),root);
   
        root.getChildren().add(p);
		
		root.getTransforms().add(new Scale(0.82,0.82));
		//root.getTransforms().add(new Rotate(-38));
		//root.setTranslateX(200);
		//root.setTranslateY(250);
        primaryStage.setScene(scene);
        primaryStage.show();
        p.requestFocus();
   
        
		/*while(!p.estTermine()){
			p.jouerTour();
		}*/
        
		
    }
	
	

	
	public static void main(String[] args) {
	
		launch(args);
	
	}
}
