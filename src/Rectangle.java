import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Natalie.
 *
 */
public class Rectangle {
    private Line top;
    private Line bottom;
    private Line left;
    private Line right;
    /**
     * construct a rectangle from an upper left point, width and height.
     * @param upperLeft the rectangle upper left point.
     * @param width the rectangle's width.
     * @param height the rectangle's height.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.top = new Line(upperLeft.getX(), upperLeft.getY(),
                            upperLeft.getX() + width, upperLeft.getY());
        this.bottom = new Line(upperLeft.getX(), upperLeft.getY() + height,
                           upperLeft.getX() + width, upperLeft.getY() + height);
        this.left = new Line(upperLeft.getX(), upperLeft.getY(),
                             upperLeft.getX(), upperLeft.getY() + height);
        this.right = new Line(upperLeft.getX() + width, upperLeft.getY(),
                          upperLeft.getX() + width, upperLeft.getY() + height);
    }
    /**
     * construct a rectangle from two coordinates, width and height.
     * @param x the x coordinate of the initial location
     * of the rectangle's upper left corner.
     * @param y the y coordinate of the initial location
     * of the rectangle's upper left corner.
     * @param width the rectangle's width.
     * @param height the rectangle's height.
     */
    public Rectangle(double x, double y, double width, double height) {
        this.top = new Line(x, y, x + width, y);
        this.bottom = new Line(x, y + height, x + width, y + height);
        this.left = new Line(x, y, x, y + height);
        this.right = new Line(x + width, y, x + width, y + height);
    }
    /**
     * this method returns the rectangle's width.
     * @return the rectangle's width.
     */
    public double getWidth() {
        return this.top.length();
    }
    /**
     * this method returns the rectangle's height.
     * @return the rectangle's height.
     */
    public double getHeight() {
        return this.left.length();
    }
    /**
     * this method returns the rectangle's upper left corner.
     * @return the rectangle's upper left corner.
     */
    public Point getUpperLeft() {
        return this.top.intersectionWith(this.left);
    }
    /**
     * this method returns the rectangle's top line.
     * @return the rectangle's top line.
     */
    public Line getTop() {
        return this.top;
    }
    /**
     * this method returns the rectangle's bottom line.
     * @return the rectangle's bottom line.
     */
    public Line getBottom() {
        return this.bottom;
    }
    /**
     * this method returns the rectangle's left line.
     * @return the rectangle's left line.
     */
    public Line getLeft() {
        return this.left;
    }
    /**
     * this method returns the rectangle's right line.
     * @return the rectangle's right line.
     */
    public Line getRight() {
        return this.right;
    }
    /**
     * this method returns a List of this rectangle's
     * intersection points with a given line.
     * @param line the given line.
     * @return a List of this rectangle's intersection points with a given line
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> l = new ArrayList<Point>();
        if (line.isIntersecting(this.top)) {
            l.add(line.intersectionWith(this.top));
        }
        if (line.isIntersecting(this.bottom)) {
            l.add(line.intersectionWith(this.bottom));
        }
        if (line.isIntersecting(this.left)) {
            l.add(line.intersectionWith(this.left));
        }
        if (line.isIntersecting(this.right)) {
            l.add(line.intersectionWith(this.right));
        }
        return l;
    }
    /**
     * this method checks if a given point is this rectangle area.
     * @param point the given point.
     * @return true if the point is in the rectangle's area, false otherwise.
     */
    public boolean isContainingPoint(Point point) {
        return point.getX() >= this.getUpperLeft().getX()
            && point.getX() <= this.getUpperLeft().getX() + this.getWidth()
            && point.getY() >= this.getUpperLeft().getY()
            && point.getY() <= this.getUpperLeft().getY() + this.getHeight();
    }
    /**
     * this nethod returns the closest edge to a given inner point.
     * @param inner the given inner point.
     * @return the closest edge.
     */
    public Line closestEdge(Point inner) {
        Line leftPlumb = new Line(inner, new Point(
                this.left.start().getX(), inner.getY()));
        Line rightPlumb = new Line(inner, new Point(
                this.right.start().getX(), inner.getY()));
        Line topPlumb = new Line(inner, new Point(
                inner.getX(), this.top.start().getY()));
        Line bottomPlumb = new Line(inner, new Point(
                inner.getX(), this.bottom.start().getY()));
        double min = leftPlumb.length();
        if (min > rightPlumb.length()) {
            min = rightPlumb.length();
        }
        if (min > topPlumb.length()) {
            min = topPlumb.length();
        }
        if (min > bottomPlumb.length()) {
            min = bottomPlumb.length();
        }
        if (min == leftPlumb.length()) {
            return this.left;
        } else if (min == rightPlumb.length()) {
            return this.right;
        } else if (min == topPlumb.length()) {
            return this.top;
        } else {
            return this.bottom;
        }
    }
}