package yeb.dicegame;

import java.util.Random;

public class Die {
    private final Random rng;
    private int value;

    public Die() {
        rng = new Random();
    }

    /**
     * Rolls the die.
     */
    public void roll() {
        value = 1 + rng.nextInt(6);
    }

    /**
     * Reports the value of the top face of the die.
     *
     * @return number of dots on the top face of the die
     */
    public int value() {
        return value;
    }
}
