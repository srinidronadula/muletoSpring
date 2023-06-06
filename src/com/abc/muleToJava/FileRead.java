package com.abc.muleToJava;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileRead {
	
	public String readFromInputStreaString(String fileName)
			   {
		StringBuffer str = new StringBuffer();
		try {
		      File myObj = new File(fileName);
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		       		     str.append(data).append("\n");
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return str.toString();
			}
	
	public List getFileName(String dirpath) throws Exception {
		File directoryPath = new File(dirpath);
		File filesList[] = directoryPath.listFiles();
		List FileNames = new ArrayList<>();
		System.out.println(filesList.length);
		if(filesList.length < 1) {
			System.out.println("Unable to locate file @ "+directoryPath.getAbsolutePath() );
			System.out.println("Please check and retry" );
			throw new Exception("Unable to locate file @ "+directoryPath.getAbsolutePath()+
					"Please check and retr");
		}else {
			 for(File file : filesList) {
		         System.out.println("File name: "+file.getAbsolutePath());
		         FileNames.add(file.getAbsolutePath());
			 }
			
		}
		return FileNames;
	}

}
