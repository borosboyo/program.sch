package hu.bme.aut.programsch.logging.executiontime;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


@Aspect
@Component
public class ExecutionTimeAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("@annotation(hu.bme.aut.programsch.logging.executiontime.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object proceed;

        try {
            proceed = joinPoint.proceed();
        } catch (Throwable e) {
            throw new CustomRuntimeException(e);
        }

        stopWatch.stop();
        logger.info("\"{}\" executed in {} ms", joinPoint.getSignature(), stopWatch.getTotalTimeMillis());

        return proceed;
    }
}