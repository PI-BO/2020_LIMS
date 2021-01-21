package database.relations;

import database.connection.MariaDB;
import database.inerfaces.Database;
import database.model.Model;

public abstract class OneToOne <T extends Model, U extends Model> {
    protected Database database = new MariaDB();

    private final T parent;
    private final U child;

    protected OneToOne(T parent, U child) {
        this.parent = parent;
        this.child = child;
    }

    public T getParent() {
        return parent;
    }

    public U getChild() {
        return child;
    }
}
