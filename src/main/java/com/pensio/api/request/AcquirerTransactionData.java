package com.pensio.api.request;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AcquirerTransactionData {
    private final Map<String, Map<String, List<String>>> groups = new LinkedHashMap<>();

    public AcquirerTransactionData add(String group, String key, String value) {
        groups.computeIfAbsent(group, g -> new LinkedHashMap<>())
              .computeIfAbsent(key, k -> new ArrayList<>())
              .add(value);
        return this;
    }

    public Map<String, Map<String, List<String>>> getAll() {
        return groups;
    }

    public boolean isEmpty() {
        return groups.isEmpty();
    }
}
