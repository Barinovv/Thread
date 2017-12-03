package ru.bav.copingfiles;

import java.io.*;

/*
Класс для копирования и вставки текста из файла в файл и подсчета времени.
 */

public class Copy extends Thread {
    private String adress;
    private String result;

    public Copy(String adress, String result) {
        this.adress = adress;
        this.result = result;
    }



    public void run(){
        String string;
        long before = System.currentTimeMillis();
        try (BufferedReader read = new BufferedReader(new FileReader(adress));
             BufferedWriter writer = new BufferedWriter(new FileWriter(result))) {
            while ((string = read.readLine()) != null){
                writer.write(string + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Копирование - " + (System.currentTimeMillis() - before) + " милесекунд(ы)");
    }
}
