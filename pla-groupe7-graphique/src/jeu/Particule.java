package jeu;

import java.util.Random;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class Particule extends Parent {
	
	public Particule(Color couleur, int nombre, int largeur, int hauteur, int x, int y, int duree){
		
		Random generator;
		generator = new Random();
		double num = generator.nextGaussian();
		
		for (int i =0; i< nombre; i++){
		
			int rad = (int)(Math.random() * 10);
		Circle particule = new Circle( rad, couleur);
		
		num = generator.nextGaussian();
		num = num -0.5;
				
		int xalea =  (int) (num * largeur)+ x;
		
		Path path = new Path();
		path.getElements().add(new MoveTo(xalea,y));
		path.getElements().add(new LineTo(xalea,y-rad*hauteur/10+hauteur));
		PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.millis(duree));
		pathTransition.setPath(path);
		pathTransition.setNode(particule);
		
		
		FadeTransition fadeTransition = 
	    new FadeTransition(Duration.millis(duree), particule);
	    fadeTransition.setFromValue(0.60f);
	    fadeTransition.setToValue(0.f);
	 
		
		Particule this2 = this;
		
		ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(fadeTransition,pathTransition);
        
		parallelTransition.setOnFinished(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent ae){
				this2.getChildren().remove(particule);
			}
		}
		);
		
		parallelTransition.setDelay(Duration.millis( (int)(Math.random() * duree)));
		parallelTransition.setCycleCount(Timeline.INDEFINITE);;
		parallelTransition.play();
		
		this.getChildren().add(particule);
		}
		
	}

}
