package com.example.rpsgame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

   private static final Random RANDOM_GENERATOR = new Random();
   private static final int BEST_OF_THREE_GAMES = 3;
   private static final int BEST_OF_FIVE_GAMES = 5;
   private static final int BEST_OF_TEN_GAMES = 10;

   private static final int BEST_OF_THREE = 1;
   private static final int BEST_OF_FIVE = 2;
   private static final int BEST_OF_TEN = 3;

   private int[] buttonNumbers = new int[]{BEST_OF_THREE,BEST_OF_FIVE,BEST_OF_TEN};
   private int[] numberOfRounds = new int[]{BEST_OF_THREE_GAMES,BEST_OF_FIVE_GAMES,BEST_OF_TEN_GAMES};
   private String[] roundNumbers = new String[]{"Best of Three","Best of Five","Best of Ten"};

   private int[] gameFlowImages = new int[]{R.drawable.rock,R.drawable.paper,R.drawable.scissors};
   private int[] winnerImages = new int[]{R.drawable.trophy,R.drawable.winner2};
   private int[] loserImages = new int[]{R.drawable.loser,R.drawable.loser2};

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
   private int targetWins;
   private String message;
   private String selectedMode;

   private TextView[] numberOfGamesLabel;
   private Button[] numberOfGamesButtons;
   private Button[] gameFlowButtons;
   private TextView[] gameFlowLabels;

   private Button bestOfThreeButton;
   private Button bestOfFiveButton;
   private Button bestOfTenButton;

   private Button rockButton;
   private Button paperButton;
   private Button scissorsButton;
   private Button resetButton;
   private Button submitButton;

   private TextView bestOfThreeGamesLabel;
   private TextView bestOfFiveGamesLabel;
   private TextView bestOfTenGamesLabel;
   private TextView rockLabel;
   private TextView paperLabel;
   private TextView scissorsLabel;
   private TextView userScoreLabel;
   private TextView computerScoreLabel;
   private TextView computerRoundScoreLabel;
   private TextView playerRoundScoreLabel;
   private TextView activeLabel;

   private ImageView playerChoiceImageView;
   private ImageView computerChoiceImageView;
   private ImageView endGameResultsImage;

   private Runnable clearScreenTimer;
   private Runnable displayWhoWonTimer;
   private Runnable clearLastImage;

   private GameStatus gameStatus = GameStatus.GAME_IN_PROGRESS;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        findViews();
        initializeGamePlayButtonsListener();
        initializeNumberOfGamesButtonsListener();

    }

    private void initializeGamePlayButtonsListener(){
       for(int i = 0; i < gameFlowButtons.length; i++){
           gamePlayButtons(gameFlowButtons[i]);
       }
   }

   private void initializeNumberOfGamesButtonsListener(){
       for(int i = 0; i < numberOfGamesButtons.length; i++){
            numberOfGamesActionListener(numberOfGamesButtons[i]);
       }
   }

   private void numberOfGamesActionListener(Button buttons){
       buttons.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
                turns = setNumberOfGames(v);
                turns = level;
           }
       });
   }

   private void gamePlayButtons(Button button){
       button.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
               if(gameStatus == GameStatus.GAME_IN_PROGRESS){
                    setChoice(v);
                    resolveRound();
               }
           }
       });
   }

   private Runnable showTemporaryMessage(TextView view,int duration,Runnable runnable){
       view.removeCallbacks(runnable);
       Runnable newTask = new Runnable(){
           @Override
           public void run() {
               view.setText("");
           }
       };
       view.postDelayed(newTask,duration);
       return newTask;
   }

   private void showTemporaryImage(ImageView imageView){
       imageView.removeCallbacks(clearLastImage);
       clearLastImage = new Runnable(){
           public void run(){
               imageView.setVisibility(View.INVISIBLE);
           }
       };
       imageView.postDelayed(clearLastImage,5000);
   }

   private void setChoice(View v){
        for(int i = 0; i < gameFlowButtons.length; i++){
            if(v.getId() == gameFlowButtons[i].getId()){
                playerChoice = i;
                printPlayerChoice(gameFlowLabels[i],gameFlowImages[i]);
            }
        }
   }
   private void printPlayerChoice(TextView view,int imageIcon){
        view.setText(imageIcon);
    }

    private int setNumberOfGames(View v){
        for(int i = 0; i < numberOfGamesButtons.length; i++){
            if(v.getId() == numberOfGamesButtons[i].getId()){
                buttonId = buttonNumbers[i];
                selectedMode = roundNumbers[i];
            }
        }
        buttonControl(numberOfGamesButtons,false);
        buttonControl(gameFlowButtons,true);
        turns = processButton();
        return buttonId;
    }

    private int processButton(){
        for(int i = 0; i < buttonNumbers.length; i++){
            level = buttonNumbers[i];
            roundRemaining = level;
            targetWins = calculateTargetWins(level);
            activeLabel = numberOfGamesLabel[i];
            printOutNumberOfGames(activeLabel,numberOfRounds[i]);
        }
        return buttonId;
    }
    private void checkWhoWon(){

   }

   private boolean hasComputerWon(){
       return true;
   }

   private boolean hasPlayerWon(){
       return true;
   }

   private boolean isGameDrawn(){
       return true;
   }

   private String getGameMessage(String message){
       return "";
   }

   private int calculateTargetWins(int totalRounds){
       return 0;
   }

   private void resolveRound(){

   }

   private void scheduleWinTextClear(){

   }

   private void printOutWhoWon(String message){

   }

   private void resetGameValues(){

   }

   private void resetGameButtons(){

   }

   private void resetGameScores(){

   }

   private void resetGameImages(){

   }

   private void resetGameLabels(){

   }
   private void printOutNumberOfGames(TextView view,int numberOfGames){

   }

   private void buttonControl(Button[] buttons,boolean isEnabled){

   }

   private void setComputerImage(){

   }

   private void printOutCurrentRoundScores(TextView view,int roundScore){

   }

   private void printCurrentScores(TextView view,int score){

   }

   private void updateScore(){

   }

   private void checkRoundWinner(){

   }

   private void handleScoringSystem(boolean isPlayer,String winnerName){

   }

   private void checkRoundTurns(int roundScore){

   }

   private void resetHandScores(){

   }
   private void findViews(){
        submitButton = findViewById(R.id.submit_button);
        resetButton = findViewById(R.id.reset_button);
        rockButton = findViewById(R.id.rock_button);
        paperButton = findViewById(R.id.paper_button);
        scissorsButton = findViewById(R.id.scissors_button);
        bestOfThreeButton = findViewById(R.id.best_of_three_games);
        bestOfFiveButton = findViewById(R.id.best_of_five_games);
        bestOfTenButton = findViewById(R.id.best_of_ten_games);

        bestOfThreeGamesLabel = findViewById(R.id.best_of_three_text_field);
        bestOfFiveGamesLabel = findViewById(R.id.best_of_five_text_field);
        bestOfTenGamesLabel = findViewById(R.id.best_of_ten_text_field);
        userScoreLabel = findViewById(R.id.user_score);
        computerScoreLabel = findViewById(R.id.computer_score);
        computerRoundScoreLabel = findViewById(R.id.computer_round_score);
        playerRoundScoreLabel = findViewById(R.id.user_round_score);

        playerChoiceImageView = findViewById(R.id.player_image);
        computerChoiceImageView = findViewById(R.id.computer_image);
        endGameResultsImage = findViewById(R.id.image_game_result_view);

        numberOfGamesLabel = initArray(bestOfThreeGamesLabel,bestOfFiveGamesLabel,bestOfTenGamesLabel);
        numberOfGamesButtons = initArray(bestOfThreeButton,bestOfFiveButton,bestOfTenButton);
        gameFlowButtons = initArray(rockButton,paperButton,scissorsButton);
        gameFlowLabels = initArray(rockLabel,paperLabel,scissorsLabel);

    }

    private Button[] initArray(Button...items){
        return items;
    }

    private TextView[] initArray(TextView...items){
        return items;
    }
}