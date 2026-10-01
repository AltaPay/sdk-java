package com.pensio.api.request;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AcquirerTransactionDataTest {

    @Test
    void emptyByDefault() {
        AcquirerTransactionData d = new AcquirerTransactionData();
        assertTrue(d.isEmpty());
        assertTrue(d.getAll().isEmpty());
    }

    @Test
    void addChainsAndStoresByGroup() {
        AcquirerTransactionData d = new AcquirerTransactionData()
            .add(PassCard.GROUP, PassCard.CREDITCODE,        "32")
            .add(PassCard.GROUP, PassCard.PAYMENTOCCURRENCE, "001");

        assertFalse(d.isEmpty());
        Map<String, List<String>> passcard = d.getAll().get(PassCard.GROUP);
        assertEquals(2, passcard.size());
        assertEquals(Collections.singletonList("32"),  passcard.get(PassCard.CREDITCODE));
        assertEquals(Collections.singletonList("001"), passcard.get(PassCard.PAYMENTOCCURRENCE));
    }

    @Test
    void addAppendsSameKeyInsteadOfOverwriting() {
        AcquirerTransactionData d = new AcquirerTransactionData()
            .add("g", "k", "v1")
            .add("g", "k", "v2");

        assertEquals(Arrays.asList("v1", "v2"), d.getAll().get("g").get("k"),
            "a merchant may send the same key twice; the second must not replace the first");
    }

    @Test
    void addPreservesGroupAndKeyInsertionOrder() {
        AcquirerTransactionData d = new AcquirerTransactionData()
            .add("metadata", "contractNumber", "CRF_A")
            .add("metadata", "contractNumber", "CRF_B")
            .add("metadata", "department",     "D9");

        assertEquals(Arrays.asList("contractNumber", "department"),
            new ArrayList<String>(d.getAll().get("metadata").keySet()));
        assertEquals(Arrays.asList("CRF_A", "CRF_B"),
            d.getAll().get("metadata").get("contractNumber"));
    }
}
