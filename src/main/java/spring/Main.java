package spring;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {
    public static void main(String[] args) throws IOException {


//        Scanner scanner = new Scanner(System.in);
//        String filename = "";
//        while (filename.isEmpty()) {
//            System.out.print("Enter a file name: ");
//            System.out.flush();
//            filename= scanner.nextLine();
//        }
//        scanner.close();

//        ApplicationContext ctx = new FileSystemXmlApplicationContext("context.xml");

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"context.xml"});
        CountService controller = (CountService) ctx.getBean("countService");
        ArrayList<WordsContainer> array = controller.start();
        for (int i = 0; i < array.size(); i++) {
            System.out.println(array.get(i).wordsCount + " " + array.get(i).topTen);
        }


    }

}
