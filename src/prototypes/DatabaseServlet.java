package prototypes;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import java.util.LinkedList;
import java.util.Queue;

public class DatabaseServlet extends HttpServlet{

	private DatabaseConnection databaseConnection = new DatabaseConnection();
	
	
	public DatabaseServlet(){
		
		System.out.println(this.getClass().getSimpleName() + ": created");
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try 
		{
			connectToDatabase();
			returnTableContentAsHTML("employees", response);
			disconnectFromDatabase();
		}
		catch (SQLException | InterruptedException e) 
		{			
			e.printStackTrace();
		}
	}
	
	private void connectToDatabase() throws SQLException{
		
		databaseConnection.connectToDatabase();
	}
	
	private void disconnectFromDatabase() throws SQLException{
		
		databaseConnection.disconnectFromDatabase();
	}
	
	private void returnTableContentAsHTML(String table, HttpServletResponse response) throws SQLException, IOException, InterruptedException {
		
		Queue<String> tableContent = getTableContent(table);
		PrintWriter htmlWriter = response.getWriter();
		writeTableContentToHtmlWriter(tableContent, htmlWriter);
	}
	
	private Queue<String> getTableContent(String table) throws SQLException{

		Queue<String> tableContent = new LinkedList<>();
		
		ResultSet tableContentResultSet = databaseConnection.returnTableContent(table);

		while(tableContentResultSet.next()){
			
			String line = new String();
			
			for (int i = 1; i <= tableContentResultSet.getMetaData().getColumnCount(); i++){
				line = line + tableContentResultSet.getString(i) + ", ";
			}
			
			line = line.trim();
			
			if(line.endsWith(",")){
				line = line.substring(0, line.length()-1);
			}
			tableContent.add(line);
		}
		return tableContent;
	}
	
	private void writeTableContentToHtmlWriter(Queue<String> tableContent, PrintWriter htmlWriter) throws InterruptedException{
		
		String line;

		while(!tableContent.isEmpty()){
				line = tableContent.poll();
				htmlWriter.println(line);
		}
	}
}
