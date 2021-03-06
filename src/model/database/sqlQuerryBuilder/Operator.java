package model.database.sqlQuerryBuilder;

import org.graalvm.compiler.nodes.calc.IntegerDivRemNode;

/**
 * Operator class to build Operation
 */
class Operator {
    String[] s;

    /**
     * Operation constructor
     * @param column column name in table to check
     * @param operant the operand (=, LIKE, ...)
     * @param value value to check column for
     */
    public Operator(String column, String operant, String value) {
        s = new String[1];
        s[0] = column + " " + operant + " \"" + value + "\"";
    }

    public Operator(String[] columns, String operant, String value) {
        s = new String[columns.length];
        for (int i=0; i<columns.length; i++)
            s[i] = columns[i] + " " + operant + " \"" + value + "\"";
    }
}
