package ru.bav.chicken;

public class ChickenOrEgg extends Thread {
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println("ЯЙЦО");
        }
    }


    public static void main(String[] args) {
        System.out.println("Начали спор");
        Thread spor = new Thread(new ChickenOrEgg());
        for (int i = 0; i < 20; i++) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println("КУРИЦА");
        }
        if (spor.isAlive()) {
            try {
                spor.join();
            } catch (InterruptedException e) {
            }
            System.out.println("Первым было яйцо");
        }else {
            System.out.println("Первой была курица");
        }
        System.out.println("Закончили спорить.");
    }
}

