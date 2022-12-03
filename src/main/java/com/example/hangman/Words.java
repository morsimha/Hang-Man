package com.example.hangman;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.IOException;

public class Words {
    private ArrayList<String> words = new ArrayList<String>();
    private final String FILE_NAME = "words.txt";

    public void ReadWords(){
        try {
            Scanner input = new Scanner(new File(FILE_NAME));
            while (input.hasNext())
                words.add(input.next());

            input.close();
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "File '"+ FILE_NAME + "' was not found.\nPlease make sure it exist outside of /src directory.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    public String generateWord(){

        Random rand = new Random();
        String word = words.get(rand.nextInt(words.size()));
        return word;
    }

}
