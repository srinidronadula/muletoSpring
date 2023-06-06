package com.abc.muleToJava;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MainProcess {

	public static void main(String[] args) throws SAXException, IOException {
		// TODO Auto-generated method stub
		
		//read xml file from mule project folder
		//check if mule xml file available or not
		Constants cons= new Constants();
		FileRead fileRead = new FileRead();
		XMLParser  xmlparser = new XMLParser();
		List muleFile = null;
		String muleFileContent="";
		try {
			muleFile = fileRead.getFileName(cons.mule_dir+cons.mule_xml_fileLocation);
			System.out.println("muleFile:"+muleFile.get(0));
			muleFileContent = fileRead.readFromInputStreaString(muleFile.get(0).toString());
			//System.out.println(muleFileContent);
			//read mule xml file
		    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		    DocumentBuilder db = dbf.newDocumentBuilder();
	          System.out.println("Mule FIle Name:"+muleFile.get(0).toString());
	          Document doc = db.parse(new File(muleFile.get(0).toString()));
	          doc.getDocumentElement().normalize();

	          System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
	          System.out.println("------");
	          //Parsing http:Request
	         if(muleFileContent.contains("http:request")){ 
	        	 System.out.println("Request Type found is: http:request");
	        	 HashMap<String, HashMap<String,String>> httprequestMap = xmlparser.parseHttpRequest(doc);
	        	 for (Entry<String, HashMap<String, String>> entry : httprequestMap.entrySet()) {
	        		    System.out.println(entry.getKey() + ":" + entry.getValue().toString());
	        		}
	        	 //now create spring project by adding controller etc...
	        		 //SpringCodeGenerate spring = new SpringCodeGenerate();
	        		 //spring.createSpringProject(httprequestMap,"Request");
	        		 
	        		 System.out.println("Spring project is created sucessfully, location is: "+System.getProperty("user.dir")+
	        				 Constants.springroot);
	         }//http:request
	         if(muleFileContent.contains("Transform Message")){ 
	        	 System.out.println("Request Type found is: Transform Message");
	        	 HashMap<String, HashMap<String,String>> httprequestMap = xmlparser.parseTransform(doc);
	        	 for (Entry<String, HashMap<String, String>> entry : httprequestMap.entrySet()) {
	        		    System.out.println(entry.getKey() + ":" + entry.getValue().toString());
	        		}
	        	 //now create spring project by adding controller etc...
	        		 SpringCodeGenerate spring = new SpringCodeGenerate();
	        		 spring.createSpringProject(httprequestMap,"Listener");
	        		 
	        		 System.out.println("Spring project is created sucessfully, location is: "+System.getProperty("user.dir")+
	        				 Constants.springroot);
	         }//http:request
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
