package client;

public class Account {
	private String ID;
	private String institution;//debug
	private String description;
	private String type;//TODO
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Account [ID=").append(ID).append(", institution=").append(institution).append(", description=")
				.append(description).append(", type=").append(type).append("]");
		return builder.toString();
	}
	
	
}
