package com.example.textparser.parsing.read;

import com.example.textparser.infra.ThreadLog;
import com.example.textparser.web.exception.ErrorCode.ErrorCode;
import com.example.textparser.web.exception.ParserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.concurrent.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class AsyncWebPageTextInputSource implements TextInputSource {
    private final ExecutorService exec;

//    private final AsyncRestTemplate asyncRestTemplate;

    private final ExecutorCompletionService<String> executorCompletionService;

    private final RestTemplate restTemplate;

    @ThreadLog
    @Override
    public String getText(boolean excludeHtmlTag, String resource) {
        FutureTask<String> stringFuture = new FutureTask<>(() -> {
            String result = restTemplate.getForObject(URI.create(resource), String.class);
            log.info(Thread.currentThread().getName());
            Thread.sleep(2000L);
            return result;
        });
        FutureTask<String> futureTask = (FutureTask<String>) exec.submit(stringFuture);

        String html = null;
        try {
            html = futureTask.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return html;
    }

    @Override
    public String getText(boolean excludeHtmlTag, String[] resources) {

        CompletableFuture<String> a = new CompletableFuture<>();
        CompletableFuture<String> b = new CompletableFuture<>();
        CompletableFuture<String> c = new CompletableFuture<>();
        CompletableFuture<String> d = new CompletableFuture<>();
        CompletableFuture<String> e = new CompletableFuture<>();
        exec.submit(() -> {
            log.info("a 시작!");
            Thread.sleep(2000);
            return a.complete(restTemplate.getForObject(URI.create(resources[0]), String.class));
        });
        exec.submit(() -> {
            log.info("b 시작!");
            Thread.sleep(2000);
            return b.complete(restTemplate.getForObject(URI.create(resources[1]), String.class));
        });
        exec.submit(() -> {
            log.info("c 시작!");
            Thread.sleep(2000);
            return c.complete(restTemplate.getForObject(URI.create(resources[2]), String.class));
        });
        exec.submit(() -> {
            log.info("d 시작!");
            Thread.sleep(2000);
            return d.complete(restTemplate.getForObject(URI.create(resources[3]), String.class));
        });
        e = a.thenCombine(b, (s, s2) -> s + s2)
                .thenCombine(c, (s, s2) -> s + s2)
                .thenCombine(d, (s, s2) -> s + s2);
        String result = null;
        try {
            result = e.get(5,TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (ExecutionException ex) {
            ex.printStackTrace();
        } catch (TimeoutException ex) {
            ex.printStackTrace();
            result="";
        }
        if(result.isEmpty()){
            throw new ParserException(ErrorCode.NO_RESULT);
        }
        return result;
    }


}
