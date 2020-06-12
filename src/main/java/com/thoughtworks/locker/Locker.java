package com.thoughtworks.locker;

import java.util.UUID;

public class Locker {
    private boolean full;

    public String checkIn() {
        if (full) {
            throw new RuntimeException("储物柜已满");
        }
        return UUID.randomUUID().toString();
    }

    public void setFull(boolean full) {
        this.full = full;
    }
}
