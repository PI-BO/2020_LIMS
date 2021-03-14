package model.database.dummyDB;

import exceptions.ModelNotFoundException;
import model.database.Database;
import model.database.relations.ManyToManyA;
import model.database.relations.OneToMany;
import model.database.tableModels.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DummyDB implements Database {

    private final List<Model> modelList = new LinkedList<>();

    public DummyDB() {
        initModels();
    }

    private void initModels() {

        // Mitarbeiter
        Mitarbeiter mitarbeiter = new Mitarbeiter();
        mitarbeiter.setVorname("Max");
        mitarbeiter.setNachname("Mustermann");
        mitarbeiter.setPassword("abc");
        mitarbeiter.setPrimaryKey("123");
        mitarbeiter.setRolle(1);
        modelList.add(mitarbeiter);

        // Partner
        Partner partner = new Partner();
        partner.setPrimaryKey("1");
        partner.setName("PETER");
        partner.setEmail("peter@Unternehmen.de");
        modelList.add(partner);

        partner = new Partner();
        partner.setPrimaryKey("2");
        partner.setName("Jeff");
        partner.setEmail("jeff@Amazon.com");
        modelList.add(partner);

        // Projekte
        Projekt projekt = new Projekt();
        projekt.setPrimaryKey("A");
        projekt.setVertragsnummer("1");
        modelList.add(projekt);

        projekt = new Projekt();
        projekt.setPrimaryKey("B");
        projekt.setVertragsnummer("1");
        modelList.add(projekt);

        projekt = new Projekt();
        projekt.setPrimaryKey("C");
        projekt.setVertragsnummer("2");
        modelList.add(projekt);

        // Substanzen
        Substanz substanz = new Substanz();
        substanz.setPrimaryKey("SubstanzA1");
        substanz.setProjektID("A");
        modelList.add(substanz);

        substanz = new Substanz();
        substanz.setPrimaryKey("SubstanzA2");
        substanz.setProjektID("A");
        modelList.add(substanz);

        substanz = new Substanz();
        substanz.setPrimaryKey("SubstanzA3");
        substanz.setProjektID("A");
        modelList.add(substanz);

        substanz = new Substanz();
        substanz.setPrimaryKey("SubstanzB");
        substanz.setProjektID("B");
        modelList.add(substanz);

        substanz = new Substanz();
        substanz.setPrimaryKey("SubstanzC");
        substanz.setProjektID("C");
        modelList.add(substanz);

        substanz = new Substanz();
        substanz.setPrimaryKey("SubstanzG");
        substanz.setProjektID("A");
        modelList.add(substanz);

        // Proben
        Probe probe = new Probe();
        probe.setPrimaryKey("ProbeA");
        probe.setSubstanzID("SubstanzA1");
        modelList.add(probe);

        probe = new Probe();
        probe.setPrimaryKey("ProbeA2");
        probe.setSubstanzID("SubstanzA1");
        modelList.add(probe);

        probe = new Probe();
        probe.setPrimaryKey("ProbeB");
        probe.setSubstanzID("SubstanzA2");
        modelList.add(probe);

        probe = new Probe();
        probe.setPrimaryKey("ProbeB2");
        probe.setSubstanzID("SubstanzA2");
        modelList.add(probe);

        probe = new Probe();
        probe.setPrimaryKey("ProbeC");
        probe.setSubstanzID("SubstanzA3");
        modelList.add(probe);

        probe = new Probe();
        probe.setPrimaryKey("ProbeC2");
        probe.setSubstanzID("SubstanzA3");
        modelList.add(probe);

        probe = new Probe();
        probe.setPrimaryKey("ProbeD");
        probe.setSubstanzID("SubstanzB");
        modelList.add(probe);

        probe = new Probe();
        probe.setPrimaryKey("ProbeD2");
        probe.setSubstanzID("SubstanzB");
        modelList.add(probe);

        probe = new Probe();
        probe.setPrimaryKey("ProbeE");
        probe.setSubstanzID("SubstanzC");
        modelList.add(probe);

        probe = new Probe();
        probe.setPrimaryKey("ProbeE2");
        probe.setSubstanzID("SubstanzC");
        modelList.add(probe);

        probe = new Probe();
        probe.setPrimaryKey("ProbeF");
        probe.setSubstanzID("SubstanzG");
        modelList.add(probe);

        probe = new Probe();
        probe.setPrimaryKey("ProbeF2");
        probe.setSubstanzID("SubstanzG");
        modelList.add(probe);

        // Experimente
        Experiment experiment = new Experiment();
        experiment.setPrimaryKey("Experiment1");
        experiment.setTyp("101");
        experiment.setProbenNr("ProbeA");
        modelList.add(experiment);

        // Experiment Typen
        Experimenttyp experimenttyp = new Experimenttyp();
        experimenttyp.setPrimaryKey("101");
        experimenttyp.setTyp("Verdampfung 1Lömi");
        modelList.add(experimenttyp);

        experimenttyp = new Experimenttyp();
        experimenttyp.setPrimaryKey("202");
        experimenttyp.setTyp("Slurry 1Lömi");
        modelList.add(experimenttyp);
    }

    @Override
    public void getModel(Model requestedModel) throws SQLException, ModelNotFoundException {

        for (Model model : modelList) {

            if (tableNotFound(requestedModel, model)) continue;
            if (primaryKeyNotFound(requestedModel, model)) continue;

            DummyResultSet dummyResultSet = model.returnAsDummyResultSet();
            requestedModel.setAttributes(dummyResultSet);

            break;
        }
    }

    private boolean primaryKeyNotFound(Model requestedModel, Model model) {
        return !model.getPrimaryKey().equals(requestedModel.getPrimaryKey());
    }

    private boolean tableNotFound(Model requestedModel, Model model) {
        return !model.getTable().equals(requestedModel.getTable());
    }

    private boolean primaryKeyNotFound(String requestedKey, DummyRelation model) {
        return !model.getForeignKey().equals(requestedKey);
    }

    private boolean tableNotFound(String requestedTable, Model model) {
        return !model.getTable().equals(requestedTable);
    }

    @Override
    public void setModel(Model model) throws SQLException {
        // TODO Auto-generated method stub
        System.out.print(this.getClass());
        System.out.println("setModel() not implemented");
    }

    @Override
    public void updateModel(Model model) throws SQLException {
        // TODO Auto-generated method stub
        System.out.print(this.getClass());
        System.out.println("updateModel() not implemented");
    }

    @Override
    public void deleteModel(Model model) throws SQLException {
        // TODO Auto-generated method stub
        System.out.print(this.getClass());
        System.out.println("deleteModel() not implemented");
    }

    @Override
    public void getTable(Model requestedModel) throws SQLException, ModelNotFoundException {

        DummyResultSet dummyResultSet = new DummyResultSet();

        for (Model listModel : modelList) {
            if (!listModel.getTable().equals(requestedModel.getTable())) continue;
            System.out.println(listModel.returnAsDummyResultSet().getEntryList().get(0).get(1).getValue());
            dummyResultSet.addResultSet(listModel.returnAsDummyResultSet());
        }

        requestedModel.setAttributes(dummyResultSet);
    }

    @Override
    public void getRelation(Model model) throws SQLException, ModelNotFoundException {
        // TODO Auto-generated method stub
        System.out.print(this.getClass());
        System.out.println("getRelation() not implemented");
    }

    @Override
    public <T extends Model, U extends Model> void resolveOneToMany(OneToMany<T, U> relation) throws SQLException, ModelNotFoundException {

        try {
            String requestedTable = relation.getManyTable();
            String requestedKey = relation.getOneKey();

            DummyResultSet dummyResultSet = new DummyResultSet();

            for (Model model : modelList) {

                if (tableNotFound(requestedTable, model)) continue;
                if (primaryKeyNotFound(requestedKey, model)) continue;

                DummyResultSetEntry dummyResultSetEntry = new DummyResultSetEntry();
                dummyResultSetEntry.addKeyValuePair(model.getPrimaryKeyColumn(), model.getPrimaryKey());
                dummyResultSet.addEntry(dummyResultSetEntry);
            }

            relation.setAttributes(dummyResultSet);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public <M extends Model, N extends Model> void resolveManyToMany(ManyToManyA<N, M> manyToMany) throws NoSuchFieldException, IllegalAccessException, SQLException {
        // TODO Auto-generated method stub
        System.out.print(this.getClass());
        System.out.println("resolveManyToMany() not implemented");
    }

    @Override
    public ResultSet findSubstring(Class<? extends Model> m, String str, String... fields) throws NoSuchFieldException, IllegalAccessException, SQLException {
        DummyResultSet dummyResultSet = new DummyResultSet();

        modelList.stream().filter(model -> {
            if (model.getClass().equals(m))
                for (String field : fields)
                    if (field.equals(model.getPrimaryKeyColumn()))
                        if (model.getPrimaryKey().contains(str))
                            return true;
            return false;
        }).forEach(model ->
                dummyResultSet.addResultSet(model.returnAsDummyResultSet())
        );

        return dummyResultSet;
    }

}
