package command;

import java.nio.file.Files;
import java.util.*;
import java.util.Scanner;

import client.Institution;
import client.UserInfo;
import util.InstitutionLookUpClient;
import util.ResultInstitution;

public class Cli {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("nihao, please type the name of financial institution to begin");
		String search = sc.nextLine();
		String searchID;
		long tmp = 0;

		List<ResultInstitution> list = InstitutionLookUpClient.institutionLookup(search);
		while(list.size() == 0) {
			System.out.println("No institution found! Plz try again");
			search = sc.nextLine();
			//TODO Search
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
		//TODO SearchID
		Institution tmpIns= InstitutionLookUpClient.institutionLookupID(searchID);
		// Institution get
		
		// get userinfo for signOn
		System.out.println("username:?");
		String username = sc.nextLine();
		System.out.println("password:?");
		String password = sc.nextLine();
		//TODO mask may be needed
		
		//System.out.println("username: "+ username);
		//System.out.println("username: "+ password);
		
		UserInfo user1 = new UserInfo();
		user1.setUsername(username);
		user1.setPassword(password);
		user1.setInstitution(tmpIns);
	
		sc.close();
	}

}
