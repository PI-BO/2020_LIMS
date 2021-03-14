package model.database.dummyDB;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DummyResultSet implements ResultSet{
	
	private final static int START_INDEX = -1;
	
	private int resultSetIndex = START_INDEX;
	
	private List<DummyResultSetEntry> resultSetEntryList = new LinkedList<>();
	
	public void addEntry(DummyResultSetEntry dummyResultSetEntry){
		resultSetEntryList.add(dummyResultSetEntry);
	}
	
	public List<DummyResultSetEntry> getEntryList(){
		return resultSetEntryList;
	}

	
	public void addResultSet(DummyResultSet dummyResultSet){
		for(DummyResultSetEntry resultSetEntry : dummyResultSet.getEntryList()){
			resultSetEntryList.add(resultSetEntry);
		}
	}
	
	@Override
	public String getString(int columnIndex) throws SQLException {
		
		DummyResultSetEntry entry = resultSetEntryList.get(resultSetIndex);
		
		if(columnIndex > entry.size()-1) throw new SQLException();

		return entry.get(columnIndex).getValue();
	}

	@Override
	public int getInt(int columnIndex) throws SQLException {
		String value = getString(columnIndex);
		return Integer.parseInt(value);
	}

	@Override
	public String getString(String columnLabel) throws SQLException {
		int columnIndex = findColumn(columnLabel);
		return getString(columnIndex);
	}

	@Override
	public int getInt(String columnLabel) throws SQLException {
		String value = getString(columnLabel);
		return Integer.parseInt(value);
	}

	@Override
	public int findColumn(String key) throws SQLException {
		
		DummyResultSetEntry entry = resultSetEntryList.get(resultSetIndex);
		
		KeyValuePair keyValuePair;
		
		Integer index = null;
		
		for(int i = 0; i < entry.size(); i++){
			keyValuePair = entry.get(i);
			
			if(keyValuePair.getKey().equalsIgnoreCase(key)){
				index = i;
				break;
			}
		}

		if(index == null) throw new SQLException();
		
		return index;
	}
	

	@Override
	public boolean next() throws SQLException {
		
		resultSetIndex++;
		
		if(resultSetIndex < resultSetEntryList.size()) return true;
		
		return false;
	}
	
	@Override
	public boolean isAfterLast() throws SQLException {
		return (resultSetIndex >= resultSetEntryList.size());
	}
	

	@Override
	public boolean isLast() throws SQLException {
		return (resultSetIndex == (resultSetEntryList.size() - 1));
	}

// -------------------------------------------------------------------------------------------------------------------------------
	
	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		System.out.println(this.getClass() + " METHOD unwrap NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		System.out.println(this.getClass() + " METHOD isWrapperFor NOT IMPLEMENTED!");
		return false;
	}

	@Override
	public void close() throws SQLException {
		System.out.println(this.getClass() + " METHOD close NOT IMPLEMENTED!");
		
	}

	@Override
	public boolean wasNull() throws SQLException {
		System.out.println(this.getClass() + " METHOD wasNull NOT IMPLEMENTED!");
		return false;
	}

	@Override
	public boolean getBoolean(int columnIndex) throws SQLException {
		System.out.println(this.getClass() + " METHOD getBoolean NOT IMPLEMENTED!");
		return false;
	}

	@Override
	public byte getByte(int columnIndex) throws SQLException {
		System.out.println(this.getClass() + " METHOD getByte NOT IMPLEMENTED!");
		return 0;
	}

	@Override
	public short getShort(int columnIndex) throws SQLException {
		System.out.println(this.getClass() + " METHOD getSort NOT IMPLEMENTED!");
		return 0;
	}

	@Override
	public long getLong(int columnIndex) throws SQLException {
		System.out.println(this.getClass() + " METHOD getLong NOT IMPLEMENTED!");
		return 0;
	}

	@Override
	public float getFloat(int columnIndex) throws SQLException {
		System.out.println(this.getClass() + " METHOD getFloat NOT IMPLEMENTED!");
		return 0;
	}

	@Override
	public double getDouble(int columnIndex) throws SQLException {
		System.out.println(this.getClass() + " METHOD getDouble NOT IMPLEMENTED!");
		return 0;
	}

	@Override
	public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException {
		System.out.println(this.getClass() + " METHOD getBigDecimal NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public byte[] getBytes(int columnIndex) throws SQLException {
		System.out.println(this.getClass() + " METHOD getBytes NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public Date getDate(int columnIndex) throws SQLException {
		System.out.println(this.getClass() + " METHOD getDate NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public Time getTime(int columnIndex) throws SQLException {
		System.out.println(this.getClass() + " METHOD getTime NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public Timestamp getTimestamp(int columnIndex) throws SQLException {
		System.out.println(this.getClass() + " METHOD getTimestamp NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public InputStream getAsciiStream(int columnIndex) throws SQLException {
		System.out.println(this.getClass() + " METHOD getAsciiStream NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public InputStream getUnicodeStream(int columnIndex) throws SQLException {
		System.out.println(this.getClass() + " METHOD getUnicodeStream NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public InputStream getBinaryStream(int columnIndex) throws SQLException {
		System.out.println(this.getClass() + " METHOD getBinaryStream NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public boolean getBoolean(String columnLabel) throws SQLException {
		System.out.println(this.getClass() + " METHOD getBoolean NOT IMPLEMENTED!");
		return false;
	}

	@Override
	public byte getByte(String columnLabel) throws SQLException {
		System.out.println(this.getClass() + " METHOD getByte NOT IMPLEMENTED!");
		return 0;
	}

	@Override
	public short getShort(String columnLabel) throws SQLException {
		System.out.println(this.getClass() + " METHOD getShort NOT IMPLEMENTED!");
		return 0;
	}

	@Override
	public long getLong(String columnLabel) throws SQLException {
		System.out.println(this.getClass() + " METHOD getLong NOT IMPLEMENTED!");
		return 0;
	}

	@Override
	public float getFloat(String columnLabel) throws SQLException {
		System.out.println(this.getClass() + " METHOD getFloat NOT IMPLEMENTED!");
		return 0;
	}

	@Override
	public double getDouble(String columnLabel) throws SQLException {
		System.out.println(this.getClass() + " METHOD getDouble NOT IMPLEMENTED!");
		return 0;
	}

	@Override
	public BigDecimal getBigDecimal(String columnLabel, int scale) throws SQLException {
		System.out.println(this.getClass() + " METHOD getBigDecimal NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public byte[] getBytes(String columnLabel) throws SQLException {
		System.out.println(this.getClass() + " METHOD getBytes NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public Date getDate(String columnLabel) throws SQLException {
		System.out.println(this.getClass() + " METHOD  getDate NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public Time getTime(String columnLabel) throws SQLException {
		System.out.println(this.getClass() + " METHOD getTime NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public Timestamp getTimestamp(String columnLabel) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public InputStream getAsciiStream(String columnLabel) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public InputStream getUnicodeStream(String columnLabel) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public InputStream getBinaryStream(String columnLabel) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public void clearWarnings() throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public String getCursorName() throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public ResultSetMetaData getMetaData() throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public Object getObject(int columnIndex) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public Object getObject(String columnLabel) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public Reader getCharacterStream(int columnIndex) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public Reader getCharacterStream(String columnLabel) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public BigDecimal getBigDecimal(String columnLabel) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public boolean isBeforeFirst() throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return false;
	}

	@Override
	public boolean isFirst() throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return false;
	}

	@Override
	public void beforeFirst() throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void afterLast() throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public boolean first() throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return false;
	}

	@Override
	public boolean last() throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return false;
	}

	@Override
	public int getRow() throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return 0;
	}

	@Override
	public boolean absolute(int row) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return false;
	}

	@Override
	public boolean relative(int rows) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return false;
	}

	@Override
	public boolean previous() throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return false;
	}

	@Override
	public void setFetchDirection(int direction) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public int getFetchDirection() throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return 0;
	}

	@Override
	public void setFetchSize(int rows) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public int getFetchSize() throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return 0;
	}

	@Override
	public int getType() throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return 0;
	}

	@Override
	public int getConcurrency() throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return 0;
	}

	@Override
	public boolean rowUpdated() throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return false;
	}

	@Override
	public boolean rowInserted() throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return false;
	}

	@Override
	public boolean rowDeleted() throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return false;
	}

	@Override
	public void updateNull(int columnIndex) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateBoolean(int columnIndex, boolean x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateByte(int columnIndex, byte x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateShort(int columnIndex, short x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateInt(int columnIndex, int x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateLong(int columnIndex, long x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateFloat(int columnIndex, float x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateDouble(int columnIndex, double x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateBigDecimal(int columnIndex, BigDecimal x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateString(int columnIndex, String x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateBytes(int columnIndex, byte[] x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateDate(int columnIndex, Date x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateTime(int columnIndex, Time x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateTimestamp(int columnIndex, Timestamp x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateAsciiStream(int columnIndex, InputStream x, int length) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateBinaryStream(int columnIndex, InputStream x, int length) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateCharacterStream(int columnIndex, Reader x, int length) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateObject(int columnIndex, Object x, int scaleOrLength) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateObject(int columnIndex, Object x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateNull(String columnLabel) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateBoolean(String columnLabel, boolean x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateByte(String columnLabel, byte x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateShort(String columnLabel, short x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateInt(String columnLabel, int x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateLong(String columnLabel, long x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateFloat(String columnLabel, float x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateDouble(String columnLabel, double x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateBigDecimal(String columnLabel, BigDecimal x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateString(String columnLabel, String x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateBytes(String columnLabel, byte[] x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateDate(String columnLabel, Date x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateTime(String columnLabel, Time x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateTimestamp(String columnLabel, Timestamp x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateAsciiStream(String columnLabel, InputStream x, int length) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateBinaryStream(String columnLabel, InputStream x, int length) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateCharacterStream(String columnLabel, Reader reader, int length) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateObject(String columnLabel, Object x, int scaleOrLength) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateObject(String columnLabel, Object x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void insertRow() throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateRow() throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void deleteRow() throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void refreshRow() throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void cancelRowUpdates() throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void moveToInsertRow() throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void moveToCurrentRow() throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public Statement getStatement() throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public Object getObject(int columnIndex, Map<String, Class<?>> map) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public Ref getRef(int columnIndex) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public Blob getBlob(int columnIndex) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public Clob getClob(int columnIndex) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public Array getArray(int columnIndex) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public Object getObject(String columnLabel, Map<String, Class<?>> map) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public Ref getRef(String columnLabel) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public Blob getBlob(String columnLabel) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public Clob getClob(String columnLabel) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public Array getArray(String columnLabel) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public Date getDate(int columnIndex, Calendar cal) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public Date getDate(String columnLabel, Calendar cal) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public Time getTime(int columnIndex, Calendar cal) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public Time getTime(String columnLabel, Calendar cal) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public Timestamp getTimestamp(int columnIndex, Calendar cal) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public Timestamp getTimestamp(String columnLabel, Calendar cal) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public URL getURL(int columnIndex) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public URL getURL(String columnLabel) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public void updateRef(int columnIndex, Ref x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateRef(String columnLabel, Ref x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateBlob(int columnIndex, Blob x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateBlob(String columnLabel, Blob x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateClob(int columnIndex, Clob x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateClob(String columnLabel, Clob x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateArray(int columnIndex, Array x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateArray(String columnLabel, Array x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public RowId getRowId(int columnIndex) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public RowId getRowId(String columnLabel) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public void updateRowId(int columnIndex, RowId x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateRowId(String columnLabel, RowId x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public int getHoldability() throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return 0;
	}

	@Override
	public boolean isClosed() throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return false;
	}

	@Override
	public void updateNString(int columnIndex, String nString) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateNString(String columnLabel, String nString) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateNClob(int columnIndex, NClob nClob) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateNClob(String columnLabel, NClob nClob) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public NClob getNClob(int columnIndex) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public NClob getNClob(String columnLabel) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public SQLXML getSQLXML(int columnIndex) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public SQLXML getSQLXML(String columnLabel) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public void updateSQLXML(int columnIndex, SQLXML xmlObject) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateSQLXML(String columnLabel, SQLXML xmlObject) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public String getNString(int columnIndex) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public String getNString(String columnLabel) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public Reader getNCharacterStream(int columnIndex) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public Reader getNCharacterStream(String columnLabel) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public void updateNCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateNCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateAsciiStream(int columnIndex, InputStream x, long length) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateBinaryStream(int columnIndex, InputStream x, long length) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateAsciiStream(String columnLabel, InputStream x, long length) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateBinaryStream(String columnLabel, InputStream x, long length) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateBlob(int columnIndex, InputStream inputStream, long length) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateBlob(String columnLabel, InputStream inputStream, long length) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateClob(int columnIndex, Reader reader, long length) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateClob(String columnLabel, Reader reader, long length) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateNClob(int columnIndex, Reader reader, long length) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateNClob(String columnLabel, Reader reader, long length) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateNCharacterStream(int columnIndex, Reader x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateNCharacterStream(String columnLabel, Reader reader) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateAsciiStream(int columnIndex, InputStream x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateBinaryStream(int columnIndex, InputStream x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateCharacterStream(int columnIndex, Reader x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateAsciiStream(String columnLabel, InputStream x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateBinaryStream(String columnLabel, InputStream x) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateCharacterStream(String columnLabel, Reader reader) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateBlob(int columnIndex, InputStream inputStream) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateBlob(String columnLabel, InputStream inputStream) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateClob(int columnIndex, Reader reader) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateClob(String columnLabel, Reader reader) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateNClob(int columnIndex, Reader reader) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public void updateNClob(String columnLabel, Reader reader) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		
	}

	@Override
	public <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}

	@Override
	public <T> T getObject(String columnLabel, Class<T> type) throws SQLException {
		System.out.println(this.getClass() + " METHOD NOT IMPLEMENTED!");
		return null;
	}
	
//	-------------------------------------------------------------------------------------------------------------------
	
	

}
