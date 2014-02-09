package daniel.king;
import processing.core.*; 

public class circle_screen_save2 extends PApplet {

float circle_x = (float) 0.0;
float circle_y = (float) 0.0;
float circle_radius = (float) 0.0;
int circle_color = 0;

public void setup() {
	size(displayWidth, displayHeight);
	background(0);
	frameRate(10);
}

public void draw() {
	
	  circle_x = random(0, displayWidth);
	  circle_y = random(0, displayHeight);
	  circle_radius = random(0, (displayWidth + displayHeight) / 5);
	  
	  circle_color = (int) random(0, 255);
	  
	  colorMode(RGB, 255);
//	  fill(0,0,0,5);
//	  rect(-1, -1, displayWidth+1, displayHeight+1);
	  
	  //noStroke();
	  colorMode(HSB, 255);
	  stroke(circle_color, 255, 255);
	  fill(circle_color, 255, 255);
	  noFill();
	  ellipse(circle_x, circle_y, circle_radius, circle_radius);
	  ellipse(circle_x, circle_y, circle_radius-1, circle_radius-1);
		
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--stop-color=#cccccc", "daniel.king.circle_screen_save2" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
