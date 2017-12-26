package ru.bav.Images;

import ru.bav.multiDownloader.Downloader;

import java.io.*;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/*
 * Класс, в котором создаются и запускаются потоки для скачивания картинок, формируется название картинки и указывается количество скачанных картинок.
 *
 * @author Barinov 1518.
 */

public class Main {
    private static final String IN_FILE_TXT = "src\\ru\\bav\\Images\\inFile.txt";
    private static final String OUT_FILE_TXT = "src\\ru\\bav\\Images\\outFile.txt";
    private static final String PATH_TO_MUSIC = "src\\ru\\bav\\Images\\download\\IMG";

    public static void main(String[] args) throws IOException {


        writeURL(parseLink());

        StartDownload(6);
    }

    /**
     * Метод, который записывает весь HTML код в одну строку.
     *
     * @return HTMl код страницы в виде строки.
     * @throws IOException исключение.
     */

    private static String parseLink() throws IOException {
        String link;
        String result = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(IN_FILE_TXT))) {
            while ((link = reader.readLine()) != null) {
                URL url = new URL(link);
                try (BufferedReader reader1 = new BufferedReader(new InputStreamReader(url.openStream()))) {
                    result = reader1.lines().collect(Collectors.joining("\n"));
                }
            }
            return result;
        }

    }

    /**
     * Метод для записи ссылок найденых по шаблону в outFile.txt.
     *
     * @param string ссылка, которую нужно записать.
     * @throws IOException исключение.
     */

    private static void writeURL(String string) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUT_FILE_TXT))) {
            Pattern pattern = Pattern.compile("\\s*(?<=href\\s?=\\s?\")[^>]*\\/*((jpg)|(png)|(gif))(?=\")");
            Matcher matcher = pattern.matcher(string);
            while (matcher.find()) {
                writer.write(matcher.group() + "\n");
            }
        }
    }

    /**
     * Метод создания и запуска потоков для загрузки картинок.
     *
     * @param countOfDownloads количество картинок, которые надо скачать.
     */

    private static void StartDownload(int countOfDownloads) {
        try (BufferedReader downloadLinks = new BufferedReader(new FileReader(OUT_FILE_TXT))) {
            String music;
            int i = 0;
            while ((music = downloadLinks.readLine()) != null && i < countOfDownloads) {
                Downloader thread = new Downloader (music, PATH_TO_MUSIC + String.valueOf(i + 1) + ".jpeg");
                thread.start();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
