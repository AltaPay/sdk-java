package com.pensio.api.request;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class AcquirerTransactionDataTest {

    @Test
    public void emptyByDefault() {
        AcquirerTransactionData d = new AcquirerTransactionData();
        assertTrue(d.isEmpty());
        assertTrue(d.getAll().isEmpty());
    }

    @Test
    public void addChainsAndStoresByGroup() {
        AcquirerTransactionData d = new AcquirerTransactionData()
            .add(PassCard.GROUP, PassCard.CREDITCODE,        "32")
            .add(PassCard.GROUP, PassCard.PAYMENTOCCURRENCE, "001");

        assertFalse(d.isEmpty());
        Map<String, String> passcard = d.getAll().get(PassCard.GROUP);
        assertEquals(2, passcard.size());
        assertEquals("32",  passcard.get(PassCard.CREDITCODE));
        assertEquals("001", passcard.get(PassCard.PAYMENTOCCURRENCE));
    }

    @Test
    public void addOverwritesSameKey() {
        AcquirerTransactionData d = new AcquirerTransactionData()
            .add("g", "k", "v1")
            .add("g", "k", "v2");

        assertEquals("v2", d.getAll().get("g").get("k"));
    }
}
