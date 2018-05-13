/**
 *
 * @author Natalie.
 *
 */
public interface HitNotifier {
    /**
     * The function adds hl as a listener to hit events.
     * @param hl a hit listener.
     */
    void addHitListener(HitListener hl);
    /**
     * The function removes hl from the list of listeners
     * to hit events.
     * @param hl a hit listener.
     */
    void removeHitListener(HitListener hl);
}
