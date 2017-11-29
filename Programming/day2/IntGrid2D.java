/**
 * An class that implements the IIntGrid2D interface for 2D grids that store char values
 */
public class IntGrid2D implements IIntGrid2D {
  int upperLeftX;
  int upperLeftY;
  int lowerRightX;
  int lowerRightY;
  char fillCell;
  char[][] first;

public IntGrid2D (int h, int i, int j, int k, char l){      //constructor
  upperLeftX = h;
  upperLeftY = i;
  lowerRightX = j;
  lowerRightY = k;
  fillCell = l;

  first = new char[Math.abs(lowerRightX-upperLeftX)+1][Math.abs(lowerRightY-upperLeftY)+1];
  for (int n=0; n<(Math.abs(lowerRightX-upperLeftX)+1); n++){
    for (int m=0; m<(Math.abs(lowerRightY-upperLeftY)+1); m++){
      first[n][m] = fillCell;
    }
  }

}

    /**
     * Sets the value at a point on the grid, replacing the previous value if any.
     * @param p The coordinate to set the value of
     * @param v The value to set at the coordinate
     */
    public void setPoint(IIntPoint2D p, char v){
      int x_location = Math.abs(p.getX() + 1);
      int y_location = Math.abs(p.getY()-1);
      first[x_location][y_location] = v;
    }

    /**
     * Gets the value at a point on the grid
     * @param p The coordinate to get the value of
     * @returns the stored value
     */
    public char getPoint(IIntPoint2D p){
      int x_location = Math.abs(p.getX() + 1);
      int y_location = Math.abs(p.getY()-1);
      return first[x_location][y_location];
    }

    /**
     * Gets the coordinate for the upper left most location
     * @returns an IIntPoint that is the coordinate of the upper left corner
     */
    public IIntPoint2D getUpperLeftCorner(){
      IIntPoint2D m = new IntPoint2D(upperLeftX, upperLeftY);
      return m;
    }

    /**
     * Gets the coordinate for the lower right most location
     * @returns an IIntPoint that is the lower right corner
     */
    public IIntPoint2D getLowerRightCorner(){
      IIntPoint2D k = new IntPoint2D(lowerRightX, lowerRightY);
      return k;
    }

}
