package yeb.dicegame;

import java.util.Random;

public class Die {
    Random rng;
    int value;

    public Die() {
        rng = new Random();
    }

    public void roll() {
        value = 1 + rng.nextInt(6);
    }

    public int value() {
        return value;
    }
}
