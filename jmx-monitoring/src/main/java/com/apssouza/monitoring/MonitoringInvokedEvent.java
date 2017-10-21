package com.apssouza.monitoring;

/**
 *
 * @author apssouza
 */
public class MonitoringInvokedEvent {

    private final long duration;
    
    private final String methodName;

    public MonitoringInvokedEvent(String methodName, long duration) {
        this.methodName = methodName;
        this.duration = duration;
    }

    public long getDuration() {
        return duration;
    }

    public String getMethodName() {
        return methodName;
    }

}
