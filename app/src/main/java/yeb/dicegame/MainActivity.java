package yeb.dicegame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView txtD1, txtD2;
    TextView txtCoins;
    Button btnPlay;
    private SwitchCompat switchOddEven;
    private EditText editWager;
    Die d1, d2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getAllViewElements();
        setNewDice();
        updateUI(100);

        setPlayButtonClickListener();
    }

    /**
     * Get all references for all relevant view elements.
     */
    private void getAllViewElements() {
        btnPlay = findViewById(R.id.btn_play);
        txtD1 = findViewById(R.id.txt_d1);
        txtD2 = findViewById(R.id.txt_d2);
        txtCoins = findViewById(R.id.txt_coins);
        switchOddEven = findViewById(R.id.switch_odd_even);
        editWager = findViewById(R.id.edit_wager);
    }

    /**
     * Creates new instances of the two dice.
     */
    private void setNewDice() {
        d1 = new Die();
        d2 = new Die();
    }

    /**
     * Update the UI to display the given number as the number of coins the player has at this point.
     */
    private void updateUI(int numCoins) {
        showIntOnATextView(txtD1, d1.value());
        showIntOnATextView(txtD2, d2.value());
        showIntOnATextView(txtCoins, numCoins);

        editWager.setText("");
    }

    /**
     * Implements the behaviour of clicking the PLAY button: roll the dice, calculate the new coins based on the wager, update the UI to reflect the changes.
     */
    private void setPlayButtonClickListener() {
        btnPlay.setOnClickListener(v -> {
            d1.roll();
            d2.roll();

            int newCoins = calculateCoins(txtCoins, editWager, switchOddEven);
            updateUI(newCoins);
        });
    }

    /**
     * Calculate and return the new number of coins the player has based on the game play.
     *
     * @param txtCoins      TextView that shows the told coins
     * @param editWager     EditText that shows the wager amount
     * @param switchOddEven Switch that indicates whether the player bet for the sum to be even or odd
     * @return the new number of coins
     */
    private int calculateCoins(TextView txtCoins, EditText editWager, @NonNull SwitchCompat switchOddEven) {
        return 0;
    }

    /**
     * Reports whether the given non-negative integer is even.
     *
     * @param num the number to check for evenness
     * @return true iff num is even
     */
    private boolean isEven(int num) {
        return num % 2 == 0;
    }

    /**
     * Shows the given message as a toast on the screen.
     *
     * @param message message to display
     */
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Displays the given integer on the given TextView
     */
    private void showIntOnATextView(@NonNull TextView txtView, int num) {
        txtView.setText(String.format(Locale.ENGLISH, "%d", num));
    }

    /**
     * Returns the integer displayed on the given text view. The app can crash if the given text view does not represent an int.
     */
    private int getIntFromTextView(@NonNull TextView txt) {
        String inputString = txt.getText().toString().trim();
        return Integer.parseInt(inputString);
    }
}