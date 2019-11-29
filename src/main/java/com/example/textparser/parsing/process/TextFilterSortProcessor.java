package com.example.textparser.parsing.process;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class TextFilterSortProcessor implements TextProcessService {
    @Override
    public String filterProcess(String content) {
        Pattern wordDigitPattern = Pattern.compile("[^A-Za-z0-9]*");
        return content.replaceAll(wordDigitPattern.pattern(), "").replaceAll(Pattern.compile("\\s+").pattern(), "");
    }

    @Override
    public String sortProcess(String content) {
        return null;
    }
}
