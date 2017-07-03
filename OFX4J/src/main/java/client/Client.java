/**
 * 
 */
package client;

import java.io.IOException;

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
	private final OkHttpClient client = new OkHttpClient();
	private String url;
	private RequestBody rb;

	public void run() throws Exception {
		Request request = new Request.Builder().url(HttpUrl.parse(url)).header("User-Agent", "OFX for Java")
				.header("Content-Type", "application/x-ofx").header("Accept", "application/x-ofx").post(rb).build();
		Response response = client.newCall(request).execute();
		if (!response.isSuccessful())
			throw new IOException("Unexpected code" + response);
		response.close();
		System.out.println(response.body().toString());
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
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
