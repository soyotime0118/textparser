package com.example.textparser.parsing.process;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@Log4j2
class TextSortTest {
    @InjectMocks
    TextFilterSortProcessor textFilterSortProcessor;

    @Test
    void sort_ascend() {
        String result =textFilterSortProcessor.sortProcess("DCBA");
        log.info(result);
        assertThat(result).isEqualTo("ABCD");
    }

    @Test
    void sort_ascend_1() {
        String result =textFilterSortProcessor.sortProcess("DdCBA");
        log.info(result);
        assertThat(result).isEqualTo("ABCDd");
    }

    @Test
    void sort_ascend_2() {
        String result =textFilterSortProcessor.sortProcess("0DdaCBA9");
        log.info(result);
        assertThat(result).isEqualTo("A0a9BCDd");
    }
}