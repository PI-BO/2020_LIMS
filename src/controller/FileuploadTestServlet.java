package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.websocket.Decoder.BinaryStream;

@WebServlet(FileuploadTestServlet.ROUTE)
@MultipartConfig
public class FileuploadTestServlet extends HttpServlet {

	private static final long serialVersionUID = -9177006959699309113L;

	public static final String ROUTE = "/fileupload-test";

	String filePath = "C:\\Users\\Leberwurst\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\2020_LIMS";

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {

//		Collection<Part> parts = request.getParts();
		Part parts = request.getPart("file");
		
		System.out.println(parts.getSubmittedFileName());

//		response.setContentType("image/png");  
	    ServletOutputStream out = response.getOutputStream();
	    
	    InputStream in = parts.getInputStream();  
	      
	    BufferedInputStream bin = new BufferedInputStream(in);  
	    BufferedOutputStream bout = new BufferedOutputStream(out);
	    
	    int b = 0;
	    
	    while((b = bin.read()) != -1)  
	    {  
	    	bout.write(b);  
	    }  
	      
	    bin.close();  
	    in.close();  
	    bout.close();  
	    out.close();  
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {

		System.out.println("GET");
	}
}