package com.pensio.api;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.codec.binary.Base64;


/**
 * @refactor RefactorUtilsIntoBaseLibrary This should be merged with HttpUtil and go into PensioBaseLibrary.
 */
public class HTTPHelper {
	
	public InputStream doPost(String urlString, Map<String, String> postVars, String username, String password, String sdkVersion) throws IOException {
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
		
		urlConn.setRequestProperty("x-altapay-client-version", sdkVersion);
		urlConn.setRequestProperty("User-Agent", sdkVersion);

		if(username != null)
		{
			String encoded = new String(Base64.encodeBase64((username+":"+password).getBytes()));
			urlConn.setRequestProperty("Authorization", "Basic "+encoded);
		}
		
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
	
	public InputStream doGet(String urlString, Map<String, String> getVars, String username, String password, String sdkVersion) throws IOException {
		
		String query = "";
		boolean isFirst = true;
		for(Entry<String, String> e : getVars.entrySet())
		{
			query += (isFirst ? "?" : "&") + e.getKey() + "=" + URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8.name());
			isFirst = false;
		}
		
		// URL of CGI-Bin script.
		URL url = new URL(urlString + query);

		// URL connection channel.
		URLConnection urlConn = url.openConnection();
	

		// Let the RTS know that we want to do output.
		urlConn.setDoOutput(true);

		// No caching, we want the real thing.
		urlConn.setUseCaches(false);

		urlConn.setRequestProperty("Accept-Charset", StandardCharsets.UTF_8.name());
		urlConn.setRequestProperty("x-altapay-client-version", sdkVersion);
		urlConn.setRequestProperty("User-Agent", sdkVersion);

		if(username != null)
		{
			String encoded = new String(Base64.encodeBase64((username+":"+password).getBytes()));
			urlConn.setRequestProperty("Authorization", "Basic "+encoded);
		}

		// Get response data.
		return new DataInputStream(urlConn.getInputStream());
	}
}
