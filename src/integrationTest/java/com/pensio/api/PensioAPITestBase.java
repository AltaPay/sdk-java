package com.pensio.api;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;

public class PensioAPITestBase {

	protected String getOrderId() throws Throwable
	{
		MessageDigest digest = MessageDigest.getInstance("MD5");
		
		return "Test_"+Hex.encodeHexString(digest.digest(String.valueOf(System.currentTimeMillis()).getBytes()));
	}

	private void printEx(Exception ex)
	{
		System.out.println("---Exception------------");
		System.out.println(ex.toString());
		System.out.println("------------------------");
	}

	protected String whiteLabelName()
	{
		return "Altapay";
	}
	
	protected String getTerminalName()
	{
		return whiteLabelName() + " Test Terminal";
	}
	
	protected String get3DSecureTerminalName()
	{
		return whiteLabelName() + " Test 3DSecure Terminal";
	}

}
