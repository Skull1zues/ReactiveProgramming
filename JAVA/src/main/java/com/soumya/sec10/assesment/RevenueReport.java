package com.soumya.sec10.assesment;

import java.time.LocalTime;
import java.util.Map;

public record RevenueReport(LocalTime time,
                            Map<String, Integer> revenue) {
}
