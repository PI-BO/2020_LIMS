package model.database.tableModels;

import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSet;
import model.database.dummyDB.DummyResultSetEntry;
import utility.JSON;

import javax.servlet.http.Cookie;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Narwutsch Dominic
 * created on 03.02.2021
 */
public class Sessions extends Model{

	private String date;
    private String mitarbeiterID;
    public static final String COLUMN_PRIMARY_KEY = "session_key";
    public static final String COLUMN_DATE = "creation_date";
    public static final String COLUMN_MITARBEITER_ID = "mitarbeiterID";
    public static final String COLUMN_FOREIGN_KEY = COLUMN_MITARBEITER_ID;
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

    public Sessions(){
		super();
	}
	
    public Sessions(String primaryKey) throws SQLException, ModelNotFoundException {
    	super(primaryKey);
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
	
	@Override
	public JSON toJSON() {

		JSON json = new JSON();
		json.addKeyValue("table", getTable());
		json.addKeyValue("id", getPrimaryKey());
		json.addKeyValue(COLUMN_DATE, getDate());
		json.addKeyValue(COLUMN_MITARBEITER_ID, getMitarbeiterID());
		
		return json;
	}
}
