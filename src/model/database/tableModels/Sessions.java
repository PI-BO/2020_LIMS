package model.database.tableModels;

import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSet;
import model.database.dummyDB.DummyResultSetEntry;

import javax.servlet.http.Cookie;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Narwutsch Dominic
 * created on 03.02.2021
 */
public class Sessions extends Model{
    private String primaryKey;
    private String date;
    private String mitarbeiterID;
    public static final String COLUMN_PRIMARY_KEY = "session_key";
    public static final String COLUMN_DATE = "creation_date";
    public static final String COLUMN_MITARBEITER_ID = "mitarbeiterID";
    public static final String TABLE = "sessions";

    public Sessions(Cookie session, Mitarbeiter mitarbeiter) {
        primaryKey = session.getValue();
        long age = System.currentTimeMillis();
        int cookieAge = session.getMaxAge();
        if (cookieAge > 0)
            age += cookieAge * 1000L;
        date = new Date(age).toString();
        mitarbeiterID = mitarbeiter.getPrimaryKey();
    }

    public Sessions(String id) throws SQLException, ModelNotFoundException{
        this.primaryKey = id;
        database.getModel(this);
    }

    public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {
        if (resultSet.next()) {
            int sessionIdIndex = resultSet.findColumn(COLUMN_PRIMARY_KEY);
            primaryKey = resultSet.getString(sessionIdIndex);
            int sessionDateIndex = resultSet.findColumn(COLUMN_DATE);
            date = resultSet.getString(sessionDateIndex);
            int sessionMitarbeiterIdIndex = resultSet.findColumn(COLUMN_MITARBEITER_ID);
            mitarbeiterID = resultSet.getString(sessionMitarbeiterIdIndex);
        } else {
            throw new ModelNotFoundException("Session nicht gefunden");
        }
    }

    public void save() throws SQLException{
        database.setModel(this);
    }

    @Override
    public String getPrimaryKey() {
        return primaryKey;
    }

    @Override
    public String getPrimaryKeyColumn() {
        return COLUMN_PRIMARY_KEY;
    }

    public String getMitarbeiterID() {
        return mitarbeiterID;
    }

    public String getColumnMitarbeiterId() {
        return COLUMN_MITARBEITER_ID;
    }

    public String getDate() {
        return date;
    }

    public String getColumnDate() {
        return COLUMN_DATE;
    }

    @Override
    public String getTable() {
        return TABLE;
    }

    @Override
    public String getValuesAsSQLString() {
        return "'" + primaryKey + "','" + date + "','" + mitarbeiterID + "'";
    }

    @Override
    public String getRelationSchema() {
        return COLUMN_PRIMARY_KEY + "," + COLUMN_DATE + "," + COLUMN_MITARBEITER_ID;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

	@Override
	public void saveToDatabase() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DummyResultSet returnAsDummyResultSet() {
		DummyResultSet dummyResultSet = new DummyResultSet();
		DummyResultSetEntry dummyResultSetEntry = new DummyResultSetEntry();
		dummyResultSetEntry.addKeyValuePair(COLUMN_PRIMARY_KEY, primaryKey);
		dummyResultSetEntry.addKeyValuePair(COLUMN_MITARBEITER_ID, mitarbeiterID);
		dummyResultSetEntry.addKeyValuePair(COLUMN_DATE, date);
		dummyResultSet.addEntry(dummyResultSetEntry);
		
		return dummyResultSet;
	}

	@Override
	public String getForeignKey() {
		return mitarbeiterID;
	}
}
