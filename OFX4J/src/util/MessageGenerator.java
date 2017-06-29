package util;
import freemarker.template.*;
import java.util.*;

import client.Institution;
import client.UserInfo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageGenerator {
	public static Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
    static {
	try {
		cfg.setDirectoryForTemplateLoading(new File("templates"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    cfg.setDefaultEncoding("UTF-8");
    cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    cfg.setLogTemplateExceptions(false);
    }
    public static void main(String[] args) throws Exception {
    	UserInfo user1 = new UserInfo();
		user1.setUsername("teset");
		user1.setPassword("testpass");
		Institution testIns = new Institution();
		testIns.setID("3101");
		testIns.setOrg("Amex");
		user1.setInstitution(testIns);
		System.out.println(user1.toMap());
		
   	 	/* Create a data-model */
        Map root = user1.toMap();
        String UID = "testUID";
        
    	//SignOnGen(root);
    	//SignUpGen(root, UID);
    }
    public static void SignOnGen(Map data) throws Exception {

    	 /* Create a data-model */       
        //data = root;
        
        data.put("hash", UUID.randomUUID().toString().replaceAll("-", ""));
        String datetime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        data.put("currentDtTm", datetime);
             
        /* Get the template (uses cache internally) */
        Template temp = cfg.getTemplate("SignOn_Template.ftlh");
        //System.out.print(cfg.getTemplateConfigurations());
        /* Merge data-model with template */
        Path path = Paths.get("output");
        Writer out = new OutputStreamWriter(Files.newOutputStream(path));
        temp.process(data, out);
        out.close();
        // Note: Depending on what `out` is, you may need to call `out.close()`.
        // This is usually the case for file output, but not for servlet output.
    }
    
    //TODO Another input argument for UID if necessary
    public static void SignUpGen(Map data, String UID) throws Exception{

    	/* Create a data-model */
        //data = root;
        
        data.put("hash", UUID.randomUUID().toString().replaceAll("-", ""));
        String datetime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        data.put("currentDtTm", datetime);
    	
    	data.put("UID", UID);
        
    	/* Get the template (uses cache internally) */
        Template temp = cfg.getTemplate("SignUp_Template.ftlh");
        //System.out.print(cfg.getTemplateConfigurations());
        /* Merge data-model with template */
        Path path = Paths.get("output");
        Writer out = new OutputStreamWriter(Files.newOutputStream(path));
        temp.process(data, out);
        out.close();
    }
    
}