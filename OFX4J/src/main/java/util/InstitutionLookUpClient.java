package util;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import client.InstitutionList;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class InstitutionLookUpClient {
	private static final Logger logger = LoggerFactory.getLogger(InstitutionLookUpClient.class);
	private static OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(10, TimeUnit.SECONDS)
			.readTimeout(30, TimeUnit.SECONDS).build();
	private static final String BASE_URL = "http://www.ofxhome.com/api.php";

	public static List<ResultInstitution> institutionLookup(String query) {
		HttpUrl qUrl = HttpUrl.parse(BASE_URL).newBuilder().addQueryParameter("search", query).build();
		Request r = new Request.Builder().url(qUrl).get().build();
		try (Response response = client.newCall(r).execute();) {
			if (!response.isSuccessful())
				throw new IOException("Unexpected code" + response);
			Serializer s = new Persister();
			InstitutionList il = s.read(InstitutionList.class, response.body().byteStream());
			il.getInstitutionIds().forEach(in -> logger.info(in.toString()));
			return il.getInstitutionIds();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
