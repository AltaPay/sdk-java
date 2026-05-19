package com.pensio.api.request;

import java.util.LinkedHashMap;
import java.util.Map;

public class AcquirerTransactionData {
    private final Map<String, Map<String, String>> groups = new LinkedHashMap<>();

    public AcquirerTransactionData add(String group, String key, String value) {
        groups.computeIfAbsent(group, g -> new LinkedHashMap<>()).put(key, value);
        return this;
    }

    public Map<String, Map<String, String>> getAll() {
        return groups;
    }

    public boolean isEmpty() {
        return groups.isEmpty();
    }
}
