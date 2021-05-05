package ee.swedbank.parking.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LoggingConfig {
    private final Logger log = LoggerFactory.getLogger(LoggingConfig.class);

    @Before("within(ee.swedbank.parking.service.*))")
    public void logBeforeCall(JoinPoint joinPoint) {
        var strBuilder = new StringBuilder();
        strBuilder.append("parking-start-method: ")
                .append(joinPoint.getSignature());

        if (joinPoint.getArgs().length > 0) {
            strBuilder.append(" with arguments: ")
                    .append(Arrays.toString(joinPoint.getArgs()));
        }

        log.info(strBuilder.toString());
    }

    @AfterReturning(value = "within(ee.swedbank.parking.service.*))", returning = "result")
    public void logAfterCall(JoinPoint joinPoint, Object result) {
        var strBuilder = new StringBuilder()
                .append("parking-end-method: ")
                .append(joinPoint.getSignature());

        if (joinPoint.getArgs().length > 0) {
            strBuilder.append(" with arguments: ")
                    .append(Arrays.toString(joinPoint.getArgs()));
        }

        if (result != null) {
            strBuilder.append("\nparking-method-result: ")
                    .append(result);
        }

        log.info(strBuilder.toString());
    }

}
