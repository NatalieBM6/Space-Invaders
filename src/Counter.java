/**
 *
 * @author Natalie.
 *
 */
public class Counter {
    private int value;
    /**
     * construct a counter from an initial value.
     * @param initialValue the given initial value.
     */
    public Counter(int initialValue) {
        this.value = initialValue;
    }
    // add number to current count.
    /**
     * this method gets an integer and increases the counter accordingly.
     * @param number the given number.
     */
    void increase(int number) {
        this.value += number;
    }
    /**
     * this method gets an integer and decreases the counter accordingly.
     * @param number the given number.
     */
    void decrease(int number) {
        this.value -= number;
    }
    /**
     * this method returns the current value of the counter.
     * @return the current value of the counter.
     */
    int getValue() {
        return this.value;
    }
}