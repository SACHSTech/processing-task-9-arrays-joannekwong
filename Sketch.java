import processing.core.PApplet;

public class Sketch extends PApplet {
	float[] circleY = new float[10];
  float[] circleX = new float[10];
	float circleYSpeed = 1; 
  float playerSpeed = 2; 
  float playerX = 200;
  float playerY = 200;
  boolean upPressed = false;
  boolean downPressed = false;
  boolean leftPressed = false;
  boolean rightPressed = false;
  int playerLives = 3;
  int currentCollision = -1;
  boolean[] ballHidden = new boolean[10];
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(400, 400);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    background(210, 255, 173); 
    for(int i = 0; i < circleY.length; i++){
      circleY[i] = random(height);
      circleX[i] = random(width);
      ballHidden[i] = false;
    }
    
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    if(playerLives > 0){
  	  background(50);
  
      for(int i = 0; i < playerLives; i++){
        fill(79, 219, 217);
        rect(0 + (i * 24),0, 20, 20);
      }
      
      for(int i = 0; i < circleY.length; i++){
        
        fill(255);
        if(ballHidden[i] == false){
          ellipse(circleX[i], circleY[i], 15, 15);
        }
        circleY[i] += circleYSpeed;
        
        if(circleY[i] > height){
          circleY[i] = 0;
        }
        
        if(ballHidden[i] == false && i != currentCollision && dist(playerX, playerY, circleX[i], circleY[i]) < 30){
          currentCollision = i;
          playerLives--;
        }
  
        if(ballHidden[i] == false && mousePressed && dist(mouseX, mouseY, circleX[i], circleY[i]) < 15){
          ballHidden[i] = true;
        }
        
      }
      
      if(keyCode == UP){
        if(circleYSpeed > 0){
          circleYSpeed -= 0.02;
        }
      }
      if(keyCode == DOWN){
        circleYSpeed += 0.02;
      }
      if(upPressed){
        playerY -= playerSpeed;
      }
      if(downPressed){
        playerY += playerSpeed;
      }
      if(leftPressed){
        playerX -= playerSpeed;
      }
      if(rightPressed){
        playerX += playerSpeed;
      }
      
      fill(79, 219, 217);
      ellipse(playerX, playerY, 20, 20);
    }
    else{
      background(255);
    }
  }
  
  // define other methods down here.
  
  
  public void keyPressed(){
    if(keyCode == UP || key == 'w'){
      upPressed = true;
    }
    else if (keyCode == DOWN || key == 's'){
      downPressed = true;
    }
    else if(keyCode == LEFT || key == 'a'){
      leftPressed = true;
    }
    else if(keyCode == RIGHT || key == 'd'){
      rightPressed = true;
    }
  }
  
  public void keyReleased() {
    if(keyCode == UP || key == 'w'){
      upPressed = false;
    }
    else if (keyCode == DOWN || key == 's'){
      downPressed = false;
    }
    else if(keyCode == LEFT || key == 'a'){
      leftPressed = false;
    }
    else if(keyCode == RIGHT || key == 'd'){
      rightPressed = false;
    }
  }
  
}