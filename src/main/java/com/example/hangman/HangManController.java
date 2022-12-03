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
    private Label upperLabel;
    @FXML
    private Label greetLabel;
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
    private int drawCounter;
    private boolean finished;
    private boolean win;


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
            finishGame();
        }
        else if (game.checkWin()) {
            win = true;
            finishGame();
        }
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
        try {
            hangTheMan();
        }
        catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Just let him die quietly..", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void initGame() {
        gc.clearRect(0, 0, cnvs.getWidth(), cnvs.getHeight());
        finished = false;
        win = false;
        finishScene();
        drawCounter = 0;
        game.setFullWord(dict.generateWord());
        game.prepWordLength(game.getFullWord().length());
        updateLabels();
    }

    private void runGame(String guess) {
        game.checkGuess(guess);
        if (game.isCorrectGuess().equals("Wrong guess.."))
            hangTheMan();
        updateLabels();
        upperLabel.setVisible(true);
    }

    @FXML
    private void finishGame() {
        game.setGuess(game.getFullWord());
        updateLabels();
        finished = true;
        finishScene();
    }

    private void hangTheMan() {
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
        } else if (drawCounter == body.getHeadIndex()) { //draw the head as oval
            gc.setLineWidth(4);
            gc.setStroke(Color.BLACK);
            gc.strokeOval(l.getStartX(), l.getStartY(), l.getEndX(), l.getEndY());
        } else //draw normally
            gc.strokeLine(l.getStartX(), l.getStartY(), l.getEndX(), l.getEndY());

        drawCounter++;
    }

    private void updateLabels() {
        upperLabel.setText(game.isCorrectGuess());
        wordLabel.setText(game.getGuess());
        triesLabel.setText("" + game.getTriesLeft());
        lettersLabel.setText(game.getLetters());
    }

    private void finishScene(){
        greetLabel.setText(game.finalMessage(win));
        finishBox.setVisible(finished);
        guessBox.setVisible(!finished);
        if (finished)
            upperLabel.setVisible(false);
    }
}