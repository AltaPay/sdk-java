package com.pensio.api;

import com.pensio.api.generated.APIResponse;
import com.pensio.api.request.CaptureReservationRequest;
import com.pensio.api.request.OrderLine;
import com.pensio.api.request.UpdateOrderRequest;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by emerson on 6/28/17.
 */
public class Example04_Klarna_Capture_UpdateOrder
{

	private final static String apiUrl = System.getProperty("pensio.TestUrl", "http://gateway.dev.earth.pensio.com/");
	private final static String username = System.getProperty("pensio.TestApiUsername", "shop api");
	private final static String password = System.getProperty("pensio.TestApiPassword", "testpassword");
	private final static PensioMerchantAPI api = new PensioMerchantAPI(apiUrl, username, password);

	public static void main(String args[]) throws PensioAPIException
	{

		String paymentId = "32"; // PUT A PAYMENT ID FROM A PREVIOUSLY CREATED ORDER HERE

		APIResponse captureResult = api.capture(
				new CaptureReservationRequest(paymentId)
		);

		assertEquals("Success", captureResult.getBody().getResult());

		OrderLine ol1 = new OrderLine("description 1", "id 01", -1, 1.1);
		OrderLine ol2 = new OrderLine("new item", "new id", 1, 1.1);

		UpdateOrderRequest req = new UpdateOrderRequest(paymentId, Arrays.asList(ol1, ol2));

		APIResponse result = api.updateOrder(req);

		assertNotNull(result);


	}
}
