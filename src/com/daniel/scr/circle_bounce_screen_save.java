package daniel.king;
import java.util.ArrayList;
import java.util.List;

import processing.core.*; 

public class circle_bounce_screen_save extends PApplet {

public static final int circle_nums = 20;

class BCircle
{
	public BCircle(float x, float y, float radius, float x_delta, float y_delta, int color) {
		super();
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.x_delta = x_delta;
		this.y_delta = y_delta;
		this.color = color;
	}
	private float x = (float) 0.0;
	private float y = (float) 0.0;
	private float radius = (float) 0.0;
	
	private float x_delta = (float) 0.0;
	private float y_delta = (float) 0.0;	
	private int color = 0; // 0 - 255
	
	private boolean valueInRange(float val, float min, float max)
	{
		return (val >= min && val < max);
	}
	
	public void moveOneStep()
	{
		if (valueInRange(x+x_delta, 0, displayWidth) && 
				valueInRange(y+y_delta, 0, displayHeight))
		{
			x += x_delta;
			y += y_delta;
		}

		if (!valueInRange(x+x_delta, 0, displayWidth))
		{
			x_delta = -x_delta;
			x += x_delta;
		}
		
		if (!valueInRange(y+y_delta, 0, displayHeight))
		{
			y_delta = - y_delta;
			y += y_delta;
		}
			
	}
	
	public void draw(int theme)
	{	  
		switch(theme)
		{
			case 1:
			{
				noFill();
				colorMode(HSB, 255);
				stroke(color, 255, 255);
				ellipse(x, y, radius+2, radius+2);
				ellipse(x, y, radius+1, radius+1);
				ellipse(x, y, radius, radius);
			}
			break;
			case 2:
			{
				colorMode(HSB, 255);
				fill(color, 255, 255);
				ellipse(x, y, radius+2, radius+2);
				ellipse(x, y, radius+1, radius+1);
				ellipse(x, y, radius, radius);
			}
		}
		
	}
}

List<BCircle> circles;
 
public void setup() {
  size(displayWidth, displayHeight);
  background(0);
  frameRate(10);
  
  circles = new ArrayList<BCircle>(circle_nums);
}
 
public void draw() {
  
	colorMode(RGB, 255);
	fill(0,0,0,30);
	rect(-1, -1, displayWidth+1, displayHeight+1);
	
	if (circles.size() < circle_nums)
	{
		float x = random(0, displayWidth);
		float y = random(0, displayHeight);
		float radius = random(20, (displayWidth + displayHeight) / 5);
		float x_delta = random(-50, 50); 
		float y_delta = random(-50, 50);
		int color = (int) random(0, 255);
		BCircle circle = new BCircle(x, y, radius, x_delta, y_delta, color);
		
		circles.add(circle);
	}
	
	for(int i=0;i<circles.size();i++)
	{
		circles.get(i).moveOneStep();
		circles.get(i).draw(1);
	}    
}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--stop-color=#cccccc", "daniel.king.circle_bounce_screen_save"};
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
