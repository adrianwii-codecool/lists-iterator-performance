package com.codecool.thehistory;

import java.util.*;

public class TheHistoryLinkedList implements TheHistory {
    /**
     * This implementation should use a String LinkedList so don't change that!
     */
    private List<String> wordsLinkedList = new LinkedList<>();

    @Override
    public void add(String text) {
        String[] words = text.split("\\s+");
        for (String word : words) {
            wordsLinkedList.add(word);
        }
    }

    @Override
    public void removeWord(String wordToBeRemoved) {
        Iterator itr = wordsLinkedList.iterator();

        while (itr.hasNext()) {
            if (itr.next().equals(wordToBeRemoved)) {
                itr.remove();
            }
        }
    }

    @Override
    public int size() {
        return wordsLinkedList.size();
    }

    @Override
    public void clear() {
        wordsLinkedList.clear();
    }

    @Override
    public void replaceOneWord(String from, String to) {
        ListIterator<String> itr = wordsLinkedList.listIterator();

        while(itr.hasNext()) {
            if (itr.next().equals(from)){
                itr.set(to);
            }
        }
    }

    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {
        for(int i = 0; i < wordsLinkedList.size(); i++) {

            // Because we change i when we replace words, we need to check
            // if earlier if it still fit to size of wordsArrayList
            if (i + fromWords.length > wordsLinkedList.size()) {
                break;
            }

            // We iterate over wordsArrayList, and in each iteration for word at given index
            // we determine sublist, which has the same size as given parameter String[] fromWords
            List<String> sublist = wordsLinkedList.subList(i, i + fromWords.length);


            if(sublist.equals(Arrays.asList(fromWords))) {

                // if we find occurrence of fromWords in wordsArrayList,
                // we need to remove each word which was covered/ duplicated.
                for (int j = 0; j < fromWords.length; j++) {
                    wordsLinkedList.remove(i);
                }

                // we replace removed words with the new from provided
                // String[] toWords parameter
                wordsLinkedList.addAll(i, Arrays.asList(toWords));

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
        for (String word : wordsLinkedList) {
            sb.append(word).append(" ");
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1); // last space char
        return sb.toString();
    }

}
