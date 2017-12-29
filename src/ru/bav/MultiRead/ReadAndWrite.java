package ru.bav.MultiRead;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;


/**
 * Класс, в котором реализовано многопоточное считывание данных из файлов и запись в результирующий файл.
 *
 * @author Barinov 1518
 */
public class ReadAndWrite extends Thread {
    private String adress;
    private static volatile BufferedWriter writer;

    ReadAndWrite(String adress, BufferedWriter writer) {
        this.adress = adress;
        this.writer = writer;
    }

    public void run() {
        String string;
        try (BufferedReader reader = new BufferedReader(new FileReader(adress))) {
            while ((string = reader.readLine()) != null) {
                writer.write(string + "\n");
                yield();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
