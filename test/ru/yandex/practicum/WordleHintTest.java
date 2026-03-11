package ru.yandex.practicum;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordleHintTest {

    @Test
    public void shouldGetHintWordCorrect() {
        List<String> list = new ArrayList<>();
        list.add("слово");
        list.add("олово");
        list.add("салют");
        list.add("самба");
        list.add("самбо");

        WordleHint hint = new WordleHint(new WordleDictionary(list));
        hint.addWordPrompt("слово","+-^-+");
        String wordHint = hint.getHint();

        assertEquals("самбо", wordHint);
    }
}
