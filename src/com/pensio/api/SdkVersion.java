package com.pensio.api;

import java.util.Scanner;

public class SdkVersion
{
	public static String current()
	{
		@SuppressWarnings("resource")
		String text = new Scanner(SdkVersion.class.getResourceAsStream("/META-INF/sdk.version"), "UTF-8").useDelimiter("\\A").next();
		return text;
	}
}
