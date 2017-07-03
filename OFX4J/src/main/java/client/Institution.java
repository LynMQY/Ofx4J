package client;

import org.simpleframework.xml.Element;

public class Institution {

	@Element(required = false)
	private String name;
	@Element(required = false)
	private String ID;
	@Element(required = false)
	private String url;
	@Element(required = false)
	private String org;
	//private String BrokerID;
	
	public String getID() {
		return ID;
	}
	public void setID(String ID) {
		this.ID = ID;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Institution [ID=").append(ID).append(", org=").append(org).append(", url=").append(url)
				.append("]");
		return builder.toString();
	}
	
}
