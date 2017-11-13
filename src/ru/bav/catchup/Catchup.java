package ru.bav.catchup;

/**
 * Класс, в котором выполяняются два потокп и меняется их приоритет.
 *
 * @author Barinov Anton 15ИТ18
 */
public class Catchup extends Thread {

    private String name;
    private int firstPriority;
    private int secondPriority;

    public Catchup(String name, int firstPriority, int secondPriority) {
        this.name = name;
        this.firstPriority = firstPriority;
        this.secondPriority = secondPriority;
    }

    public void run() {
        setPriority(firstPriority);
        for (int i = 0; i < 4000; i++) {
            try {
                sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println(i + "." + name);
            if (i == 550) {
                setPriority(secondPriority);
            }

        }
    }

    public static void main(String[] args) {
        Catchup potok1 = new Catchup("Flash", MIN_PRIORITY, MAX_PRIORITY);
        Catchup potok2 = new Catchup("Arrow", MAX_PRIORITY, MIN_PRIORITY);
        potok1.start();
        potok2.start();

    }
}


