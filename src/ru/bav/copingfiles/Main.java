package ru.bav.copingfiles;

/*
Класс для создания и запуска потоков и подсчета времени выполнения.
 */

public class Main {
    public static void main(String[] args) {

long before = System.currentTimeMillis();
        Copy read = new Copy("C:\\Users\\User\\IdeaProjects\\Thread\\src\\ru\\bav\\copingfiles\\main file.txt",
                "C:\\Users\\User\\IdeaProjects\\Thread\\src\\ru\\bav\\copingfiles\\result file.txt");

 read.start();
        System.out.println((System.currentTimeMillis() - before) + " милесекунд(ы)");

        long before1 = System.currentTimeMillis();
        Copy read1 = new Copy("C:\\Users\\User\\IdeaProjects\\Thread\\src\\ru\\bav\\copingfiles\\main file.txt",
                "C:\\Users\\User\\IdeaProjects\\Thread\\src\\ru\\bav\\copingfiles\\second result file.txt");
        read1.start();
        System.out.println((System.currentTimeMillis() - before1) + " милесекунд(ы)");

    }
}
