package com.pensio.api;

import com.pensio.api.generated.APIResponse;
import com.pensio.api.generated.Terminal;
import java.util.List;

public class GetMerchantTerminals {

	// This is the URL to connect to your gateway instance. If you are in doubt
	// contact support.
	// For test, use: testgateway.altapaysecure.com
	private static String pgwUrl = "https://testgateway.altapaysecure.com/";
	private static String user = "test user";
	private static String password = "test pass";

	public static void main(String[] args) throws Throwable {
		merchantTerminals();
	}

	public static List<Terminal> merchantTerminals() throws PensioAPIException {
		PensioMerchantAPI api = new PensioMerchantAPI(pgwUrl, user, password);

		try {
			APIResponse response = api.getTerminals();
			List<Terminal> terminals = response.getBody().getTerminals().getTerminal();

			for (Terminal terminal : terminals) {

				List<String> currencies = terminal.getCurrencies().getCurrency();
				List<String> natures = terminal.getNatures().getNature();
				List<String> methods = terminal.getMethods().getMethod();

				System.out.println("------------------------------------");
				System.out.println("Title: " + terminal.getTitle());
				System.out.println("Country: " + terminal.getCountry());
				System.out.println("Currencies: " + currencies.toString());
				System.out.println("Natures: " + natures.toString());
				System.out.println("Methods: " + methods.toString());

			}

			return terminals;

		} catch (PensioAPIException e) {
			e.printStackTrace();
		}
		return null;
	}
}
