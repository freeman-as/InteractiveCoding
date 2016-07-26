import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class menger_sponge_fractal extends PApplet {

float a = 0;
Box b;
int count;
ArrayList<Box> sponge;

public void setup() {
  
  sponge = new ArrayList<Box>();
  b = new Box(0, 0, 0, 200);
  sponge.add(b);
  println(sponge.size());
}

//void mousePressed() {
//  count++;
//  if(count > 2){
//    sponge.clear();
//    b = new Box(0, 0, 0, 200);
//    sponge.add(b);
//    count = 0;
//  }
//  ArrayList<Box> next = new ArrayList<Box>();
//  for(Box b : sponge){
//    ArrayList<Box> newBoxes = b.generate();
//    next.addAll(newBoxes);
//  }
//  sponge = next;
//  println(count);
//  println(sponge.size());
//}

public void draw(){
  background(100);
  stroke(255);
  noFill();
  lights();
  if(frameCount % 120 == 0){
    count++;
    if(count > 2){
      sponge.clear();
      b = new Box(0, 0, 0, 200);
      sponge.add(b);
      count = 0;
    }
    ArrayList<Box> next = new ArrayList<Box>();
    for(Box b : sponge){
      ArrayList<Box> newBoxes = b.generate();
      next.addAll(newBoxes);
    }
    sponge = next;
  }

  translate(width/2, height/2);
  rotateX(a);
  rotateY(a * 0.4f);
  rotateZ(a * 0.1f);
  for(Box b : sponge) {
     b.show();
  }

  a += 0.01f;

}
class Box {
    PVector pos;
    float r;

  Box(float x, float y, float z, float r_){
    pos = new PVector(x, y, z);
    r = r_;
  }
  public ArrayList<Box> generate() {
    ArrayList<Box> boxes = new ArrayList<Box>();
    for(int x = -1; x < 2; x++){
      for(int y = -1; y < 2; y++){
        for(int z = -1; z < 2; z++){

          int sum = abs(x)+ abs(y) + abs(z);

          float newR = r/3;
          if(sum > 1){
            Box b = new Box(
              pos.x + x * newR,
              pos.y + y * newR,
              pos.z + z * newR,
              newR
            );
            boxes.add(b);
          }
        }
      }
    }
    return boxes;
  }

  public void show(){
    pushMatrix();
    translate(pos.x, pos.y, pos.z);
    noStroke();
    colorMode(HSB, 360, 100, 100);
    fill(random(360), 0, 100, 255);
    box(r * (0.98f));
    popMatrix();
  }
}
  public void settings() {  size(600,400,P3D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "menger_sponge_fractal" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
