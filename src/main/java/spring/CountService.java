package spring;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CountService {

    public String folderPath;

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    ArrayList<WordsContainer> start() throws IOException {
        File folder = new File(this.folderPath);
        ArrayList<WordsContainer> wordsContainers = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            if (!fileEntry.isDirectory()) {
                wordsContainers.add(CountersFactory.getCounter(getFolderPath() + fileEntry.getName()).parse());
            }
        }
        return wordsContainers;
    }
}
