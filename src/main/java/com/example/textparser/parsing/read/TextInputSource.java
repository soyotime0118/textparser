package com.example.textparser.parsing.read;

public interface TextInputSource {

    String getText(boolean excludeHtmlTag, String resource);

    String getText(boolean excludeHtmlTag, String[] resources);
}
