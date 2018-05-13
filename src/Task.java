/**
 *
 * @author Natalie.
 *
 * @param <T> generic object the menu has.
 */
public interface Task<T> {
    /**
     * The function runs the animation loop.
     * @return the animation.
     */
    T run();
}
