package com.daniel.scr;
import java.util.ArrayList;
import java.util.List;

import processing.core.*;

public class lissajous_screen_save1 extends PApplet {

//int n=4;
int p=1;
int q=1;

int a = displayWidth /2 ;
int b = a;//displayHeight /2 ;

int radius = 2;
float miu = 0;//PI / 2 / p;
float miu_max = TWO_PI;
float miu_delta = miu_max / 100;

@Override
public void setup() {
  size(displayWidth, displayHeight);
  background(0);
  frameRate(20);

  a = displayWidth / 2   - 100;
  b = displayHeight / 2 - 100;
}

@Override
public void draw() {

    miu += miu_delta;
    if (miu >= miu_max)
        miu = 0;
//    if (miu >= miu_max)
//    {
//        miu_delta = -miu_delta;
//        miu += miu_delta;
//    }
//    else if (miu < 0)
//    {
//        miu_delta = -miu_delta;
//        miu += miu_delta;
//    }

    fill(0,0,0,1);
    rect(-1,-1, displayWidth+1, displayHeight+1);

    int last_x = -1;
    int last_y = -1;

    int color = (int) random(0, 255);

    for (float theta=0;theta<TWO_PI;theta+=TWO_PI/360)
    {
        int x = (int) (a * sin(p * theta)) + displayWidth /2;
        int y = (int) (b * sin(q * theta + (miu))) + displayHeight /2;

        colorMode(HSB, 255);
        stroke(90, 255, 255);
        fill(90, 255, 255);

        if (last_x != -1 || last_y != -1)
        {
            line(last_x, last_y, x, y);
            line(last_x-1, last_y, x-1, y);
        }

        last_x = x;
        last_y = y;

        //ellipse(x, y, radius, radius);
    }

}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--stop-color=#cccccc", "com.daniel.scr.lissajous_screen_save1"};
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
