package com.apssouza.interceptors;

import com.apssouza.events.TodoServiceMethodCalledEvent;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * Interceptor for methods called in the Todo service methods
 *
 * @author apssouza
 */
@Aspect
@Component
public class TodoServiceInterceptor {

    private final ApplicationEventPublisher publisher;

    @Autowired
    public TodoServiceInterceptor(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }
    /**
     * (..) matches any number of parameters (zero or more)
     * * as the returning type pattern, which matches any return type
     * @param joinPoint
     * @return 
     */
    @Around("execution(* com.apssouza.services.ToDoService.*(..))")
    public Object logCall(ProceedingJoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        long start = System.currentTimeMillis();
        try {
            joinPoint.proceed();
        } catch (Throwable ex) {
            Logger.getLogger(TodoServiceInterceptor.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        long duration = System.currentTimeMillis() - start;

        publisher.publishEvent(
                new TodoServiceMethodCalledEvent(
                        method.getName(),
                        duration
                )
        );
        return joinPoint.getThis();
    }

}
