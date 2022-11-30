/**
 * Maman 13, question 1.
 * Mor Simha, 206029993.
 */

package com.example.hangman;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Line;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

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

    @FXML
    private Canvas cnvs;

    private GraphicsContext gc;
    Words dict;
    HangManLogic game;
    HangManBody body;
    private final int TRIES = 6;


    public void initialize() throws FileNotFoundException {
        dict = new Words();
        game = new HangManLogic();
        body = new HangManBody();
        gc = cnvs.getGraphicsContext2D();
        gc.setLineWidth(5);
        dict.ReadWords();
        body.build();
        game.fullWord = dict.generateWord(); // maybe private and get\set
        game.prepWordLength(game.fullWord.length());
        labelWord.setText(game.currGuess);
        labelTries.setText("" + game.triesLeft);
    }

    @FXML
    private void btnPressed() {
        String guess = guessField.getText();
        if (game.checkValidity(guess))
            runGame(guess);
        else
            JOptionPane.showMessageDialog(null, "Please enter one alphabetic character only!\nAlso, make sure you didnt try it yet.", "Error", JOptionPane.ERROR_MESSAGE);
        guessField.clear();

        if (game.triesLeft == 0) {
            labelTries.setText(game.triesLeft + "");
            labelWord.setText(game.fullWord);
            JOptionPane.showMessageDialog(null, "Game Over!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void runGame(String guess){
        game.checkGuess(guess);
        if (!game.correctGuess)
            HangTheMan();
        labelTries.setText("" + game.triesLeft);
        labelWord.setText(game.currGuess);
        labelLetters.setText(game.guesses.toString());
    }


    private void HangTheMan(){
        int currBodyPart = TRIES - game.triesLeft -1;
        Line d = body.bodyParts.get(currBodyPart);
        gc.strokeLine(d.getStartX(),d.getStartY(),d.getEndX(),d.getEndY());
       // gc.strokeLine(370,220,410,220);
    }



}