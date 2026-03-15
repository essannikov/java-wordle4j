package ru.yandex.practicum;

import ru.yandex.practicum.exceptions.WordNotMatch;

public class WordleConverter {
    public static final int WORD_LENGTH = 5;

    public String getNormalizedWord(String word) throws WordNotMatch {
        if (check(word)) {
            return convert(word);
        } else {
            throw new WordNotMatch("Слово не соответствует правилам.");
        }
    }

    protected boolean check(String word) {
        if (word == null) {
            return false;
        }

        if (word.length() != WORD_LENGTH) {
            return false;
        }

        for (int i = 0; i < word.length(); i++) {
            if (!isRussianRegex(word.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    protected boolean isRussianRegex(char ch) {
        return String.valueOf(ch).matches("[а-яА-ЯёЁ]");
    }

    protected String convert(String word) {
        return word.toLowerCase().replace('ё','е');
    }
}
