/**
 * Maman 13, question 1.
 * Mor Simha, 206029993.
 */

package com.example.hangman;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.io.FileNotFoundException;

public class HangManController {
    @FXML
    private Canvas cnvs;

    private final int SIZE = 200;
    private GraphicsContext gc;
    Words first = new Words();
    HangManLogic game = new HangManLogic();

    public void initialize() throws FileNotFoundException {
        gc = cnvs.getGraphicsContext2D();
        gc.setLineWidth(2); //Setting the drawing lines width
        first.ReadWords();
        game.word = first.generateWord(); // maybe private and get\set
        game.prepWordLength(game.word.length());
      //  System.out.println(game.word);

        drawHangMan();
    }

    public void drawHangMan(){
        System.out.println("Welcome to hangman, too lazy for fx:");
        System.out.println("This is your very predictable word to guess:");
        System.out.println(game.currGuess);

        while (!game.currGuess.equals(game.word)) {
            game.getGuess();
            game.checkGuess();
            System.out.println(game.currGuess);
        }
    }

}