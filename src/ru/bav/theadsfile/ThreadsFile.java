package ru.bav.theadsfile;

import java.io.*;

/**
 * Класс, в котором реализовано многопоточное считывание данных из файлов из запись в результирующий файл.
 *
 * @author Barinov Anton 1518
 */

public class ThreadsFile extends Thread {
    private static volatile BufferedWriter bufferedWriter;
    private String adress;

    public ThreadsFile(String adress) {
        this.adress = adress;
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        ThreadsFile firstThread = new ThreadsFile("C:\\Users\\User\\IdeaProjects\\Thread\\src\\ru\\bav\\theadsfile\\first.txt");
        ThreadsFile secondThread = new ThreadsFile("C:\\Users\\User\\IdeaProjects\\Thread\\src\\ru\\bav\\theadsfile\\second.txt");
        bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\User\\IdeaProjects\\Thread\\src\\ru\\bav\\theadsfile\\output.txt", false));

        firstThread.start();
        secondThread.start();

        if (firstThread.isAlive()) {
            firstThread.join();
        }
        if (secondThread.isAlive()) {
            secondThread.join();
        }

        bufferedWriter.close();
    }

    public void run() {
        String string;
        try (BufferedReader first = new BufferedReader(new FileReader(adress)))
        {
            while ((string = first.readLine()) != null) {
                write(string + "\n");

            }
        } catch (IOException e) {
        }

    }

    /**
     * Метод для записи строки в результирующий файл - "output.txt"
     *
     * @param string строка, которую нужно записать в файл
     * @throws IOException исключение
     */

    public static synchronized void write(String string) throws IOException{
        bufferedWriter.write(string);
    }
}

