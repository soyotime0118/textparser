package com.example.textparser.infra;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
@Slf4j
public class AopConfig {

    @Around("@annotation(ThreadLog)")
    public Object logThread(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.info("start");
        Object result = pjp.proceed();
        log.info("end");
        stopWatch.stop();
        Double time = stopWatch.getTotalTimeSeconds();
        log.info("소요시간 {}", time);
        return result;
    }
}
