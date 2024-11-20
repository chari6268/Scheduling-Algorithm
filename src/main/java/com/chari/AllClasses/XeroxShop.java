package com.chari.AllClasses;

import java.util.LinkedList;
import java.util.Queue;

public class XeroxShop {
    private final int id;
    private final String name;
    private final double processingSpeed; // pages per minute
    private final Queue<Customer> queue;
    private Customer currentCustomer;
    private double totalWaitingTime;
    private int totalCustomersServed;

    public XeroxShop(int id, String name, double processingSpeed) {
        this.id = id;
        this.name = name;
        this.processingSpeed = processingSpeed;
        this.queue = new LinkedList<>();
        this.totalWaitingTime = 0;
        this.totalCustomersServed = 0;
    }
    public double estimateProcessingTime(Customer customer) {
        return customer.getPagesToCopy() / processingSpeed;
    }

    public double getTotalEstimatedWaitTime() {
        return queue.stream()
                .mapToDouble(this::estimateProcessingTime)
                .sum();
    }

    // Getters and queue operations
    public int getId() { return id; }
    public String getName() { return name; }
    public Queue<Customer> getQueue() { return queue; }
    public void addToQueue(Customer customer) { queue.offer(customer); }
    public int getQueueLength() { return queue.size(); }
}
