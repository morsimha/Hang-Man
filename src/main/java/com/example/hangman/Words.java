package com.example.hangman;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Words {
    ArrayList<String> words = new ArrayList<>();


    public void ReadWords() throws FileNotFoundException {

        Scanner input = new Scanner(new File("words.txt"));


        while (input.hasNext())
            words.add(input.next());

        input.close();
    }

    public String generateWord(){

        Random rand = new Random();
  //      System.out.println(words.get(4));
        String word = words.get(rand.nextInt(words.size()-1));
        return word;
    }

    public void doz (){
        try {
            ReadWords();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(generateWord());

    }

}
