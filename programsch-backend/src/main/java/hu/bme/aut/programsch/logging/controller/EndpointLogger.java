package hu.bme.aut.programsch.logging.controller;

import hu.bme.aut.programsch.logging.controller.RestApiMessageType;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
@Aspect
@Log4j2
public class EndpointLogger {

    private static final String API_POINT_CUT = "execution(* hu.bme.aut.programsch.*.*.*(..))";
    private static final String EXCEPTION_POINTCUT = "execution(* hu.bme.aut.programsch.*.*.*(..))";

    @Pointcut(API_POINT_CUT)
    public void logController() {
        // empty
    }

    private void logDetails(JoinPoint joinPoint) {
        log.info("Class: " + joinPoint.getSignature().getDeclaringTypeName());
        log.info("Method: " + joinPoint.getSignature().getName());
        log.info("Arguments: " + Arrays.toString(joinPoint.getArgs()));
        log.info("Joinpont: " + joinPoint);
    }

    @Before("logController()")
    public void logRequest(JoinPoint joinPoint) {
        logDetails(joinPoint);
        log.info(createJoinPointForLogs(joinPoint, RestApiMessageType.REQUEST));
    }

    @AfterReturning("logController()")
    public void logsResponse(JoinPoint joinPoint) {
        logDetails(joinPoint);
        log.info(createJoinPointForLogs(joinPoint, RestApiMessageType.RESPONSE));
    }


    @AfterThrowing(value = EXCEPTION_POINTCUT, throwing = "exception")
    public void logsErrors(JoinPoint joinPoint, Throwable exception) {
        logDetails(joinPoint);
    }


    private String createJoinPointForLogs(JoinPoint joinPoint, RestApiMessageType dataType) {
        if (joinPoint.getArgs().length == 0) {
            return joinPoint.getSignature().getName()
                    .concat(" method called with no arguments");
        }
        Object[] obj = joinPoint.getArgs();
        StringBuilder requestValue = new StringBuilder();
        if (dataType.equals(RestApiMessageType.REQUEST)) {
            requestValue.append("Request value: ");
        } else {
            requestValue.append("Response value: ");
        }
        Arrays.stream(obj).forEach(x -> {
            requestValue.append(x.toString());
        });
        requestValue.append("\r\n");
        return requestValue.toString();
    }
}
