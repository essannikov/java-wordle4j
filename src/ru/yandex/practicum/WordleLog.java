package ru.yandex.practicum;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

public class WordleLog {
    private final String filename;

    public WordleLog(String filename) {
        this.filename = filename;
    }

    public void println(List<String> messages) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, StandardCharsets.UTF_8))) {
            LocalDateTime dateTime = LocalDateTime.now();
            for (String message : messages) {
                writer.write(String.valueOf(dateTime) + ' ');
                writer.write(message);
                writer.write('\n');
            }
        }
    }

    public void println(String message) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, StandardCharsets.UTF_8))) {
            LocalDateTime dateTime = LocalDateTime.now();
            writer.write(String.valueOf(dateTime) + ' ');
            writer.write(message);
            writer.write('\n');
        }
    }
}
