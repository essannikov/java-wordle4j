package ru.yandex.practicum.enums;

public enum WordleMask {
    CHAR_EQUAL('+'),
    CHAR_ON_DIFFERENT_PLACE('^'),
    CHAR_NOT_EQUAL('-');

    private final char code;

    WordleMask(char code) {
        this.code = code;
    }

    public char getCode() {
        return code;
    }
}
