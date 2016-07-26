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

public class metaballs extends PApplet {

// Blob b;
Blob[] blobs = new Blob[10];

public void setup() {
  
  colorMode(HSB);
  for (int i = 0; i < blobs.length; i++){
    blobs[i] = new Blob(
      random(width),
      random(height)
    );
  }
}

public void draw(){
  background(51);

  // \u30d4\u30af\u30bb\u30eb\u3092
  loadPixels();
  for(int x = 0; x < width; x++) {
    for(int y = 0; y < height; y++){
      int index = x + y * width;
      // float d = dist(x, y, width/2, height/2);
      float sum = 0;
      for(Blob b : blobs){
        float d = dist(x, y, b.pos.x, b.pos.y);
        sum += 10 * b.r / d;
      }
      pixels[index] = color(sum, 255, 255);
    }
  }

  updatePixels();

  for(Blob b : blobs){
    b.update();
    // b.show();
  }

}
class Blob {
    PVector pos;
    PVector vel;
    float r;

  Blob(float x, float y){
    pos = new PVector(x, y);
    vel = PVector.random2D();
    vel.mult(random(2, 5));
    r = random(10, 150);
  }

  public void update(){
    pos.add(vel);

    if(pos.x > width || pos.x < 0 ){
      vel.x *= -1;
    }
    if(pos.y > height || pos.y < 0 ){
      vel.y *= -1;
    }
  }

  public void show(){
    noFill();
    stroke(0);
    strokeWeight(4);
    ellipse(pos.x, pos.y, r*2, r*2);
  }
}
  public void settings() {  size(640,360); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "metaballs" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
