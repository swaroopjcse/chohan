package yeb.dicegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txtD1, txtD2;
    TextView txtCoins;
    Button btnPlay;
    int coins;
    Die d1, d2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = findViewById(R.id.btn_play);
        txtD1 = findViewById(R.id.txt_d1);
        txtD2 = findViewById(R.id.txt_d2);
        txtCoins = findViewById(R.id.txt_coins);

        btnPlay.setOnClickListener(v -> {
            EditText editWager = findViewById(R.id.edit_wager);
            int wager = getIntFromEditText(editWager);

        });
    }

    private int getIntFromEditText(EditText editText) {
        String inputString = editText.getText().toString();
        return Integer.parseInt(inputString);
    }
}