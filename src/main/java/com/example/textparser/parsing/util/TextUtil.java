package com.example.textparser.parsing.util;

import com.example.textparser.parsing.function.TextSortProcessor;

public class TextUtil {
    public static String function1(Character c1, Character c2, TextSortProcessor textSortProcessor) {
        return textSortProcessor.process(c1, c2);
    }
}
