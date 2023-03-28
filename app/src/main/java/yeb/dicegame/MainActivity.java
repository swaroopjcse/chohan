package yeb.dicegame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView txtD1, txtD2;
    TextView txtCoins;
    Button btnPlay;
    Die d1, d2;
    private SwitchCompat switchOddEven;
    private EditText editWager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = findViewById(R.id.btn_play);
        txtD1 = findViewById(R.id.txt_d1);
        txtD2 = findViewById(R.id.txt_d2);
        txtCoins = findViewById(R.id.txt_coins);

        switchOddEven = findViewById(R.id.switch_odd_even);
        editWager = findViewById(R.id.edit_wager);

        d1 = new Die();
        d2 = new Die();

        btnPlay.setOnClickListener(v -> {
            d1.roll();
            d2.roll();

            int newCoins = calculateCoins(txtCoins, editWager, switchOddEven);
            updateUI(newCoins);
        });
    }


    private int calculateCoins(TextView txtCoins, EditText editWager, @NonNull SwitchCompat switchOddEven) {
        int coins = getIntFromTextView(txtCoins);
        int wager = getIntFromTextView(editWager);
        boolean betForEven = switchOddEven.isChecked();

        int diceSum = d1.value() + d2.value();

        if (betForEven && isEven(diceSum)) {
            coins += wager;
        } else if (!betForEven && !isEven(diceSum)) {
            coins += wager;
        } else {
            coins -= wager;
        }
        return coins;
    }

    private boolean isEven(int num) {
        return num % 2 == 0;
    }

    private void updateUI(int newCoins) {
        showIntOnATextView(txtD1, d1.value());
        showIntOnATextView(txtD2, d2.value());
        showIntOnATextView(txtCoins, newCoins);
    }

    private void showIntOnATextView(@NonNull TextView txtView, int num) {
        txtView.setText(String.format(Locale.ENGLISH, "%d", num));
    }

    private int getIntFromTextView(@NonNull TextView txt) {
        String inputString = txt.getText().toString().trim();
        return Integer.parseInt(inputString);
    }
}