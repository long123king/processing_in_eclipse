package com.daniel.lines;

import com.daniel.config.ConfigureManager;

import processing.core.*;

public class Photo extends PApplet
{
	@Override
  public void setup()
	{
	  size(displayWidth, displayHeight);
	  frameRate(30);
	  background(0);
	  colorMode(HSB, 255);
	}

	@Override
  public void draw()
	{
	   float x = random(displayWidth / 2, displayWidth);
	   float y = random(displayHeight / 2, displayHeight);
	   float len = random(0, (displayHeight + displayWidth) / 2);
	   float ale = random(0, TWO_PI);

	   float color = random(0, 255);
	   stroke(color, 255, 255);

	   line(x, y, len * sin(ale), len * cos(ale));


	}

	static public void main(String[] passedArgs)
	{
		String[] appletArgs = new String[] { ConfigureManager.FULLSCREEN, Photo.class.getName() };
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
