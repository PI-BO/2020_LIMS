package controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.database.Database;
import model.database.dummyDB.DummyDB;
import model.database.manager.DatabaseManager;
import model.database.tableModels.Model;
import utility.JSON;
import utility.JSONArray;

@WebServlet(SucheServlet.ROUTE)
public class SucheServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ROUTE = "/Suche";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SucheServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		setAccessControlHeaders(response); // TODO nur fuer Testzwecke! in
											// Produktion rausnehmen!
		// response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		Database database = DatabaseManager.getDatabaseInstance();

		List<List<Model>> branches = database.getDatabaseAsTupelList();
		
//		System.out.println("------------------------");
////		
//		DummyDB dummyDB = (DummyDB)database;
//		System.out.println("size = "+ dummyDB.modelList.size());
//		for(Model model : dummyDB.modelList){
//			System.out.println(model.toJSON() + "\t" + model.hasChildren());
//			for(Model child : model.getChildren()) System.out.println("\t" + child.toJSON() + "\t" + child.hasChildren());
//		}

		sendRelationsAsJSONArray(out, branches);

		// sendJavascriptJson(out);

		// sendTestJson(out);

	}

	private void sendTestJson(PrintWriter out) {
		String objectToReturn = "[ { \"blaArray\":\"test\" } ]";
		String objectToReturn2 = "{ \"blaArray\":\"test\",\"blaArray2\":\"test2\" }";
		String objectToReturn3 = "{ \"array 1\": [ { \"param 1\":\"value 1\" }, { \"param 2\":\"value 2\" } ] }";
		String objectToReturn4 = "[ { \"array 1\": [ { \"param 1\":\"value 1\" } ] } ]";
		String objectToReturn5 = "{ \"json 1\": { \"param 1\":\"value 1\" } }";
		String objectToReturn6 = "[ [ { \"param 1\":\"value 1\" } ] ]";

		JSON json = new JSON();
		json.addKeyValue("parameter 1", "value 1");
		json.addKeyValue("parameter 2", "value 2");
		// out.print(json.toString());

		JSON json2 = new JSON();
		json2.addKeyValue("parameter 3", "value 3");
		json2.addKeyValue("parameter 4", "value 4");
		// out.print(json2.toString());

		JSONArray jsonArray = new JSONArray();
		jsonArray.addJSON(json);

		json = new JSON();
		json.addKeyValue("parameter 3", "value 3");
		json.addKeyValue("parameter 4", "value 4");

		jsonArray.addJSON(json);
		// jsonArray.addJSON(json2);
		// out.print(jsonArray.toString());

		JSON jsonWithArray = new JSON();
		jsonWithArray.addJSONArray("jsonWithArray", jsonArray);
		out.print(jsonWithArray.toString());

		JSON jsonWithJSON = new JSON();
		jsonWithJSON.addJSON("jsonWithJSON", json);
		// out.print(jsonWithJSON.toString());
	}

	private void sendJavascriptJson(PrintWriter out) {
		JSON partner1 = new JSON();
		partner1.addKeyValue("pk", "1");
		partner1.addKeyValue("name", "Partner A");

		JSON projekt1 = new JSON();
		projekt1.addKeyValue("pk", "1");
		projekt1.addKeyValue("name", "Projekt 1");
		projekt1.addKeyValue("fk", "1");

		JSON probe1 = new JSON();
		probe1.addKeyValue("pk", "1");
		probe1.addKeyValue("name", "Probe 1");
		probe1.addKeyValue("fk", "1");

		JSON experiment1 = new JSON();
		experiment1.addKeyValue("pk", "1");
		experiment1.addKeyValue("name", "Experiment 1");
		experiment1.addKeyValue("fk", "1");

		JSON experiment2 = new JSON();
		experiment2.addKeyValue("pk", "2");
		experiment2.addKeyValue("name", "Experiment 2");
		experiment2.addKeyValue("fk", "1");

		JSON methode1 = new JSON();
		methode1.addKeyValue("pk", "1");
		methode1.addKeyValue("name", "Methode 1");
		methode1.addKeyValue("fk", "1");

		JSON methode2 = new JSON();
		methode2.addKeyValue("pk", "2");
		methode2.addKeyValue("name", "Methode 2");
		methode2.addKeyValue("fk", "1");

		JSON methode3 = new JSON();
		methode3.addKeyValue("pk", "3");
		methode3.addKeyValue("name", "Methode 3");
		methode3.addKeyValue("fk", "2");

		JSON methode4 = new JSON();
		methode4.addKeyValue("pk", "4");
		methode4.addKeyValue("name", "Methode 4");
		methode4.addKeyValue("fk", "2");

		JSON operator1 = new JSON();
		operator1.addKeyValue("pk", "1");
		operator1.addKeyValue("name", "John Doe");
		operator1.addKeyValue("fk", "1");

		JSON operator2 = new JSON();
		operator2.addKeyValue("pk", "2");
		operator2.addKeyValue("name", "Jane Doe");
		operator2.addKeyValue("fk", "2");

		JSON operator3 = new JSON();
		operator3.addKeyValue("pk", "2");
		operator3.addKeyValue("name", "Jane Doe");
		operator3.addKeyValue("fk", "3");

		JSON operator4 = new JSON();
		operator4.addKeyValue("pk", "2");
		operator4.addKeyValue("name", "Jane Doe");
		operator4.addKeyValue("fk", "4");

		JSONArray projektpartner = new JSONArray();
		projektpartner.addJSON(partner1);

		JSONArray projekt = new JSONArray();
		projekt.addJSON(projekt1);

		JSONArray probe = new JSONArray();
		probe.addJSON(probe1);

		JSONArray experiment = new JSONArray();
		experiment.addJSON(experiment1);
		experiment.addJSON(experiment2);

		JSONArray methode = new JSONArray();
		methode.addJSON(methode1);
		methode.addJSON(methode2);
		methode.addJSON(methode3);
		methode.addJSON(methode4);

		JSONArray operator = new JSONArray();
		operator.addJSON(operator1);
		operator.addJSON(operator2);
		operator.addJSON(operator3);
		operator.addJSON(operator4);

		JSON database = new JSON();
		database.addJSONArray("projektpartner", projektpartner);
		database.addJSONArray("projekt", projekt);
		database.addJSONArray("probe", probe);
		database.addJSONArray("experiment", experiment);
		database.addJSONArray("methode", methode);
		database.addJSONArray("operator", operator);

		out.print(database.toString());
	}

	private void sendRelationsAsJSONArray(PrintWriter out, List<List<Model>> branches) {
		String databaseJson = "[";

		int index = 0;
		for (List<Model> branch : branches) {

			JSONArray jsonArray = new JSONArray();

			for (Model model : branch) {

				JSON modelJson = model.toJSON();
				jsonArray.addJSON(modelJson);
			}

			databaseJson += jsonArray.toString();
			index++;
			if (index < branches.size())
				databaseJson += ",";
		}
		databaseJson += "]";
		out.print(databaseJson);
	}

	private void setAccessControlHeaders(HttpServletResponse resp) {
		resp.setHeader("Access-Control-Allow-Origin", "*");
	}

}
