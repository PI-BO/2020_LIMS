package model.database.relations;

import exceptions.ModelNotFoundException;
import model.database.tableModels.Projekt;
import model.database.tableModels.Substanz;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ProjekteSubstanz extends OneToMany<Projekt, Substanz> {
    private List<Substanz> substanzen;

    public ProjekteSubstanz(Projekt one) throws SQLException, ModelNotFoundException {
        super(one, Substanz.class);
        database.resolveOneToMany(this);
    }

    @Override
    public List<String> getManyKeys() {
        return substanzen.stream().map(Substanz::getPrimaryKey).collect(Collectors.toList());
    }

    @Override
    public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException, NoSuchFieldException, IllegalAccessException {
        substanzen = new LinkedList<>();

        while (resultSet.next()) {
            int substanzIdIndex = resultSet.findColumn(getManyKeyColumn());
            String substanzId = resultSet.getString(substanzIdIndex);
            Substanz substanz = new Substanz(substanzId);

            substanzen.add(substanz);
        }
    }

    public List<Substanz> getSubstanzen() {
        return substanzen;
    }
}
