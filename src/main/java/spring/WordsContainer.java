package spring;

import java.util.HashMap;

public class WordsContainer {

    String fileName;
    int wordsCount;

    public HashMap<String, Integer> getTopTen() {
        return topTen;
    }

    public int getWordsCount() {
        return wordsCount;
    }

    HashMap<String, Integer> topTen;
    HashMap<String, Integer> allWords;

    public WordsContainer(String fileName) {
        this.fileName = fileName;
        topTen = new HashMap<>(10);
        allWords = new HashMap<>();
    }

    public void setWordsCount(int wordsCount) {
        this.wordsCount = wordsCount;
    }

    public void setTopTen(HashMap<String, Integer> topTen) {
        this.topTen = topTen;
    }

    public void setAllWords(HashMap<String, Integer> allWords) {
        this.allWords = allWords;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return this.fileName;
    }
}
