package com.daniel.d3d;

import java.util.ArrayList;
import java.util.Calendar;

import com.daniel.config.ConfigureManager;

import processing.core.*;
import processing.opengl.*;
import processing.data.*;
import processing.event.*;
import processing.core.PApplet.*;

public class dancingBasket extends PApplet
{

	int numSystems = 40;
	PSystem[] ps = new PSystem[numSystems];
	float theta, theta2 = 0.0f;
	float r;
	float amplitude;
	float x, y,z;
	float inx, iny, inz;
	PVector centerLoc;
	float rotx,roty;
	int bounds = 1000;
	float jx,jy,jz;
	float jxSpeed, jySpeed, jzSpeed, jxRot,jyRot,jzRot;
	int zoom;

	@Override
  public void setup()
	{
	  size(displayWidth, displayHeight, P3D);
	  colorMode(HSB, 360, 100, 100, 100);
	  inx = 0;
	  iny = 0;
	  centerLoc = new PVector(width/2, height/2, 0);
	  for(int i=0; i<numSystems; i++){
	    // dispose PSystems in a circle
	    x = r * cos(theta);
	    y = r * sin(theta);
	    z = 1;
	    x += inx;
	    y += iny;
	    ps[i] = new PSystem(new PVector(x, y, z), 60, theta);
	    theta += 0.16; //0.0572;
	  }
	  r = 20.0f; //40
	  amplitude = r;

	  this.jxSpeed = 1;
	  this.jySpeed = 1;
	  this.jzSpeed = 1;
	  this.jxRot = random(100, 150);
	  this.jyRot = random(100, 150);
	  this.jzRot = random(100, 150);
	}
	@Override
  public void mouseDragged () {
	  zoom =-mouseY*4;
	}

	@Override
  public void draw()
	{
	  translate (width/2,height/2,zoom);
	  rotx += map((float)mouseX, (float)0, (float)width,(float)-0.01,(float)0.01) * HALF_PI;
	  roty += map((float)mouseY, (float)0, (float)height,(float)-0.01,(float)0.01) * HALF_PI;
	  rotateX(rotx);
	  rotateY(roty);

	  noSmooth();
	  background(224, 94, 28, 100);
	  waveR(); // cycle and run all the PSystems

	  pushMatrix();
	  noiseDetail(1,(float) 0.2);
	  jyRot += noise ((float)-0,002,(float)0.002);
	  jzRot += noise ((float)-0.002,(float)0.002);
	  rotateY(radians(jyRot));
	  rotateZ(radians(jzRot));
	  for(int i = 0; i < numSystems; i++)
	  {
	    ps[i].run();
	  }
	  popMatrix();
	}

	public void waveR()
	{
	  theta += 0.03;//0.05
	  r = theta;
	  r = sin(r)*amplitude;
	  r += 100; //100
	}

	class PSystem
	{
	  float th;
	  PVector ps_loc;
	  ArrayList particles;
	  float zP;

	  public PSystem(PVector ps_loc, int num, float th)
	  {
	    this.ps_loc = ps_loc;
	    this.th = th;
	    particles = new ArrayList();
	    for (int i = 0; i < num; i++) {
	      particles.add(new jParticle(new PVector(), new PVector(), new PVector(ps_loc.x, ps_loc.y,ps_loc.z), random((float)1.0, (float)70.0), i));
	    }
	  }

	  void run()
	  {
	    update();
	    for (int i = particles.size()-1; i >= 0; i--) {
	      jParticle prt = (jParticle) particles.get(i);
	      prt.run();
	      //      ps_loc.z = inz+i*10;
	      prt.move(new PVector(ps_loc.x,ps_loc.y,ps_loc.z));


	    }
	    //ellipse(ps_loc.x,ps_loc.y, 10, 10);
	  }

	  void update()
	  {
	    th += 0.0025f;
	    ps_loc.x = inx + r * cos(th);
	    ps_loc.y = iny + r * sin(th);
	    ps_loc.z = inz - r/2;
	    ps_loc.x += random(-30.0f, 30.0f);
	    ps_loc.y += random(-30.0f, 30.0f);
	    //    ps_loc.z += random(-30.0f, 30.0f);

	  }

	}


	class jParticle {
	  PVector loc;
	  PVector vel;
	  PVector acc;
	  float ms;
	  float counter;
	  float lengthVar;

	  jParticle(PVector a, PVector v, PVector l, float ms_, float counter_) {
	    acc = a;
	    vel = v;
	    loc = l;
	    ms = ms_;
	    counter = counter_;
	    lengthVar = random (30);
	  }

	  void run() {
	    update();
	    render();
	    //    print (counter);
	  }

	  void update() {
	    vel.add(acc);
	    loc.add(vel);
	    acc = new PVector();
	  }

	  void render() {
	    noStroke();
	    fill(257, 28, 65, 10);
	    float tenticleSize = ms/30 + 1;
	    strokeWeight(tenticleSize);
	    //    ellipse(loc.x,loc.y, ms/8, ms/8);
	    stroke(238, 14, 85, 30);
	    point(loc.x,loc.y,loc.z-ms*4);
	    float al = map((float)vel.mag(), (float)0, (float)1.2, (float).1, (float)3);

	    stroke(238, 14, 85, al);
	//    stroke(238, 14, 85, 100);
	    //print(counter%5 + " ");
	    //    if(ms >= 69.5)
	    noFill();
	    strokeWeight((float)1.5);
	    if(ms <= 5) {
	      bezier(inx,iny,inz+70, loc.x - (inx-loc.x)/20,loc.y - (iny-loc.y)/20,inz+60,loc.x - (inx-loc.x)/3,loc.y - (iny-loc.y)/3,inz-10,loc.x,loc.y,loc.z + lengthVar);
	       bezier((float)(loc.x + (inx-loc.x)/1.5),(float)(loc.y + (iny-loc.y)/1.5),inz+20, loc.x - (inx-loc.x)/40,loc.y - (iny-loc.y)/40,inz+40,loc.x - (inx-loc.x)/3,loc.y - (iny-loc.y)/3,inz-10,loc.x,loc.y,loc.z + lengthVar);
	    }
	  }

	  void move(PVector target) {
	    acc.add(steer(target));
	  }

	  PVector getLocation() {
	    return loc;
	  }

	  PVector steer(PVector target) {
	    PVector steer;
	    PVector desired = PVector.sub(target,loc);
	    float d = desired.mag();
	    desired.normalize();
	    desired.mult(3.5f);
	    steer = PVector.sub(desired,vel);
	    steer.limit(3.0f);
	    steer.div(ms);
	    return steer;
	  }
	}


	static public void main(String[] passedArgs)
	{
		String[] appletArgs = new String[] { ConfigureManager.FULLSCREEN, dancingBasket.class.getName() };
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
