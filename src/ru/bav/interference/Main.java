package ru.bav.interference;

/**
 * Класс для запуска метода example() из класса InterferenceExample, демонстрирующий инкремент переменной i 2000 раз без потери данных.
 *
 * @author Barinov Anton 1518
 */

public class Main {
    public static void main(String[] args) throws InterruptedException {
        new InterferenceExample().example();
    }
}
