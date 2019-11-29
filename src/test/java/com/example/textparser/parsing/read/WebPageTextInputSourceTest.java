package com.example.textparser.parsing.read;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
@Log4j2
class WebPageTextInputSourceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    WebPageTextInputSource webPageTextInputSource;

    @BeforeEach
    void setUp() {
        String mockResult = "<div>ABCD0011</div>hihi";
        Mockito.when(restTemplate.getForObject(Mockito.any(URI.class),Mockito.eq(String.class))).thenReturn(mockResult);
    }

    @Test
    void getText() {
        String result = webPageTextInputSource.getText(true,"http://www.naver.com");
        log.info(result);
        assertThat(result).isNotNull();
    }


    @Test
    @DisplayName("태그제외시 태그 안보임")
    void True_TagInfo_No_Tag() {
        String result = webPageTextInputSource.getText(true,"http://www.naver.com");
        log.info(result);
        assertThat(result).isNotNull();
        assertThat(result).doesNotContain("<div>");
        assertThat(result).doesNotContain("</div>");
    }

    @Test
    @DisplayName("태그제외시 태그 안보임,(짝이 안맞는경우)")
    void True_TagInfo_No_Tag_1() {
        Mockito.when(restTemplate.getForObject(URI.create("http://www.naver.com"),String.class)).thenReturn("<divABCD0011</div>hihi");
        String result = webPageTextInputSource.getText(true,"http://www.naver.com");
        log.info(result);
        assertThat(result).isNotNull();
        assertThat(result).doesNotContain("<");
        assertThat(result).doesNotContain(">");
    }

    @Test
    @DisplayName("태그포함시, 태그정보가 보임")
    void False_TagInfo_Tag(){
        String result = webPageTextInputSource.getText(false,"http://www.naver.com");
        log.info(result);
        assertThat(result).isNotNull();
        assertThat(result).contains("<div>");
        assertThat(result).contains("</div>");
    }
}