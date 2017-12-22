package ru.bav.catchup;

public class Main {
    public static void main(String[] args) {

        Catchup firstThread = new Catchup(1, 10, "Первый");
        Catchup secondThread = new Catchup(10, 1, "Второй");
        firstThread.start();
        secondThread.start();
    }
}
