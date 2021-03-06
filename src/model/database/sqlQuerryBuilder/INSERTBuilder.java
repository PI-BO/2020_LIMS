package model.database.sqlQuerryBuilder;

import java.util.Arrays;

class INSERTBuilder {
    private String[] columns;
    private String[] values;
    private String table;

    public INSERTBuilder into(String table) {
        this.table = table;
        return this;
    }

    public INSERTBuilder columns(String... val) {
        columns = val;
        return this;
    }

    public INSERTBuilder values(String... val) {
        values = Arrays.stream(val)
                .map(v -> "'" + v + "'")
                .toArray(String[]::new);
        return this;
    }

    public String build() {
        String columns = Arrays.toString(this.columns)
                .replace("[", "(")
                .replace("]", ")");
        String values = Arrays.toString(this.values)
                .replace("[", "(")
                .replace("]", ")");
        return "INSERT INTO " + table + " " + columns + " VALUES " + values + ";";
    }
}
