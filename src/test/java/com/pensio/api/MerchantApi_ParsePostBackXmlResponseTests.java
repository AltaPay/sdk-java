package com.pensio.api;

import com.pensio.api.generated.APIResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by emerson on 7/5/17.
 */
public class MerchantApi_ParsePostBackXmlResponseTests
{
	private PensioMerchantAPI api;

	@BeforeEach
	public void setUp() throws Exception
	{
		api = new PensioMerchantAPI("url", "username", "password");
	}

	@Test
	public void ParsePostBackXmlResponse_ReadCardHolderMessageMustBeShown() throws PensioAPIException, IOException, URISyntaxException {
		String xmlResponse = readFile("com/pensio/api/txt/CardHolderMessageMustBeShownFalse.xml");
		APIResponse response = api.parsePostBackXMLParameter(xmlResponse);
		Assertions.assertFalse(response.getBody().isCardHolderMessageMustBeShown());

		xmlResponse = readFile("com/pensio/api/txt/CardHolderMessageMustBeShownTrue.xml");
		response = api.parsePostBackXMLParameter(xmlResponse);
		Assertions.assertTrue(response.getBody().isCardHolderMessageMustBeShown());
	}

	private String readFile(String file) throws IOException, URISyntaxException {
		URL resource = getClass().getClassLoader().getResource(file);
		if (resource == null) {
			throw new IllegalArgumentException("file not found!");
		} else {
			return Files.readString(Path.of(resource.toURI()));
		}
	}

	@Test
	public void ParsePostBackXmlResponse_ReadReasonCode() throws PensioAPIException, IOException, URISyntaxException {
		String xmlResponse = readFile("com/pensio/api/txt/ReasonCode.xml");
		APIResponse response = api.parsePostBackXMLParameter(xmlResponse);
		Assertions.assertEquals("NONE", response.getBody().getTransactions().getTransaction().get(0).getReasonCode());

	}

	@Test
	public void ParsePostBackXmlResponse_ReadPaymentId() throws PensioAPIException, IOException, URISyntaxException {
		String xmlResponse = readFile("com/pensio/api/txt/ReasonCode.xml");
		APIResponse response = api.parsePostBackXMLParameter(xmlResponse);
		Assertions.assertEquals("17794956-9bb6-4854-9712-bce5931e6e3a", response.getBody().getTransactions().getTransaction().get(0).getPaymentId());

	}

	@Test
	public void ParsePostBackXmlResponse_ReadPaymentSource() throws PensioAPIException, IOException, URISyntaxException {
		String xmlResponse = readFile("com/pensio/api/txt/PaymentSource.xml");
		APIResponse response = api.parsePostBackXMLParameter(xmlResponse);
		Assertions.assertEquals("eCommerce", response.getBody().getTransactions().getTransaction().get(0).getPaymentSource());
	}

	@Test
	public void ParsePostBackXmlResponse_WrongPaymentSource() throws PensioAPIException, IOException, URISyntaxException {
		String xmlResponse = readFile("com/pensio/api/txt/PaymentSource.xml");
		APIResponse response = api.parsePostBackXMLParameter(xmlResponse);
		Assertions.assertNotSame("eCommerce_without3ds", response.getBody().getTransactions().getTransaction().get(0).getPaymentSource());
	}

	@Test
	public void ParsePostBackXmlResponse_ReadECommerceWithout3dSecurePaymentSource() throws PensioAPIException, IOException, URISyntaxException {
		String xmlResponse = readFile("com/pensio/api/txt/PaymentSourceECommerceWithout3dSecure.xml");
		APIResponse response = api.parsePostBackXMLParameter(xmlResponse);
		Assertions.assertEquals("eCommerce_without3ds", response.getBody().getTransactions().getTransaction().get(0).getPaymentSource());
	}
}
