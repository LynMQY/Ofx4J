package util;

public class MessageParser {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String response1 = "<OFX><SIGNONMSGSRSV1><SONRS><STATUS><CODE>15150<SEVERITY>ERROR<MESSAGE>Please verify your identity within the next 7 days. Using your desktop computer, go to your bank?s website and visit the Secure Message Center for instructions.</STATUS><DTSERVER>20170709164307.211[-4:EDT]<LANGUAGE>ENG<FI><ORG>B1<FID>10898</FI></SONRS></SIGNONMSGSRSV1></OFX>";
		
		System.out.println(SignOnResponse(response1));
	
	
	}
	/*
	 * return a string array
	 * first element is status code, 0 means success
	 * second element is message
	 */
	public static String[] SignOnResponse (String response) {
		String[] res = new String[2];
		int idx0 = response.indexOf("<STATUS><CODE>");
		int idx1 = response.indexOf("<SEVERITY>");
		
		res[0] = response.substring(idx0+14, idx1);
		
		res[1] = response.substring(response.indexOf("<MESSAGE>")+9, response.indexOf("</STATUS>"));
			
		//System.out.println(res[0]+" "+res[1]);
		return res;
	}
	
}

