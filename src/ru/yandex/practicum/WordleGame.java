package ru.yandex.practicum;

import ru.yandex.practicum.exceptions.WordNotFoundInDictionary;
import ru.yandex.practicum.exceptions.WordNotMatch;
import ru.yandex.practicum.exceptions.WordleException;

import java.util.Random;
import java.util.Scanner;

/*
в этом классе хранится словарь и состояние игры
    текущий шаг
    всё что пользователь вводил
    правильный ответ

в этом классе нужны методы, которые
    проанализируют совпадение слова с ответом
    предложат слово-подсказку с учётом всего, что вводил пользователь ранее

не забудьте про специальные типы исключений для игровых и неигровых ошибок
 */
public class WordleGame {
    public static final int STEPS_MAX = 6;

    private final WordleDictionary dictionary;
    private final WordleConverter converter;
    private final WordleHint hint;
    private final Scanner scanner;
    private String answer;
    private int steps;
    boolean success;

    public WordleGame(WordleDictionary dictionary, WordleConverter converter) throws WordleException {
        if (dictionary.isEmpty()) {
            throw new WordleException("Словарь пуст.");
        }
        this.dictionary = dictionary;
        this.converter = converter;
        this.hint = new WordleHint(dictionary);
        this.scanner = new Scanner(System.in);
        this.steps = 0;
        this.success = false;
    }

    public WordleGame start() {
        this.answer = getRandomWord();

        while (steps < STEPS_MAX) {
            String wordUser = getUserWord();

            if (wordUser.isBlank()) {
                wordUser = hint.getHint();
                System.out.println(wordUser);
            } else {
                steps++;
            }

            if (answer.equals(wordUser)) {
                success = true;
                break;
            }

            String prompt = WordleDictionary.compareWords(answer, wordUser);
            hint.addWordPrompt(wordUser, prompt);
            System.out.println(prompt);
        }

        return this;
    }

    public void status() {
        if (success) {
            System.out.println("Слово отгадано!");
        } else {
            System.out.println("Слово не отгадано.");
        }
        System.out.println("Было загадано слово: " + answer);
        System.out.println("Количество попыток: " + steps);
        System.out.println();
    }

    protected String getRandomWord() throws WordleException {
        int maxLength = dictionary.size();
        if (maxLength == 0) {
            throw new WordleException("Пустой словарь.");
        }
        int index = new Random().nextInt(maxLength - 1);

        return dictionary.get(index);
    }

    protected String getUserWord() {
        String wordUser = "";

        while (wordUser.isBlank()) {
            System.out.println("Введите слово:");
            String line = scanner.nextLine();

            if (line.isBlank()) {
                break;
            } else {
                try{
                    int index = dictionary.indexOf(converter.getNormalizedWord(line));
                    wordUser = dictionary.get(index);
                } catch (WordNotMatch | WordNotFoundInDictionary wordNotMatch) {
                    System.out.println(wordNotMatch.getMessage());
                }
            }
        }

        return wordUser;
    }
}
