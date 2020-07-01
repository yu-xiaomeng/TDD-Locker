package com.thoughtworks.locker.utils;

import com.thoughtworks.locker.ReportPrint;

import java.util.List;
import java.util.stream.Collectors;

public final class PrintUtil {
    private PrintUtil() {

    }

    public static String print(Object... args) {
        StringBuilder builder = new StringBuilder();
        for (Object obj : args) {
            builder.append(" ");
            builder.append(obj.toString());
        }
        return builder.toString().trim();
    }

    public static <T extends ReportPrint> String printReports(List<T> reportPrints, String tabs) {
        final String tabStr = tabs == null ? "\t" : "\t" + tabs;
        return String.join("\n\n", reportPrints.stream().map(p -> p.print(tabStr)).collect(Collectors.toList()));
    }
}
