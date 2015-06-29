import com.pensio.api.request.PaymentSource;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PaymentSourceTest
{
	@Test
	public void fromString_existingValue()
	{
		assertEquals(PaymentSource.eCommerce, PaymentSource.fromString("eCommerce"));
	}

	@Test
	public void fromString_nonExistentValue()
	{
		assertEquals(null, PaymentSource.fromString("RudyTheRudeRoot"));
	}
}
