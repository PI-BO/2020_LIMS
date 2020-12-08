package prototypes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableBilder {
	
	private static String tableName = "bilder";
	private static String columnName = "bild"; 
	
	public static void getFile(File file, Connection connection) throws SQLException, IOException{
		
		String sql = "SELECT " + columnName + " FROM " + tableName;
	    PreparedStatement stmt = connection.prepareStatement(sql);
	    ResultSet resultSet = stmt.executeQuery();

	    while (resultSet.next()) {
	    	
	    	//File image = new File(fileLocation);
	      
			 if(file.exists()){
				 System.out.println("FILE EXISTS!");		//todo: Meldung erstellen falls etwas ueberschrieben werden soll 
				 return;
			 }
			  
			 FileOutputStream fos = new FileOutputStream(file);
			
			 byte[] buffer = new byte[1];
			 InputStream is = resultSet.getBinaryStream(1);

			 while (is.read(buffer) > 0) {
			   fos.write(buffer);
			 }
			 
			 fos.close();
			 
			 System.out.println("FILE CREATED!");
	    }
	}
	
	public static String getTableName(){
		return tableName;
	}
	
	public static String getColumnName(){
		return columnName;
	}
}
