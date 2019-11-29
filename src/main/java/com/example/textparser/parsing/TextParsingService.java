package com.example.textparser.parsing;

import com.example.textparser.parsing.after.TextAfterProcessService;
import com.example.textparser.parsing.process.TextProcessService;
import com.example.textparser.parsing.read.TextInputSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
@Log4j2
public class TextParsingService {
    private final TextInputSource textInputSource;
    private final TextProcessService textProcessService;
    private final TextAfterProcessService textAfterProcessService;

    public Map<String, String> parsingText(String url, boolean excludeHtmlTag, int divisorNumber) {
        String htmlResponse = textInputSource.getText(excludeHtmlTag, url);
        String filterResponse = textProcessService.filterProcess(htmlResponse);
        String sortResponse = textProcessService.sortProcess(filterResponse);

        return textAfterProcessService.afterProcess(sortResponse, divisorNumber);
    }
}
