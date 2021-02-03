package controller.testServlets;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(FileuploadTestServlet.ROUTE)
@MultipartConfig
public class FileuploadTestServlet extends HttpServlet {

	private static final long serialVersionUID = -9177006959699309113L;

	public static final String ROUTE = "/fileupload-test";

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {

		printTestLog(request);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {

		System.out.println(this.getClass() + " doGet");
	}

	private void printTestLog(HttpServletRequest request) throws IOException, ServletException {
		System.out.println(this.getClass() + " doPost");
		
		Collection<Part> parts = request.getParts();
		
		System.out.println("\n/////////// FileuploadTestServlet /////////////\n");
		
		for(Part p : parts){
			System.out.println(p.getSubmittedFileName());
		}
		
		System.out.println("\n///////////////////////////////////////////////\n");
	}
	
	private void returnImagesToClient(){

//		Part part = request.getPart("file");
//		
//		System.out.println(part.getSubmittedFileName());
//
////		response.setContentType("image/png");  
//	    ServletOutputStream out = response.getOutputStream();
//	    
//	    InputStream in = part.getInputStream();  
//	      
//	    BufferedInputStream bin = new BufferedInputStream(in);  
//	    BufferedOutputStream bout = new BufferedOutputStream(out);
//	    
//	    int b = 0;
//	    
//	    while((b = bin.read()) != -1)  
//	    {  
//	    	bout.write(b);  
//	    }  
//	      
//	    bin.close();  
//	    in.close();  
//	    bout.close();  
//	    out.close();  
	}
}