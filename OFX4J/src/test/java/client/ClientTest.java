/**
 * 
 */
package client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import util.MessageGenerator;

/**
 * @author kevin
 *
 */
public class ClientTest {

	public MockWebServer server;
	private HttpUrl baseUrl;

	@Before
	public void setUp() throws IOException {
		server = new MockWebServer();
		server.start();
		baseUrl = server.url("/");
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
		Client c = new Client();
		c.setRb(MessageGenerator.SignOnRequestGen(user1));
//		System.out.println(baseUrl.toString());
		c.setUrl(baseUrl);
		MockResponse response = new MockResponse().addHeader("Content-Type", "application/json; charset=utf-8")
				.addHeader("Cache-Control", "no-cache").setBody("{}");
		server.enqueue(response);
		c.run();
		RecordedRequest request = server.takeRequest();
		assertEquals(request.getHeader("User-Agent"),"OFX for Java");
		assertNotNull(request.getBody());
	}

}
