package com.apssouza.interceptors;

import com.apssouza.events.TodoServiceMethodInvokedEvent;
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
 * Interceptor for methods invoked in the To do service, AOP.
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
     * (..) matches any number of parameters (zero or more) * as the returning
     * type pattern, which matches any return type
     *
     * @param joinPoint
     * @return
     */
    //@Around("execution(* com.apssouza.services.TodoServiceImpl.*(..))")
    /**
     * Disabled the interceptor in detriment of the new monitoring module
     *
     * @param joinPoint
     * @return
     */
    public Object logCall(ProceedingJoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        long start = System.currentTimeMillis();
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable ex) {
            Logger.getLogger(TodoServiceInterceptor.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        long duration = System.currentTimeMillis() - start;

        publisher.publishEvent(new TodoServiceMethodInvokedEvent(
                method.getName(),
                duration
        )
        );
        return result;
    }

}
