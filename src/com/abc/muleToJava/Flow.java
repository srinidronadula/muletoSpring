package com.abc.muleToJava;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Flow {
	
	public void parseHttpRequest(Document doc) {
		NodeList reQuestlist = doc.getElementsByTagName("http:request");
        for (int temp = 0; temp < reQuestlist.getLength(); temp++) {
            Node node = reQuestlist.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                // get staff's attribute
                String method = element.getAttribute("method");
                String configref = element.getAttribute("config-ref");
                String path = element.getAttribute("path");
                String docId = element.getAttribute("doc:id");
                String httpbody = doc.getElementsByTagName("http:request").item(0).getTextContent().trim();
                System.out.println("method:"+method);
                System.out.println("configref:"+configref);
                System.out.println("path:"+path);
                System.out.println("docId:"+docId);
                //System.out.println(muleFileContent);
                System.out.println("httpbody : " + httpbody);
                NodeList requestConfiglist = doc.getElementsByTagName("http:request-config");
                for (int temp1 = 0; temp1 < requestConfiglist.getLength(); temp1++) {
  	              Node node1 = requestConfiglist.item(temp1);
  	              if (node1.getNodeType() == Node.ELEMENT_NODE) {
  	                  Element element1 = (Element) node1;
  	                  String name = element1.getAttribute("name");
  	                  String basePath = element1.getAttribute("basePath");
  	                if(configref.equalsIgnoreCase(name)){
  	                	 System.out.println("Req Name: "+name);
  	  	                  System.out.println("basePath: "+basePath);
  	  	                  System.out.println(configref+":"+name);
  	  	                  
  	  	                  
  	                }
  	                 
  	                 
  	                
  	                /*  if(configref.equalsIgnoreCase(name)) {
  	                	  String httRequestConfigData = doc.getElementsByTagName("http:request-config").item(0).getTextContent().trim();
  	                	  //doc.getElementsByTagName("http:request-config").item(0).getElementsByTagName("http:request-config");
  	                	  NodeList reQuestlist1 = doc.getElementsByTagName("http:request-config").item(1).getChildNodes();
  	                	  for (int temp2 = 0; temp2 < reQuestlist1.getLength(); temp2++) {
  	                		  Node node2 = reQuestlist1.item(temp2);
  	        	              if (node2.getNodeType() == Node.ELEMENT_NODE) {
  	        	                  Element element2 = (Element) node2;
  	        	                  // get staff's attribute
  	        	                  String host = element2.getAttribute("host");
  	        	                  String port = element2.getAttribute("port");
  	        	                  System.out.println("port: "+port);
  	        	                  
  	        	              }
  	                		  
  	                	  }
  	                	  //cell = doc.getElementsByTagName("docname").item(0); //doesn't work
  	                      //System.out.print(cell.getTextContent() + "\t");
  	                  }*/
  	              }
                }
                
                
            }
            System.out.println("=================");
	}
	}

}
