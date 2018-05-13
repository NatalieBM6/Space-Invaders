/**
 *
 * @author Natalie.
 *
 */
public class Velocity {
    private double dx;
    private double dy;
    /**
     * construct Velocity object from Cartesian representation.
     * @param dx the change in position on the x axis.
     * @param dy the change in position on the y axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     * "constructor" - construct Velocity object from Polar representation.
     * @param angle the angle of the vector.
     * @param speed the length of the vector.
     * @return Velocity object.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double angleRad = Math.toRadians(angle - 90.0);
        double dx = Math.cos(angleRad) * speed;
        double dy = Math.sin(angleRad) * speed;
        return new Velocity(dx, dy);
     }
    /**
     * this method returns the velocity's change in position on the x axis.
     * @return the velocity's change in position on the x axis.
     */
    public double getDx() {
        return this.dx;
    }
    /**
     * this method returns the velocity's change in position on the y axis.
     * @return the velocity's change in position on the y axis.
     */
    public double getDy() {
        return this.dy;
    }
    /**
     * this method returns the angle from
     * the polar representation of this velocity.
     * @return the angle from the polar representation of this velocity.
     */
    public double getAngle() {
        double result = Math.toDegrees(Math.atan2(this.dy, this.dx)) + 90;
        if (result < 0) {
            result += 360;
        }
        return result;
    }
    /**
     * this method returns the "speed" (length) from the
     * polar representation of this velocity.
     * @return the "speed" (length) from the
     * polar representation of this velocity.
     */
    public double getSpeed() {
        return Math.sqrt(this.dx * this.dx + this.dy * this.dy);
    }
    /**
     * this method moves a point in two dimensions.
     * @param p a point with position (x,y).
     * @return a new point with position (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        double newX = p.getX() + this.dx;
        double newY = p.getY() + this.dy;
        return new Point(newX, newY);
    }
    /**
     * this method calculates a new velocity according to the given dt value.
     * @param dt the given dt value.
     * @return the new velocity.
     */
    public Velocity adjust(double dt) {
        return new Velocity(this.dx * dt, this.dy * dt);
    }
}