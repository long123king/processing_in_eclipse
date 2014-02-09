package com.daniel.scr;
import java.util.ArrayList;
import java.util.List;

import processing.core.*;

public class triangle_screen_save extends PApplet {

  final int radius = 3;
  int scale = 1;

@Override
public void setup() {
  size(displayWidth, displayHeight);
  background(0);
  frameRate(60);
}

@Override
public void draw() {

  fill(0, 0, 0, 150);
  rect(-1, -1, displayWidth + 1, displayHeight + 1);

  colorMode(HSB, 255);
//  int color = (int) random(0, 255);
  int color = 50;
  stroke(color, 255, 255);
  fill(color, 255, 255);
  line(0, displayHeight/2, displayWidth, displayHeight/2);
  line(displayWidth/2, 0, displayWidth/2, displayHeight);
  scale += 1;

  for (int i = 0; i < displayWidth; i++) {
    ellipse(i, displayHeight / 2 * sin((i - displayWidth/2) * TWO_PI * (scale) / (displayWidth)) + displayHeight /2 , radius, radius);
  }

}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--stop-color=#cccccc", "com.daniel.scr.triangle_screen_save"};
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
