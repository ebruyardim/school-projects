

import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
 
public class Assignment4 extends Application {
 
	int score = 0;	
	int level;	
	int hizArtisi = 2;
	Cars[] cars;
	Label label;
	AnimationTimer animation;
	Circle[] leftTrees;
	Circle[] rightTrees;
	Rectangle[] roadways;
	Rectangle line;
	Pane root;
	Label scoreLabel;
	Label levelLabel;
	Label gameOver;
	Player player;	
	Scene scene;
	
	
	public void restart() {
		root.getChildren().remove(gameOver);
		score = 0;
		level = 0;
		for (int i=0;i<300;i++) {
			cars[i].setFill(Color.YELLOW);
		}
		player.setFill(Color.RED);
		player.setTranslateX(280);
		root.getChildren().remove(cars[carNo]);
		root.getChildren().remove(cars[carNo+1]);		
	}
	
	int carNo = 299; 
	int katSayi = 0;
	public void checkCollision(){
		
		if (!cars[carNo].getType().equals("X")) {
			if ((cars[carNo].getLayoutY() > (katSayi*100)+75)  && (cars[carNo].getLayoutY() > (katSayi*100)+85)) {	
				cars[carNo].setType("X");
				if ((player.getTranslateX() > cars[carNo].getTranslateX()-45) && player.getTranslateX() < cars[carNo].getTranslateX()+50) {	
					player.setFill(Color.BLACK);
					cars[carNo].setFill(Color.BLACK);
			        gameOver.setText("        GAME OVER!\n        Your Score: "+score+"\n\nPress ENTER to restart!");
					animation.stop();
			    	root.getChildren().add(gameOver);
				}
			}
		}
		else {
			carNo--;
			katSayi++;
		}
	}
	
	int firstCarY = 145;
	int carNumber = 299;
	public void TurnGreen() {
		if (cars[carNumber].getLayoutY()>firstCarY-5 && cars[carNumber].getLayoutY()>firstCarY+5) {
			cars[carNumber].setFill(Color.GREEN);
			carNumber--;
			firstCarY+=100;
		}
	}
	
	public void levelCounter() {
		level = score/10;
		levelLabel.setText("Level: "+Integer.toString(level));
		if (score%10==0 && level!=0) {
			hizArtisi = level+2;
		}
	}
	
	int s=100;			
	public void scoreCounter() {
		if (line.getLayoutY()>s) {
			score+=1;
			s+=100;
			scoreLabel.setText("Score: "+Integer.toString(score));
		}
	}
	
    @Override
    public void start(final Stage myStage) {
 
        root = new Pane();       
        
        root.setStyle("-fx-background-color: GREY");

        Rectangle solKenar = new Rectangle(120,800,Color.GREEN);           
        solKenar.relocate(0,0);
        
        Rectangle sagKenar = new Rectangle(380,800,Color.GREEN);        
        sagKenar.relocate(475,0);         
       
        scoreLabel = new Label();	
        scoreLabel.setText("Score: "+Integer.toString(score));
        scoreLabel.setFont(Font.font("Cambria", 20));

        levelLabel = new Label();
        levelLabel.setText("Level: "+Integer.toString(level));
        levelLabel.setTranslateY(20);
        levelLabel.setFont(Font.font("Cambria", 20));
        
        gameOver = new Label();
        gameOver.setText("        GAME OVER!\n        Your Score: "+score+"\n\nPress ENTER to restart!");
        gameOver.setTextFill(Color.RED);
        gameOver.setFont(Font.font("Cambria", FontWeight.BOLD, 50));
        gameOver.setTranslateX(50);
        gameOver.setTranslateY(200);
        
        
        player = new Player(280, 700, 50, 70, "player", Color.RED);

        // TREES
        leftTrees = new Circle[1000];
        rightTrees = new Circle[1000];
        int y = -10000;
        int x;
        for (int i=0;i<1000;i++) {
        	if (i%3==0)
        		x=25;
        	else if (i%3==1)
        		x=75;
        	else
        		x=100;
        	Circle circle1 = new Circle(x,y,20,Color.DARKGREEN);
        	leftTrees[i] = circle1;
        	Circle circle2 = new Circle(480+x,y,20,Color.DARKGREEN);
        	rightTrees[i] = circle2;
        	y+=200;
        }

        // ROADWAY
        roadways = new Rectangle[1000];

        int b = -10000;
        int a=300;
        for (int i=0;i<1000;i++) {
        	Rectangle roadway = new Rectangle(a,b,7,50);
        	roadways[i] = roadway;
        	b+=80;
        }        
        
        // CARS
        cars = new Cars[1000];
        
        Random random = new Random();
        int v = -29350; // Y coordinate
        for (int i=0;i<1000;i++) {
            int u = random.nextInt(305)+125;  // X coordinate
            Cars car = new Cars(u,v,50,70,"Car",Color.YELLOW);
            cars[i] = car;
            v+=100;
        }
                
        // FINISH LINE 
        line = new Rectangle(120,-10000,355,5);
        
        
        animation = new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				for (int i=0;i<1000;i++) {
					leftTrees[i].setLayoutY(leftTrees[i].getLayoutY()+hizArtisi);
					rightTrees[i].setLayoutY(rightTrees[i].getLayoutY()+hizArtisi);
					roadways[i].setLayoutY(roadways[i].getLayoutY()+hizArtisi);
					cars[i].setLayoutY(cars[i].getLayoutY()+hizArtisi);
					TurnGreen();
				}
				line.setLayoutY(line.getLayoutY()+hizArtisi);
	            levelCounter();
	            scoreCounter();
	            checkCollision();
			}
        };
       

      
        root.getChildren().addAll(solKenar,sagKenar);
        for (int i=0;i<1000;i++)
        	root.getChildren().addAll(leftTrees[i],roadways[i],rightTrees[i]);
        
        root.getChildren().addAll(player,scoreLabel,levelLabel);
        
        for (int i=0;i<300;i++)
        	root.getChildren().addAll(cars[i]);

          
        Scene scene = new Scene(root, 600, 800);
        
        scene.setOnKeyPressed(e ->{
        	switch (e.getCode()) {
        	case LEFT:
        		player.moveLeft();	
        		break;
        	case RIGHT:
        		player.moveRight();
        		break;
        	case UP:
        		animation.start();
        		break;
        	case ENTER:
        		restart();
        		break;
			default:
				break;
        	}
        });

        
        myStage.setTitle("HUBBM-Racer");
        myStage.setScene(scene);
        myStage.show();


    }
    

 





	public static void main(String[] args) {
        launch(args);  
    }
 

}