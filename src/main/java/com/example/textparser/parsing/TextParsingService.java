package com.example.textparser.parsing;

import com.example.textparser.parsing.after.TextAfterProcessService;
import com.example.textparser.parsing.process.TextProcessService;
import com.example.textparser.parsing.read.TextInputSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.Map;

@RequiredArgsConstructor
@Service
@Log4j2
public class TextParsingService {
    private final TextInputSource asyncWebPageTextInputSource;
    private final TextProcessService textProcessService;
    private final TextAfterProcessService textAfterProcessService;

    public Map<String, String> parsingText(String url, boolean excludeHtmlTag, int divisorNumber) {
        String htmlResponse = asyncWebPageTextInputSource.getText(excludeHtmlTag, url);
        String sortResponse = filteringSorting(htmlResponse);
        return textAfterProcessService.afterProcess(sortResponse, divisorNumber);
    }

    public Map<String,String> parsingText(String[] urls, boolean excludeHtmlTag, int divisorNumber) {
        String htmlResponse = asyncWebPageTextInputSource.getText(excludeHtmlTag, urls);
        String sortResponse = filteringSorting(htmlResponse);
        return textAfterProcessService.afterProcess(sortResponse, divisorNumber);
    }

    private String filteringSorting(String htmlResponse) {
        String filterResponse = textProcessService.filterProcess(htmlResponse);
        return textProcessService.sortProcess(filterResponse);
    }
}
