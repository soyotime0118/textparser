package com.example.textparser.web.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TextParsingDto {
    private String url;
    private int divisorNumber;
    private boolean excludeHtmlTag;
}
