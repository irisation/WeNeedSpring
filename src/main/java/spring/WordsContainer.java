package spring;

import java.util.HashMap;

public class WordsContainer {

    String fileName;
    int wordsCount;

    HashMap<String, Integer> topTen;
    HashMap<String, Integer> allWords;

    public void setWordsCount(int wordsCount) {
        this.wordsCount = wordsCount;
    }

    public void setTopTen(HashMap<String, Integer> topTen) {
        this.topTen = topTen;
    }

    public void setAllWords(HashMap<String, Integer> allWords) {
        this.allWords = allWords;
    }

    public WordsContainer(String s) {
        setFileName(s);
        allWords = new HashMap<>();
        topTen = new HashMap<>(10);

    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return this.fileName;
    }
}
