/**
 *
 * @author Natalie.
 *
 */
public class Point {
    private double x;
    private double y;

    /**
     * The function builds a point.
     * @param x the x value of a point.
     * @param y the y value of a point.
     */
       public Point(double x, double y) {
           this.x = x;
           this.y = y;
       }

       /**
        * The function receives a point and finds the distance between it to another point.
        * @param other another Point.
        * @return return the distance of this point to the other point.
        */
       public double distance(Point other) {
           double dx = this.x - other.getX();
           double dy = this.y - other.getY();
           return Math.sqrt((dx * dx) + (dy * dy));
       }

       /**
        * The function checks if two points are equal.
        * @param other another point.
        * @return true if the points are equal, false otherwise.
        */
      public boolean equals(Point other) {
           if ((other.getX() == this.x) && (other.getY() == this.y)) {
               return true;
           }
           return false;
       }

       /**
        * The function finds the x value of a point.
        * @return the x value of this point.
        */
       public double getX() {
           return this.x;
       }
       /**
        * The function finds the y value of a point.
        * @return the y value of this point.
        */
       public double getY() {
           return this.y;
       }

    /**
     * @param newX the x to set
     */
    public void setX(double newX) {
        this.x = newX;
    }

    /**
     * @param newY the y to set
     */
    public void setY(double newY) {
        this.y = newY;
    }
}
