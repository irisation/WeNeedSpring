package spring;

public abstract class CountersFactory {

    public abstract Counter getCounter(String filePath);
    //return new Counter(filePath);

}
