package ru.yandex.practicum;

import ru.yandex.practicum.exceptions.WordNotMatch;
import ru.yandex.practicum.exceptions.WordleException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/*
этот класс содержит в себе всю рутину по работе с файлами словарей и с кодировками
    ему нужны методы по загрузке списка слов из файла по имени файла
    на выходе должен быть класс WordleDictionary
 */
public class WordleDictionaryLoader {
    private WordleConverter converter;

    public WordleDictionaryLoader(WordleConverter converter) {
        this.converter = converter;
    }

    public WordleDictionary getDictionary(String filename) throws IOException {
        List<String> stringList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename, StandardCharsets.UTF_8))) {
            while (br.ready()){
                try{
                    stringList.add(converter.getNormalizedWord(br.readLine()));
                } catch (WordNotMatch ignored) {
                }
            }
        }

        return new WordleDictionary(stringList);
    }
}
