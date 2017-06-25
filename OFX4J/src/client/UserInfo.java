package client;

import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;


public class UserInfo {
	private String username;
	private String password;
	private Institution institution;
	private List<Account> listOfAcc;
	private String description;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Institution getInstitution() {
		return institution;
	}
	public void setInstitution(Institution institution) {
		this.institution = institution;
	}
	public List<Account> getListOfAcc() {
		return listOfAcc;
	}
	public void setListOfAcc(List<Account> list) {
		this.listOfAcc = list;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Map toMap() {//lowercase every letter for every properties		
		return new ObjectMapper().convertValue(this, Map.class);		
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserInfo [");
		if (username != null)
			builder.append("username=").append(username).append(", ");
		if (password != null)
			builder.append("password=").append(password).append(", ");
		if (institution != null)
			builder.append("institution=").append(institution).append(", ");
		if (listOfAcc != null)
			builder.append("list=").append(listOfAcc).append(", ");
		if (description != null)
			builder.append("description=").append(description);
		builder.append("]");
		return builder.toString();
	}
	
	public static void main(String[] args) {
		UserInfo user1 = new UserInfo();
		user1.setUsername("teset");
		Institution testIns = new Institution();
		testIns.setID("3101");
		testIns.setOrg("Amex");
		user1.setInstitution(testIns);
		System.out.println(user1.toMap());
		
	}
}
