package com.codecool.thehistory;

import java.util.Arrays;

public class TheHistoryArray implements TheHistory {

    /**
     * This implementation should use a String array so don't change that!
     */
    private String[] wordsArray = new String[0];

    @Override
    public void add(String text) {

        String[] words = text.split("\\s+");

        int newLength = words.length + wordsArray.length;

        String[] result = Arrays.copyOf(wordsArray, newLength);

        System.arraycopy(words, 0, result, wordsArray.length, words.length);

        wordsArray = result;
    }

    @Override
    public void removeWord(String wordToBeRemoved) {
        StringBuilder str = new StringBuilder();

        for (String word : wordsArray) {
            if(!wordToBeRemoved.equals(word)) {
                str.append(word+",");
            }
        }

        String[] result = str.toString().split(",");
        wordsArray = result;
    }

    @Override
    public int size() {
        return wordsArray.length;
    }

    @Override
    public void clear() {
        wordsArray = new String[0];
    }

    @Override
    public void replaceOneWord(String from, String to) {
        int count = 0;
        for (String word : wordsArray) {
            if(word.equals(from)) {
                wordsArray[count] = to;
            }
            count++;
        }
    }

    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {
        StringBuilder words = new StringBuilder();

        for (int i=0; i<wordsArray.length; i++) {
            words.append(wordsArray[i] + " ");
        }

        String from = String.join(" ", fromWords);
        String to = String.join(" ", toWords);

        int i = 0;
        while(words.indexOf(from, i) > -1) {

            int index = words.indexOf(from, i);

            // fromWords: "o b", wordsArray: "foo bar baz"
            // we have constraint that fromWords cannot be in the middle of wordsArray
            // fromWords: "foo b", wordsArray: "foo bar baz"
            // when we put words into wrodsArray, first character after it should be white space.
            // fromWords: "o bar", wordsArray: "foo bar baz"
            // the same as above is when words occurred the middle of wordsArray
            // so we need to us or statement ||
            // this is because: 0 1 (words at the begging, so on the left side we haven't space, on the right we should)
            // this is because: 1 1 (space is on the left and on the right - words occurred in the middle)
            // this is because: 1 0 (space is on the left, but on the right we have different char)
            if(index > 0 && words.charAt(index-1) != ' '
                    || words.charAt(index + from.length()) != ' ') {
                i = index+1;
                continue;
            }

            i = index + to.length();

            //
            words.replace(index, index + from.length(), to);
        }

        wordsArray = words.toString().split(" ");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String word : wordsArray) {
            sb.append(word).append(" ");
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1); // last space char
        return sb.toString();
    }
}
