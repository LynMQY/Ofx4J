package util;

public class MessageParser {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String response1 = "<OFX><SIGNONMSGSRSV1><SONRS><STATUS><CODE>0<SEVERITY>ERROR<MESSAGE>Please verify your identity within the next 7 days. Using your desktop computer, go to your bank?s website and visit the Secure Message Center for instructions.</STATUS><DTSERVER>20170709164307.211[-4:EDT]<LANGUAGE>ENG<FI><ORG>B1<FID>10898</FI></SONRS></SIGNONMSGSRSV1></OFX>";
		
		System.out.println(SignOnResponse(response1));
	
	
	}
	public static boolean SignOnResponse (String response) {
		int idx0 = response.indexOf("<STATUS><CODE>");
		int idx1 = response.indexOf("<SEVERITY>");
		
		int res = Integer.valueOf(response.substring(idx0+14, idx1));
		System.out.println(res);
		if (res == 0) return true;		
		return false;
	}
	
}
