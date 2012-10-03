package com.pensio.api;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;


public class HTTPHelper {
	
	public InputStream doPost(String urlString, Map<String, String> postVars) throws IOException {
		// URL of CGI-Bin script.
		URL url = new URL(urlString);

		// URL connection channel.
		URLConnection urlConn = url.openConnection();
		
		/* Ensure we trust the certificate
		if(urlConn instanceof HttpsURLConnection)
		{
			//System.out.println("This is an HTTPS connection");
			((HttpsURLConnection)urlConn).setSSLSocketFactory(getSSLSocketFactory());
		}
		*/

		// Let the run-time system (RTS) know that we want input.
		urlConn.setDoInput(true);

		// Let the RTS know that we want to do output.
		urlConn.setDoOutput(true);

		// No caching, we want the real thing.
		urlConn.setUseCaches(false);

		// Specify the content type.
		urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		
		// Send POST output.
		DataOutputStream printout = new DataOutputStream(urlConn.getOutputStream());

		String content = "";
		boolean isFirst = true;
		for(Entry<String, String> e : postVars.entrySet())
		{
			content += (isFirst ? "" : "&")+e.getKey()+"="+URLEncoder.encode(e.getValue(), "utf-8");
			isFirst = false;
		}

		printout.writeBytes(content);
		printout.flush();
		printout.close();

		// Get response data.
		return new DataInputStream(urlConn.getInputStream());
	}
}
