package ru.yandex.practicum;

import ru.yandex.practicum.enums.WordleMask;
import ru.yandex.practicum.exceptions.WordNotFoundInDictionary;

import java.util.List;

/*
этот класс содержит в себе список слов List<String>
    его методы похожи на методы списка, но учитывают особенности игры
    также этот класс может содержать рутинные функции по сравнению слов, букв и т.д.
 */
public class WordleDictionary {
    private final List<String> wordsList;

    public WordleDictionary(List<String> wordsList) {
        this.wordsList = wordsList;
    }

    public boolean isEmpty() {
        return wordsList.isEmpty();
    }

    public int size() {
        return wordsList.size();
    }

    public String get(int index) {
        return wordsList.get(index);
    }

    public List<String> getWordsList() {
        return wordsList;
    }

    public int indexOf(String word) throws WordNotFoundInDictionary {
        int index = wordsList.indexOf(word);

        if (index < 0) {
            throw new WordNotFoundInDictionary("Слово отсутствует в словаре.");
        }

        return index;
    }

    public static String compareWords(String searchWord, String inputWord) {
        StringBuilder prompt = new StringBuilder();

        for (int i = 0; i < inputWord.length(); i++) {
            if (inputWord.charAt(i) == searchWord.charAt(i)) {
                prompt.append(WordleMask.CHAR_EQUAL.getCode());
            } else if (searchWord.contains(String.valueOf(inputWord.charAt(i)))) {
                prompt.append(WordleMask.CHAR_ON_DIFFERENT_PLACE.getCode());
            } else {
                prompt.append(WordleMask.CHAR_NOT_EQUAL.getCode());
            }
        }

        return prompt.toString();
    }
}
