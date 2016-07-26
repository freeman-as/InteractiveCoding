float a = 0;
Box b;
int count;
ArrayList<Box> sponge;

void setup() {
  size(600,400,P3D);
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

void draw(){
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
  rotateY(a * 0.4);
  rotateZ(a * 0.1);
  for(Box b : sponge) {
     b.show();
  }

  a += 0.01;

}
