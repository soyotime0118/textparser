package com.example.textparser.parsing.read;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class WebPageTextInputSource implements TextInputSource{

    private final RestTemplate restTemplate;
    @Override
    public String getText(boolean excludeHtmlTag, String resource) {
        String html = restTemplate.getForObject(URI.create(resource),String.class);
        if(excludeHtmlTag){
            html = removeHtmlTag(html);
        }
        return html;
    }

    @Override
    public String getText(boolean excludeHtmlTag, String[] resources) {
        return null;
    }

    private String removeHtmlTag(String htmlText) {
        Pattern htmlTagPattern = Pattern.compile("(<([^>]+)>)");
        return htmlText.replaceAll(htmlTagPattern.pattern(),"").replaceAll(Pattern.compile("\\s+").pattern(),"");
    }
}
