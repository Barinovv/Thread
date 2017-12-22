package ru.bav.catchup;


public class Catchup extends Thread {
    private int first;
    private int second;
    private String name;

    public Catchup(int first, int second, String name) {
        this.first = first;
        this.second = second;
        this.name = name;
    }

    public void run() {
        setPriority(first);
        for (int i = 0; i < 4000; i++) {
            try {
                sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println(name + " = " +
                    i + "." );
            if (i == 550) {
                setPriority(second);
            }

        }
    }


}


