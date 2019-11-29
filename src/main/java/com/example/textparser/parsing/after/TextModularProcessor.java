package com.example.textparser.parsing.after;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Log4j2
@Service
public class TextModularProcessor implements TextAfterProcessService {
    @Override
    public Map<String, String> afterProcess(String content, int number) {
        int contentLength = content.length();
        int remainder = contentLength % number;

        Map<String, String> result = new HashMap<>();
        int endIndex = contentLength - remainder;
        result.put("quotient", content.substring(0, endIndex));
        result.put("remainder", content.substring(endIndex));

        return result;
    }
}
