package com.example.textparser.parsing.after;

import java.util.Map;

public interface TextAfterProcessService {
    Map<String,String> afterProcess(String content, int number);
}
