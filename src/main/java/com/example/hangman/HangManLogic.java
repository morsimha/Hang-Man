package com.example.hangman;

import java.util.ArrayList;
import java.util.Scanner;

public class HangManLogic {
    ArrayList<Character> guesses = new ArrayList<>();
    String word;
    String currGuess = "";


    public void prepWordLength(int len) {
        for (int i = 0; i < len; i++)
            currGuess += "_";

    }

    public void getGuess() {
        Scanner sc = new Scanner(System.in); //System.in is a standard input stream
        System.out.print("Guess one letter please:");
        char guess = sc.next().charAt(0);
        guesses.add(guess);
    }

    public void checkGuess() {
        char letter = guesses.get(guesses.size()-1);
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter)
                currGuess = currGuess.substring(0, i) + letter + currGuess.substring(i+1);
        }
    }


    public void createFirstGeneration() {
//        ArrayList<String> words = new ArrayList<>();
//        words.add("bridge");
//        words.add("polymorphism");
//        words.add("water");
//        words.add("sophisticated");
//        words.add("flute");

    }

}