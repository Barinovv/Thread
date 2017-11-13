package ru.bav.catchup;


public class Catchup extends Thread {   
    public void run() {
        setPriority(MIN_PRIORITY);
        for (int i = 0; i < 4000; i++) {
            try {
                sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println(i + "." + "ДОГНАЛ");
            if (i == 550) {
                setPriority(MAX_PRIORITY);
            }

        }
    }

    public static void main(String[] args) {
        Catchup potok = new Catchup();
        Thread thread1 = new Thread();
        potok.start();
        potok.setPriority(MAX_PRIORITY);
        for (int i = 0; i < 4000; i++) {
            try {
                thread1.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println(i + "." + "Не догнал");
            if (i == 500) {
                thread1.setPriority(MIN_PRIORITY);
            }

        }
    }
}


