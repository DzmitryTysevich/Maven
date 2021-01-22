package com.test.maven.service;

public class TimeService {
    public static final TimeService SINGLETON = new TimeService();

    private TimeService() {
    }

    public long getExecutionTime(long start, long end) {
        return end - start;
    }
}