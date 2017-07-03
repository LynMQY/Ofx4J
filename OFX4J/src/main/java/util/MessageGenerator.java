package util;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import client.UserInfo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class MessageGenerator {
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageGenerator.class);
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


	public static RequestBody SignOnRequestGen(UserInfo user) throws Exception {

		/* Create a data-model */
		// data = root;
		Map<Object, Object> data = user.toMap();
		data.put("hash", UUID.randomUUID().toString().replaceAll("-", ""));
		String datetime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		data.put("currentDtTm", datetime);

		/* Get the template (uses cache internally) */
		Template temp = cfg.getTemplate("SignOn_Template.ftlh");
		// ByteArrayOutputStream out = new ByteArrayOutputStream();
		StringWriter out = new StringWriter();
		// System.out.print(cfg.getTemplateConfigurations());
		/* Merge data-model with template */
		// Path path = Paths.get("output");
		// Writer out = new OutputStreamWriter(Files.newOutputStream(path));
		temp.process(data, out);
		LOGGER.info(out.toString());
		return RequestBody.create(MediaType.parse("application/x-ofx; charset=utf-8"), out.toString());
	}


	// TODO Another input argument for UID if necessary
	public static RequestBody SignUpGen(UserInfo user) throws Exception {

		/* Create a data-model */
		// data = root;

		Map<Object, Object> data = user.toMap();
		String UID = UUID.randomUUID().toString().replaceAll("-", "");
		data.put("hash", UID);
		String datetime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		data.put("currentDtTm", datetime);

		data.put("UID", UID);

		/* Get the template (uses cache internally) */
		Template temp = cfg.getTemplate("SignUp_Template.ftlh");
		// System.out.print(cfg.getTemplateConfigurations());
		/* Merge data-model with template */
//		Path path = Paths.get("output");
//		Writer out = new OutputStreamWriter(Files.newOutputStream(path));
//		temp.process(data, out);
//		out.close();
		StringWriter out = new StringWriter();
		// System.out.print(cfg.getTemplateConfigurations());
		/* Merge data-model with template */
		// Path path = Paths.get("output");
		// Writer out = new OutputStreamWriter(Files.newOutputStream(path));
		temp.process(data, out);
		LOGGER.info(out.toString());
		return RequestBody.create(MediaType.parse("application/x-ofx; charset=utf-8"), out.toString());
	}

}