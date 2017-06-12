package com.apssouza.events;

/**
 * To Do service method invoked event
 *
 * @author apssouza
 */
public class TodoServiceMethodInvokedEvent {

    private String methodName;
    private long duration;

    public TodoServiceMethodInvokedEvent(String methodName, long duration) {
        this.methodName = methodName;
        this.duration = duration;
    }

    public TodoServiceMethodInvokedEvent() {
    }

    public String getMethodName() {
        return methodName;
    }

    public long getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "CallEvent{" + "methodName=" + methodName + ", duration=" + duration + '}';
    }

}
