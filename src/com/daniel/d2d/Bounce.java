package com.daniel.d2d;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import com.daniel.config.ConfigureManager;
import com.daniel.plot.Plot;

import processing.core.PApplet;
import sun.misc.BASE64Encoder;

public class Bounce extends PApplet{

  /**
   * Bounce.
   *
   * When the shape hits the edge of the window, it reverses its direction.
   */

  int rad = 60;        // Width of the shape
  float xpos, ypos;    // Starting position of shape

  float xspeed = (float) 2.8;  // Speed of the shape
  float yspeed = (float) 2.2;  // Speed of the shape

  int xdirection = 1;  // Left or Right
  int ydirection = 1;  // Top to Bottom


  @Override
  public void setup()
  {
    size(displayWidth, displayHeight);
    noStroke();
    frameRate(60);
    ellipseMode(RADIUS);
    // Set the starting position of the shape
    xpos = width/2;
    ypos = height/2;
  }

  @Override
  public void draw()
  {
    colorMode(HSB, 100, 100, 100);
    background(50, 50, 50);

    xspeed = (float) (xspeed + 0.2);
    yspeed = (float) (yspeed + 0.2);

    // Update the position of the shape
    xpos = xpos + ( xspeed * xdirection );
    ypos = ypos + ( yspeed * ydirection );

    // Test to see if the shape exceeds the boundaries of the screen
    // If it does, reverse its direction by multiplying by -1
    if (xpos > width-rad || xpos < rad) {
      xdirection *= -1;
      //xspeed = (float) 2.8;
      //yspeed = (float) 2.2;
    }
    if (ypos > height-rad || ypos < rad) {
      ydirection *= -1;
      //xspeed = (float) 2.8;
      //yspeed = (float) 2.2;
    }

    fill(70, 50, 20);
    // Draw the shape
    ellipse(xpos, ypos, rad, rad);
  }

  static public void main(String[] passedArgs)
  {
    String[] appletArgs = new String[] { ConfigureManager.FULLSCREEN, Bounce.class.getName() };
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
