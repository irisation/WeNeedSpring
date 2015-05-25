package spring;

import org.apache.commons.lang3.EnumUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Counter {

    String root = "D:\\Students\\";
    enum Propositions{at, on, in, of }
    WordsContainer wc;
    String storageType;

    public WordsContainer getWc() {
        return wc;
    }

    public void setWc(WordsContainer wc) {
        this.wc = wc;
    }

    Counter(WordsContainer wc){
        setWc(this.wc);
    }

    static Map sortByValue(Map map) {
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o2)).getValue())
                        .compareTo(((Map.Entry) (o1)).getValue());
            }
        });

        Map result = new LinkedHashMap();
        int i = 0;
        int j = 0;
        while( j < 10 && i < list.size()) {
            Map.Entry entry = (Map.Entry)list.get(i);
            if(!EnumUtils.isValidEnum(Propositions.class, (String) entry.getKey())){
                result.put(entry.getKey(), entry.getValue());
                j++;
            }
            i++;
        }
        return result;
    }


    public void readFile(WordsContainer wc) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(root + wc.getFileName()));
        String line = in.readLine();
        String[] words;
        while (line != null) {
            words = line.split("[\\s\\.\\-\\d+-.,!@#$%^&*();\\\\/|<>\"']+");
            for (int i = 0; i < words.length ; i++) {
                String currentWord = words[i].toLowerCase();
                if(wc.allWords.containsKey(currentWord)) {
                    wc.allWords.put(currentWord, wc.allWords.get(currentWord) + 1);
                } else {
                    wc.allWords.put(currentWord, 1);
                }
            }
            line = in.readLine();
        }

        wc.setTopTen((HashMap<String, Integer>) sortByValue(wc.allWords));
        wc.setWordsCount(wc.allWords.size());

     }

}
