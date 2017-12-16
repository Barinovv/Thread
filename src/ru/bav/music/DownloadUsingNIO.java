package ru.bav.music;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Класс, который осуществляет скачивание музыки
 */

public class DownloadUsingNIO extends Thread {
    private String strUrl;
    private String file;

    public DownloadUsingNIO(String url, String file) {
        this.strUrl = url;
        this.file = file;
    }

    @Override
    public void run() {
       URL url = null;
        try {
            url= new URL(strUrl);

        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        ReadableByteChannel byteChannel = null;
        try {
            byteChannel = Channels.newChannel(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            stream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            byteChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
