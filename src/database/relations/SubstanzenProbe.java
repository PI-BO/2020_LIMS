package database.relations;

import exceptions.ModelNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import database.model.Probe;
import database.model.Substanz;

public class SubstanzenProbe extends OneToMany<Substanz, Probe>{
    List<Probe> proben;

    public SubstanzenProbe(Substanz one) throws ModelNotFoundException, SQLException {
        super(one, Probe.class);
        database.resolveOneToMany(this);
    }

    @Override
    public List<String> getManyKeys() {
        return proben.stream().map(Probe::getPrimaryKey).collect(Collectors.toList());
    }

    @Override
    public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException, NoSuchFieldException, IllegalAccessException {
        proben = new ArrayList<>();

        while (resultSet.next()) {
            int probenIdIndex = resultSet.findColumn(getManyKeyColumn());
            String ProbenId = resultSet.getString(probenIdIndex);
            Probe probe = new Probe(ProbenId);

            proben.add(probe);
        }
    }

    public List<Probe> getProben(){
        return proben;
    }
}
