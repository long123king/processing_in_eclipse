import processing.core.*; 
import processing.data.*; 
import processing.opengl.*; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class sketch_dic22a extends PApplet {

/* OpenProcessing Tweak of *@*http://www.openprocessing.org/sketch/765*@* */
/* !do not delete the line above, required for linking your tweak if you re-upload */
int PMAX = 35000;
ArrayList p = new ArrayList(PMAX);
//Part[] p = new Part[20000];
float speed = 0;
float yCoord = 0;
float myr;
float c = 0.0f; 
boolean drawbg = true;
public void setup() 
{ 
  size(800,600, P2D); 
  //smooth(); 
  colorMode(HSB, 360, 100, 100, 100);
  for(int i=0; i<PMAX; i++){ 
    PVector a = new PVector();  
    PVector v = new PVector();   
    PVector l = new PVector(random(10, width-10),random(10, height-10)); 
    float m = random(1, 10);
  
  
  
//    float r = noise(off)*60; // estrae un numero che vale 1/2 di di r
//    off += 0.01;
//    print("  " + r);


   p.add(new Part (l, v, a, m));
  } 
  
} 

public void draw()
{
  //background(1, 0, 100, 100);
  noStroke();
  fill(1, 100, 0, 75);
  rect(0, 0, width, height);

  yCoord += speed;
  speed += 0.01f;
  stroke(1, 0, 100, 50);
  line(1,yCoord, 4, yCoord);
  line(width-4,yCoord, width-2, yCoord);
  
  
  float progAttrito = map(yCoord, 0, height-10, -0.10f, -0.80f);
  c = progAttrito;
  //print(" "+c);
  if (yCoord > height-10) {
    speed = speed * -0.995f;  
  }
  
  for(int i = p.size()-1; i >= 0; i--){
    Part particle = (Part) p.get(i);
    //print(" " +i);
    particle.live();
    
    // forza grav
    PVector grav = new PVector(0.0f,0.40f);
    particle.add_force(grav);
    
    // attrito
    PVector actualVel = particle.getVel(); 
    PVector attrito = PVector.mult(actualVel,c); 
    particle.add_force(attrito);     

    

    
    if(mousePressed == true){
    // il mouse attrae
    PVector mouseLoc = new PVector(mouseX,mouseY);
    PVector diff = PVector.sub(mouseLoc,particle.getLoc());
    diff.normalize();
      
    float factor = 0.90f; 
    diff.mult(factor);
    particle.setAcc(diff);
    }
      
      if (particle.pop()) {
          p.remove(i); // rimuove
          PVector a = new PVector();  
          PVector v = new PVector();   
          PVector l = new PVector(random(10, width-10),random(100)+yCoord); 
          myr = map(yCoord, 0, height-10, 1, 10);
          p.add(new Part (l, v, a, myr)); // aggiunge
      }
  }
  //if(p.isEmpty())
   //print(" end ");
}

 	

public void keyPressed() { 
  //saveFrame();
}







class Part 
{
  PVector l; 
  PVector v; 
  PVector a; 
  float m; 
  float RMIN;
  float RMAX;
  int cl = 0;
  
  Part(PVector l,PVector v,PVector a, float m)
  {
    this.l = l;
    this.v = v;
    this.a = a;
    this.m = m;
  }
  
  public void live()
  {
    update();
  }
  
  public void update()
  {
  v.add(a);
  l.add(v);
  a.x = 0.0f; 
  a.y = 0.0f; 
  
  // disegna
  float col = map(m, 1, 10, 1, 360);
  stroke(col, 100, 100, 10);
  //print(col+" ");
  point(l.x, l.y);  
  }
  
  public boolean pop() { 
    if (((l.x > width-0) || (l.x < 0)) || ((l.y > height-0) || (l.y < 0))){ 
      return true;
    }
    else return false;   
  }  
  
  public void setLoc(PVector v) {
    l = v;
  }

  public void setVel(PVector v) {
    v = v;
  }

  public void setAcc(PVector v) {
    a = v;
  }
  
  public void add_force(PVector force) {
    force.div(m);  
    a.add(force); 
  } 
  
  public PVector getVel() { 
    return v; 
  } 
 
  public PVector getAcc() { 
    return a; 
  } 
 
  public PVector getLoc() { 
    return l; 
  } 
}  

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--stop-color=#cccccc", "sketch_dic22a" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
