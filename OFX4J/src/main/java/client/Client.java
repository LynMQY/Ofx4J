/**
 * 
 */
package client;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author kevin
 *
 */
public class Client {
	private static final Logger logger = LoggerFactory.getLogger(Client.class);
	private OkHttpClient client = new OkHttpClient();
	private HttpUrl url;
	private RequestBody rb;
	
	public String SignOnResponse = null;//change to Response for general purpose

	public void run() throws Exception {
		Request request = new Request.Builder().url(url).header("User-Agent", "OFX for Java")
				.header("Content-Type", "application/x-ofx").header("Accept", "application/x-ofx").post(rb).build();
		try (Response response = client.newCall(request).execute();) {
			if (!response.isSuccessful())
				throw new IOException("Unexpected code" + response);
			SignOnResponse = response.body().string();
			logger.debug(SignOnResponse);
			
		}
	}

	/**
	 * @return the url
	 */
	public HttpUrl getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(HttpUrl url) {
		this.url = url;
	}

	/**
	 * @param rb
	 *            the rb to set
	 */
	public void setRb(RequestBody rb) {
		this.rb = rb;
	}
}
