/**
 * 
 */
package client;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import util.MessageGenerator;

/**
 * @author kevin
 *
 */
public class ClientTest {
	@Rule
	public MockWebServer server = new MockWebServer();
	private static HttpUrl baseUrl;

	@Before
	public void setUp() throws IOException {
		baseUrl = server.url("/test");
	}

	@After
	public void cleanUp() throws IOException {
		server.shutdown();
	}

	/**
	 * Test method for {@link client.Client#run()}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testRun() throws Exception {
		UserInfo user1 = new UserInfo();
		user1.setUsername("teset");
		user1.setPassword("testpass");
		Institution testIns = new Institution();
		testIns.setID("3101");
		testIns.setOrg("Amex");
		user1.setInstitution(testIns);
		// Client c = new Client();
		// c.setRb(MessageGenerator.SignOnRequestGen(user1));
		// System.out.println(baseUrl.toString());
		// c.setUrl(baseUrl.toString());
		// c.run();
		Request r = new Request.Builder().url(baseUrl)
				.post(RequestBody.create(MediaType.parse("application/json"), "{Hello:hhh}")).build();
		new OkHttpClient().newCall(r).execute();
		server.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody("{}"));
		RecordedRequest request1 = server.takeRequest();
		assertNotNull(request1.getBody());
	}

}
