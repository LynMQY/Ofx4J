package client;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict=false)
public class Institution {

	@Element(name="name", required = false)
	private String name;
	@Element(name="fid",required = false)
	private String ID;
	@Element(name="url",required = false)
	private String url;
	@Element(name="org", required = false)
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
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Institution [ID=").append(ID).append(", org=").append(org).append(", url=").append(url)
				.append("]");
		return builder.toString();
	}
	
}