package az.company.payments.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Around("execution(* az.company.payments.controller..*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info(
                "ActionLog.{}() with argument[s] = {}",
                joinPoint.getSignature().getName(),
                joinPoint.getArgs()
        );

        try {
            var result = joinPoint.proceed();
            log.info(
                    "ActionLog.{}() with result = {}",
                    joinPoint.getSignature().getName(),
                    result
            );
            return result;
        } catch (IllegalArgumentException e) {
            log.error(
                    "Illegal argument: {} in {}()",
                    joinPoint.getArgs(),
                    joinPoint.getSignature().getName()
            );
            throw e;
        }
    }

}
