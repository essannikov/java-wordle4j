package ru.yandex.practicum;

import ru.yandex.practicum.exceptions.WordleException;

import java.io.IOException;
import java.util.Scanner;

/*
в главном классе нам нужно:
    создать лог-файл (он должен передаваться во все классы)
    создать загрузчик словарей WordleDictionaryLoader
    загрузить словарь WordleDictionary с помощью класса WordleDictionaryLoader
    затем создать игру WordleGame и передать ей словарь
    вызвать игровой метод в котором в цикле опрашивать пользователя и передавать информацию в игру
    вывести состояние игры и конечный результат
 */
public class Wordle {
    public static final String FILE_NAME = "words_ru.txt";
    public static final String FILE_LOG = "log.txt";

    public static void main(String[] args) {
        try (WordleLog log = new WordleLog(FILE_LOG);
             Scanner scanner = new Scanner(System.in)) {
            try {
                WordleConverter converter = new WordleConverter();
                WordleDictionary dictionary = new WordleDictionaryLoader(converter).getDictionary(FILE_NAME);

                boolean running = true;
                while (running) {
                    showMenu();

                    String choice = scanner.nextLine();
                    switch (choice) {
                        case "1":
                            new WordleGame(dictionary, converter).start().status();
                            break;
                        case "0":
                            running = false;
                            break;
                        default:
                            System.out.println("Неверный выбор.");
                    }
                }
            } catch (IOException e) {
                log.println("Произошла ошибка во время чтения файла.");
            } catch (WordleException wordleException) {
                log.println(wordleException.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Ошибка записи в журнал.");
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Начать игру");
        System.out.println("0 — Завершить");
    }
}
