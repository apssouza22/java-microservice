package com.apssouza.interceptors;

import com.apssouza.events.CallMethodEvent;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

@Aspect
public class LoggingInterceptor {

    private final ApplicationEventPublisher publisher;

    @Autowired
    public LoggingInterceptor(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @AfterReturning(
            pointcut = "execution(* com.apssouza.services.TodoService.addCustomerReturnValue(..))",
            returning = "result"
    )
    public Object logCall(JoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        long start = System.currentTimeMillis();
        try {
            long duration = System.currentTimeMillis() - start;
            publisher.publishEvent(
                    new CallMethodEvent(
                            joinPoint.getTarget().toString(),
                            duration
                    )
            );

        } catch (Throwable ex) {
            Logger.getLogger(LoggingInterceptor.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return joinPoint.getThis();
    }

}
