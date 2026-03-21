package ru.yandex.practicum;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.exceptions.WordleException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WordleTest {

    private static WordleGame game;
    private static List<String> list;

    @BeforeAll
    public static void create() {
        list = new ArrayList<>();
        list.add("слово");
        list.add("олово");
        list.add("салют");
        list.add("самба");
        list.add("самбо");

        WordleDictionary dictionary = new WordleDictionary(list);
        WordleConverter converter = new WordleConverter();

        game = new WordleGame(dictionary, converter);
    }

    @Test
    public void checkPrompt() {
        String prompt = WordleDictionary.compareWords("салют","самба");
        assertEquals("++--^", prompt);
    }

    @Test
    public void checkRandomWord() {
        String answer = game.getRandomWord();
        assertNotEquals(-1, list.indexOf(answer));
    }
}
