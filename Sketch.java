import processing.core.PApplet;

public class Sketch extends PApplet {

  // Related arrays for the (x, y) coordinate of the snowflakes
  float[] snowX = new float[42];
  float[] snowY = new float[42];
  int snowDiameter = 10;

  public void settings() {
    size(400, 400);
  }

  public void setup() {
    background(0);

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
  }
  
  // All other defined methods are written below:
  public void snow() {
    for (int i = 0; i < snowX.length; i++ ) {
      circle(snowX[i], snowY[i], snowDiameter);

      snowY[i] += 2; 

      // reset snowflakes back to the top
      if (snowY[i] > height) {
        snowY[i] = 0;
      }
    }
  }
}