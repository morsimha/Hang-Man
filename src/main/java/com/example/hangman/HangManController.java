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
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

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

    @FXML
    private Canvas cnvs;

    private GraphicsContext gc;
    Words dict;
    HangManLogic game;
    HangManBody body;
    private final int TRIES = 7;
    int drawCounter = 0;


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
            hangTheMan();
            labelTries.setText(game.triesLeft + "");
            labelWord.setText(game.fullWord);
            JOptionPane.showMessageDialog(null, "Game Over!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void runGame(String guess){
        game.checkGuess(guess);
        if (!game.correctGuess)
            hangTheMan();
        labelTries.setText("" + game.triesLeft);
        labelWord.setText(game.currGuess);
        labelLetters.setText(game.guesses.toString());
    }


    private void hangTheMan(){
        int headInd = 4;
        Line l = body.bodyParts.get(drawCounter);

        if (drawCounter == 0) { //first time print the base
            gc.setStroke(Color.SADDLEBROWN);
            Line base1 = body.bodyParts.get(++drawCounter);
            Line base2 = body.bodyParts.get(++drawCounter);
            Line base3 = body.bodyParts.get(++drawCounter);
            gc.strokeLine(l.getStartX(),l.getStartY(),l.getEndX(),l.getEndY());
            gc.strokeLine(base1.getStartX(),base1.getStartY(),base1.getEndX(),base1.getEndY());
            gc.strokeLine(base2.getStartX(),base2.getStartY(),base2.getEndX(),base2.getEndY());
            gc.strokeLine(base3.getStartX(),base3.getStartY(),base3.getEndX(),base3.getEndY());

        }
        else if (drawCounter == headInd) { //draw the head as oval
            gc.setStroke(Color.BLACK);
            gc.strokeOval(l.getStartX(), l.getStartY(), l.getEndX(), l.getEndY());
        }
        else {//draw normally
            gc.setStroke(Color.BLACK);
            gc.strokeLine(l.getStartX(), l.getStartY(), l.getEndX(), l.getEndY());
        }
        drawCounter++;
    }

    }