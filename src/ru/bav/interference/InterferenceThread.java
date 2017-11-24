package ru.bav.interference;
/**
 * Класс потоков.
 *
 * @author Barinov Anton 1518
 */
public class InterferenceThread extends Thread {
    private final InterferenceExample checker;
    private static volatile int i;

    public InterferenceThread(String name, InterferenceExample checker) {
        super(name);
        this.checker = checker;
    }

    public void run() {
        System.out.println(this.getName() + " Поток запущен.");
        while (!checker.stop()) {
            increment();
        }
        System.out.println(this.getName() + " Поток завершен.");
    }

    /**
     * Метод для инкремента переменной i.
     */
    private static synchronized void increment() {
        i++;
    }

    public int getI() {
        return i;
    }
}

