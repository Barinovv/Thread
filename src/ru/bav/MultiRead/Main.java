package ru.bav.MultiRead;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Класс для создания и запуска потоков.
 *
 * @author Barinov 1518
 */

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\User\\IdeaProjects\\Thread\\src\\ru\\bav\\MultiRead\\Result.txt", false));
        ReadAndWrite firstThread = new ReadAndWrite("C:\\Users\\User\\IdeaProjects\\Thread\\src\\ru\\bav\\MultiRead\\FirstFile.txt", writer);
        ReadAndWrite secondThread = new ReadAndWrite("C:\\Users\\User\\IdeaProjects\\Thread\\src\\ru\\bav\\MultiRead\\SecondFile.txt", writer);

        firstThread.start();
        secondThread.start();

        if (firstThread.isAlive()) {
            firstThread.join();
        }
        if (secondThread.isAlive()) {
            secondThread.join();
        }
        writer.close();
    }
}
