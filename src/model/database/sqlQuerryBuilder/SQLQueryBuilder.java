package model.database.sqlQuerryBuilder;

/**
 * SQL Query builder
 */
public class SQLQueryBuilder {
    /**
     * Build SELECT query for SQL Database
     *
     * @return SELECT query Builder
     */
    public SELECTBuilder select() {
        return new SELECTBuilder();
    }

    /**
     * Build INSERT query for SQL Database
     *
     * @return INSERT query Builder
     */
    public INSERTBuilder insert() {
        return new INSERTBuilder();
    }

    /**
     * Equals operator for where clause
     *
     * @param column column in table to check
     * @param value  value to check column for
     * @return String of operation
     */
    public Operator equals(String column, String value) {
        return new Operator(column, "=", value);
    }

    /**
     * LIKE operator for where clause
     *
     * @param column column in table to check
     * @param value  value to check column for
     * @return String of operation
     */
    public Operator like(String column, String value) {
        return new Operator(column, "LIKE", value);
    }

    /**
     * LIKE operator (value cntains) for where clause
     *
     * @param column column in table to check
     * @param value  value to check column for, adds % before and after value
     * @return String of operation
     */
    public Operator contains(String column, String value) {
        return new Operator(column, "LIKE", "%" + value + "%");
    }

    public Operator contains(String[] columns, String value) {
        return new Operator(columns, "LIKE", "%" + value + "%");
    }

    public Operator in(String column, SELECTBuilder select) {
        return new Operator(column, "IN", select.build());
    }
}
