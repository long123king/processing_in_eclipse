package com.daniel.scr;
import java.util.ArrayList;
import java.util.List;

import processing.core.*;

public class fourier_screen_save1 extends PApplet {

  int radius = 2;
  int[] accumys;
  int times = 0;

  float scale = 1;
  int origin = 400;

  float factor = 1;

  @Override
  public void setup() {
    size(displayWidth, displayHeight);
    background(0);
    frameRate(4);

    accumys = new int[displayWidth];

    for(int i=0;i<displayWidth;i++)
    {
        accumys[i] = 0;
    }

    scale = 1;
    origin = displayHeight / 2;

    factor = (PI * 3/ displayWidth );
  }

  @Override
  public void draw() {

      fill(0,0,0,50);
      rect(-1,-1,displayWidth + 1, displayHeight+1);

      float k = ++times;
      int last_y = 0;
      for (int i=0;i<displayWidth;i++)
      {
          float t = i;
          float up = sin((2 * k - 1) * factor * t);
          float down = (2 * k - 1) * factor;

          accumys[i] += (int) ((float)4 * up / (PI * down));

          if (i >= 1)
          {
              colorMode(HSB, 255);
              stroke(90, 255, 255);
              line(i-1, last_y * scale + origin, i, accumys[i] * scale + origin);
              line(i, last_y * scale + origin, i+1, accumys[i] * scale + origin);
          }

          last_y = accumys[i];
      }

  }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--stop-color=#cccccc", "com.daniel.scr.fourier_screen_save1"};
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
