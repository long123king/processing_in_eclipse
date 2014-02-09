package com.daniel.d2d;
import com.daniel.config.ConfigureManager;

import processing.core.PApplet;

public class ColorPaste extends PApplet{


  int n = 0;

  final int radius = 40;
  final int cellWidth = 150;
  final int cellHeight = 30;

  @Override
  public void setup()
  {
    size(displayWidth, displayHeight);
    noStroke();
    frameRate(3);
    colorMode(HSB, 100, 100, 100);
    background(50, 50, 50);
  }

  @Override
  public void draw()
  {

      int orgX = displayWidth / 2;
      int orgY = displayHeight / 2;

      int theta = (int) (n * 180 / PI);
      int h = cellHeight;
      int w = cellWidth;

      int startX = (int) (orgX + radius * sin(theta) + random(100));
      int startY = (int) (orgY + radius * cos(theta) + random(100));

      int startLX = (int) (startX - h * sin(theta));
      int startLY = (int) (startY + h * cos(theta));

      int startRX = (int) (startX + w * cos(theta));
      int startRY = (int) (startY + w * sin(theta));

      int startTX = (int) (startX + w * cos(theta) - h * sin(theta));
      int startTY = (int) (startY + w * sin(theta) + h * cos(theta));

      fill(n * PI % 100, 100, 100);

      quad(startX, startY, startLX, startLY, startTX, startTY, startRX, startRY);
      n += 1;

  }

  static public void main(String[] passedArgs)
  {
    String[] appletArgs = new String[] { ConfigureManager.FULLSCREEN, ColorPaste.class.getName() };
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
