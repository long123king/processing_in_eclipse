package com.daniel.scr;
import java.util.ArrayList;
import java.util.List;

import processing.core.*;

public class triangle_screen_save1 extends PApplet {

  final int radius = 3;
  int scale = 1;
  float angle = 0;

@Override
public void setup() {
  size(displayWidth, displayHeight);
  background(0);
  frameRate(90);
}

@Override
public void draw() {

  fill(0, 0, 0, 1);
  rect(-1, -1, displayWidth + 1, displayHeight + 1);

  colorMode(HSB, 255);
  int color = (int) random(100, 255);
//  int color = (int) random(0, 255);
//  int color = 50;
//  stroke(50, 255, 255);
  stroke(90, 255, 255);
//  fill(70, 255, 255);
//  fill(255, color, 255);
//  line(0, displayHeight/2, displayWidth, displayHeight/2);
//  line(displayWidth/2, 0, displayWidth/2, displayHeight);
  scale += 1;
  angle = ((angle + 1) % TWO_PI);

  for (int i = 0; i < displayWidth; i+=3) {
//    ellipse(i, (displayHeight / 2-100) * tan((i - displayWidth/2) * TWO_PI * (scale) / (displayWidth)) + displayHeight /2 , radius, radius);
    line(i, displayHeight/2, i, displayHeight / 2 + 200 * sin ((i - displayWidth /2) * TWO_PI / displayWidth));
  }

}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--stop-color=#cccccc", "com.daniel.scr.triangle_screen_save1"};
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
