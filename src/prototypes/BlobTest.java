package prototypes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.LinkedList;
import java.util.Queue;

public class BlobTest {
	
	private static Connection connection;
	private static String loginName = "root";
	private static String passwort = "123";
	private static String dbAdresse= "jdbc:mariadb://localhost:3306/test";

	public static void main(String[]args) throws SQLException, IOException{
		connectToDatabase();
		
		//printTableContent(convertTableContent(returnTableContent("employees")));
		
		//insertBlobFrom("C:\\Users\\Leberwurst\\Desktop\\Vue.jpg");
		
		File file = new File("C:\\Users\\Leberwurst\\Desktop\\Vue2.jpg");
		
		TableBilder.getFile(file, connection);
		
		disconnectFromDatabase();
	}
	
	public static void connectToDatabase() throws SQLException{
		
		connection = DriverManager.getConnection(dbAdresse, loginName, passwort);
		System.out.println("connected");
	}
	
	
	
	public static void insertBlobFrom(String fileLocation) throws SQLException, IOException{
		
		String sql = "INSERT INTO bilder (bild) VALUES (?)";
	    PreparedStatement stmt = connection.prepareStatement(sql);

	    File image = new File(fileLocation);
	    FileInputStream   fis = new FileInputStream(image);
	    stmt.setBinaryStream(1, fis, (int) image.length());
	    stmt.execute();

	    connection.commit();
	    fis.close();
	}
	
	public static void disconnectFromDatabase() throws SQLException{
		
		connection.close();
		System.out.println("disconnected");
	}
	
	public static ResultSet returnTableContent(String table) throws SQLException{
		
		Statement statement;
		ResultSet resultSet;		

		String query = "SELECT * FROM " + table + ";";
		
		statement = connection.createStatement();
		resultSet = statement.executeQuery(query);
		System.out.println("table content returned");
		return resultSet;
	}
	
	private static Queue<String> getTableContentAsQueue(ResultSet tableContentResultSet) throws SQLException{

		Queue<String> tableContent = new LinkedList<>();

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
	
	private static void printTableContent(Queue<String> tableContent){
		
		String line;

		while(!tableContent.isEmpty()){
				line = tableContent.poll();
				System.out.println(line);
		}
	}
}
