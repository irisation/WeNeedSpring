package spring;

public class CountersFactory {

    public static Counter getCounter(String filePath) {
        return new Counter(filePath);
    }
}
