package com.example.textparser.web;

import com.example.textparser.parsing.TextParsingService;
import com.example.textparser.web.dto.CalcResponse;
import com.example.textparser.web.dto.TextParsingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final TextParsingService textParsingService;

    @PostMapping
    public String printText(@Valid TextParsingDto textParsingDto, Model model) {
        String url = textParsingDto.getUrl();
        boolean excludeHtmlTag = textParsingDto.isExcludeHtmlTag();
        int divisorNumber = textParsingDto.getDivisorNumber();
        Map<String, String> result = textParsingService.parsingText(url, excludeHtmlTag, divisorNumber);
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
