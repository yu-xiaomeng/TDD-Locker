package com.thoughtworks.locker;

import java.util.UUID;

public class Locker {

    public String checkIn() {
        return UUID.randomUUID().toString();
    }
}
