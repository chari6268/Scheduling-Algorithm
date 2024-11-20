package com.chari.AllClasses;

import java.time.Instant;

public class Customer {
    private final int id;
    private final Instant arrivalTime;
    private final int pagesToCopy;
    private final int priority; // 0 = high priority, 1 = normal priority

    public Customer(int id, int pagesToCopy, int priority) {
        this.id = id;
        this.arrivalTime = Instant.now();
        this.pagesToCopy = pagesToCopy;
        this.priority = priority;
    }

    public int getId() { return id; }
    public Instant getArrivalTime() { return arrivalTime; }
    public int getPagesToCopy() { return pagesToCopy; }
    public int getPriority() { return priority; }

    @Override
    public String toString() {
        return String.format("Customer%d(pages=%d, priority=%d)",
                id, pagesToCopy, priority);
    }
}
