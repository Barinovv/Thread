package ru.bav.music;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Класс, в котором реализованно скачивание музыки.
 *
 * @author Barinov 1518
 */

public class Main {
    private static final String IN_FILE_TXT = "C:\\Users\\User\\IdeaProjects\\Thread\\src\\ru\\bav\\music\\inFile.txt";
    private static final String OUT_FILE_TXT = "C:\\Users\\User\\IdeaProjects\\Thread\\src\\ru\\bav\\music\\outFile.txt";
    private static final String PATH_TO_MUSIC = "C:\\Users\\User\\IdeaProjects\\Thread\\src\\ru\\bav\\music\\";

    public static void main(String[] args) {
        first();
        second();
    }


    private static void first() {
        String Url;
        try (BufferedReader inFile = new BufferedReader(new FileReader(IN_FILE_TXT));
             BufferedWriter outFile = new BufferedWriter(new FileWriter(OUT_FILE_TXT))) {
            while ((Url = inFile.readLine()) != null) {
                URL url = new URL(Url);

                String result;
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                    result = bufferedReader.lines().collect(Collectors.joining("\n"));
                }
                Pattern email_pattern = Pattern.compile("\\s*(?<=data-url\\s?=\\s?\")[^>]*/*(?=\")");
                Matcher matcher = email_pattern.matcher(result);
                int i = 0;
                while (matcher.find() && i < 3) {
                    outFile.write(matcher.group() + "\r\n");
                    i++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void second() {
        try (BufferedReader musicFile = new BufferedReader(new FileReader(OUT_FILE_TXT))) {
            String music;
            int count = 0;
            try {
                while ((music = musicFile.readLine()) != null) {
                    new DownloadUsingNIO(music, PATH_TO_MUSIC + String.valueOf(count) + ".mp3").start();
                    count++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

