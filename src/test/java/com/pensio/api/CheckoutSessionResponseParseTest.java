package com.pensio.api;

import com.pensio.api.generated.APIResponse;
import com.pensio.api.generated.Session;
import com.pensio.response.CheckoutSessionResponse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CheckoutSessionResponseParseTest {

    private static final String XML_HEADER =
        "<?xml version=\"1.0\"?>" +
        "<APIResponse version=\"20260519\">" +
        "  <Header>" +
        "    <Date>2026-05-19T11:00:00+00:00</Date>" +
        "    <Path>API/createCheckoutSession</Path>" +
        "    <ErrorCode>0</ErrorCode>" +
        "    <ErrorMessage>Success</ErrorMessage>" +
        "  </Header>";

    private final PensioMerchantAPI api = new PensioMerchantAPI("url", "u", "p");

    @Test
    void mapsSessionWithPopulatedSupportedTerminals() throws Exception {
        String xml = XML_HEADER +
            "  <Body>" +
            "    <Session>" +
            "      <Id>sess-1</Id>" +
            "      <Status>CREATED</Status>" +
            "      <SupportedTerminals>" +
            "        <Terminal>terminal-a</Terminal>" +
            "        <Terminal>terminal-b</Terminal>" +
            "      </SupportedTerminals>" +
            "    </Session>" +
            "  </Body>" +
            "</APIResponse>";

        APIResponse parsed = api.parsePostBackXMLParameter(xml);
        Session session = parsed.getBody().getSession();
        CheckoutSessionResponse mapped = PensioMerchantAPI.mapCheckoutSessionResponse(session);

        assertEquals("sess-1", mapped.getSessionId());
        assertNotNull(mapped.getSupportedTerminals());
        assertEquals(List.of("terminal-a", "terminal-b"), mapped.getSupportedTerminals());
    }

    @Test
    void mapsSessionWithEmptySupportedTerminalsElement() throws Exception {
        String xml = XML_HEADER +
            "  <Body>" +
            "    <Session>" +
            "      <Id>sess-2</Id>" +
            "      <Status>CREATED</Status>" +
            "      <SupportedTerminals/>" +
            "    </Session>" +
            "  </Body>" +
            "</APIResponse>";

        APIResponse parsed = api.parsePostBackXMLParameter(xml);
        Session session = parsed.getBody().getSession();
        CheckoutSessionResponse mapped = PensioMerchantAPI.mapCheckoutSessionResponse(session);

        assertNotNull(mapped.getSupportedTerminals());
        assertTrue(mapped.getSupportedTerminals().isEmpty());
    }

    @Test
    void mapsSessionWithoutSupportedTerminalsElement() throws Exception {
        String xml = XML_HEADER +
            "  <Body>" +
            "    <Session>" +
            "      <Id>sess-3</Id>" +
            "      <Status>CREATED</Status>" +
            "    </Session>" +
            "  </Body>" +
            "</APIResponse>";

        APIResponse parsed = api.parsePostBackXMLParameter(xml);
        Session session = parsed.getBody().getSession();
        CheckoutSessionResponse mapped = PensioMerchantAPI.mapCheckoutSessionResponse(session);

        assertNull(mapped.getSupportedTerminals());
    }
}
