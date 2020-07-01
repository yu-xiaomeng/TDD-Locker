package com.thoughtworks.locker.utils;

import java.util.List;

public final class CollectionUtil {
    private  CollectionUtil() {

    }

    public static boolean isNullOrEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }
}
