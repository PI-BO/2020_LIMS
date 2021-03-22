package model.database.dummyDB;

import exceptions.ModelNotFoundException;
import model.database.Database;
import model.database.relations.ManyToManyA;
import model.database.relations.OneToMany;
import model.database.tableModels.*;
import model.database.tableModels.analyse.AnalyseTemperaturprogramme;
import model.database.tableModels.analyse.Analysetyp;
import model.database.tableModels.experimente.*;
import model.database.tableModels.experimente.ExperimenttypSlurry;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DummyDB implements Database {

	private final List<Model> modelList = new LinkedList<>();

	public void initModels() throws ModelNotFoundException, SQLException {
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
		rolle.setTyp("Durchfuehrung");
		rolle.setZugehoerigkeit("Laborteam");
		modelList.add(rolle);

		// Mitarbeiter
		Mitarbeiter mitarbeiter1 = new Mitarbeiter();
		mitarbeiter1.setVorname("Max");
		mitarbeiter1.setNachname("Mustermann");
		mitarbeiter1.setPassword("abc");
		mitarbeiter1.setPrimaryKey("123");
		mitarbeiter1.setRolle(1);
		modelList.add(mitarbeiter1);

		Mitarbeiter mitarbeiter2 = new Mitarbeiter();
		mitarbeiter2.setVorname("Maxime");
		mitarbeiter2.setNachname("Musterfrau");
		mitarbeiter2.setPassword("dot");
		mitarbeiter2.setPrimaryKey("987");
		mitarbeiter2.setRolle(2);
		modelList.add(mitarbeiter2);

		Mitarbeiter mitarbeiter3 = new Mitarbeiter();
		mitarbeiter3.setVorname("Harry");
		mitarbeiter3.setNachname("Potter");
		mitarbeiter3.setPassword("qwe");
		mitarbeiter3.setPrimaryKey("456");
		mitarbeiter3.setRolle(3);
		modelList.add(mitarbeiter3);

		// Partner
		Partner partner1 = new Partner();
		partner1.setPrimaryKey("1");
		partner1.setName("Bayer");
		partner1.setEmail("bayer@Unternehmen.de");
		modelList.add(partner1);

		Partner partner2 = new Partner();
		partner2.setPrimaryKey("2");
		partner2.setName("Novartis");
		partner2.setEmail("novartis@pharma.com");
		modelList.add(partner2);

		// Projekte
		Projekt projekt1 = new Projekt();
		projekt1.setPrimaryKey("A");
		projekt1.setVertragsnummer("1");
		projekt1.setProjektPartnerId("1");
		modelList.add(projekt1);

		Projekt projekt2 = new Projekt();
		projekt2.setPrimaryKey("B");
		projekt2.setVertragsnummer("1");
		projekt2.setProjektPartnerId("1");
		modelList.add(projekt2);

		Projekt projekt3 = new Projekt();
		projekt3.setPrimaryKey("C");
		projekt3.setVertragsnummer("2");
		projekt3.setProjektPartnerId("2");
		modelList.add(projekt3);

		// Substanzen
		Substanz substanz1 = new Substanz();
		substanz1.setPrimaryKey("SubstanzA1");
		substanz1.setProjektID("A");
		modelList.add(substanz1);

		Substanz substanz2 = new Substanz();
		substanz2.setPrimaryKey("SubstanzA2");
		substanz2.setProjektID("A");
		modelList.add(substanz2);

		Substanz substanz3 = new Substanz();
		substanz3.setPrimaryKey("SubstanzA3");
		substanz3.setProjektID("A");
		modelList.add(substanz3);

		Substanz substanz4 = new Substanz();
		substanz4.setPrimaryKey("SubstanzB");
		substanz4.setProjektID("B");
		modelList.add(substanz4);

		Substanz substanz5 = new Substanz();
		substanz5.setPrimaryKey("SubstanzC");
		substanz5.setProjektID("C");
		modelList.add(substanz5);

		// Proben
		Probe probe1 = new Probe();
		probe1.setPrimaryKey("ProbeA1");
		probe1.setSubstanzID("SubstanzA1");
		modelList.add(probe1);

		Probe probe2 = new Probe();
		probe2.setPrimaryKey("ProbeA2");
		probe2.setSubstanzID("SubstanzA2");
		modelList.add(probe2);

		Probe probe3 = new Probe();
		probe3.setPrimaryKey("ProbeB1");
		probe3.setSubstanzID("SubstanzA3");
		modelList.add(probe3);

		Probe probe4 = new Probe();
		probe4.setPrimaryKey("ProbeB");
		probe4.setSubstanzID("SubstanzB");
		modelList.add(probe4);

		Probe probe5 = new Probe();
		probe5.setPrimaryKey("ProbeC");
		probe5.setSubstanzID("SubstanzB");
		modelList.add(probe5);

		Probe probe6 = new Probe();
		probe6.setPrimaryKey("ProbeC2");
		probe6.setSubstanzID("SubstanzC");
		modelList.add(probe6);

		Probe probe7 = new Probe();
		probe7.setPrimaryKey("ProbeD");
		probe7.setSubstanzID("SubstanzC");
		modelList.add(probe7);

		Probe probe8 = new Probe();
		probe8.setPrimaryKey("ProbeD2");
		probe8.setSubstanzID("SubstanzC");
		modelList.add(probe8);

		Probe probe9 = new Probe();
		probe9.setPrimaryKey("ProbeE");
		probe9.setSubstanzID("SubstanzC");
		modelList.add(probe9);

		Probe probe10 = new Probe();
		probe10.setPrimaryKey("ProbeE2");
		probe10.setSubstanzID("SubstanzC");
		modelList.add(probe10);

		Probe probe11 = new Probe();
		probe11.setPrimaryKey("ProbeF");
		probe11.setSubstanzID("SubstanzC");
		modelList.add(probe11);

		Probe probe12 = new Probe();
		probe12.setPrimaryKey("ProbeF2");
		probe12.setSubstanzID("SubstanzC");
		modelList.add(probe12);

		// Experimente
		Experiment experiment1 = new Experiment();
		experiment1.setPrimaryKey("1");
		experiment1.setTyp("101");
		experiment1.setProbenNr("ProbeA1");
		modelList.add(experiment1);

		Experiment experiment2 = new Experiment();
		experiment2.setPrimaryKey("2");
		experiment2.setTyp("202");
		experiment2.setProbenNr("ProbeB");
		modelList.add(experiment2);

		// Experimente Verdampfung
		ExperimenttypVerdampfung experimenttypVerdampfung = new ExperimenttypVerdampfung();
		experimenttypVerdampfung.setPrimaryKey("1");
		experimenttypVerdampfung.setExperiment_no("Experiment1Verdampfung");
		modelList.add(experimenttypVerdampfung);

		// Experimente Slurry
		ExperimenttypSlurry experimenttypSlurry = new ExperimenttypSlurry();
		experimenttypSlurry.setPrimaryKey("2");
		experimenttypSlurry.setExperiment_no("Experiment2Slurry");
		modelList.add(experimenttypSlurry);

		// Experiment Typen
		Experimenttyp experimenttyp1 = new Experimenttyp();
		experimenttyp1.setPrimaryKey("101");
		experimenttyp1.setTyp("Verdampfung");
		modelList.add(experimenttyp1);

		Experimenttyp experimenttyp2 = new Experimenttyp();
		experimenttyp2.setPrimaryKey("202");
		experimenttyp2.setTyp("Slurry");
		modelList.add(experimenttyp2);

		// Experiment Durchfuehrungstext
		ExperimentDurchfuehrungstext experimentDurchfuehrungstext1 = new ExperimentDurchfuehrungstext();
		experimentDurchfuehrungstext1.setPrimaryKey("Durchfuehrungstext A");

		// TODO Sonderzeichen machen Probleme

		experimentDurchfuehrungstext1.setText("20-30 mg API in 4.0 mL Vial mit PTFE Dichtung einwiegen.\n" + "Die genaue Einwaage in den Datensatz eintragen.\n"
				+ "Das 4.0 mL Vial lediglich mit einem Edding mit Exp No im oberen Viertel der Gefäßwand beschriften.\n" + "Zugabe von 3.0 mL Lösungsmittel.\n"
				+ "Das Gefäß dicht (!) verschließen und in ein auf 25°C temperiertes Ultraschallbad stellen oder spannen,- Eintauchtiefe 50% der Vialhöhe.\n"
				+ "Ultraschalldauer 5 min.\n" + "Die Ultraschallbadtemperatur ist stets zu kontrollieren und ggfs. zu korrigieren.\n"
				+ "5 ml Spritzen, Kanülen und 0.2 µm Rezist Spritzenfilteraufsätze sind im 25 °C vortemperierten Trockenschrank aufzubewahren.\n"
				+ "Mit einer Spritze wird zügig die Lösung oder Suspension vollständig aufgezogen und über einen 0.2 µm Rezist Filter in ein neues 4.0 mL Vial mit aufgeklebter Experiment-No, dessen Tara-Wert im Datensatz notiert wird, filtriert und für das Verdampfungsexperiment in den Abzug gestellt.\n"
				+ "Es werden, wenn möglich, täglich alle Vials auf Fortschritt der Verdampfung überprüft und die Beobachtung mit Datum im Datensatz notiert, bis die vollständige Verdampfung des Lösungsmittels festgestellt wurde.\n\n"
				+ "Von den fertigen vials, die einen Rückstand enthalten wird zunächst eine Rückwaage durchgeführt und im Datensatz notiert und dann Makrofotos (Labor-Kamera genügt) angefertigt und dem Datensatz zugeordnet.\n"
				+ "Die Vials werden dann mit dem Keyence Mikroskop in Augenschein genommen.\n"
				+ "Sollten interessante Beobachtungen, Strukturen, Kristalle zu sehen sein, dann sind mit dem Keyence Mikroskop Fotos zu machen und diese werden dem Datensatz zugeordnet.\n"
				+ "In kurzen Worten sind die Resultate im Datensatz zu notieren.\n"
				+ "Es ist besonders Ausschau zu halten ob vermeintliche Einkristalle zu sehen sind, die dann gesondert heraus präpariert werden.\n\n"
				+ "Die Proben werden verschlossen der Analytik übergeben.\n"
				+ "Der Operator der Analytik muss den Inhalt des gesamten Vials, möglichst mit quantitativen Ansprüchen rauskratzen, mörsern und den Rest nach der Analytik wieder zurück in dasselbe oder in ein neues Vial geben.\n"
				+ "Der leitende Projektmanager entscheidet über die  durchzuführende Analytik (und markiert und Kommentiert dies im Datensatz).");
		modelList.add(experimentDurchfuehrungstext1);

		ExperimentDurchfuehrungstext experimentDurchfuehrungstext2 = new ExperimentDurchfuehrungstext();
		experimentDurchfuehrungstext2.setPrimaryKey("Durchführungstext B");

		// TODO Sonderzeichen machen Probleme

		experimentDurchfuehrungstext2.setText("20-30 mg API in 4.0 mL Vial mit PTFE Dichtung einwiegen.\n" + "Die genaue Einwaage in den Datensatz eintragen.\n"
				+ "Das 4.0 mL Vial lediglich mit einem Edding mit Exp No im oberen Viertel der Gefäßwand beschriften.\n" + "Zugabe von 3.0 mL Lösungsmittel.\n"
				+ "Das Gefäß dicht (!) verschließen und in ein auf 25°C temperiertes Ultraschallbad stellen oder spannen,- Eintauchtiefe 50% der Vialhöhe.\n"
				+ "Ultraschalldauer 5 min.\n" + "Die Ultraschallbadtemperatur ist stets zu kontrollieren und ggfs. zu korrigieren.\n"
				+ "5 ml Spritzen, Kanülen und 0.2 µm Rezist Spritzenfilteraufsätze sind im 25 °C vortemperierten Trockenschrank aufzubewahren.\n"
				+ "Mit einer Spritze wird zügig die Lösung oder Suspension vollständig aufgezogen und über einen 0.2 µm Rezist Filter in ein neues 4.0 mL Vial mit aufgeklebter Experiment-No, dessen Tara-Wert im Datensatz notiert wird, filtriert und für das Verdampfungsexperiment in den Abzug gestellt.\n"
				+ "Es werden, wenn möglich, täglich alle Vials auf Fortschritt der Verdampfung überprüft und die Beobachtung mit Datum im Datensatz notiert, bis die vollständige Verdampfung des Lösungsmittels festgestellt wurde.\n\n"
				+ "Von den fertigen vials, die einen Rückstand enthalten wird zunächst eine Rückwaage durchgeführt und im Datensatz notiert und dann Makrofotos (Labor-Kamera genügt) angefertigt und dem Datensatz zugeordnet.\n"
				+ "Die Vials werden dann mit dem Keyence Mikroskop in Augenschein genommen.\n"
				+ "Sollten interessante Beobachtungen, Strukturen, Kristalle zu sehen sein, dann sind mit dem Keyence Mikroskop Fotos zu machen und diese werden dem Datensatz zugeordnet.\n"
				+ "In kurzen Worten sind die Resultate im Datensatz zu notieren.\n"
				+ "Es ist besonders Ausschau zu halten ob vermeintliche Einkristalle zu sehen sind, die dann gesondert heraus präpariert werden.\n\n"
				+ "Die Proben werden verschlossen der Analytik übergeben.\n"
				+ "Der Operator der Analytik muss den Inhalt des gesamten Vials, möglichst mit quantitativen Ansprüchen rauskratzen, mörsern und den Rest nach der Analytik wieder zurück in dasselbe oder in ein neues Vial geben.\n"
				+ "Der leitende Projektmanager entscheidet über die  durchzuführende Analytik (und markiert und Kommentiert dies im Datensatz).");
		modelList.add(experimentDurchfuehrungstext2);

		 // Analysetyp
		 Analysetyp analysetyp = new Analysetyp();
		 analysetyp.setPrimaryKey("1");
		 analysetyp.setTyp("PXRD");
		 modelList.add(analysetyp);
		
		 analysetyp = new Analysetyp();
		 analysetyp.setPrimaryKey("2");
		 analysetyp.setTyp("DSC");
		 modelList.add(analysetyp);
		
		 analysetyp = new Analysetyp();
		 analysetyp.setPrimaryKey("3");
		 analysetyp.setTyp("TGA");
		 modelList.add(analysetyp);
		
		 analysetyp = new Analysetyp();
		 analysetyp.setPrimaryKey("4");
		 analysetyp.setTyp("IR");
		 modelList.add(analysetyp);
		
		 // temperaturprogramme
		 AnalyseTemperaturprogramme temperaturprogramme = new
		 AnalyseTemperaturprogramme();
		 temperaturprogramme.setTabelle("1");
		 temperaturprogramme.setSchritt("1");
		 temperaturprogramme.setTemperatur("180");
		 temperaturprogramme.setRampe("10");
		 temperaturprogramme.setZeit(null);
		 temperaturprogramme.setSegmenttyp("Dynamisch");
		 modelList.add(temperaturprogramme);
		
		 temperaturprogramme = new AnalyseTemperaturprogramme();
		 temperaturprogramme.setTabelle("1");
		 temperaturprogramme.setSchritt("2");
		 temperaturprogramme.setTemperatur("25");
		 temperaturprogramme.setRampe("10");
		 temperaturprogramme.setZeit(null);
		 temperaturprogramme.setSegmenttyp("Dynamisch");
		 modelList.add(temperaturprogramme);
		
		 temperaturprogramme = new AnalyseTemperaturprogramme();
		 temperaturprogramme.setTabelle("1");
		 temperaturprogramme.setSchritt("3");
		 temperaturprogramme.setTemperatur("180");
		 temperaturprogramme.setRampe("10");
		 temperaturprogramme.setZeit(null);
		 temperaturprogramme.setSegmenttyp("Dynamisch");
		 modelList.add(temperaturprogramme);
		
		 temperaturprogramme = new AnalyseTemperaturprogramme();
		 temperaturprogramme.setTabelle("1");
		 temperaturprogramme.setSchritt("4");
		 temperaturprogramme.setTemperatur("25");
		 temperaturprogramme.setRampe("10");
		 temperaturprogramme.setZeit(null);
		 temperaturprogramme.setSegmenttyp("Dynamisch");
		 modelList.add(temperaturprogramme);
		
		 temperaturprogramme = new AnalyseTemperaturprogramme();
		 temperaturprogramme.setTabelle("2");
		 temperaturprogramme.setSchritt("1");
		 temperaturprogramme.setTemperatur("180");
		 temperaturprogramme.setRampe("10");
		 temperaturprogramme.setZeit(null);
		 temperaturprogramme.setSegmenttyp("Dynamisch");
		 modelList.add(temperaturprogramme);
		
		 temperaturprogramme = new AnalyseTemperaturprogramme();
		 temperaturprogramme.setTabelle("2");
		 temperaturprogramme.setSchritt("2");
		 temperaturprogramme.setTemperatur("180");
		 temperaturprogramme.setRampe(null);
		 temperaturprogramme.setZeit("30");
		 temperaturprogramme.setSegmenttyp("Isotherme");
		 modelList.add(temperaturprogramme);
		
		 temperaturprogramme = new AnalyseTemperaturprogramme();
		 temperaturprogramme.setTabelle("2");
		 temperaturprogramme.setSchritt("3");
		 temperaturprogramme.setTemperatur("25");
		 temperaturprogramme.setRampe("10");
		 temperaturprogramme.setZeit(null);
		 temperaturprogramme.setSegmenttyp("Dynamisch");
		 modelList.add(temperaturprogramme);

		// ------------Test-Daten fuer Suche------------//

		// Partner partner1 = new Partner();
		// partner1.setName("Partner A");
		// partner1.setPrimaryKey("1");
		// partner1.setEmail("partner-A@gmail.com");
		// modelList.add(partner1);
		//
		// Partner partner2 = new Partner();
		// partner2.setName("Partner B");
		// partner2.setPrimaryKey("2");
		// partner2.setEmail("partner-B@gmx.com");
		// modelList.add(partner2);
		//
		// Projekt projekt1 = new Projekt(partner1);
		// projekt1.setPrimaryKey("Projekt A");
		// projekt1.setVertragsnummer("1");
		// modelList.add(projekt1);
		//
		// Projekt projekt2 = new Projekt(partner2);
		// projekt2.setPrimaryKey("Projekt B");
		// projekt2.setVertragsnummer("2");
		// modelList.add(projekt2);
		//
		// Probe probe1 = new Probe(projekt1);
		// probe1.setPrimaryKey("0001A");
		// probe1.setName("Probe A");
		// modelList.add(probe1);
		//
		// Probe probe2 = new Probe(projekt2);
		// probe2.setPrimaryKey("0001B");
		// probe2.setName("Probe B");
		// modelList.add(probe2);
		//
		// Experiment experiment1 = new Experiment(probe1);
		// experiment1.setPrimaryKey("0001Aexp0");
		// experiment1.setTyp("Slurry");
		// modelList.add(experiment1);
		//
		// Experiment experiment2 = new Experiment(probe1);
		// experiment2.setPrimaryKey("0001Aexp1");
		// experiment2.setTyp("Vedampfung");
		// modelList.add(experiment2);
		//
		// Experiment experiment3 = new Experiment(probe2);
		// experiment3.setPrimaryKey("0001Bexp0");
		// experiment3.setTyp("Slurry");
		// modelList.add(experiment3);
		//
		// Experiment experiment4 = new Experiment(probe2);
		// experiment4.setPrimaryKey("0001Bexp1");
		// experiment4.setTyp("Vedampfung");
		// modelList.add(experiment4);
	}

	@Override
	public void getModel(Model requestedModel) throws SQLException, ModelNotFoundException {
		for (Model model : modelList) {
			if (tableNotFound(requestedModel, model))
				continue;
			if (primaryKeyNotFound(requestedModel, model))
				continue;

			DummyResultSet dummyResultSet = model.returnAsDummyResultSet();
			requestedModel.setAttributes(dummyResultSet);

			break;
		}
	}

	@Override
	public void getModelAnalyseTemperaturprogramme(AnalyseTemperaturprogramme requestedModel) throws ModelNotFoundException, SQLException {
		for (Model model : modelList) {
			if (tableNotFound(requestedModel, model))
				continue;
			if (compositeKeyNotFound(requestedModel, model))
				continue;

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

	private boolean tableNotFound(String requestedTable, Model model) {
		return !model.getTable().equals(requestedTable);
	}

	@Override
	public void replaceModel(Model model) throws SQLException {
		if (model instanceof AnalyseTemperaturprogramme)
			modelList.removeIf(listModel -> {
				if (listModel instanceof AnalyseTemperaturprogramme)
					return Arrays.equals(((AnalyseTemperaturprogramme) listModel).getPrimaryKeys(), ((AnalyseTemperaturprogramme) model).getPrimaryKeys());
				return false;
			});
		else
			modelList.removeIf(listModel -> listModel.getPrimaryKey().equals(model.getPrimaryKey()));
		modelList.add(model);
	}

	@Override
	public void updateModel(Model updatedModel) throws SQLException {

		for (int i = 0; i < modelList.size(); i++) {

			Model oldModel = modelList.get(i);

			if (tableNotFound(updatedModel, oldModel))
				continue;
			if (primaryKeyNotFound(updatedModel, oldModel))
				continue;

			System.out.println("---------------" + updatedModel.toJSON());
			if (updatedModel.getParents().size() > 0)
				System.out.println("parent = " + updatedModel.getParents().get(0).toJSON());
			System.out.println("child = " + updatedModel.getChildren().get(0).toJSON());

			updatedModel.getParents().addAll(oldModel.getParents());
			updatedModel.getChildren().addAll(oldModel.getChildren());

			System.out.println("---------------");
			if (updatedModel.getParents().size() > 0)
				System.out.println("parent = " + updatedModel.getParents().get(0).toJSON());
			System.out.println("child = " + updatedModel.getChildren().get(0).toJSON());

			modelList.remove(i);
			modelList.add(updatedModel);

			break;
		}
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
			if (!listModel.getTable().equals(requestedModel.getTable()))
				continue;
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
				if (tableNotFound(requestedTable, model))
					continue;
				if (primaryKeyNotFound(requestedKey, model))
					continue;

				DummyResultSetEntry dummyResultSetEntry = new DummyResultSetEntry();
				dummyResultSetEntry.addKeyValuePair(model.getPrimaryKeyColumn(), model.getPrimaryKey());
				dummyResultSet.addEntry(dummyResultSetEntry);
			}

			relation.setAttributes(dummyResultSet);

		}
		catch (NoSuchFieldException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public <M extends Model, N extends Model> void resolveManyToMany(ManyToManyA<N, M> manyToMany)
			throws NoSuchFieldException, IllegalAccessException, SQLException {
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
		}).forEach(model -> dummyResultSet.addResultSet(model.returnAsDummyResultSet()));

		return dummyResultSet;
	}

	@Override
	public Model findModel(Model requestedModel) throws SQLException, ModelNotFoundException {

		Model foundModel = null;

		for (Model model : modelList) {

			if (tableNotFound(requestedModel, model))
				continue;
			if (primaryKeyNotFound(requestedModel, model))
				continue;

			foundModel = model;
			break;
		}

		return foundModel;
	}

	@Override
	public void saveModel(Model model) throws SQLException {
		for (Model m : modelList)
			if (model.getTable().equals(m.getTable()) && model.getPrimaryKey().equals(m.getPrimaryKey()))
				throw new SQLException("Duplicate primary key");
		modelList.add(model);
	}

	private List<Model> cloneBranch(List<Model> branch) {
		List<Model> newBranch = new LinkedList<>();
		newBranch.addAll(branch);
		return newBranch;
	}

	private void extendBranch(List<Model> branch, Model nextModel, List<List<Model>> branches) {

		branch.add(nextModel);

		while (nextModel.hasChildren()) {

			if (nextModel.getChildren().size() > 1) {

				for (int i = 1; i < nextModel.getChildren().size(); i++) {

					Model childNode = nextModel.getChildren().get(i);
					List<Model> newBranch = cloneBranch(branch);
					branches.add(newBranch);
					extendBranch(newBranch, childNode, branches);
				}
			}

			nextModel = nextModel.getChildren().get(0);
			branch.add(nextModel);
		}
	}

	private List<Model> getAllRootModels() {
		List<Model> rootModelList = new LinkedList<>();

		for (Model model : modelList) {
			if (model.getClass() == Partner.class)
				rootModelList.add(model);
			// if(!model.hasParents()) rootModelList.add(model);
		}
		return rootModelList;
	}

	@Override
	public List<List<Model>> getDatabaseAsTupelList() {

		List<List<Model>> branches = new LinkedList<>();

		List<Model> rootModelList = getAllRootModels();

		for (Model rootModel : rootModelList) {

			List<Model> branch = new LinkedList<>();
			branches.add(branch);
			extendBranch(branch, rootModel, branches);
		}

		return branches;
	}
}
