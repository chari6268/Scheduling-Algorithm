package com.chari;

import com.chari.AllClasses.*;

import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        XeroxScheduler scheduler = new XeroxScheduler();
        scheduler.addShop(1, "Shop 1", 20);
        scheduler.addShop(2, "Shop 2", 15);
        scheduler.addShop(3, "Shop 3", 25);
        scheduler.addShop(4, "Shop 4", 5);

        List<Customer> customers = Arrays.asList(
                new Customer(1, 50, 1), // 50 pages, normal priority
                new Customer(2, 10, 0), // 10 pages, high priority
                new Customer(3, 30, 1), // 30 pages, normal priority
                new Customer(4, 5, 0),   // 5 pages, high priority
                new Customer(5, 20, 1)  // 20 pages, normal priority
        );

        System.out.println("Testing FCFS Scheduling:");
        scheduler.setAlgorithm(SchedulingAlgorithm.FCFS);
        customers.forEach(scheduler::addCustomer);

        // Print status for each shop
        for (int i = 1; i <= 4; i++) {
            Map<String, Object> status = scheduler.getShopStatus(i);
            System.out.println("\nShop: " + status.get("name"));
            System.out.println("Queue length: " + status.get("queueLength"));
            System.out.println("Queue: " + status.get("queue"));
            System.out.println("Estimated wait time: " +
                    String.format("%.2f minutes", status.get("estimatedWaitTime")));
        }
    }
}