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

	public DummyDB(){
		initModels();
	}

    private void initModels() {
        // Rollen
        MitarbeiterRolle rolle = new MitarbeiterRolle();
        rolle.setPrimaryKey("1");
        rolle.setTyp("Projektplanung");
        rolle.setZugehoerigkeit("Projektmanager");
        modelList.add(rolle);

        rolle = new MitarbeiterRolle();
        rolle.setPrimaryKey("2");
        rolle.setTyp("Laborbetreuung");
        rolle.setZugehoerigkeit("Laborleiter");
        modelList.add(rolle);

        rolle = new MitarbeiterRolle();
        rolle.setPrimaryKey("3");
        rolle.setTyp("Durchführung");
        rolle.setZugehoerigkeit("Laborteam");
        modelList.add(rolle);

        // Mitarbeiter
        Mitarbeiter mitarbeiter = new Mitarbeiter();
        mitarbeiter.setVorname("Max");
        mitarbeiter.setNachname("Mustermann");
        mitarbeiter.setPassword("abc");
        mitarbeiter.setPrimaryKey("123");
        mitarbeiter.setRolle(1);
        modelList.add(mitarbeiter);

        mitarbeiter = new Mitarbeiter();
        mitarbeiter.setVorname("Maxime");
        mitarbeiter.setNachname("Musterfrau");
        mitarbeiter.setPassword("dot");
        mitarbeiter.setPrimaryKey("987");
        mitarbeiter.setRolle(2);
        modelList.add(mitarbeiter);

        mitarbeiter = new Mitarbeiter();
        mitarbeiter.setVorname("Harry");
        mitarbeiter.setNachname("Potter");
        mitarbeiter.setPassword("qwe");
        mitarbeiter.setPrimaryKey("456");
        mitarbeiter.setRolle(3);
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

        // Experiment Durchführungstext
        ExperimentDurchfuehrungstext experimentDurchfuehrungstext = new ExperimentDurchfuehrungstext();
        experimentDurchfuehrungstext.setPrimaryKey("Durchführungstext A");
        experimentDurchfuehrungstext.setText(
                "20-30 mg API in 4.0 mL Vial mit PTFE Dichtung einwiegen.\n" +
                        "Die genaue Einwaage in den Datensatz eintragen.\n" +
                        "Das 4.0 mL Vial lediglich mit einem Edding mit Exp No im oberen Viertel der Gefäßwand beschriften.\n" +
                        "Zugabe von 3.0 mL Lösungsmittel.\n" +
                        "Das Gefäß dicht (!) verschließen und in ein auf 25°C temperiertes Ultraschallbad stellen oder spannen,- Eintauchtiefe 50% der Vialhöhe.\n" +
                        "Ultraschalldauer 5 min.\n" +
                        "Die Ultraschallbadtemperatur ist stets zu kontrollieren und ggfs. zu korrigieren.\n" +
                        "5 ml Spritzen, Kanülen und 0.2 µm Rezist Spritzenfilteraufsätze sind im 25 °C vortemperierten Trockenschrank aufzubewahren.\n" +
                        "Mit einer Spritze wird zügig die Lösung oder Suspension vollständig aufgezogen und über einen 0.2 µm Rezist Filter in ein neues 4.0 mL Vial mit aufgeklebter Experiment-No, dessen Tara-Wert im Datensatz notiert wird, filtriert und für das Verdampfungsexperiment in den Abzug gestellt.\n" +
                        "Es werden, wenn möglich, täglich alle Vials auf Fortschritt der Verdampfung überprüft und die Beobachtung mit Datum im Datensatz notiert, bis die vollständige Verdampfung des Lösungsmittels festgestellt wurde.\n\n" +
                        "Von den fertigen vials, die einen Rückstand enthalten wird zunächst eine Rückwaage durchgeführt und im Datensatz notiert und dann Makrofotos (Labor-Kamera genügt) angefertigt und dem Datensatz zugeordnet.\n" +
                        "Die Vials werden dann mit dem Keyence Mikroskop in Augenschein genommen.\n" +
                        "Sollten interessante Beobachtungen, Strukturen, Kristalle zu sehen sein, dann sind mit dem Keyence Mikroskop Fotos zu machen und diese werden dem Datensatz zugeordnet.\n" +
                        "In kurzen Worten sind die Resultate im Datensatz zu notieren.\n" +
                        "Es ist besonders Ausschau zu halten ob vermeintliche Einkristalle zu sehen sind, die dann gesondert heraus präpariert werden.\n\n" +
                        "Die Proben werden verschlossen der Analytik übergeben.\n" +
                        "Der Operator der Analytik muss den Inhalt des gesamten Vials, möglichst mit quantitativen Ansprüchen rauskratzen, mörsern und den Rest nach der Analytik wieder zurück in dasselbe oder in ein neues Vial geben.\n" +
                        "Der leitende Projektmanager entscheidet über die  durchzuführende Analytik (und markiert und Kommentiert dies im Datensatz)."
        );
        modelList.add(experimentDurchfuehrungstext);

        experimentDurchfuehrungstext = new ExperimentDurchfuehrungstext();
        experimentDurchfuehrungstext.setPrimaryKey("Durchführungstext B");
        experimentDurchfuehrungstext.setText(
                "20-30 mg API in 4.0 mL Vial mit PTFE Dichtung einwiegen.\n" +
                        "Die genaue Einwaage in den Datensatz eintragen.\n" +
                        "Das 4.0 mL Vial lediglich mit einem Edding mit Exp No im oberen Viertel der Gefäßwand beschriften.\n" +
                        "Zugabe von 3.0 mL Lösungsmittel.\n" +
                        "Das Gefäß dicht (!) verschließen und in ein auf 25°C temperiertes Ultraschallbad stellen oder spannen,- Eintauchtiefe 50% der Vialhöhe.\n" +
                        "Ultraschalldauer 5 min.\n" +
                        "Die Ultraschallbadtemperatur ist stets zu kontrollieren und ggfs. zu korrigieren.\n" +
                        "5 ml Spritzen, Kanülen und 0.2 µm Rezist Spritzenfilteraufsätze sind im 25 °C vortemperierten Trockenschrank aufzubewahren.\n" +
                        "Mit einer Spritze wird zügig die Lösung oder Suspension vollständig aufgezogen und über einen 0.2 µm Rezist Filter in ein neues 4.0 mL Vial mit aufgeklebter Experiment-No, dessen Tara-Wert im Datensatz notiert wird, filtriert und für das Verdampfungsexperiment in den Abzug gestellt.\n" +
                        "Es werden, wenn möglich, täglich alle Vials auf Fortschritt der Verdampfung überprüft und die Beobachtung mit Datum im Datensatz notiert, bis die vollständige Verdampfung des Lösungsmittels festgestellt wurde.\n\n" +
                        "Von den fertigen vials, die einen Rückstand enthalten wird zunächst eine Rückwaage durchgeführt und im Datensatz notiert und dann Makrofotos (Labor-Kamera genügt) angefertigt und dem Datensatz zugeordnet.\n" +
                        "Die Vials werden dann mit dem Keyence Mikroskop in Augenschein genommen.\n" +
                        "Sollten interessante Beobachtungen, Strukturen, Kristalle zu sehen sein, dann sind mit dem Keyence Mikroskop Fotos zu machen und diese werden dem Datensatz zugeordnet.\n" +
                        "In kurzen Worten sind die Resultate im Datensatz zu notieren.\n" +
                        "Es ist besonders Ausschau zu halten ob vermeintliche Einkristalle zu sehen sind, die dann gesondert heraus präpariert werden.\n\n" +
                        "Die Proben werden verschlossen der Analytik übergeben.\n" +
                        "Der Operator der Analytik muss den Inhalt des gesamten Vials, möglichst mit quantitativen Ansprüchen rauskratzen, mörsern und den Rest nach der Analytik wieder zurück in dasselbe oder in ein neues Vial geben.\n" +
                        "Der leitende Projektmanager entscheidet über die  durchzuführende Analytik (und markiert und Kommentiert dies im Datensatz)."
        );
        modelList.add(experimentDurchfuehrungstext);
        
		//------------Relation-Test fuer Suche------------//
		
//		Partner partner1 = new Partner();
//		partner1.setName("Partner A");
//		partner1.setPrimaryKey("1");
//		partner1.setEmail("partner-A@gmail.com");
//		modelList.add(partner1);
//		
//		Partner partner2 = new Partner();
//		partner2.setName("Partner B");
//		partner2.setPrimaryKey("2");
//		partner2.setEmail("partner-B@gmx.com");
//		modelList.add(partner2);
//		
//		Projekt projekt1 = new Projekt(partner1);
//		projekt1.setPrimaryKey("Projekt A");
//		projekt1.setVertragsnummer("1");
//		modelList.add(projekt1);
//		
//		Projekt projekt2 = new Projekt(partner2);
//		projekt2.setPrimaryKey("Projekt B");
//		projekt2.setVertragsnummer("2");
//		modelList.add(projekt2);
//		
//		Probe probe1 = new Probe(projekt1);
//		probe1.setPrimaryKey("0001A");
//		probe1.setName("Probe A");
//		modelList.add(probe1);
//		
//		Probe probe2 = new Probe(projekt2);
//		probe2.setPrimaryKey("0001B");
//		probe2.setName("Probe B");
//		modelList.add(probe2);
//		
//		Experiment experiment1 = new Experiment(probe1);
//		experiment1.setPrimaryKey("0001Aexp0");
//		experiment1.setTyp("Slurry");
//		modelList.add(experiment1);
//		
//		Experiment experiment2 = new Experiment(probe1);
//		experiment2.setPrimaryKey("0001Aexp1");
//		experiment2.setTyp("Vedampfung");
//		modelList.add(experiment2);
//		
//		Experiment experiment3 = new Experiment(probe2);
//		experiment3.setPrimaryKey("0001Bexp0");
//		experiment3.setTyp("Slurry");
//		modelList.add(experiment3);
//		
//		Experiment experiment4 = new Experiment(probe2);
//		experiment4.setPrimaryKey("0001Bexp1");
//		experiment4.setTyp("Vedampfung");
//		modelList.add(experiment4);
    }

    @Override
    public void getModelAnalyseTemperaturprogramme(AnalyseTemperaturprogramme requestedModel) throws ModelNotFoundException, SQLException {
        for (Model model : modelList) {
            if (tableNotFound(requestedModel, model)) continue;
            if (compositeKeyNotFound(requestedModel, model)) continue;

            DummyResultSet dummyResultSet = model.returnAsDummyResultSet();
            requestedModel.setAttributes(dummyResultSet);

            break;
        }
    }
    
    @Override
	public void getModel(Model requestedModel) throws SQLException, ModelNotFoundException {

		for(Model model : modelList){
			
			if(tableNotFound(requestedModel, model)) continue;
			if(primaryKeyNotFound(requestedModel, model)) continue;
			
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
    
    private boolean compositeKeyNotFound(AnalyseTemperaturprogramme requestModel, DummyRelation model) {
        boolean ret = false;
        String[] keys = requestModel.getPrimaryKeys();
        if (model instanceof AnalyseTemperaturprogramme) {
            String[] dummyKeys = ((AnalyseTemperaturprogramme) model).getPrimaryKeys();
            ret = true;
            for (int i = 0; i < keys.length; i++)
                ret = ret && keys[i].equals(dummyKeys[i]);
        }
        return ret;
    }

    @Override
    public void saveModel(Model model) throws SQLException {
    	modelList.add(model);
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
    public void getTable(ModelTable requestedModel) throws SQLException, ModelNotFoundException {

        DummyResultSet dummyResultSet = new DummyResultSet();

        for (Model listModel : modelList) {
            if (!listModel.getTable().equals(requestedModel.getTable())) continue;
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

	@Override
	public <M extends Model, N extends Model> void resolveManyToMany(ManyToManyA<N, M> manyToMany) throws NoSuchFieldException, IllegalAccessException, SQLException {
		// TODO Auto-generated method stub
		System.out.print(this.getClass());
		System.out.println("resolveManyToMany() not implemented");
	}

	@Override
	public List<List<Model>> getRelationList() {
		
		List<List<Model>> branches = new LinkedList<>();
		
		List<Model> rootModelList = getAllRootModels();
		
		for(Model rootModel : rootModelList){
			
			List<Model> branch = new LinkedList<>();
			branches.add(branch);
			
			branch.add(rootModel);
			
			System.out.println(rootModel.toJSON());
			
			extendBranch(branch, rootModel, branches);
			
		}
		
		return branches;
	}

	private void extendBranch(List<Model> branch, Model nextModel, List<List<Model>> branches) {
		
		while(nextModel.hasChildren()){
			
			if(nextModel.getChildren().size() > 1){
				
				for(int i = 1; i < nextModel.getChildren().size(); i++){
					
					List<Model> newBranch = new LinkedList<>();
					newBranch.addAll(branch);
					branches.add(newBranch);
					
					Model child = nextModel.getChildren().get(i);
					newBranch.add(child);
					
					extendBranch(newBranch, child, branches);
				}
			}
				
			nextModel = nextModel.getChildren().get(0);
			System.out.println(nextModel.toJSON());
			branch.add(nextModel);
		}
	}

	private List<Model> getAllRootModels() {
		List<Model> rootModelList = new LinkedList<>();
		
		for(Model model : modelList){
//			if(model.getClass() == Partner.class) rootModelList.add(model);
			if(!model.hasParents()) rootModelList.add(model);
		}
		return rootModelList;
	}
}