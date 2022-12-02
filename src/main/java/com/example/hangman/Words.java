package com.example.hangman;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Words {
    private ArrayList<String> words = new ArrayList<String>();

    public void ReadWords() throws FileNotFoundException {
        Scanner input = new Scanner(new File("words.txt"));
        while (input.hasNext())
            words.add(input.next());

        input.close();
    }

    public String generateWord(){

        Random rand = new Random();
        String word = words.get(rand.nextInt(words.size()));
        return word;
    }

}
