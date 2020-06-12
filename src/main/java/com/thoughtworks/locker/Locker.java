package com.thoughtworks.locker;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Locker {
    private boolean full;
    private List<String> tickets = new ArrayList<>();

    public String checkIn() {
        if (full) {
            throw new RuntimeException("储物柜已满");
        }
        String ticket = UUID.randomUUID().toString();
        tickets.add(ticket);
        return ticket;
    }

    public void setFull(boolean full) {
        this.full = full;
    }

    public boolean checkOut(String ticket) {
        if (tickets.indexOf(ticket) < 0) {
            throw new RuntimeException("非法票据");
        }
        tickets.remove(ticket);
        return true;
    }
}
