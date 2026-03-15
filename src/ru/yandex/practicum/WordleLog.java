package ru.yandex.practicum;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

public class WordleLog implements Closeable {
    private final BufferedWriter writer;

    public WordleLog(String filename) throws IOException {
        this.writer = new BufferedWriter(new FileWriter(filename, StandardCharsets.UTF_8));
    }

    public void println(List<String> messages) throws IOException {
        LocalDateTime dateTime = LocalDateTime.now();
        for (String message : messages) {
            writer.write(String.valueOf(dateTime) + ' ');
            writer.write(message);
            writer.write('\n');
        }
    }

    public void println(String message) throws IOException {
        LocalDateTime dateTime = LocalDateTime.now();
        writer.write(String.valueOf(dateTime) + ' ');
        writer.write(message);
        writer.write('\n');
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
