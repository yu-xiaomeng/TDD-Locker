package com.thoughtworks.locker;

import java.util.UUID;

public class Bag {
    private String bagId;

    public Bag() {
        this.bagId = UUID.randomUUID().toString();
    }

    public String getBagId() {
        return bagId;
    }
}
