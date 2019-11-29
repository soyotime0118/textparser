package com.example.textparser.parsing.process;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

@Service
public class TextFilterSortProcessor implements TextProcessService {
    @Override
    public String filterProcess(String content) {
        Pattern wordDigitPattern = Pattern.compile("[^A-Za-z0-9]*");
        return content.replaceAll(wordDigitPattern.pattern(), "").replaceAll(Pattern.compile("\\s+").pattern(), "");
    }

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
                .flatMap(character -> characterListMap.get(character).stream()
                        .map(character1 -> Character.toString(character1)))
                .collect(joining());
        return onlySort;
    }
}
