package com.example.textparser.parsing.process;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@Log4j2
class TextFilterSortProcessorTest {

    @InjectMocks
    TextFilterSortProcessor textFilterSortProcessor;

    @Test
    @DisplayName("!+특수문자 제거, 영문, 숫자만 표시")
    void includeSpec_filter_excludeSpec() {
        String input = "!!!aabbcc++++ddee00\ndd0011";
        String result = textFilterSortProcessor.filterProcess(input);
        log.info(result);
        assertThat(result).doesNotContain("+");
        assertThat(result).doesNotContain("!");
    }

    @Test
    @DisplayName("!+특수문자 제거, 영문, 숫자만 표시")
    void includeSpec_filter_excludeSpec_1() {
        String input = "!@#$%^()aabbccddee^%@#%가나다";
        String result = textFilterSortProcessor.filterProcess(input);
        log.info(result);
        assertThat(result).doesNotContain("!@");
        assertThat(result).doesNotContain("!");
        assertThat(result).doesNotContain("가나다");
    }

}