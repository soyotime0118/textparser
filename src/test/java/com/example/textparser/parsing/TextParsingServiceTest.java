package com.example.textparser.parsing;

import com.example.textparser.parsing.after.TextAfterProcessService;
import com.example.textparser.parsing.process.TextProcessService;
import com.example.textparser.parsing.read.TextInputSource;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Log4j2
class TextParsingServiceTest {

    @Autowired
    TextParsingService textParsingService;

    @Autowired
    TextInputSource textInputSource;

    @Autowired
    TextProcessService textProcessService;

    @Autowired
    TextAfterProcessService textAfterProcessService;

    @Test
    void test_1() {
        Map<String, String> result = textParsingService.parsingText("http://www.naver.com", true, 10);
        log.info(result);
        assertThat(result).isNotNull();
    }
}