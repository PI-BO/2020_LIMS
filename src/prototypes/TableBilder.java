package prototypes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableBilder {
    private static final Logger logger = LogManager.getLogger(TableBilder.class.getSimpleName());
    private static String tableName = "bilder";
    private static String columnName = "bild";

    public static void getFile(File file, Connection connection) throws SQLException, IOException {

        String sql = "SELECT " + columnName + " FROM " + tableName;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {

                //File image = new File(fileLocation);

                if (file.exists()) {
                    logger.debug("FILE EXISTS!"); //todo: Meldung erstellen falls etwas ueberschrieben werden soll
                    return;
                }

                try (FileOutputStream fos = new FileOutputStream(file)) {
                    byte[] buffer = new byte[1];
                    InputStream is = resultSet.getBinaryStream(1);

                    while (is.read(buffer) > 0) {
                        fos.write(buffer);
                    }
                }

                logger.debug("FILE CREATED!");
            }
        }
    }

    public static String getTableName() {
        return tableName;
    }

    public static String getColumnName() {
        return columnName;
    }
}
