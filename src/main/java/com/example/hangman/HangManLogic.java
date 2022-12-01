package com.example.hangman;

import java.util.ArrayList;

public class HangManLogic {
    ArrayList<String> guesses = new ArrayList<>();
    String fullWord;
    String currGuess = "";
    boolean correctGuess;
    int triesLeft = 7;

    public void resetGame() {
        guesses.clear();
        fullWord = "";
        currGuess = "";
        correctGuess = false;
        triesLeft = 7;
    }


    public void prepWordLength(int len) {
        for (int i = 0; i < len; i++)
            currGuess += "_";
    }

//    public void getGuess() {
//        Scanner sc = new Scanner(System.in); //System.in is a standard input stream
//        System.out.print("Guess one letter please:");
//        char guess = sc.next().charAt(0);
//        guesses.add(guess);
//    }

    public void checkGuess(String guess) {
        correctGuess = false;
        guesses.add(guess);
        char letter = guesses.get(guesses.size() - 1).charAt(0);
        for (int i = 0; i < fullWord.length(); i++) {
            if (fullWord.charAt(i) == letter) {
                currGuess = currGuess.substring(0, i) + letter + currGuess.substring(i + 1);
                correctGuess = true;
            }
        }
        if (!correctGuess)
            triesLeft--;
    }


    public boolean checkValidity(String guess) {
        boolean validGuess = true;
        if (guess.length() == 1 && Character.isLetter(guess.charAt(0))) { // if its valid, check it didnt show yet
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

    public boolean checkWin() {
        return fullWord.equals(currGuess);
    }


}