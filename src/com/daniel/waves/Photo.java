package com.daniel.waves;

import com.daniel.config.ConfigureManager;

import processing.core.*;

public class Photo extends PApplet
{

  double[][] arrays;

  boolean valueInRange(double val, double min, double max) // val in [min, max)
  {
    return (val >= min && val < max);
  }
	@Override
  public void setup()
	{
	  size(displayWidth, displayHeight);
	  arrays = new double[displayWidth][displayHeight];
	  for (int i = 0; i < displayWidth; i++) {
      for (int j = 0; j < displayHeight; j++) {
        arrays[i][j] = 0.0;
      }
    }

	  arrays[displayWidth/2][displayHeight/2] = 255.0; // first stone

	  noLoop();
	  frameRate(1);
	  colorMode(RGB, 255);
	  background(0, 0, 0);
	  colorMode(HSB, 255);

	   for (int i = 0; i < displayWidth; i++) {
	      for (int j = 0; j < displayHeight; j++) {
	        double energy = arrays[i][j];
	        if (energy != 0.0) {
	          if (valueInRange(i-1, 0, displayWidth) && valueInRange(j-1, 0, displayHeight)) {
	            arrays[i-1][j-1] += energy * 0.2 * 0.125;
	          }
	          if (valueInRange(i-1, 0, displayWidth) && valueInRange(j, 0, displayHeight)) {
	            arrays[i-1][j] += energy * 0.2 * 0.125;
	          }
	          if (valueInRange(i-1, 0, displayWidth) && valueInRange(j+1, 0, displayHeight)) {
	            arrays[i-1][j+1] += energy * 0.2 * 0.125;
	          }
	          if (valueInRange(i, 0, displayWidth) && valueInRange(j-1, 0, displayHeight)) {
	            arrays[i][j-1] += energy * 0.2 * 0.125;
	          }
	          if (valueInRange(i, 0, displayWidth) && valueInRange(j+1, 0, displayHeight)) {
	            arrays[i][j+1] += energy * 0.2 * 0.125;
	          }
	          if (valueInRange(i+1, 0, displayWidth) && valueInRange(j-1, 0, displayHeight)) {
	            arrays[i+1][j-1] += energy * 0.2 * 0.125;
	          }
	          if (valueInRange(i+1, 0, displayWidth) && valueInRange(j, 0, displayHeight)) {
	            arrays[i+1][j] += energy * 0.2 * 0.125;
	          }
	          if (valueInRange(i+1, 0, displayWidth) && valueInRange(j+1, 0, displayHeight)) {
	            arrays[i+1][j+1] += energy * 0.2 * 0.125;
	          }

	          arrays[i][j] = energy * 0.8 * 0.8;
	        }
	      }
	    }

	    for (int i = 0; i < displayWidth; i++) {
	      for (int j = 0; j < displayHeight; j++) {
	        colorMode(HSB, 255);
	        int color_v = color(255, (int) arrays[i][j], 255);
	        fill(color_v);
	        rect(i, j, 50, 50);
	        //set(i, j, color_v);
	      }
	    }
	}

	@Override
  public void draw()
	{
	  // how to simulate a wave moves
	  // 1/ A wave moves as enlarging circles
	  // 2/ a point's energy transfer to its neighbors
	  // 3/ array to save points' energy
	  // 4/ a way to show how large the energy is
	  // 5/ add rules to fade the energy


	  // rules to fade
	  // 1/ 70 percents energy transfer to neighbors
	  // 2/ 30 percents energy left
	  // 3/ 40 percents energy fade every time





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
