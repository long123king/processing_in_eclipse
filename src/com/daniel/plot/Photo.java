package com.daniel.plot;

import com.daniel.config.ConfigureManager;

import processing.core.*;

public class Photo extends PApplet
{
  PImage imgs[];
  final int nums = 8;
  float a = (float) -100.0;
  float b = (float) 0.0;
  int time = 0;

  final String path = "C:\\Users\\Administrator\\Pictures\\Picasa\\Exports\\upload\\ani\\";

	@Override
  public void setup()
	{
	  size(displayWidth, displayHeight, P3D);
	  imgs = new PImage[nums];
	  for (int i=0;i<nums;i++)
	  {
	    PImage img = loadImage(String.format("%s\\%d.JPG", path, i+1));
	    imgs[i] = img;
	  }
	  frameRate(5);
	}

	@Override
  public void draw()
	{
//	  a = random(displayWidth - img.width);
//	  b = random(displayHeight - img.height);
	  image(imgs[time], a, b);
	  image(imgs[(time+1)%nums], a + imgs[time].width, b);
	  image(imgs[(time+2)%nums], a + imgs[time].width * 2, b);
	  time = (time+1) % nums;
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
