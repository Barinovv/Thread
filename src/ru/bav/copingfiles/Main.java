package ru.bav.copingfiles;

/*
Класс для создания и запуска потоков и подсчета времени выполнения.
 */

public class Main {
    public static void main(String[] args) throws InterruptedException {

        long first = System.currentTimeMillis();
        Copy firstCopy = new Copy("C:\\Users\\User\\IdeaProjects\\Thread\\src\\ru\\bav\\copingfiles\\main file.txt",
                "C:\\Users\\User\\IdeaProjects\\Thread\\src\\ru\\bav\\copingfiles\\result file.txt");
        firstCopy.start();

        if (firstCopy.isAlive()) {
            firstCopy.join();
        }
        System.out.println("Вставка в первый текстовик занимает - " + (System.currentTimeMillis() - first) + " милесекунд(ы)");
        long second = System.currentTimeMillis();
        Copy secondCopy = new Copy("C:\\Users\\User\\IdeaProjects\\Thread\\src\\ru\\bav\\copingfiles\\main file.txt",
                "C:\\Users\\User\\IdeaProjects\\Thread\\src\\ru\\bav\\copingfiles\\second result file.txt");


        secondCopy.start();


        if (secondCopy.isAlive()) {
            secondCopy.join();

        }
        System.out.println("Вставка во второй текстовик занимает  - " + (System.currentTimeMillis() - second) + " милесекунд(ы)");
    }
}
