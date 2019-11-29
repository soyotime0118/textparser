package com.example.textparser.web.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

@Setter
@Getter
public class TextParsingDto {
    @URL(message = "URL형식으로 입력가능합니다.")
    private String url;
    @Range(min = 1, max = Integer.MAX_VALUE, message = "0보다큰 수만 입력 가능합니다.")
    private int divisorNumber = 1;
    private boolean excludeHtmlTag;
}
