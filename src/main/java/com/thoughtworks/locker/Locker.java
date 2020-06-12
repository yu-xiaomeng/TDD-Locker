package com.thoughtworks.locker;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Locker {
    private boolean full;
    private List<String> tickets = new ArrayList<>();
    private String lockerIsFull = "储物柜已满";
    private String ticketIsInvalid = "非法票据";

    public String checkIn() {
        if (full) {
            throw new RuntimeException(lockerIsFull);
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
            throw new RuntimeException(ticketIsInvalid);
        }
        tickets.remove(ticket);
        return true;
    }
}
