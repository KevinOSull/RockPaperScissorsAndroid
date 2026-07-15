package com.example.rpsgame;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final int NO_DRAWABLE = -1;
    private final int currentDrawable = NO_DRAWABLE;
    private static final int[] GAME_IMAGES = {
            R.drawable.paper,
            R.drawable.scissors,
            R.drawable.rock
    };

    private int[] winnerImages = new int[]{};
    private int[]loserImages = new int[]{};

    private Button rockButton;
    private Button scissorsButton;
    private Button paperButton;
    private Button bestOfThreeGamesBtn;
    private Button bestOfFiveGamesBtn;
    private Button bestOfTenGamesBtn;
    private Button resetGameBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void findViews(){

    }
}