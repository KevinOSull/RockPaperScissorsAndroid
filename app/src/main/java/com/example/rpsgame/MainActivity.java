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
    private static final int BEST_OF_THREE_GAMES = 3;
    private static final int BEST_OF_FIVE_GAMES = 5;
    private static final int BEST_OF_TEN_GAMES = 10;
    private static final int BEST_OF_THREE = 1;
    private static final int BEST_OF_FIVE = 2;
    private static final int BEST_OF_TEN = 3;
    private Button rockBtn;
    private Button scissorsBtn;
    private Button paperBtn;
    private Button bestOfThreeGamesBtn;
    private Button bestOfFiveGamesBtn;
    private Button bestOfTenGamesBtn;
    private Button resetGameBtn;
    private int level;
    private int turns;
    private int buttonId;
    private int computerScore = 0;
    private int playerScore = 0;
    private int playerRoundScore = 0;
    private int computerRoundScore = 0;
    private int playerChoice;
    private int computerChoice;
    private static GameStatus gameStatus = GameStatus.GAME_IN_PROGRESS;
    private static int foo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

    }

    private void findViews(){

    }
}