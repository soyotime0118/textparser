package com.example.textparser.parsing.util;

import com.example.textparser.parsing.function.TextSortProcessor;

import java.util.List;

import static java.util.stream.Collectors.joining;

public class TextUtil {
    public static String function1(List<Character> characterList, List<Character> characterList1, TextSortProcessor textSortProcessor) {
        int minSize = Math.min(characterList.size(), characterList1.size());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < minSize; i++) {
            stringBuilder.append(textSortProcessor.process(characterList.get(i), characterList1.get(i)));
        }
        return stringBuilder.toString();
    }

    public static String function2(List<Character> characterList, int startIndex) {
        return characterList.stream().map(String::valueOf).collect(joining()).substring(startIndex);
    }
}
