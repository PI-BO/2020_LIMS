package model.database.relations;

import exceptions.ModelNotFoundException;
import model.database.tableModels.Partner;
import model.database.tableModels.Projekt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Narwutsch Dominic
 * created on 08.03.2021
 */
public class PartnerProjekt extends OneToMany<Partner, Projekt> {
    private List<Projekt> projekte;

    public PartnerProjekt(Partner partner) throws ModelNotFoundException, SQLException {
        super(partner, Projekt.class);
        database.resolveOneToMany(this);
    }

    @Override
    public List<String> getManyKeys() {
        return projekte.stream().map(Projekt::getPrimaryKey).collect(Collectors.toList());
    }

    @Override
    public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException, NoSuchFieldException, IllegalAccessException {
        projekte = new LinkedList<>();

        while (resultSet.next()) {
            int projektIdIndex = resultSet.findColumn(getManyKeyColumn());
            String projektId = resultSet.getString(projektIdIndex);
            Projekt projekt = new Projekt(projektId);

            projekte.add(projekt);
        }
    }

    public List<Projekt> getProjekte() {
        return projekte;
    }
}
