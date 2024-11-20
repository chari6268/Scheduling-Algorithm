package com.chari;

import com.chari.AllClasses.*;
import java.util.*;

enum SchedulingAlgorithm {
    FCFS,   // First Come First Serve
    SJF,    // Shortest Job First
    ROUND_ROBIN,
    PRIORITY
}

public class XeroxScheduler {
    private final Map<Integer, XeroxShop> shops;
    private final List<Customer> waitingCustomers;
    private SchedulingAlgorithm currentAlgorithm;
    private final int timeQuantum; // for Round Robin

    public XeroxScheduler() {
        this.shops = new HashMap<>();
        this.waitingCustomers = new ArrayList<>();
        this.currentAlgorithm = SchedulingAlgorithm.FCFS;
        this.timeQuantum = 5; // 5 minutes for Round Robin
    }

    public void addShop(int id, String name, double processingSpeed) {
        shops.put(id, new XeroxShop(id, name, processingSpeed));
    }

    public void addCustomer(Customer customer) {
        waitingCustomers.add(customer);
        scheduleCustomers();
    }
    private void scheduleCustomers() {
        switch (currentAlgorithm) {
            case FCFS:
                scheduleFCFS();
                break;
            case SJF:
                scheduleSJF();
                break;
            case ROUND_ROBIN:
                scheduleRoundRobin();
                break;
            case PRIORITY:
                schedulePriority();
                break;
        }
    }

    private void scheduleFCFS() {
        while (!waitingCustomers.isEmpty()) {
            XeroxShop selectedShop = shops.values().stream()
                    .min(Comparator.comparingInt(XeroxShop::getQueueLength))
                    .orElseThrow();
            selectedShop.addToQueue(waitingCustomers.remove(0));
        }
    }

    private void scheduleSJF() {
        waitingCustomers.sort(Comparator.comparingInt(Customer::getPagesToCopy));
        while (!waitingCustomers.isEmpty()) {
            XeroxShop selectedShop = shops.values().stream()
                    .min(Comparator.comparingDouble(XeroxShop::getTotalEstimatedWaitTime))
                    .orElseThrow();
            selectedShop.addToQueue(waitingCustomers.remove(0));
        }
    }

    private void scheduleRoundRobin() {
        while (!waitingCustomers.isEmpty()) {
            XeroxShop selectedShop = shops.values().stream()
                    .min(Comparator.comparingInt(XeroxShop::getQueueLength))
                    .orElseThrow();
            selectedShop.addToQueue(waitingCustomers.remove(0));
        }
    }

    private void schedulePriority() {
        waitingCustomers.sort(Comparator
                .comparingInt(Customer::getPriority)
                .thenComparing(Customer::getArrivalTime));
        while (!waitingCustomers.isEmpty()) {
            XeroxShop selectedShop = shops.values().stream()
                    .min(Comparator.comparingInt(shop ->
                            (int) shop.getQueue().stream()
                                    .filter(c -> c.getPriority() == 0)
                                    .count()))
                    .orElseThrow();
            selectedShop.addToQueue(waitingCustomers.remove(0));
        }
    }
    public void setAlgorithm(SchedulingAlgorithm algorithm) {
        this.currentAlgorithm = algorithm;
        // Reschedule all customers with new algorithm
        List<Customer> allCustomers = new ArrayList<>();
        for (XeroxShop shop : shops.values()) {
            allCustomers.addAll(shop.getQueue());
            shop.getQueue().clear();
        }
        allCustomers.addAll(waitingCustomers);
        waitingCustomers.clear();
        waitingCustomers.addAll(allCustomers);
        scheduleCustomers();
    }
    public Map<String, Object> getShopStatus(int shopId) {
        XeroxShop shop = shops.get(shopId);
        if (shop == null) return null;

        Map<String, Object> status = new HashMap<>();
        status.put("name", shop.getName());
        status.put("queueLength", shop.getQueueLength());
        status.put("estimatedWaitTime", shop.getTotalEstimatedWaitTime());
        status.put("queue", new ArrayList<>(shop.getQueue()));
        return status;
    }
}
