package spring;

import javax.xml.bind.annotation.*;
import java.util.HashMap;


@XmlRootElement(name = "file")
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

    public WordsContainer() {
        topTen = new HashMap<>(10);
        allWords = new HashMap<>();
    }

    public WordsContainer(String fileName) {
        this.fileName = fileName;
        topTen = new HashMap<>(10);
        allWords = new HashMap<>();
    }

    @XmlElement
    public void setWordsCount(int wordsCount) {
        this.wordsCount = wordsCount;
    }

    @XmlElement
    public void setTopTen(HashMap<String, Integer> topTen) {
        this.topTen = topTen;
    }

    public void setAllWords(HashMap<String, Integer> allWords) {
        this.allWords = allWords;
    }


    @XmlAttribute
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return this.fileName;
    }
}
