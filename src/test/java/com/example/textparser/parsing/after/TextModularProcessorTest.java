package com.example.textparser.parsing.after;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@Log4j2
class TextModularProcessorTest {

    @InjectMocks
    TextModularProcessor textModularProcessor;

    @Test
    void modular_calc_string_1() {
        String input = "abcdefg";
        int number =  2;
        Map<String,String> result =textModularProcessor.afterProcess(input,number);
        assertThat(result.get("quotient")).isEqualTo("abcdef");
        assertThat(result.get("remainder")).isEqualTo("g");
        log.info(result);
    }

    @Test
    void modular_calc_string_2() {
        String input = "abcdefg";
        int number =  3;
        Map<String,String> result =textModularProcessor.afterProcess(input,number);
        assertThat(result.get("quotient")).isEqualTo("abcdef");
        assertThat(result.get("remainder")).isEqualTo("g");
        log.info(result);
    }

    @Test
    void modular_calc_string_3() {
        String input = "abcdefg";
        int number =  100;
        Map<String,String> result =textModularProcessor.afterProcess(input,number);
        assertThat(result.get("quotient")).isEqualTo("");
        assertThat(result.get("remainder")).isEqualTo("abcdefg");
        log.info(result);
    }

    @Test
    void modular_calc_string_4() {
        String input = "abcdefg";
        int number =  1;
        Map<String,String> result =textModularProcessor.afterProcess(input,number);
        assertThat(result.get("quotient")).isEqualTo("abcdefg");
        assertThat(result.get("remainder")).isEqualTo("");
        log.info(result);
    }
}