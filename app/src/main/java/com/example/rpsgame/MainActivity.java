package com.example.rpsgame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedHashMap;
import java.util.Map;
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
   private TextView printOutWhoWonLabel;
   private ImageView playerChoiceImageView;
   private ImageView computerChoiceImageView;
   private ImageView endGameResultsImage;

   private Runnable clearScreenTimer;
   private Runnable displayWhoWonTimer;


   private GameStatus gameStatus = GameStatus.GAME_IN_PROGRESS;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        findViews();
        playerChoiceImageView.setVisibility(View.INVISIBLE);
        computerChoiceImageView.setVisibility(View.INVISIBLE);
        endGameResultsImage.setVisibility(View.INVISIBLE);
        initializeGamePlayButtonsListener();
        initializeNumberOfGamesButtonsListener();
        resetButtonListener();
        //buttonControl(gameFlowButtons, false);
        resetGameValues();
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

   private void resetButtonListener(){
       resetButton.setOnClickListener(new View.OnClickListener(){

           @Override
           public void onClick(View v) {
               resetGameValues();
           }
       });
   }
   private Runnable displayOutputTimer(int timeDelay,Runnable taskToRun){
        playerChoiceImageView.postDelayed(taskToRun,timeDelay);
        return taskToRun;
    }

    private void stopTimer(Runnable task){
        if(task != null){
            playerChoiceImageView.removeCallbacks(task);
        }
    }

   private void setChoice(View v){
        for(int i = 0; i < gameFlowButtons.length; i++){
            if(v.getId() == gameFlowButtons[i].getId()){
                playerChoice = i;
                //printPlayerChoice(gameFlowLabels[i],gameFlowImages[i]);
                playerChoiceImageView.setImageResource(gameFlowImages[playerChoice]);
                playerChoiceImageView.setVisibility(View.VISIBLE);
            }
        }
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
        resetGameScores();
        return buttonId;
    }

    private int processButton(){
        for(int i = 0; i < buttonNumbers.length; i++) {
            if (buttonId == buttonNumbers[i]) {
                level = numberOfRounds[i];
                roundRemaining = level;
                targetWins = calculateTargetWins(level);
                activeLabel = numberOfGamesLabel[i];
                printOutNumberOfGames(activeLabel, numberOfRounds[i]);
            }
        }
        return buttonId;
    }
    private void checkWhoWon(){
        Map<Integer,Boolean> winConditions = new LinkedHashMap<>();
        winConditions.put(R.string.computer_win,hasComputerWon());
        winConditions.put(R.string.player_win,hasPlayerWon());
        winConditions.put(R.string.outcome_is_a_draw,isGameDrawn());
        for(Map.Entry<Integer,Boolean> entry:winConditions.entrySet()){
            if(entry.getValue()){
                String message = getString(entry.getKey());
                printOutWhoWon(printOutWhoWonLabel,message);
                scheduleWinTextClear();
                updateScore();
                checkRoundWinner();
                break;
            }
        }
   }

   private boolean hasComputerWon(){
       return  (computerChoice == 0 && playerChoice == 2)||
               (computerChoice == 2 && playerChoice == 1)||
               (computerChoice == 1 && playerChoice == 0);
   }

   private boolean hasPlayerWon(){
       return  (playerChoice == 0 && computerChoice == 2)||
               (playerChoice == 2 && computerChoice == 1)||
               (playerChoice == 1 && computerChoice == 0);
   }

   private boolean isGameDrawn(){
       return computerChoice == playerChoice;
   }

   private int calculateTargetWins(int totalRounds){
       return (totalRounds/2)+1;
   }

   private void resolveRound(){
        computerChoice = getComputerChoice();
        setComputerImage();
        checkWhoWon();
        stopTimer(clearScreenTimer);
        clearScreenTimer = displayOutputTimer(3000,()->resetGameImages());
    }

   private int getComputerChoice(){
       return RANDOM_GENERATOR.nextInt(gameFlowImages.length);
   }

   private void scheduleWinTextClear(){
        stopTimer(displayWhoWonTimer);
        displayWhoWonTimer = displayOutputTimer(3000,()->printOutWhoWonLabel.setText(""));
   }

   private TextView printOutWhoWon(TextView view,String message){
       view.setText(message);
       return view;
   }

   private void resetGameValues(){
       gameStatus = GameStatus.GAME_IN_PROGRESS;
       resetGameButtons();
       resetGameScores();
       resetGameLabels();
       resetGameImages();
   }

   private void resetGameButtons(){
        buttonControl(gameFlowButtons,false);
        buttonControl(numberOfGamesButtons,true);
   }

   private void resetGameScores(){
        playerScore = 0;
        computerScore = 0;
        playerRoundScore = 0;
        computerRoundScore = 0;
        printCurrentScores(userScoreLabel, playerScore);
        printCurrentScores(computerScoreLabel, computerScore);
        printOutCurrentRoundScores(playerRoundScoreLabel, playerRoundScore);
        printOutCurrentRoundScores(computerRoundScoreLabel, computerRoundScore);
        
   }

   private void resetGameImages(){
       playerChoiceImageView.setImageResource(0);
       computerChoiceImageView.setImageResource(0);
       playerChoiceImageView.setVisibility(View.INVISIBLE);
       computerChoiceImageView.setVisibility(View.INVISIBLE);
       checkIfaNewGameStarted();
   }

   private void checkIfaNewGameStarted(){
       if(gameStatus == GameStatus.GAME_IN_PROGRESS){
           endGameResultsImage.setImageResource(0);
           endGameResultsImage.setVisibility(View.INVISIBLE);
       }
   }

   private void resetGameLabels(){
       for(int i = 0; i < numberOfGamesLabel.length; i++){
           numberOfGamesLabel[i].setText("");
       }
   }
   private void printOutNumberOfGames(TextView view,int numberOfGames){
        view.setText(" " + numberOfGames);
   }

   private void buttonControl(Button[] buttons,boolean isEnabled){
       for(int i = 0; i < buttons.length; i++){
           buttons[i].setEnabled(isEnabled);
       }
   }

   private void setComputerImage(){
       computerChoiceImageView.setImageResource(gameFlowImages[computerChoice]);
       computerChoiceImageView.setVisibility(View.VISIBLE);
   }

   private void printOutCurrentRoundScores(TextView view,int roundScore){
       view.setText(String.valueOf(roundScore));
   }

   private void printCurrentScores(TextView view,int score){
       view.setText(String.valueOf(score));
   }

   private void updateScore(){
       if(hasComputerWon()){
           computerScore++;
           printCurrentScores(computerScoreLabel,computerScore);
       }else if(hasPlayerWon()){
           playerScore++;
           printCurrentScores(userScoreLabel,playerScore);
       }
   }

   private void checkRoundWinner(){
       if(playerScore >= level){
           handleScoringSystem(true,"Player");
       }else if(computerScore >= level){
           handleScoringSystem(false,"Computer");
       }
   }

   private void handleScoringSystem(boolean isPlayer,String winnerName){
       message = winnerName;
       if(isPlayer){
           playerRoundScore++;
           printOutCurrentRoundScores(playerRoundScoreLabel,playerRoundScore);
       }else{
           computerRoundScore++;
           printOutCurrentRoundScores(computerRoundScoreLabel,computerRoundScore);
       }
       resetHandScores();
       roundRemaining--;
       printOutNumberOfGames(activeLabel,roundRemaining);
       checkRoundTurns(isPlayer ? playerRoundScore : computerRoundScore);
   }

   private void checkRoundTurns(int roundScore){
       if(roundScore >= targetWins){
           gameStatus = GameStatus.GAME_OVER;
           String winner = (playerRoundScore >= targetWins) ? "PLAYER" : "COMPUTER";
           String message = getString(R.string.end_of_game_message,winner);
           printOutWhoWon(printOutWhoWonLabel,message);
           buttonControl(gameFlowButtons,false);
           checkWhichImageToShow();
       }
   }

   private void checkWhichImageToShow(){
       if(playerRoundScore >= targetWins){
           int randomWinnerImage = RANDOM_GENERATOR.nextInt(winnerImages.length);
           endGameResultsImage.setImageResource(winnerImages[randomWinnerImage]);
       }else{
           int randomLoserImage = RANDOM_GENERATOR.nextInt(loserImages.length);
           endGameResultsImage.setImageResource(loserImages[randomLoserImage]);
       }
       endGameResultsImage.setVisibility(View.VISIBLE);
   }

   private void resetHandScores(){
       playerScore = 0;
       computerScore = 0;
       printCurrentScores(userScoreLabel,playerScore);
       printCurrentScores(computerScoreLabel,computerScore);
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
        printOutWhoWonLabel = findViewById(R.id.end_game_message);
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