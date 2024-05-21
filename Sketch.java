import processing.core.PApplet;
import processing.core.PImage;
public class Sketch extends PApplet {
  PImage imgMushroom;

// click on snow = bye bye 
  public void mousePressed(){
    for (int i = 0; i < snowX.length; i++) {
      if (dist(snowX[i], snowY[i], mouseX, mouseY) < snowDiameter) {
        ballHideStatus[i] = true;
      }
    }
  }

  // collision detection
  public boolean collision(float x1, float x2, float y1, float y2, float r1, float r2) {

    float dx = x1 - x2;
    float dy = y1 - y2;
    float distance = sqrt(dx * dx + dy * dy);
    return distance < r1 + r2;
  }

  // the counter bar for the mushroom's health 
  public void drawMushroomLives() {
    
    for (int i = 0; i < mushroomLives; i++) {
      float x = width - 50 + i * 20;
      float y = 20;
      rect(x, y, 10, 10);

    }
  }


  // x and y coordinate for the mushroom
  float fltMushroomX = 0;
  float fltMushroomY = 0;


  // Related arrays for the (x, y) coordinate of the snowflakes
  float[] snowX = new float[42];
  float[] snowY = new float[42];
  boolean[] ballHideStatus = new boolean[42];

  // size of the snowflakes
  int snowDiameter = 10;

  public void settings() {
    size(400, 400);
  }
  
  // making the mushroom lives
  int imgLives = 3;

  public void setup() {
    background(0);

     // load image
     imgMushroom = loadImage("png.png");
     // resize image
     imgMushroom.resize(imgMushroom.width/23, imgMushroom.height/23);
  
  

    // generate random places for the snowflakes to be (x and y values)
    for (int i = 0; i < snowX.length; i++) {
      snowX[i] = random(width);
      snowY[i] = random(height);
      circle(snowX[i], snowY[i], snowDiameter);
    }
  }

  public void draw() {
    background(0);

    // draw snow
    snow();

    // draw image
    image(imgMushroom ,fltMushroomX, fltMushroomY);
    fltMushroomX = constrain(fltMushroomX, 0, width - 50);
    fltMushroomY = constrain(fltMushroomY, 0, height - 50);

    drawMushroomLives();
  

  }
  
  
  // All other defined methods are written below:
  public void snow() {
    for (int i = 0; i < snowX.length; i++ ) {
      if(ballHideStatus[i]) {
        circle(snowX[i], snowY[i], snowDiameter);
        snowY[i] +=0.5;
        snowY[i] += 1; 
      }
      
      // the mushroom lives and collision updater/checker
      if (collision(fltMushroomX + 20, snowX[i], snowY[i], 20, snowDiameter)) {
        mushroomLives--;
        snowY[i] = 0;
      }



      // reset snowflakes back to the top
      if (snowY[i] > height) {
        snowY[i] = 0;
      }
    }
  }
}
