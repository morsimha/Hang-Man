/**
 * Maman 13, question 1.
 * Mor Simha, 206029993.
 */

package com.example.hangman;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;

public class HangManController {
    @FXML
    private Label labelTries;
    @FXML
    private Label labelWord;
    @FXML
    private Label labelLetters;
    @FXML
    private TextField guessField;
    @FXML
    private Button btnGuess;


    private final int SIZE = 200;
    Words first = new Words();
    HangManLogic game = new HangManLogic();

    public void initialize() throws FileNotFoundException {
        labelTries.setText("6");
        first.ReadWords();
        game.word = first.generateWord(); // maybe private and get\set
        game.prepWordLength(game.word.length());
      //  System.out.println(game.word);
        labelWord.setText(game.currGuess);
        drawHangMan();
    }

    @FXML
    private void btnPressed(){
        if (game.triesLeft != 0) {
            labelTries.setText("" + game.triesLeft);
            String guess = guessField.getText();
            game.guesses.add(guess);
            //    game.getGuess();
            game.checkGuess();
            System.out.println(game.currGuess);
            labelWord.setText(game.currGuess);
            guessField.clear();
            labelLetters.setText(game.guesses.toString());
        }
        else
            System.out.println("Game over");
    }


    public void drawHangMan(){


        System.out.println("Welcome to hangman, too lazy for fx:");
        System.out.println("This is your very predictable word to guess:");
        System.out.println(game.currGuess);


//        while (!game.currGuess.equals(game.word)) {
//
//            game.guesses.add(guessField.getText().charAt(0));
//        //    game.getGuess();
//            game.checkGuess();
//            System.out.println(game.currGuess);
//            labelWord.setText(game.currGuess);
//           // labelLetters.setText(game.guesses.get(game.guesses.size()));
//        }
//        System.out.println("good job! the word was " + game.currGuess);
    }
}