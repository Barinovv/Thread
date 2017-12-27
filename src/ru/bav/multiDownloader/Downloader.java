package ru.bav.multiDownloader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Downloader extends Thread {
    private String link;


    private String fileName;

    public Downloader(String link, String fileName) {
        this.link = link;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(link);

            try (ReadableByteChannel byteChannel = Channels.newChannel(url.openStream());
                 FileOutputStream stream = new FileOutputStream(fileName)) {

                stream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}


