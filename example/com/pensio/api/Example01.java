package com.pensio.api;

public class Example01 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PensioMerchantAPI api = new PensioMerchantAPI("https://testgateway.pensio.com", "test user", "test pass");

		if(api.login()) {
			System.out.println("We logged in");
		}else {
			System.out.println("We could not log in!");
		}
	}

}
