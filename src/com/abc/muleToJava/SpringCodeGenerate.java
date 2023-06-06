package com.abc.muleToJava;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class SpringCodeGenerate {
	
	public void createSpringProject(HashMap<String, HashMap<String,String>> httprequestMap, String type) throws IOException {
		Constants con= new Constants();
		for (Entry<String, HashMap<String, String>> entry : httprequestMap.entrySet()) {
			System.out.println("entered in to controller");
			String path="";
			String method="";
			String basePath="";
			String port="";
			String host="";
			String httpbody="";
			if(entry.getKey().contains(type)){
		    System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		    for (Map.Entry<String, String> entry1 : entry.getValue().entrySet()) {
		        System.out.println(entry1.getKey() + "/" + entry1.getValue());
		        
		        if(entry1.getKey().toString().equalsIgnoreCase("path")) {
		        	path = entry1.getValue();
		        }
		        if(entry1.getKey().toString().equalsIgnoreCase("method")) {
		        	method = entry1.getValue();
		        }
		        if(entry1.getKey().toString().equalsIgnoreCase("basePath")) {
		        	basePath = entry1.getValue();
		        }
		        if(entry1.getKey().toString().equalsIgnoreCase("port")) {
		        	port = entry1.getValue();
		        }
		        if(entry1.getKey().toString().equalsIgnoreCase("host")) {
		        	host = entry1.getValue();
		        }
		        if(entry1.getKey().toString().equalsIgnoreCase("httpbody")) {
		        	String httpbody1 =entry1.getValue();
		        	if(httpbody1.contains("{")) {
		        		httpbody=(String) httpbody1.subSequence(httpbody1.indexOf("{"), httpbody1.indexOf("}")+1);
		        		httpbody = httpbody.replace("\"","\\\"");
		        		httpbody = httpbody.replaceAll("\n", "");
		        	}
		        	else
		        	httpbody = entry1.getValue();
		        }
		        
		    }
	        String content = "package com.example.muletoSping;\r\n"
	        		+ "import org.apache.log4j.Level;\r\n"
	        		+ "import org.apache.log4j.Logger;\r\n"
	        		//+ "import org.springframework.beans.factory.annotation.Autowired;\r\n"
	        		//+ "import org.springframework.context.annotation.Bean;\r\n"
	        		+ "import org.springframework.web.bind.annotation.RequestMapping;\r\n"
	        		+ "import org.springframework.web.bind.annotation.RequestMethod;\r\n"
	        		+ "import org.springframework.web.bind.annotation.RestController;\r\n"
	        		//+ "import org.springframework.web.client.RestTemplate;\r\n"
	        		+ "\r\n"
	        		+ "@RestController\r\n"
	        		+ "class "+Constants.spring_controller_class_name+" {\r\n"
	        		+ "	private static final Logger LOG = Logger.getLogger(ELKController.class.getName());\r\n"
	        		+ "\r\n"
					/*
					 * + "	@Autowired\r\n" + "	RestTemplate restTemplete;\r\n" + "\r\n" +
					 * "	@Bean\r\n" + "	RestTemplate restTemplate() {\r\n" +
					 * "		return new RestTemplate();\r\n" + "	}\r\n"
					 */
	        		+ "\r\n"
	        		+ "	@RequestMapping(value = \""+path+"\", method = RequestMethod."+method+")\r\n"
	        		+ "	public String "+method.toLowerCase()+"() {\r\n"
	        		+ "		String response = \""+httpbody+"\";\r\n"
	        		+ "		LOG.log(Level.INFO, response);\r\n"
	        		+ "\r\n"
	        		+ "		return response ;\r\n"
	        		+ "	}\r\n"
	        		+ "\r\n"
	        		+ "}";
	        //creating controller file
	        createFile(System.getProperty("user.dir")+Constants.spring_controller_class, content);
	        
	        String propertiesContent = "spring.application.name = muleToSpring\r\n"
	        		+ "logging.file="+System.getProperty("user.dir")+Constants.logfile_location+"\r\n"
	        		+ "server.port = "+port+"\r\n"
	        		+ "spring.data.rest.basePath= "+basePath+"";
	        //spring.data.rest.basePath=/api
	      //creating app pros file
	        createFile(System.getProperty("user.dir")+Constants.resourceFile, propertiesContent);
			}
			
		}
		
		
	}
	
	public void createFile(String fileName, String content) 
  		  throws IOException {
  		    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
  		    writer.write(content);
  		    writer.close();
  		}

}
