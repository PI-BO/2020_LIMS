package model.database.sqlQuerryBuilder;

import exceptions.WhereAddedException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Narwutsch Dominic
 * created on 06.03.2021
 */
public class SELECTBuilder {
    private String[] columns;
    private String table;
    private Set<String> joins = new HashSet<>();
    private String conditions;

    public SELECTBuilder columns(String... val) {
        this.columns = val;
        return this;
    }

    public SELECTBuilder from(String table) {
        this.table = "FROM " + table + " ";
        return this;
    }

    private String join(String joinType, String fromTable, String toTable, String column) {
        return joinType + toTable + " ON " + fromTable + "." + column + " = " + toTable + "." + column + " ";
    }

    public SELECTBuilder innerJoin(String fromTable, String toTable, String on) {
        this.joins.add(
                join("INNER JOIN ", fromTable, toTable, on)
        );
        return this;
    }

    public SELECTBuilder outerJoin(String fromTable, String toTable, String on) {
        this.joins.add(
                join("OUTER JOIN ", fromTable, toTable, on)
        );
        return this;
    }

    public SELECTBuilder where(Operator op) throws WhereAddedException {
        if (conditions == null) {
            conditions = "WHERE " + op.s[0];
        } else
            throw new WhereAddedException("WHERE clause does exist");
        return this;
    }

    public SELECTBuilder and(Operator op) {
        for (String s : op.s) {
            if (conditions == null) {
                conditions = "WHERE " + s;
            } else
                conditions += " AND " + s;
        }
        return this;
    }

    public SELECTBuilder or(Operator op) {
        for (String s : op.s) {
            if (conditions == null) {
                conditions = "WHERE " + s;
            } else
                conditions += " OR " + s;
        }
        return this;
    }

    public String build() {
        String vals = Arrays.toString(columns)
                .replace("[", "")
                .replace("]", "");
        StringBuilder query = new StringBuilder("SELECT " + vals + " " + table);
        if (!joins.isEmpty())
            for (String s : joins)
                query.append(s);
        query.append(conditions).append(";");
        return query.toString();
    }
}
