package com.daniel.d2d;
import com.daniel.config.ConfigureManager;

import processing.core.PApplet;

public class ColorArray extends PApplet{

  int hue = 0;

  final static int GRIDS = 10;

  @Override
  public void setup()
  {
    size(displayWidth, displayHeight);
    noStroke();
    frameRate(1);
  }

  @Override
  public void draw()
  {
    colorMode(HSB, 100, 100, 100);
    background(50, 50, 50);

    int cellX = (displayWidth) / GRIDS;
    int cellY = (displayHeight) / GRIDS;

    hue = (hue + 1) % 101;

    for (int i=0;i<GRIDS;i++)
    {
      for (int j=0;j<GRIDS;j++)
      {
        fill(i * 100 / GRIDS, 50 + j * 50 / GRIDS, 100);
        rect(i * cellX, j * cellY, (i+1)*cellX, (j+1)*cellY);
      }
    }
  }

  static public void main(String[] passedArgs)
  {
    String[] appletArgs = new String[] { ConfigureManager.FULLSCREEN, ColorArray.class.getName() };
    if (passedArgs != null)
    {
      PApplet.main(concat(appletArgs, passedArgs));
    }
    else
    {
      PApplet.main(appletArgs);
    }
  }

}
