package com.example.textparser.parsing.function;

import java.util.function.BiFunction;

@FunctionalInterface
public interface TextSortProcessor {

//    BiFunction<Character,Character,String> f1= (character, character1) -> character +String.valueOf(character1);

    String process(Character c1, Character c2);

    default String andOtherText(TextSortProcessor textSortProcessor, String anotherStr) {
        return textSortProcessor + anotherStr;
    }
}
