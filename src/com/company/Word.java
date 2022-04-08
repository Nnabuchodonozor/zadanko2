package com.company;

public class Word {
    int distance;
    String word;
    public Word(int distance, String word) {
        this.distance=distance;
        this.word=word;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
