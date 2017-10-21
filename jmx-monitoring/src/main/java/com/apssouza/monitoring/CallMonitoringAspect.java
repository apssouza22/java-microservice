package com.apssouza.monitoring;

import java.lang.reflect.Method;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.util.StopWatch;

/**
 * Simple aspect that monitors call count and call invocation time. It uses JMX annotations and therefore can be
 * monitored using any JMX console such as the jConsole
 *
 */
@ManagedResource(objectName = "apssouza:type=CallMonitor")
@Aspect
public class CallMonitoringAspect {

    private boolean enabled = true;

    private int callCount = 0;

    private long accumulatedCallTime = 0;
    
    private final ApplicationEventPublisher publisher;
    
    @Autowired
    public CallMonitoringAspect(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @ManagedAttribute
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @ManagedAttribute
    public boolean isEnabled() {
        return enabled;
    }

    @ManagedOperation
    public void reset() {
        this.callCount = 0;
        this.accumulatedCallTime = 0;
    }

    @ManagedAttribute
    public int getCallCount() {
        return callCount;
    }

    @ManagedAttribute
    public long getCallTime() {
        if (this.callCount > 0) {
            return this.accumulatedCallTime / this.callCount;
        } else {
            return 0;
        }
    }


    @Around("@annotation(com.apssouza.monitoring.Monitored)")
    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("callend");
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        if (this.enabled) {
            StopWatch sw = new StopWatch(joinPoint.toShortString());

            sw.start("invoke");
            try {
                return joinPoint.proceed();
            } finally {
                sw.stop();
                synchronized (this) {
                    this.callCount++;
                    this.accumulatedCallTime += sw.getTotalTimeMillis();
                }
                publisher.publishEvent(new MonitoringInvokedEvent(
                        method.getName(),
                        this.accumulatedCallTime
                ));
            }
        } else {
            return joinPoint.proceed();
        }
    }

}
