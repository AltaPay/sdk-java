package com.pensio.api;

import com.pensio.api.PensioMerchantAPI;
import com.pensio.api.generated.APIResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.io.IOException;
import java.nio.file.Paths;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;

/**
 * Created by emerson on 7/5/17.
 */
public class MerchantApi_ParsePostBackXmlResponseTests
{
	private PensioMerchantAPI api;

	@Before
	public void setUp() throws Exception
	{
		api = new PensioMerchantAPI("url", "username", "password");
	}

	@Test
	public void ParsePostBackXmlResponse_ReadCardHolderMessageMustBeShown() throws PensioAPIException, IOException
	{
		String xmlResponse = readFile("test/unit/com/pensio/api/txt/CardHolderMessageMustBeShownFalse.xml");
		APIResponse response = api.parsePostBackXMLParameter(xmlResponse);
		Assert.assertFalse(response.getBody().isCardHolderMessageMustBeShown());

		xmlResponse = readFile("test/unit/com/pensio/api/txt/CardHolderMessageMustBeShownTrue.xml");
		response = api.parsePostBackXMLParameter(xmlResponse);
		Assert.assertTrue(response.getBody().isCardHolderMessageMustBeShown());
	}

	private String readFile(String file) throws IOException
	{
		return new String(readAllBytes(get((file))));
	}

	@Test
	public void ParsePostBackXmlResponse_ReadReasonCode() throws PensioAPIException, IOException
	{
		String xmlResponse = readFile("test/unit/com/pensio/api/txt/ReasonCode.xml");
		APIResponse response = api.parsePostBackXMLParameter(xmlResponse);
		Assert.assertEquals("NONE", response.getBody().getTransactions().getTransaction().get(0).getReasonCode());

	}

	@Test
	public void ParsePostBackXmlResponse_ReadPaymentId() throws PensioAPIException, IOException
	{
		String xmlResponse = readFile("test/unit/com/pensio/api/txt/ReasonCode.xml");
		APIResponse response = api.parsePostBackXMLParameter(xmlResponse);
		Assert.assertEquals("17794956-9bb6-4854-9712-bce5931e6e3a", response.getBody().getTransactions().getTransaction().get(0).getPaymentId());

	}

	@Test
	public void ParsePostBackXmlResponse_ReadPaymentSource() throws PensioAPIException, IOException
	{
		String xmlResponse = readFile("test/unit/com/pensio/api/txt/PaymentSource.xml");
		APIResponse response = api.parsePostBackXMLParameter(xmlResponse);
		Assert.assertEquals("eCommerce", response.getBody().getTransactions().getTransaction().get(0).getPaymentSource());
	}

}
