package ru.yandex.practicum;

import ru.yandex.practicum.enums.WordleMask;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WordleHint {
    private List<String> wordsList;
    private Map<String, String> filterMap;

    public WordleHint(WordleDictionary dictionary) {
        this.wordsList = dictionary.getWordsList();
        this.filterMap = new LinkedHashMap<>();
    }

    public void addWordPrompt(String word, String prompt) {
        filterMap.put(word, prompt);
    }

    public String getHint() {
        if (!filterMap.isEmpty()) {
            wordListFilter();
        }

        return wordsList.getFirst();
    }

    protected void wordListFilter() {
        List<String> wordsListNew = new ArrayList<>();

        for (String word : wordsList) {
            if (checkWord(word)) {
                wordsListNew.add(word);
            }
        }

        wordsList = wordsListNew;
        filterMap.clear();
    }

    protected boolean checkWord(String word) {
        for (Map.Entry<String, String> filter: filterMap.entrySet()) {
            for (int i = 0; i < filter.getValue().length(); i++) {
                char charPrompt = filter.getValue().charAt(i);
                char charValue = filter.getKey().charAt(i);

                if (charPrompt == WordleMask.CHAR_NOT_EQUAL.getCode()) {
                    if (word.contains(String.valueOf(charValue))) {
                        return false;
                    }
                } else if (charPrompt == WordleMask.CHAR_ON_DIFFERENT_PLACE.getCode()) {
                    if (charValue == word.charAt(i)) {
                        return false;
                    }
                    if (!word.contains(String.valueOf(charValue))) {
                        return false;
                    }
                } else if (charPrompt == WordleMask.CHAR_EQUAL.getCode()) {
                    if (charValue != word.charAt(i)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
