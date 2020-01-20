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
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@Log4j2
public class AsyncInputSourceTest {
    @InjectMocks
    AsyncWebPageTextInputSource asyncWebPageTextInputSource;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ExecutorService exec;

    @BeforeEach
    void setUp() {
        String mockResult = "<div>ABCD0011</div>hihi";
        Mockito.when(restTemplate.getForObject(Mockito.any(URI.class),Mockito.eq(String.class))).thenReturn(mockResult);
    }

    @Test
    @DisplayName("입력이 없을경우 Exception")
    void Empty_Exception(){
        Mockito.when(restTemplate.getForObject(Mockito.any(URI.class),Mockito.eq(String.class))).thenReturn("");
        String[] resources = new String[]{"a","b","c","d"};
        String result = asyncWebPageTextInputSource.getText(true, resources);
        assertThat(result).isNotNull();
    }
}
