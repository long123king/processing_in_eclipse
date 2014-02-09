package com.daniel.scr;
import java.util.ArrayList;
import java.util.List;

import processing.core.*;

public class path_screen_save extends PApplet {

class TPoint
{
	public TPoint(int _x, int _y) {
		super();
		this._x = _x;
		this._y = _y;
	}
	public int _x;
	public int _y;
	TPoint _next;
}

List<TPoint> points;
int x;
int y;
final int speed = 20;
final int radius = 5;
int points_num = 20;
int xspeed;
int yspeed;
double angle;
TPoint startPoint;
TPoint endPoint;

void calcAngle()
{
	if (endPoint._y == startPoint._y &&
			endPoint._x > startPoint._x)
	{
		angle = HALF_PI;
	}
	else if (endPoint._y == startPoint._y &&
			endPoint._x < startPoint._x)
	{
		angle = PI + HALF_PI;
	}
	else
	{
		angle =  atan((float) ((double)(endPoint._x - startPoint._x)/(double)(endPoint._y - startPoint._y)));
	}
}

void calcSpeed()
{
	if (endPoint._x >= startPoint._x)
	{
		xspeed = (int) (speed * abs(sin((float) angle)));
	}
	else
	{
		xspeed = -(int) (speed * abs(sin((float) angle)));
	}

	if (endPoint._y >= startPoint._y)
	{
		yspeed = (int) (speed * abs(cos((float) angle)));
	}
	else
	{
		yspeed = -(int) (speed * abs(cos((float) angle)));
	}
}

void setupTheme(int theme)
{
	switch(theme)
	{
	case 0:	// random
		{
			  points = new ArrayList<TPoint>(points_num);

			  for (int i=0;i<points_num;i++)
			  {
				  points.add(new TPoint((int)random(100, displayWidth - 100), (int)(random(100, displayHeight - 100))));
			  }

			  for (int i=0;i<points_num;i++)
			  {
				  points.get(i)._next = points.get((i+1) % points_num);
			  }

			  startPoint = points.get(0);
			  endPoint = points.get(1);

			  x = startPoint._x;
			  y = startPoint._y;

			  calcAngle();
			  calcSpeed();
		}
		break;
	case 1: // stars
		{
			 List<TPoint> stars = new ArrayList<TPoint>(5);
			  stars.add(new TPoint(452, 196));
			  stars.add(new TPoint(867, 189));
			  stars.add(new TPoint(526, 494));
			  stars.add(new TPoint(669, 67));
			  stars.add(new TPoint(822, 472));

			  points_num = 5;
			  points = stars;

			  for (int i=0;i<points_num;i++)
			  {
				  points.get(i)._next = points.get((i+1) % points_num);
			  }

			  startPoint = points.get(0);
			  endPoint = points.get(1);

			  x = startPoint._x;
			  y = startPoint._y;

			  calcAngle();
			  calcSpeed();
		}
		break;
	case 2:
		{
			List<TPoint> pulse = new ArrayList<TPoint>(points_num + 2);

			pulse.add(new TPoint(100, displayHeight / 2));
			for (int i=0;i<points_num;i++)
			{
				pulse.add(new TPoint(100 + (i * (displayWidth - 200) / points_num), (int) (displayHeight / 2  +  (pow(-1, (i % 2))) * (int)(random(100, displayHeight / 2 - 100)))));
			}
			pulse.add(new TPoint(displayWidth - 100, displayHeight / 2));

			points_num += 2;

			points = pulse;

			for (int i=0;i<points_num;i++)
			{
				points.get(i)._next = points.get((i+1) % points_num);
			}

			startPoint = points.get(0);
			endPoint = points.get(1);

			x = startPoint._x;
			y = startPoint._y;

			calcAngle();
			calcSpeed();
		}
		break;
	default:
		{

		}
		break;
	}
}

@Override
public void setup() {
  size(displayWidth, displayHeight);
  background(0);
  frameRate(90);

  setupTheme(1);
}

void determineNextPos()
{
	if (endPoint._y == y && abs(endPoint._x - x) < abs(speed))
	{
		startPoint = endPoint;
		endPoint = endPoint._next;

		calcAngle();
		calcSpeed();

		x = startPoint._x;
		y = startPoint._y;
	}
	else if (endPoint._x == x && abs(endPoint._y - y) < abs(speed))
	{
		startPoint = endPoint;
		endPoint = endPoint._next;

		calcAngle();
		calcSpeed();

		x = startPoint._x;
		y = startPoint._y;
	}
	else if ((endPoint._x != x) &&
			(endPoint._y != y) &&
			(abs(endPoint._x - x) < abs(speed * sin((float) angle) / 2) ||
			abs(endPoint._y - y) < abs(speed * cos((float) angle) /2 )))
	{
		startPoint = endPoint;
		endPoint = endPoint._next;

		calcAngle();
		calcSpeed();

		x = startPoint._x;
		y = startPoint._y;
	}
	else
	{
		x += xspeed;
		y += yspeed;
	}
}

@Override
public void draw() {

 determineNextPos();

  colorMode(RGB, 255);
  fill(0,0,0,10);
  rect(-1, -1, displayWidth+1, displayHeight+1);

  noFill();
  colorMode(HSB, 255);
  stroke(random(0, 255), 255, 255);

  ellipse(x, y, radius, radius);
  ellipse(x, y, radius + 1, radius + 1);

}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--stop-color=#cccccc", "com.daniel.scr.path_screen_save"};
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
