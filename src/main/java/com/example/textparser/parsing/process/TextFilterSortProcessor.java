package com.example.textparser.parsing.process;

import com.example.textparser.parsing.function.TextSortProcessor;
import com.example.textparser.parsing.util.TextUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.partitioningBy;

@Log4j2
@Service
public class TextFilterSortProcessor implements TextProcessService {
    @Override
    public String filterProcess(String content) {
        Pattern wordDigitPattern = Pattern.compile("[^A-Za-z0-9]*");
        Pattern spaceRegPattern = Pattern.compile("\\s+");
        return content.replaceAll(wordDigitPattern.pattern(), "").replaceAll(spaceRegPattern.pattern(), "");
    }

    /**
     * 문자열 정렬
     */
    @Override
    public String sortProcess(String content) {
        Map<Character, List<Character>> characterListMap = content.chars()
                .mapToObj(ch -> (char) ch)
                .collect(
//                        정렬시 알파벳 순서로 groupby
                        Collectors.groupingBy(
//                                key, 대문자
                                Character::toUpperCase,
//                                list, 같은 문자끼리도 오름차순 정렬, 대문자->소문자
                                Collectors.collectingAndThen(
//                                AAaAA -> AAAAa
                                        Collectors.toCollection(ArrayList::new),
                                        l -> {
                                            l.sort(Character::compareTo);
                                            return l;
                                        })
                        )
                );
        String onlySort = characterListMap.keySet()
                .stream()
                .sorted()
                .flatMap(character -> characterListMap.get(character).stream().map(character1 -> Character.toString(character1)))
                .collect(joining());
        return crossOutStringProcess(onlySort);
    }

    /**
     * 문자, 숫자가 교대로 나오도록 재정렬
     */
    private String crossOutStringProcess(String content) {
        Map<Boolean, List<Character>> result = content.chars()
                .mapToObj(ch -> (char) ch)
                .collect(partitioningBy((Predicate<Character>) Character::isAlphabetic));

        List<Character> wordList = result.get(true);
        List<Character> digitList = result.get(false);

        TextSortProcessor f1 = (character, character1) -> character + String.valueOf(character1);

        int wordSize = wordList.size();
        int digitSize = digitList.size();
        int minLength = Math.min(wordSize, digitSize);

        String result1 = TextUtil.function1(wordList, digitList, f1);
        String result2;
        if (wordList.size() > digitList.size()) {
            result2 = TextUtil.function2(wordList, minLength);
        } else {
            result2 = TextUtil.function2(digitList, minLength);
        }
        return result1 + result2;
    }

}
