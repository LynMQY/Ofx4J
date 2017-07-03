package util;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "institutionid")
public class ResultInstitution {
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResultInstitution [name=").append(name).append(", id=").append(id).append("]");
		return builder.toString();
	}

	@Attribute
	private String name;

	@Attribute
	private String id;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

}