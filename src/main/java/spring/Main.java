package spring;

import java.io.IOException;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {
    public static void main(String[] args) throws IOException {



        Scanner scanner = new Scanner(System.in);
        String filename = "";

        while (filename.isEmpty()) {
            System.out.print("Enter a file name: ");
            System.out.flush();
            filename= scanner.nextLine();
        }
        scanner.close();
//        ApplicationContext ctx = new FileSystemXmlApplicationContext("context.xml");
//        Counter controller = (Counter)ctx.getBean("counter");
//        WordsContainer text = (WordsContainer)ctx.getBean("wordsContainer");
//        controller.readFile(text);

        WordsContainer text = new WordsContainer(filename);
        Counter counter = new Counter(text);
        counter.readFile(text);
        System.out.println(text.wordsCount);
        System.out.println(text.topTen);


    }

}
