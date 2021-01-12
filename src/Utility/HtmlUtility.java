package Utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

public class HtmlUtility {

	public static void printHtmlFile(String filepath, HttpServlet servlet, HttpServletResponse response) throws IOException{
		
		String filePath = getHtmlFilePath(filepath, servlet);
    	BufferedReader htmlFile = openHtmlFile(filePath);
    	
    	PrintWriter htmlWriter = response.getWriter();
    	
    	String line;
    	
    	while((line = htmlFile.readLine()) != null){
         	
    		htmlWriter.println(line);
    	}
	}
	
	private static String getHtmlFilePath(String htmlFileName, HttpServlet servlet){
		
		ServletContext context = servlet.getServletContext();        
        String absoluteFilePath = context.getRealPath(htmlFileName);
        
        return absoluteFilePath;
	}
	
	public static BufferedReader openHtmlFile(String filePath) throws FileNotFoundException{
		
        FileReader file = new FileReader(filePath);
		BufferedReader reader = new BufferedReader(file);
		return reader;
	}
	
	public static BufferedReader getHtmlFile(String fileName, HttpServlet servlet) throws FileNotFoundException {
		
		String filePath = getHtmlFilePath(fileName, servlet);
    	
    	BufferedReader htmlFile = openHtmlFile(filePath);
    	
		return htmlFile;
	}
}
