package model.database.dummyDB;

import config.Const;
import exceptions.DublicateModelException;
import exceptions.ModelNotFoundException;
import model.database.Database;
import model.database.relations.ManyToManyA;
import model.database.relations.OneToMany;
import model.database.tableModels.*;
import model.database.tableModels.analyse.AnalyseTemperaturprogramme;
import model.database.tableModels.analyse.Analysetyp;
import model.database.tableModels.experimente.*;
import model.database.tableModels.experimente.ExperimenttypSlurry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DummyDB implements Database {

	private final List<Model> modelList = new LinkedList<>();

	public void initModels() throws ModelNotFoundException, SQLException, DublicateModelException {
		// Rollen
		MitarbeiterRolle rolle = new MitarbeiterRolle();
		rolle.setPrimaryKey("1");
		rolle.setTyp("Projektplanung");
		rolle.setZugehoerigkeit("Projektmanager");
		rolle.saveToDatabase();

		rolle = new MitarbeiterRolle();
		rolle.setPrimaryKey("2");
		rolle.setTyp("Laborbetreuung");
		rolle.setZugehoerigkeit("Laborleiter");
		rolle.saveToDatabase();

		rolle = new MitarbeiterRolle();
		rolle.setPrimaryKey("3");
		rolle.setTyp("Durchfuehrung");
		rolle.setZugehoerigkeit("Laborteam");
		rolle.saveToDatabase();

		// Mitarbeiter
		Mitarbeiter mitarbeiter1 = new Mitarbeiter();
		mitarbeiter1.setVorname("Max");
		mitarbeiter1.setNachname("Mustermann");
		mitarbeiter1.setPassword("abc");
		mitarbeiter1.setPrimaryKey("123");
		mitarbeiter1.setRolle(1);
		mitarbeiter1.saveToDatabase();

		Mitarbeiter mitarbeiter2 = new Mitarbeiter();
		mitarbeiter2.setVorname("Maxime");
		mitarbeiter2.setNachname("Musterfrau");
		mitarbeiter2.setPassword("dot");
		mitarbeiter2.setPrimaryKey("987");
		mitarbeiter2.setRolle(2);
		mitarbeiter2.saveToDatabase();

		Mitarbeiter mitarbeiter3 = new Mitarbeiter();
		mitarbeiter3.setVorname("Harry");
		mitarbeiter3.setNachname("Potter");
		mitarbeiter3.setPassword("qwe");
		mitarbeiter3.setPrimaryKey("456");
		mitarbeiter3.setRolle(3);
		mitarbeiter3.saveToDatabase();

		// Partner
		Partner partner = new Partner();
		partner.setPrimaryKey("1");
		partner.setName("Bayer");
		partner.setEmail("bayer@Unternehmen.de");
		partner.saveToDatabase();

		partner = new Partner();
		partner.setPrimaryKey("2");
		partner.setName("Novartis");
		partner.setEmail("novartis@pharma.com");
		partner.saveToDatabase();

		// Projekte
		Projekt projekt = new Projekt();
		projekt.setPrimaryKey("1");
		projekt.setVertragsnummer("1");
		projekt.setProjektPartnerId("1");
		projekt.saveToDatabase();

		projekt = new Projekt();
		projekt.setPrimaryKey("2");
		projekt.setVertragsnummer("1");
		projekt.setProjektPartnerId("1");
		projekt.saveToDatabase();

		projekt = new Projekt();
		projekt.setPrimaryKey("3");
		projekt.setVertragsnummer("2");
		projekt.setProjektPartnerId("2");
		projekt.saveToDatabase();

		// Proben
		Probe probe = new Probe();
		probe.setPrimaryKey("ProbeA1");
		probe.setProjektID("1");
		probe.saveToDatabase();

		probe = new Probe();
		probe.setPrimaryKey("ProbeA2");
		probe.setProjektID("1");
		probe.saveToDatabase();

		probe = new Probe();
		probe.setPrimaryKey("ProbeB1");
		probe.setProjektID("2");
		probe.saveToDatabase();

		probe = new Probe();
		probe.setPrimaryKey("ProbeB");
		probe.setProjektID("2");
		probe.saveToDatabase();

		probe = new Probe();
		probe.setPrimaryKey("ProbeC");
		probe.setProjektID("3");
		probe.saveToDatabase();

		probe = new Probe();
		probe.setPrimaryKey("ProbeC2");
		probe.setProjektID("3");
		probe.saveToDatabase();

		probe = new Probe();
		probe.setPrimaryKey("ProbeD");
		probe.setProjektID("3");
		probe.saveToDatabase();

		probe = new Probe();
		probe.setPrimaryKey("ProbeD2");
		probe.setProjektID("3");
		probe.saveToDatabase();

		probe = new Probe();
		probe.setPrimaryKey("ProbeE");
		probe.setProjektID("3");
		probe.saveToDatabase();

		probe = new Probe();
		probe.setPrimaryKey("ProbeE2");
		probe.setProjektID("3");
		probe.saveToDatabase();

		probe = new Probe();
		probe.setPrimaryKey("ProbeF");
		probe.setProjektID("3");
		probe.saveToDatabase();

		probe = new Probe();
		probe.setPrimaryKey("ProbeF2");
		probe.setProjektID("3");
		probe.saveToDatabase();

		// Experimente
		Experiment experiment = new Experiment();
		experiment.setPrimaryKey("11");
		experiment.setTyp("101");
		experiment.setProbenNr("ProbeA1");
		experiment.saveToDatabase();

		experiment = new Experiment();
		experiment.setPrimaryKey("2");
		experiment.setTyp("202");
		experiment.setProbenNr("ProbeB");
		experiment.saveToDatabase();

		// Experimente Verdampfung
		ExperimenttypVerdampfung experimenttypVerdampfung = new ExperimenttypVerdampfung();
		experimenttypVerdampfung.setPrimaryKey("11");
		experimenttypVerdampfung.setExperiment_no("Experiment1Verdampfung");
		experimenttypVerdampfung.setDurchfuehrung("Durchfuehrungstext A");
		modelList.add(experimenttypVerdampfung);

		// Experimente Slurry
		ExperimenttypSlurry experimenttypSlurry = new ExperimenttypSlurry();
		experimenttypSlurry.setPrimaryKey("2");
		experimenttypSlurry.setExperiment_no("Experiment2Slurry");
		experimenttypSlurry.setDurchfuehrung("Durchfuehrungstext B");
		modelList.add(experimenttypSlurry);

		// Experiment Typen
		Experimenttyp experimenttyp = new Experimenttyp();
		experimenttyp.setPrimaryKey("101");
		experimenttyp.setTyp(Const.EXPERIMENT_TYP_VERDAMPFUNG);
		experimenttyp.saveToDatabase();

		experimenttyp = new Experimenttyp();
		experimenttyp.setPrimaryKey("202");
		experimenttyp.setTyp(Const.EXPERIMENT_TYP_SLURRY);
		experimenttyp.saveToDatabase();

		// Experiment Durchfuehrungstext
		ExperimentDurchfuehrungstext experimentDurchfuehrungstext = new ExperimentDurchfuehrungstext();
		experimentDurchfuehrungstext.setPrimaryKey("Durchfuehrungstext A");

		// TODO Sonderzeichen machen Probleme

		experimentDurchfuehrungstext.setText("20-30 mg API in 4.0 mL Vial mit PTFE Dichtung einwiegen.\n" + "Die genaue Einwaage in den Datensatz eintragen.\n"
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
		experimentDurchfuehrungstext.saveToDatabase();

		 experimentDurchfuehrungstext = new ExperimentDurchfuehrungstext();
		experimentDurchfuehrungstext.setPrimaryKey("Durchfuehrungstext B");

		// TODO Sonderzeichen machen Probleme

		experimentDurchfuehrungstext.setText("20-30 mg API in 4.0 mL Vial mit PTFE Dichtung einwiegen.\n" + "Die genaue Einwaage in den Datensatz eintragen.\n"
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
		experimentDurchfuehrungstext.saveToDatabase();

		 // Analysetyp
		 Analysetyp analysetyp = new Analysetyp();
		 analysetyp.setPrimaryKey("1");
		 analysetyp.setTyp("PXRD");
		 analysetyp.saveToDatabase();
		
		 analysetyp = new Analysetyp();
		 analysetyp.setPrimaryKey("2");
		 analysetyp.setTyp("DSC");
		 analysetyp.saveToDatabase();
		
		 analysetyp = new Analysetyp();
		 analysetyp.setPrimaryKey("3");
		 analysetyp.setTyp("TGA");
		 analysetyp.saveToDatabase();
		
		 analysetyp = new Analysetyp();
		 analysetyp.setPrimaryKey("4");
		 analysetyp.setTyp("IR");
		 analysetyp.saveToDatabase();
		
		 // temperaturprogramme
		 AnalyseTemperaturprogramme temperaturprogramme = new
		 AnalyseTemperaturprogramme();
		 temperaturprogramme.setTabelle("1");
		 temperaturprogramme.setSchritt("1");
		 temperaturprogramme.setTemperatur("180");
		 temperaturprogramme.setRampe("10");
		 temperaturprogramme.setZeit(null);
		 temperaturprogramme.setSegmenttyp("Dynamisch");
		 temperaturprogramme.saveToDatabase();
		
		 temperaturprogramme = new AnalyseTemperaturprogramme();
		 temperaturprogramme.setTabelle("1");
		 temperaturprogramme.setSchritt("2");
		 temperaturprogramme.setTemperatur("25");
		 temperaturprogramme.setRampe("10");
		 temperaturprogramme.setZeit(null);
		 temperaturprogramme.setSegmenttyp("Dynamisch");
		 temperaturprogramme.saveToDatabase();
		
		 temperaturprogramme = new AnalyseTemperaturprogramme();
		 temperaturprogramme.setTabelle("1");
		 temperaturprogramme.setSchritt("3");
		 temperaturprogramme.setTemperatur("180");
		 temperaturprogramme.setRampe("10");
		 temperaturprogramme.setZeit(null);
		 temperaturprogramme.setSegmenttyp("Dynamisch");
		 temperaturprogramme.saveToDatabase();
		
		 temperaturprogramme = new AnalyseTemperaturprogramme();
		 temperaturprogramme.setTabelle("1");
		 temperaturprogramme.setSchritt("4");
		 temperaturprogramme.setTemperatur("25");
		 temperaturprogramme.setRampe("10");
		 temperaturprogramme.setZeit(null);
		 temperaturprogramme.setSegmenttyp("Dynamisch");
		 temperaturprogramme.saveToDatabase();
		
		 temperaturprogramme = new AnalyseTemperaturprogramme();
		 temperaturprogramme.setTabelle("2");
		 temperaturprogramme.setSchritt("1");
		 temperaturprogramme.setTemperatur("180");
		 temperaturprogramme.setRampe("10");
		 temperaturprogramme.setZeit(null);
		 temperaturprogramme.setSegmenttyp("Dynamisch");
		 temperaturprogramme.saveToDatabase();
		
		 temperaturprogramme = new AnalyseTemperaturprogramme();
		 temperaturprogramme.setTabelle("2");
		 temperaturprogramme.setSchritt("2");
		 temperaturprogramme.setTemperatur("180");
		 temperaturprogramme.setRampe(null);
		 temperaturprogramme.setZeit("30");
		 temperaturprogramme.setSegmenttyp("Isotherme");
		 temperaturprogramme.saveToDatabase();
		
		 temperaturprogramme = new AnalyseTemperaturprogramme();
		 temperaturprogramme.setTabelle("2");
		 temperaturprogramme.setSchritt("3");
		 temperaturprogramme.setTemperatur("25");
		 temperaturprogramme.setRampe("10");
		 temperaturprogramme.setZeit(null);
		 temperaturprogramme.setSegmenttyp("Dynamisch");
		 temperaturprogramme.saveToDatabase();
	}

	@Override
	public void getModel(Model requestedModel) throws SQLException, ModelNotFoundException {
		for (Model model : modelList) {
			if (tableNotFound(requestedModel, model)) continue;
			if (primaryKeyNotFound(requestedModel, model)) continue;

			DummyResultSet dummyResultSet = model.returnAsDummyResultSet();
			requestedModel.setAttributes(dummyResultSet);

			return;
		}
		
		throw new ModelNotFoundException("requestedModel: " + requestedModel.getClass().toString());
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
			if (updatedModel.getChildren().size() > 0)
				System.out.println("child = " + updatedModel.getChildren().get(0).toJSON());

			updatedModel.getParents().addAll(oldModel.getParents());
			updatedModel.getChildren().addAll(oldModel.getChildren());

			System.out.println("---------------");
			if (updatedModel.getParents().size() > 0)
				System.out.println("parent = " + updatedModel.getParents().get(0).toJSON());
			if (updatedModel.getChildren().size() > 0)
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
		
		if(foundModel == null) throw new ModelNotFoundException();
		
		return foundModel;
	}

	@Override
	public void saveModel(Model model) throws SQLException, DublicateModelException {
		try
		{
			findModel(model);
		}
		catch (ModelNotFoundException e) {
			modelList.add(model);
			return;
		}
		
		throw new DublicateModelException(model);
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
