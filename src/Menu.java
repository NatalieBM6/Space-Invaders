/**
 *
 * @author Natalie.
 *
 * @param <T> generic object the menu has.
 */
public interface Menu<T> extends Animation {
    /**
     * this method adds a selection to this menu.
     * @param key the key of the selection.
     * @param message the name of the selection.
     * @param returnVal the selection value according to the menu type.
     */
    void addSelection(String key, String message, T returnVal);
    /**
     * this method adds a sub-menu to this menu.
     * @param key the key of the selection.
     * @param message the name of the selection.
     * @param subMenu the given sub-menu.
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
    /**
     * this method resets this menu.
     */
    void reset();
    /**
     * this method returns the selected value after the occurrence
     * of a selection event, according to the menu type.
     * @return the selected value.
     */
    T getStatus();
}