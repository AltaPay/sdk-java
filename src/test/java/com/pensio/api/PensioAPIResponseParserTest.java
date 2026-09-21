package com.pensio.api;

import com.pensio.api.generated.APIResponse;
import com.pensio.api.generated.AcquirerTransactionData;
import com.pensio.api.generated.AcquirerTransactionDataEntry;
import com.pensio.api.generated.AcquirerTransactionDataGroup;
import com.pensio.api.generated.Transaction;
import com.pensio.api.generated.Terminal;
import com.pensio.api.generated.Metadatas;
import com.pensio.api.generated.Metadata;
import com.pensio.api.generated.KeyValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PensioAPIResponseParserTest {

    @Test
    void parsesAcquirerTransactionDataOnTransaction() throws Exception {
        String xml =
            "<?xml version=\"1.0\"?>" +
            "<APIResponse version=\"20170228\">" +
            "  <Header>" +
            "    <Date>2026-05-14T11:50:45+02:00</Date>" +
            "    <Path>API/reservation</Path>" +
            "    <ErrorCode>0</ErrorCode>" +
            "    <ErrorMessage>Success</ErrorMessage>" +
            "  </Header>" +
            "  <Body>" +
            "    <Result>Success</Result>" +
            "    <Transactions>" +
            "      <Transaction>" +
            "        <TransactionId>1</TransactionId>" +
            "        <AcquirerTransactionData>" +
            "          <MerchantIdentifier>mid_123456</MerchantIdentifier>" +
            "          <Group name=\"passcard\">" +
            "            <Entry key=\"creditcode\">32</Entry>" +
            "            <Entry key=\"paymentoccurrence\">001</Entry>" +
            "          </Group>" +
            "        </AcquirerTransactionData>" +
            "      </Transaction>" +
            "    </Transactions>" +
            "  </Body>" +
            "</APIResponse>";

        PensioMerchantAPI api = new PensioMerchantAPI("url", "username", "password");
        APIResponse parsed = api.parsePostBackXMLParameter(xml);

        Transaction t = parsed.getBody().getTransactions().getTransaction().get(0);

        AcquirerTransactionData atd = t.getAcquirerTransactionData();
        assertNotNull(atd);
        assertEquals("mid_123456", atd.getMerchantIdentifier());
        assertEquals(1, atd.getGroup().size());

        AcquirerTransactionDataGroup group = atd.getGroup().get(0);
        assertEquals("passcard", group.getName());
        assertEquals(2, group.getEntry().size());

        AcquirerTransactionDataEntry e0 = group.getEntry().get(0);
        assertEquals("creditcode", e0.getKey());
        assertEquals("32", e0.getValue());

        AcquirerTransactionDataEntry e1 = group.getEntry().get(1);
        assertEquals("paymentoccurrence", e1.getKey());
        assertEquals("001", e1.getValue());
    }

    @Test
    void parsesTerminalMetadatas() throws Exception {
        String xml =
            "<?xml version=\"1.0\"?>" +
            "<APIResponse version=\"3.1.25\">" +
            "  <Header>" +
            "    <Date>2026-09-21T11:50:45+02:00</Date>" +
            "    <Path>API/getTerminals</Path>" +
            "    <ErrorCode>0</ErrorCode>" +
            "    <ErrorMessage>Success</ErrorMessage>" +
            "  </Header>" +
            "  <Body>" +
            "    <Result>Success</Result>" +
            "    <Terminals>" +
            "      <Terminal>" +
            "        <Title>Test Terminal</Title>" +
            "        <ShopName>Test Shop</ShopName>" +
            "        <Country>DK</Country>" +
            "        <Metadatas>" +
            "          <Metadata>" +
            "            <KeyValue key=\"key1\">value1</KeyValue>" +
            "            <KeyValue key=\"key2\">value2</KeyValue>" +
            "          </Metadata>" +
            "        </Metadatas>" +
            "      </Terminal>" +
            "    </Terminals>" +
            "  </Body>" +
            "</APIResponse>";

        PensioMerchantAPI api = new PensioMerchantAPI("url", "username", "password");
        APIResponse parsed = api.parsePostBackXMLParameter(xml);

        assertNotNull(parsed.getBody().getTerminals());
        assertEquals(1, parsed.getBody().getTerminals().getTerminal().size());

        Terminal terminal = parsed.getBody().getTerminals().getTerminal().get(0);
        assertEquals("Test Terminal", terminal.getTitle());
        assertEquals("Test Shop", terminal.getShopName());
        assertEquals("DK", terminal.getCountry());

        Metadatas metadatas = terminal.getMetadatas();
        assertNotNull(metadatas);
        assertEquals(1, metadatas.getMetadata().size());

        Metadata metadata = metadatas.getMetadata().get(0);
        assertEquals(2, metadata.getKeyValue().size());

        KeyValue kv1 = metadata.getKeyValue().get(0);
        assertEquals("key1", kv1.getKey());
        assertEquals("value1", kv1.getValue());

        KeyValue kv2 = metadata.getKeyValue().get(1);
        assertEquals("key2", kv2.getKey());
        assertEquals("value2", kv2.getValue());
    }
}
