package com.example.rpsgame;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

   private static final Random RANDOM_GENERATOR = new Random();
   private static GameStatus gameStatus;
   private static final int BEST_OF_THREE_GAMES = 3;
   private static final int BEST_OF_FIVE_GAMES = 5;
   private static final int BEST_OF_TEN_GAMES = 10;

   private static final int BEST_OF_THREE = 1;
   private static final int BEST_OF_FIVE = 2;
   private static final int BEST_OF_TEN = 3;

   private int[] buttonNumbers = new int[]{BEST_OF_THREE,BEST_OF_FIVE,BEST_OF_TEN};
   private int[] numberOfRounds = new int[]{BEST_OF_THREE_GAMES,BEST_OF_FIVE_GAMES,BEST_OF_TEN_GAMES};
   private String[] roundNumber = new String[]{"Best of Three","Best of Five","Best of Ten"};

   private int computerChoice;
   private int playerChoice;
   private int playerScore = 0;
   private int computerScore = 0;
   private int computerRoundScore = 0;
   private int playerRoundScore = 0;
   private int buttonId;
   private int turns;
   private int level;
   private int choice;
   private int roundRemaining;
   private String message;
   private String selectedMode;

   private TextView[] numberOfGamesLabel;
   private Button[] numberOfGamesButtons;
   private Button[] gameFlowButtons;
   private Button[] gameFlowLabels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

    }

    private void findViews(){

    }
}