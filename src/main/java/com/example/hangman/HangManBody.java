package com.example.hangman;

import javafx.scene.shape.Line;
import java.util.ArrayList;

public class HangManBody {
    ArrayList<Line> bodyParts = new ArrayList<Line>();

    public void build() {

        Line base1 = new Line(370, 220, 410, 220);
        Line base2 = new Line(390, 20, 390, 220);
        Line base3 = new Line(220, 20, 390, 20);
        Line base4 = new Line(220, 20, 220, 30);

        Line head = new Line(190, 30, 60, 60);

        Line line1 = new Line(220, 90, 220, 170);
        Line line2 = new Line(250, 120, 220, 130);
        Line line3 = new Line(190, 120, 220, 130);
        Line line4 = new Line(190, 200, 220, 170);
        Line line5 = new Line(250, 200, 220, 170);

        Line dance1 = new Line(200, 200, 220, 170);
        Line dance2 = new Line(260, 200, 220, 170);
        Line dance3 = new Line(180, 200, 220, 170);
        Line dance4 = new Line(240, 200, 220, 170);

        //   Rectangle body1 = new Oval(370, 220, 410, 220);
        //     Line line6 = new Line(370, 220, 410, 220);

        bodyParts.add(base1);
        bodyParts.add(base2);
        bodyParts.add(base3);
        bodyParts.add(base4);
        bodyParts.add(head);
        bodyParts.add(line1);
        bodyParts.add(line2);
        bodyParts.add(line3);
        bodyParts.add(line4);
        bodyParts.add(line5);
        bodyParts.add(dance1);
        bodyParts.add(dance2);
//        bodyParts.add(dance3);
//        bodyParts.add(dance4);



    }

}
