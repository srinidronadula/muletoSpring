package com.abc.muleToJava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLParser {
	
	public HashMap<String, HashMap<String,String>> parseHttpRequest(Document doc)throws Exception {
		HashMap<String, HashMap<String,String>> httpRequestMap = new HashMap<String, HashMap<String,String>>();
		NodeList flowRequest = doc.getElementsByTagName("flow");
			 for (int temp = 0; temp < flowRequest.getLength(); temp++) {
				 Node node = flowRequest.item(temp);
				 if (node.getNodeType() == Node.ELEMENT_NODE) {
					  Element element = (Element) node;
					  String flowName = element.getAttribute("name");
					  System.out.println("Flow Name:"+flowName);
					  //Read Http Request
					  NodeList httpReq = doc.getElementsByTagName("flow").item(temp).getChildNodes();
					  for (int tempReq = 0; tempReq < httpReq.getLength(); tempReq++) {
						  //List<String> flowNameList = new ArrayList<String>();
						  HashMap<String,String> flowChildMap = new HashMap<String,String>();
						  String flowChildname ="";
						  Node tempReqNode = httpReq.item(tempReq);
				            if (tempReqNode.getNodeType() == Node.ELEMENT_NODE) {
				            	Element tempReqNodeElement = (Element) tempReqNode;
				            	  flowChildname = tempReqNodeElement.getAttribute("doc:name");
				            	 String configref = tempReqNodeElement.getAttribute("config-ref");
				                 String path = tempReqNodeElement.getAttribute("path");
				                 String method = tempReqNodeElement.getAttribute("method");
				                 System.out.println("flowChildname:"+flowChildname);
				                 //flowNameList.add(flowChildname);
				                 if(flowChildname.equalsIgnoreCase("Request")) {
				                 System.out.println("method:"+method);
				                 flowChildMap.put("method", method);
				                 }
				                 System.out.println("path:"+path);
				                 flowChildMap.put("path", path);
				                 if(flowChildname.equalsIgnoreCase("Request")) {
				                	 String httpbody = doc.getElementsByTagName("http:request").item(temp).getTextContent().trim();
					                 System.out.println("httpbody:"+httpbody);
					                 flowChildMap.put("httpbody", httpbody);
				                 }
				                 System.out.println("configref:"+configref);
				                 
				                 NodeList requestConfiglist = doc.getElementsByTagName("http:request-config");
				                 for (int temp1 = 0; temp1 < requestConfiglist.getLength(); temp1++) {
				   	              Node node1 = requestConfiglist.item(temp1);
				   	              if (node1.getNodeType() == Node.ELEMENT_NODE) {
				   	                  Element element1 = (Element) node1;
				   	                  String name11 = element1.getAttribute("name");
				   	                  String basePath = element1.getAttribute("basePath");
				   	                  System.out.println(configref+":"+name11);
				   	                  if(configref.equalsIgnoreCase(name11)){
				   	                	 System.out.println("basePath: "+basePath);
				   	                	flowChildMap.put("basePath", basePath);
				   	                	 
				   	                	NodeList candidates = node1.getChildNodes();
				   	                	for (int temp11 = 0; temp11 < candidates.getLength(); temp11++) {
							   	              Node node11 = candidates.item(temp11);
							   	              if (node11.getNodeType() == Node.ELEMENT_NODE) {
							   	            	 Element element11 = (Element) node11;
							   	                  String host = element11.getAttribute("host");
							   	                  System.out.println("Host: "+host);
							   	               flowChildMap.put("host", host);
							   	               String port = element11.getAttribute("port");
							   	                  System.out.println("port: "+port);
							   	               flowChildMap.put("port", port);
							   	            httpRequestMap.put(flowChildname.concat(Integer.toString(temp1)), flowChildMap);  

							   	              }
							   	          
							   	              }
				   	  	              }
				   	              }
				                 }
				                 //read listener properties
				                 NodeList configRef = doc.getElementsByTagName("http:listener-config");
								  for (int tmpconfigRef = 0; tmpconfigRef < configRef.getLength(); tmpconfigRef++) {
									  Node tmpconfigRefNode = configRef.item(tmpconfigRef);
										 if (tmpconfigRefNode.getNodeType() == Node.ELEMENT_NODE) {
											  Element tmpconfigRefelement = (Element) tmpconfigRefNode;
											  String tmpconfigRefname = tmpconfigRefelement.getAttribute("name");
											  if(configref.equalsIgnoreCase(tmpconfigRefname)) {
												  System.out.println("tmpconfigRefname: "+tmpconfigRefname);
												  NodeList listnerList = tmpconfigRefNode.getChildNodes();
												  for (int i = 0; i < listnerList.getLength(); i++) {
													  Node listnerListChldNode = listnerList.item(i);
													  if (listnerListChldNode.getNodeType() == Node.ELEMENT_NODE) {
										   	            	 Element element11 = (Element) listnerListChldNode;
										   	                  String host = element11.getAttribute("host");
										   	                  System.out.println("Listener Host: "+host);
										   	               flowChildMap.put("host", host);
										   	               String port = element11.getAttribute("port");
										   	                  System.out.println("Listener port: "+port);
										   	               flowChildMap.put("port", port);
										   	            httpRequestMap.put(flowChildname.concat(Integer.toString(tmpconfigRef)), flowChildMap); 

										   	              }
													  
													
												}
											  
											  }
										 }
										  
									  
								  }
				                 
				                 System.out.println("=============");
				                 
				            }
					  }
				 }
				
			
		}
		
		return httpRequestMap;
		
	}
	
	public HashMap<String, HashMap<String,String>> parseTransform(Document doc)throws Exception {
		HashMap<String, HashMap<String,String>> httpRequestMap = new HashMap<String, HashMap<String,String>>();
		NodeList flowRequest = doc.getElementsByTagName("flow");
			 for (int temp = 0; temp < flowRequest.getLength(); temp++) {
				 Node node = flowRequest.item(temp);
				 if (node.getNodeType() == Node.ELEMENT_NODE) {
					  Element element = (Element) node;
					  String flowName = element.getAttribute("name");
					  System.out.println("Flow Name:"+flowName);
					  //Read Http Request
					  NodeList httpReq = doc.getElementsByTagName("flow").item(temp).getChildNodes();
					  for (int tempReq = 0; tempReq < httpReq.getLength(); tempReq++) {
						  //List<String> flowNameList = new ArrayList<String>();
						  HashMap<String,String> flowChildMap = new HashMap<String,String>();
						  String flowChildname ="";
						  Node tempReqNode = httpReq.item(tempReq);
				            if (tempReqNode.getNodeType() == Node.ELEMENT_NODE) {
				            	Element tempReqNodeElement = (Element) tempReqNode;
				            	  flowChildname = tempReqNodeElement.getAttribute("doc:name");
				            	 String configref = tempReqNodeElement.getAttribute("config-ref");
				                 String path = tempReqNodeElement.getAttribute("path");
				                 String method = tempReqNodeElement.getAttribute("allowedMethods");
				                 System.out.println("flowChildname:"+flowChildname);
				                 
				                 System.out.println("method:"+method);
				                 flowChildMap.put("method", method);
				                 
				                 System.out.println("path:"+path);
				                 flowChildMap.put("path", path);
				                 //if(flowChildname.equalsIgnoreCase("Transform Message")) {
				                	 String httpbody = doc.getElementsByTagName("ee:message").item(temp).getTextContent().trim();
					                 System.out.println("httpbody::"+httpbody);
					                 flowChildMap.put("httpbody", httpbody);
					                 //httpRequestMap.put(httpbody, flowChildMap)
				                 //}
				                 System.out.println("configref:"+configref);
				                 
				                
				                 //read listener properties
				                 NodeList configRef = doc.getElementsByTagName("http:listener-config");
								  for (int tmpconfigRef = 0; tmpconfigRef < configRef.getLength(); tmpconfigRef++) {
									  Node tmpconfigRefNode = configRef.item(tmpconfigRef);
										 if (tmpconfigRefNode.getNodeType() == Node.ELEMENT_NODE) {
											  Element tmpconfigRefelement = (Element) tmpconfigRefNode;
											  String tmpconfigRefname = tmpconfigRefelement.getAttribute("name");
											  if(configref.equalsIgnoreCase(tmpconfigRefname)) {
												  System.out.println("tmpconfigRefname: "+tmpconfigRefname);
												  NodeList listnerList = tmpconfigRefNode.getChildNodes();
												  for (int i = 0; i < listnerList.getLength(); i++) {
													  Node listnerListChldNode = listnerList.item(i);
													  if (listnerListChldNode.getNodeType() == Node.ELEMENT_NODE) {
										   	            	 Element element11 = (Element) listnerListChldNode;
										   	                  String host = element11.getAttribute("host");
										   	                  System.out.println("Listener Host: "+host);
										   	               flowChildMap.put("host", host);
										   	               String port = element11.getAttribute("port");
										   	                  System.out.println("Listener port: "+port);
										   	               flowChildMap.put("port", port);
										   	               httpRequestMap.put(flowChildname.concat(Integer.toString(tmpconfigRef)), flowChildMap); 

										   	              }
													  
													
												}
											  
											  }
										 }
										  
									  
								  }
				                 
				                 System.out.println("=============");
				                 
				            }
					  }
				 }
				
			
		}
		
		return httpRequestMap;
		
	}

}
