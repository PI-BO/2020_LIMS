package controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());

		setAccessControlHeaders(response);
		response.setStatus(HttpServletResponse.SC_OK);

		String objectToReturn = "key1: 'value1', key2: 'value2'";

//		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		out.print(objectToReturn);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		setAccessControlHeaders(response);
//		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		
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
		
		JSON operator1= new JSON();
		operator1.addKeyValue("pk", "1");
		operator1.addKeyValue("name", "John Doe");
		operator1.addKeyValue("fk", "1");
		
		JSON operator2= new JSON();
		operator2.addKeyValue("pk", "2");
		operator2.addKeyValue("name", "Jane Doe");
		operator2.addKeyValue("fk", "2");
		
		JSON operator3= new JSON();
		operator3.addKeyValue("pk", "2");
		operator3.addKeyValue("name", "Jane Doe");
		operator3.addKeyValue("fk", "3");
		
		JSON operator4= new JSON();
		operator4.addKeyValue("pk", "2");
		operator4.addKeyValue("name", "Jane Doe");
		operator4.addKeyValue("fk", "4");
		
		
		
		

//		String objectToReturn = "[ { \"blaArray\":\"test\" } ]";
//		String objectToReturn2 = "{ \"blaArray\":\"test\",\"blaArray2\":\"test2\" }";
//		String objectToReturn3 = "{ \"array 1\": [ { \"param 1\":\"value 1\" }, { \"param 2\":\"value 2\" } ] }";
//		String objectToReturn4 = "[ { \"array 1\": [ { \"param 1\":\"value 1\" } ] } ]";
//		String objectToReturn5 = "{ \"json 1\": { \"param 1\":\"value 1\" } }";
//		String objectToReturn6 = "[ [ { \"param 1\":\"value 1\" } ] ]";
//		
//		JSON json = new JSON();
//		json.addKeyValue("parameter 1", "value 1");
//		json.addKeyValue("parameter 2", "value 2");
////		out.print(json.toString());
//		
//		JSON json2 = new JSON();
//		json2.addKeyValue("parameter 3", "value 3");
//		json2.addKeyValue("parameter 4", "value 4");
////		out.print(json2.toString());
//
//		JSONArray jsonArray = new JSONArray();
//		jsonArray.addJSON(json);
//		
//		json = new JSON();
//		json.addKeyValue("parameter 3", "value 3");
//		json.addKeyValue("parameter 4", "value 4");
//		
//		
//		jsonArray.addJSON(json);
////		jsonArray.addJSON(json2);
////		out.print(jsonArray.toString());
//		
//		JSON jsonWithArray = new JSON();
//		jsonWithArray.addJSONArray("jsonWithArray", jsonArray);
//		out.print(jsonWithArray.toString());
//		
//		JSON jsonWithJSON = new JSON();
//		jsonWithJSON.addJSON("jsonWithJSON", json);
////		out.print(jsonWithJSON.toString());
		
	}

	private void setAccessControlHeaders(HttpServletResponse resp) {
		resp.setHeader("Access-Control-Allow-Origin", "*");
	}

}
