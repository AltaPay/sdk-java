package com.pensio.api;

import com.pensio.api.generated.APIResponse;
import com.pensio.api.generated.AcquirerTransactionData;
import com.pensio.api.generated.AcquirerTransactionDataEntry;
import com.pensio.api.generated.AcquirerTransactionDataGroup;
import com.pensio.api.generated.Transaction;
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
}
