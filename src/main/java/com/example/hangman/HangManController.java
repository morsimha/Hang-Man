/**
 * Maman 13, question 1.
 * Mor Simha, 206029993.
 */

package com.example.hangman;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;

import java.io.FileNotFoundException;

public class HangManController {
    @FXML
    private Canvas cnvs;

    private final int SIZE = 200;
    private GraphicsContext gc;

    public void initialize() throws FileNotFoundException {
        gc = cnvs.getGraphicsContext2D();
        gc.setLineWidth(2); //Setting the drawing lines width
        Words first = new Words();
        first.ReadWords();
        System.out.println(first.generateWord());

    }

}