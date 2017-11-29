import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class IntPoint2D implements IIntPoint2D{
  int x;
  int y;

  public IntPoint2D (int i, int j){   // constructor
    x = i;
    y = j;
  }

  public int getX(){
      return x;
  }

  public int getY(){
      return y;
  }

  public int manhattanDistance(IIntPoint2D o){
    return abs(x-o.getX()) + abs(y-o.getY());
  }

  public double distance(IIntPoint2D o){
    return sqrt((x-o.getX())*(x-o.getX()) + (y-o.getY())*(y-o.getY()));
  }

  public String toString(){
    return "(" + x + " , " + y + ")";
  }

  public boolean equals(Object o){
    IntPoint2D p = (IntPoint2D)o;    //this is called "casting" in which we tie a new class object to our anonymous initial object
    if (x==p.x && y==p.y) {return true; }
    return false;
  }

  public int hashcode(){
    return (x<<16)+y;
  }


}
