package command;

import java.util.*;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import client.*;
import okhttp3.HttpUrl;
import util.InstitutionLookUpClient;
import util.MessageGenerator;
import util.PasswordWindow;
import util.ResultInstitution;

public class Cli {
	public static UserInfo user1 = new UserInfo();

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("nihao, please type the name of financial institution to begin");
		String search = sc.nextLine();
		String searchID;
		long tmp = 0;

		List<ResultInstitution> list = InstitutionLookUpClient.institutionLookup(search);
		while (list.size() == 0) {
			System.out.println("No institution found! Plz try again");
			search = sc.nextLine();
			list = InstitutionLookUpClient.institutionLookup(search);
		}
		// list.forEach(fi -> System.out.println(fi.getName()));
		for (int i = 0; i < list.size(); i++) {
			ResultInstitution cur = list.get(i);
			System.out.println(i + ": " + cur.getName());
		}
		do {
			System.out.println("Select the institution");
			tmp = Integer.valueOf(sc.nextLine());
			// sc.nextLine();
			System.out.println(tmp);
		} while (!(tmp >= 0 && tmp < list.size()));
		searchID = list.get((int) tmp).getId();
		// System.out.println(searchID);

		Institution tmpIns = InstitutionLookUpClient.institutionLookupID(searchID);
		// Institution got

		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeAndWait(new Runnable() {
			public void run() {
				// Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				PasswordWindow.createAndShowGUI();
			}
		});

		// TODO mask may be needed

		// Console
		// Console console = System.console();
		// //char[] password = console.readPassword("passwords:?");
		// System.out.println(new String(password));

		user1.setInstitution(tmpIns);
		synchronized (user1) {
			user1.wait();
		}
//		System.out.println(user1);


		// Client
		Client client = new Client();
		client.setUrl(HttpUrl.parse(user1.getInstitution().getUrl()));
		client.setRb(MessageGenerator.SignOnRequestGen(user1));
		client.run();
		//System.out.println(user1);

		sc.close();
	}

}
