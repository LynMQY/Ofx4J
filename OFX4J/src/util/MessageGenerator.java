package util;
import freemarker.template.*;
import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    	SignOnGen();
    }
    public static void SignOnGen() throws Exception {
    	 /* Create a data-model */
        Map root = new HashMap();
        //TODO
        root.put("hash", "test_hash");
        root.put("currentDtTm", "");
        root.put("username", "");
        root.put("password", "testpass");
        root.put("FI_ORG", "testorg");
        root.put("FI_ID", "testid");

        /* Get the template (uses cache internally) */
        Template temp = cfg.getTemplate("SignOn_Template.ftlh");
        //System.out.print(cfg.getTemplateConfigurations());
        /* Merge data-model with template */
        Path path = Paths.get("output");
        Writer out = new OutputStreamWriter(Files.newOutputStream(path));
        temp.process(root, out);
        out.close();
        // Note: Depending on what `out` is, you may need to call `out.close()`.
        // This is usually the case for file output, but not for servlet output.

    }
    
    
}