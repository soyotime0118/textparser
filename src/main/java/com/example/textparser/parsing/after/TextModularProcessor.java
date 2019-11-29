package com.example.textparser.parsing.after;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Log4j2
public class TextModularProcessor implements TextAfterProcessService {
    @Override
    public Map<String, String> afterProcess(String content, int number) {
        int contentLength = content.length();
        int remainder = contentLength % number;

        Map<String, String> result = new HashMap<>();
        result.put("quotient", content.substring(0, contentLength - remainder));
        result.put("remainder", content.substring(contentLength - remainder));

        return result;
    }
}
