/**
 * Maman 13, question 1.
 * Mor Simha, 206029993.
 */

package com.example.hangman;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javax.swing.*;
import java.io.FileNotFoundException;

public class HangManController {
    @FXML
    private Label triesLabel;
    @FXML
    private Label wordLabel;
    @FXML
    private Label lettersLabel;
    @FXML
    private VBox finishBox;
    @FXML
    private HBox guessBox;
    @FXML
    private TextField guessField;
    @FXML
    private Canvas cnvs;

    private GraphicsContext gc;
    private Words dict;
    private HangManLogic game;
    private HangManBody body;
    private final int TRIES = 7;
    private int drawCounter;
    private boolean finished;


    public void initialize() throws FileNotFoundException {
        dict = new Words();
        game = new HangManLogic();
        body = new HangManBody();
        gc = cnvs.getGraphicsContext2D();
        gc.setLineWidth(5);
        dict.ReadWords();
        body.build();
        initGame();
    }

    @FXML
    private void goBtnPressed() {
        String guess = guessField.getText();
        if (game.checkValidity(guess))
            runGame(guess);
        else
            JOptionPane.showMessageDialog(null, "Please enter one alphabetic character only!\nAlso, make sure you didnt try it yet.", "Error", JOptionPane.ERROR_MESSAGE);
        guessField.clear();

        if (game.getTriesLeft() == 0) {
            JOptionPane.showMessageDialog(null, "Game Over!\nYou have passed the guesses limit.", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            finished = true;
        }

        if (finished || game.checkWin())
            finishGame();
    }

    @FXML
    private void yesBtnPressed() {
        game.resetGame();
        initGame();
    }

    @FXML
    private void noBtnPressed() {
        Platform.exit();
        JOptionPane.showMessageDialog(null, "Good Bye!", "GoodBye", JOptionPane.INFORMATION_MESSAGE);

    }

    @FXML
    private void keepBtnPressed() {
        if (drawCounter < body.getPartsAmount())
            hangTheMan();
    }


    private void initGame() {
        gc.clearRect(0, 0, cnvs.getWidth(), cnvs.getHeight());
        finishBox.setVisible(false);
        guessBox.setVisible(true);
        finished = false;
        drawCounter = 0;
        game.setFullWord(dict.generateWord());
        game.prepWordLength(game.getFullWord().length());
        updateLabels();
    }

    private void runGame(String guess) {
        game.checkGuess(guess);
        if (!game.isCorrectGuess())
            hangTheMan();
        updateLabels();
    }

    @FXML
    private void finishGame() {
        game.setGuess(game.getFullWord());
        updateLabels();
        finishBox.setVisible(true);
        guessBox.setVisible(false);
    }

    private void hangTheMan() {
        int headInd = 4;
        Line l = body.getPart(drawCounter);
        if (drawCounter == 0) { //first time print the base
            gc.setStroke(Color.SADDLEBROWN);
            Line base1 = body.getPart(++drawCounter);
            Line base2 = body.getPart(++drawCounter);
            Line base3 = body.getPart(++drawCounter);
            gc.strokeLine(l.getStartX(), l.getStartY(), l.getEndX(), l.getEndY());
            gc.strokeLine(base1.getStartX(), base1.getStartY(), base1.getEndX(), base1.getEndY());
            gc.strokeLine(base2.getStartX(), base2.getStartY(), base2.getEndX(), base2.getEndY());
            gc.strokeLine(base3.getStartX(), base3.getStartY(), base3.getEndX(), base3.getEndY());
            gc.setLineWidth(4);
        } else if (drawCounter == headInd) { //draw the head as oval
            gc.setStroke(Color.BLACK);
            gc.strokeOval(l.getStartX(), l.getStartY(), l.getEndX(), l.getEndY());
        } else {//draw normally
            gc.setStroke(Color.BLACK);
            gc.strokeLine(l.getStartX(), l.getStartY(), l.getEndX(), l.getEndY());
        }
        drawCounter++;
    }

    private void updateLabels() {
        wordLabel.setText(game.getGuess());
        triesLabel.setText("" + game.getTriesLeft());
        if (game.getGuessesList().size()>0)
            lettersLabel.setText(game.getGuessesList().toString());

    }
}