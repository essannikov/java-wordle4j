package ru.yandex.practicum;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.exceptions.WordNotMatch;
import ru.yandex.practicum.exceptions.WordleException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WordleConverterTest {
    private static WordleConverter converter;

    @BeforeAll
    public static void create() {
        converter = new WordleConverter();
    }

    @Test
    public void shouldGetNormalizedWordException() {
        boolean checkCatch;

        checkCatch = false;
        try{
            converter.getNormalizedWord("");
        } catch (WordNotMatch e) {
            checkCatch = true;
        }
        assertTrue(checkCatch);

        checkCatch = false;
        try{
            converter.getNormalizedWord("qwert");
        } catch (WordNotMatch e) {
            checkCatch = true;
        }
        assertTrue(checkCatch);

        checkCatch = false;
        try{
            converter.getNormalizedWord("12345");
        } catch (WordNotMatch e) {
            checkCatch = true;
        }
        assertTrue(checkCatch);

        checkCatch = false;
        try{
            converter.getNormalizedWord("авантюрист");
        } catch (WordNotMatch e) {
            checkCatch = true;
        }
        assertTrue(checkCatch);
    }

    @Test
    public void shouldGetNormalizedWordOk() {
        boolean checkCatch;
        String wordConvert = "";

        checkCatch = false;
        try{
            wordConvert = converter.getNormalizedWord("Ёрник");
        } catch (WordNotMatch e) {
            checkCatch = true;
        }
        assertFalse(checkCatch);
        assertEquals("ерник", wordConvert);

        checkCatch = false;
        try{
            wordConvert = converter.getNormalizedWord("слОво");
        } catch (WordNotMatch e) {
            checkCatch = true;
        }
        assertFalse(checkCatch);
        assertEquals("слово", wordConvert);
    }
}
