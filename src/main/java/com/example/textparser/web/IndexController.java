package com.example.textparser.web;

import com.example.textparser.infra.ThreadLog;
import com.example.textparser.parsing.TextParsingService;
import com.example.textparser.web.dto.CalcResponse;
import com.example.textparser.web.dto.TextParsingDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@Slf4j
public class IndexController {

    private final TextParsingService textParsingService;

    @ThreadLog
    @PostMapping
    public String printText(@Valid TextParsingDto textParsingDto, Model model) {
        String[] urls = textParsingDto.getUrl();
        boolean excludeHtmlTag = textParsingDto.isExcludeHtmlTag();
        int divisorNumber = textParsingDto.getDivisorNumber();
        Map<String, String> result = null;
        result = textParsingService.parsingText(urls, excludeHtmlTag, divisorNumber);
        CalcResponse calcResponse = new CalcResponse();
        calcResponse.setQuotientString(result.get("quotient"));
        calcResponse.setRemainderString(result.get("remainder"));
        model.addAttribute("calcResponse", calcResponse);
        return "index";
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("textParsingDto", new TextParsingDto());
        return "index";
    }

}
