package command;

import java.io.Console;
import java.util.*;
import client.Institution;
import client.UserInfo;
import util.InstitutionLookUpClient;
import util.ResultInstitution;

public class Cli {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("nihao, please type the name of financial institution to begin");
		String search = sc.nextLine();
		String searchID;
		long tmp = 0;

		List<ResultInstitution> list = InstitutionLookUpClient.institutionLookup(search);
		while(list.size() == 0) {
			System.out.println("No institution found! Plz try again");
			search = sc.nextLine();
			list = InstitutionLookUpClient.institutionLookup(search);
		}
//		list.forEach(fi -> System.out.println(fi.getName()));
		for(int i = 0; i < list.size(); i++) {
			ResultInstitution cur = list.get(i);
			System.out.println(i+": "+ cur.getName());
		}
		do {
			System.out.println("Select the institution");
			tmp = Integer.valueOf(sc.nextLine());
			//sc.nextLine();
			System.out.println(tmp);
		} while(!(tmp>=0 && tmp<list.size()));
		searchID = list.get((int)tmp).getId();
		//System.out.println(searchID);

		Institution tmpIns= InstitutionLookUpClient.institutionLookupID(searchID);
		// Institution get
		
		// get userinfo for signOn
		System.out.println("username:?");
		String username = sc.nextLine();
		System.out.println("password:?");
		String password = sc.nextLine();
		//TODO mask may be needed
		
		Console console = System.console();
		char[] password = console.readPassword("passwords:?");
		System.out.println(new String(password));
		
		
		//System.out.println("username: "+ username);
		//System.out.println("username: "+ password);
		
		UserInfo user1 = new UserInfo();
		user1.setUsername(username);
		//user1.setPassword(password);
		user1.setInstitution(tmpIns);
	
		sc.close();
	}

}
