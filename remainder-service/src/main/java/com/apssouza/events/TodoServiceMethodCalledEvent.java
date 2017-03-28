package com.apssouza.events;

/**
 *
 * @author apssouza
 */
public class TodoServiceMethodCalledEvent {

    private String methodName;
    private long duration;

    public TodoServiceMethodCalledEvent(String methodName, long duration) {
        this.methodName = methodName;
        this.duration = duration;
    }

    public TodoServiceMethodCalledEvent() {
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
