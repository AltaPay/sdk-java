package com.pensio.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AmountTests.class,
        PensioMerchantApiUnitTests.class,
        MerchantApi_ParsePostBackXmlResponseTests.class
})
public class SuiteAltaPayTest {
}