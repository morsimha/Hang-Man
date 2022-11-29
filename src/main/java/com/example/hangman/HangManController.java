/**
 * Maman 13, question 1.
 * Mor Simha, 206029993.
 */

package com.example.hangman;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.*;
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


    private final int TRIES = 2;
    Words dict = new Words();
    HangManLogic game = new HangManLogic();

    public void initialize() throws FileNotFoundException {
        labelTries.setText("mor");
        dict.ReadWords();
        game.finalWord = dict.generateWord(); // maybe private and get\set
        game.prepWordLength(game.finalWord.length());
      //  System.out.println(game.word);
        labelWord.setText(game.currWord);
      //  drawHangMan();
    }

    @FXML
    private void btnPressed(){
        if (game.triesLeft == 0) {
            labelTries.setText(game.triesLeft + "");
            labelWord.setText(game.finalWord);
            JOptionPane.showMessageDialog(null, "Game Over!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            String guess = guessField.getText();
            if (game.checkValidity(guess))
                runGame(guess);
            else
                JOptionPane.showMessageDialog(null, "Please enter one alphabetic character only!\nAlso, make sure you didnt try it yet.", "Error", JOptionPane.ERROR_MESSAGE);
            guessField.clear();
            labelTries.setText("" + game.triesLeft);
        }
    }

    private void runGame(String guess){
        game.checkGuess(guess);
        labelTries.setText("" + game.triesLeft);
        labelWord.setText(game.currWord);
        labelLetters.setText(game.guesses.toString());
    }


    private void drawHangMan(){

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