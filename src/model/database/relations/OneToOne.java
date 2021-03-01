package model.database.relations;

import model.database.Database;
import model.database.manager.DatabaseManager;
import model.database.tableModels.Model;

public abstract class OneToOne <T extends Model, U extends Model> {
    
	protected Database database = DatabaseManager.getDatabaseInstance();

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
