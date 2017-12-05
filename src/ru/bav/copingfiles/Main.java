package ru.bav.copingfiles;

/*
Класс для создания и запуска потоков и подсчета времени выполнения.
 */

public class Main {
    public static void main(String[] args) throws InterruptedException {

        long first = System.currentTimeMillis();
        Copy read = new Copy("C:\\Users\\User\\IdeaProjects\\Thread\\src\\ru\\bav\\copingfiles\\main file.txt",
                "C:\\Users\\User\\IdeaProjects\\Thread\\src\\ru\\bav\\copingfiles\\result file.txt");
        read.start();

        if (read.isAlive()) {
            read.join();
        }
        System.out.println("Вставка в первый текстовик занимает - " + (System.currentTimeMillis() - first) + " милесекунд(ы)");
        long second = System.currentTimeMillis();
        Copy read1 = new Copy("C:\\Users\\User\\IdeaProjects\\Thread\\src\\ru\\bav\\copingfiles\\main file.txt",
                "C:\\Users\\User\\IdeaProjects\\Thread\\src\\ru\\bav\\copingfiles\\second result file.txt");


        read1.start();


        if (read1.isAlive()) {
            read1.join();

        }
        System.out.println("Вставка во второй текстовик занимает  - " + (System.currentTimeMillis() - second) + " милесекунд(ы)");
    }
}
