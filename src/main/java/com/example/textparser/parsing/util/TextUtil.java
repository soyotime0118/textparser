package com.example.textparser.parsing.util;

import com.example.textparser.parsing.function.TextSortProcessor;

import java.util.List;

public class TextUtil {
    public static String function1(List<Character> characterList, List<Character> characterList1, TextSortProcessor textSortProcessor) {
        int minSize = Math.min(characterList.size(), characterList1.size());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < minSize; i++) {
            stringBuilder.append(textSortProcessor.process(characterList.get(i), characterList1.get(i)));
        }
        return stringBuilder.toString();
    }
}
