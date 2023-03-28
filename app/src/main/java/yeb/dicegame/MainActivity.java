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

    private void setNewDice() {
        d1 = new Die();
        d2 = new Die();
    }

    private void setPlayButtonClickListener() {
        btnPlay.setOnClickListener(v -> {
            d1.roll();
            d2.roll();

            int newCoins = calculateCoins(txtCoins, editWager, switchOddEven);
            updateUI(newCoins);
        });
    }

    private void getAllViewElements() {
        btnPlay = findViewById(R.id.btn_play);
        txtD1 = findViewById(R.id.txt_d1);
        txtD2 = findViewById(R.id.txt_d2);
        txtCoins = findViewById(R.id.txt_coins);
        switchOddEven = findViewById(R.id.switch_odd_even);
        editWager = findViewById(R.id.edit_wager);
    }

    private int calculateCoins(TextView txtCoins, EditText editWager, @NonNull SwitchCompat switchOddEven) {
        int coins = getIntFromTextView(txtCoins);
        int wager = getIntFromTextView(editWager);
        boolean betForEven = switchOddEven.isChecked();

        int diceSum = d1.value() + d2.value();

        boolean win = (betForEven && isEven(diceSum)) || (!betForEven && !isEven(diceSum));

        if (win) {
            showToast("Congratulations!");
            coins += wager;
        } else {
            showToast("Aww! Try again!");
            coins -= wager;
        }
        return coins;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private boolean isEven(int num) {
        return num % 2 == 0;
    }

    private void updateUI(int numCoins) {
        showIntOnATextView(txtD1, d1.value());
        showIntOnATextView(txtD2, d2.value());
        showIntOnATextView(txtCoins, numCoins);

        editWager.setText("");
        switchOddEven.setChecked(false);
    }

    private void showIntOnATextView(@NonNull TextView txtView, int num) {
        txtView.setText(String.format(Locale.ENGLISH, "%d", num));
    }

    private int getIntFromTextView(@NonNull TextView txt) {
        String inputString = txt.getText().toString().trim();
        return Integer.parseInt(inputString);
    }
}