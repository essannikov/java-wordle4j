package ru.yandex.practicum.exceptions;

public class WordNotMatch extends RuntimeException {
    public WordNotMatch(String message) {
        super(message);
    }
}
