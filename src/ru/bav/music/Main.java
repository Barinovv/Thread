package ru.bav.music;


import java.io.*;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Класс, в создаются потоки для скачивания музыки, формируется название треков.
 *
 * @author Barinov 1518.
 */

public class Main {
    private static final String IN_FILE_TXT = "src\\ru\\bav\\music\\inFile.txt";
    private static final String OUT_FILE_TXT = "src\\ru\\bav\\music\\outFile.txt";
    private static final String PATH_TO_MUSIC = "src\\ru\\bav\\music\\download\\song";

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(IN_FILE_TXT));
        BufferedWriter writer = new BufferedWriter(new FileWriter(OUT_FILE_TXT));

        writeURL(parseLink(reader), writer);

        StartDownload(3);
    }

    /**
     * Метод, который записывает весь HTML код в одну строку.
     *
     * @param reader файл с ссылкой на сайт.
     * @return HTMl код страницы в виде строки.
     * @throws IOException исключение.
     */

    private static String parseLink(BufferedReader reader) throws IOException {
        String link;
        String result = null;
        while ((link = reader.readLine()) != null) {
            URL url = new URL(link);
            try (BufferedReader reader1 = new BufferedReader(new InputStreamReader(url.openStream()))) {
                result = reader1.lines().collect(Collectors.joining("\n"));
            }
        }
        return result;
    }

    /**
     * Метод для записи ссылок найденых по шаблону в outFile.txt.
     *
     * @param string ссылка, которую нужно записать.
     * @param writer записывает ссылку.
     * @throws IOException исключение.
     */

    private static void writeURL(String string, BufferedWriter writer) throws IOException {
        Pattern pattern = Pattern.compile("\\s*(?<=data-url\\s?=\\s?\")[^>]*\\/*(?=\")");
        Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {
            writer.write(matcher.group() + "\n");
        }
    }

    /**
     * Метод создания и запуска потоков для загрузки музыки.
     *
     * @param countOfDownloads количество песен, которые надо скачать.
     */

    private static void StartDownload(int countOfDownloads) {
        try (BufferedReader downloadLinks = new BufferedReader(new FileReader(OUT_FILE_TXT))) {
            String music;
            int i = 0;
            while ((music = downloadLinks.readLine()) != null && i < countOfDownloads) {
                DownloadMusic thread = new DownloadMusic(music, PATH_TO_MUSIC + String.valueOf(i+1) + ".mp3");
                thread.start();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
