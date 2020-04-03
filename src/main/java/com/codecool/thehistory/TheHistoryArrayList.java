package com.codecool.thehistory;

import java.util.*;

public class TheHistoryArrayList implements TheHistory {
    /**
     * This implementation should use a String ArrayList so don't change that!
     */
    private List<String> wordsArrayList = new ArrayList<>();

    @Override
    public void add(String text) {
        String[] words = text.split("\\s+");
        for (String word : words) {
            wordsArrayList.add(word);
        }
    }

    @Override
    public void removeWord(String wordToBeRemoved) {
        wordsArrayList.removeIf(wordToBeRemoved::equals);
    }

    @Override
    public int size() {
        return wordsArrayList.size();
    }

    @Override
    public void clear() {
        wordsArrayList.clear();
    }

    @Override
    public void replaceOneWord(String from, String to) {
        ListIterator<String> itr = wordsArrayList.listIterator();

        while(itr.hasNext()) {
            if (itr.next().equals(from)){
                itr.set(to);
            }
        }
    }

    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {

        for(int i = 0; i < wordsArrayList.size(); i++) {

            // Because we change i when we replace words, we need to check
            // if earlier if it still fit to size of wordsArrayList
            if (i + fromWords.length > wordsArrayList.size()) {
                break;
            }

            // We iterate over wordsArrayList, and in each iteration for word at given index
            // we determine sublist, which has the same size as given parameter String[] fromWords
            List<String> sublist = wordsArrayList.subList(i, i + fromWords.length);


            if(sublist.equals(Arrays.asList(fromWords))) {

                // if we find occurrence of fromWords in wordsArrayList,
                // we need to remove each word which was covered/ duplicated.
                for (int j = 0; j < fromWords.length; j++) {
                    wordsArrayList.remove(i);
                }

                // we replace removed words with the new from provided
                // String[] toWords parameter
                wordsArrayList.addAll(i, Arrays.asList(toWords));

                // we don't know if size od toWords is equal to size of fromWords
                // or maybe it has grater size. Based on that we need to increase
                // our index i. Remember to subtract 1, this is because we increment
                // again this variable in for loop.
                i += toWords.length-1;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String word : wordsArrayList) {
            sb.append(word).append(" ");
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1); // last space char
        return sb.toString();
    }

}
