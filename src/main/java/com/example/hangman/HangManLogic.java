package com.example.hangman;

import java.util.ArrayList;

public class HangManLogic {
    ArrayList<String> guesses = new ArrayList<>();
    String finalWord;
    String currWord = "";
    int triesLeft = 2;


    public void prepWordLength(int len) {
        for (int i = 0; i < len; i++)
            currWord += "_";

    }

//    public void getGuess() {
//        Scanner sc = new Scanner(System.in); //System.in is a standard input stream
//        System.out.print("Guess one letter please:");
//        char guess = sc.next().charAt(0);
//        guesses.add(guess);
//    }

    public void checkGuess(String guess) {
        boolean correctGuess = false;
        guesses.add(guess);
        char letter = guesses.get(guesses.size() - 1).charAt(0);
        for (int i = 0; i < finalWord.length(); i++) {
            if (finalWord.charAt(i) == letter) {
                currWord = currWord.substring(0, i) + letter + currWord.substring(i + 1);
                correctGuess = true;
            }
        }
        if (!correctGuess)
            triesLeft--; //HANG THE MAN
    }


    public boolean checkValidity(String guess) {
        boolean validGuess = true;

        if (Character.isLetter(guess.charAt(0)) && guess.length() == 1) { // if its valid, check it didnt show yet
            for (String g : guesses)
                if (guess.equals(g)) {
                    validGuess = false;
                    return validGuess;
                }
        }
        else
            validGuess = false;

        return validGuess;
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