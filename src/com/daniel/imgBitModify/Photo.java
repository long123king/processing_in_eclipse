package com.daniel.imgBitModify;

import com.daniel.config.ConfigureManager;

import processing.core.*;

public class Photo extends PApplet
{
  final String path = "F:\\Photo\\2011-02-15\\DSC_1476.JPG";
  PImage img;
  double scale = 1.0;
  PImage cacheImage;


  int g_times = 0;

	@Override
  public void setup()
	{
	  size(displayWidth, displayHeight);
	  img = loadImage(path);

	  frameRate(30);







//	    int halfImage = width*height/2;
//
//	    loadPixels();
//	    for (int i = 0; i < halfImage; i++) {
//	      int pixel = pixels[i];
//	      pixel = (pixel & 0xFF00FFFF) | (0x00FF0000 - (pixel & 0x00FF0000));
//	      pixels[i+halfImage] = pixel;
//	    }
//	    updatePixels();


	}

	@Override
  public void draw()
	{
	  scale((float) ((double)displayWidth / (double)img.width));
	  image(img, 0, 0);
    colorMode(HSB, 255);
    g_times = (++g_times) % 255;

    for (int i = 0; i < displayWidth; i++) {
     for (int j = 0; j < displayHeight; j++) {
       float hue_v = hue(get(i,j));
       float sat_v = saturation(get(i, j));
       float bri_v = brightness(get(i, j));

       hue_v += (mouseX * 255/ displayWidth);
       sat_v += (mouseY * 255/ displayHeight);

       int c_v = color(hue_v, sat_v, bri_v);
       set(i, j, c_v);
     }
   }


    float width_div = (float)img.width / (float)255.;
    float height_div = 150;
    for (int i = 0; i < 255; i++) {
      noStroke();
      fill(i, 255,255);
      rect(i * width_div, 0, width_div + 5, height_div);
    }

    height_div = (float)img.height / (float)255.;
    width_div = 150;
    for (int i = 0; i < 255; i++) {
      noStroke();
      fill((mouseX * 255/ displayWidth), i ,255);
      rect(0, i * height_div,width_div, height_div + 5);
    }


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
