package com.example.textparser.parsing.process;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.partitioningBy;

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
        return crossOutStringProcess(onlySort);
    }

    private String crossOutStringProcess(String content){
        Map<Boolean, List<Character>> result = content.chars()
                .mapToObj(ch -> (char) ch)
                .collect(partitioningBy((Predicate<Character>) Character::isAlphabetic));

        List<Character> wordList = result.get(true);
        List<Character> digitList = result.get(false);

        int minLength = Math.min(wordList.size(), digitList.size());

        StringBuilder resultStr = new StringBuilder();
        for (int i = 0; i < minLength; i++) {
            resultStr.append(wordList.get(i).toString()).append(digitList.get(i).toString());
        }
        if(wordList.size()>digitList.size()){
            resultStr.append(wordList.subList(minLength, wordList.size()).stream().map(String::valueOf).collect(joining()));
        }else{
            resultStr.append(digitList.subList(minLength, digitList.size()).stream().map(String::valueOf).collect(joining()));
        }
        return resultStr.toString();

    }

}
