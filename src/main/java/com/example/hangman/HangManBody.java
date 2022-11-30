package com.example.hangman;

import javafx.scene.shape.Line;
import java.util.ArrayList;

public class HangManBody {
    ArrayList<Line> bodyParts = new ArrayList<>();

    public void build() {

        Line line1 = new Line(370, 220, 410, 220);
        Line line2 = new Line(390, 20, 390, 220);
        Line line3 = new Line(220, 20, 390, 20);
        Line line4 = new Line(220, 20, 220, 70);
     //   Rectangle body1 = new Oval(370, 220, 410, 220);
   //     Line line6 = new Line(370, 220, 410, 220);

        bodyParts.add(line1);
        bodyParts.add(line2);
        bodyParts.add(line3);
        bodyParts.add(line4);

    }
}
