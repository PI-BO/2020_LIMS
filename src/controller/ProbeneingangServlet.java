package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet(ProbeneingangServlet.ROUTE)
public class ProbeneingangServlet extends HttpServlet {

	private static final Logger LOGGER = LogManager.getLogger(ProbeneingangServlet.class.getSimpleName());

	private static final long serialVersionUID = 7322122506656092712L;
	public static final String ROUTE = "/probeneingang";
		
	public static final String INTERNE_VERGABENUMMER = "INTERNE_VERGABENUMMER";
	public static final String WIRKSTOFF = "WIRKSTOFF";
	public static final String AUFTRAGGEBER = "AUFTRAGGEBER";
	public static final String PROBEN_NR = "PROBEN_NR";
	public static final String PROJEKTVERTRAGNUMMER= "PROJEKTVERTRAGNUMMER";
	public static final String ANLAGENNUMMER = "ANLAGENNUMMER";
	public static final String SUMMENFORMEL = "SUMMENFORMEL";
	public static final String BEZEICHNUNG = "BEZEICHNUNG";
	public static final String ORIGINATOR = "ORIGINATOR";
	public static final String PROBENEINGANG = "PROBENEINGANG";
	public static final String PROBENMASSE = "PROBENMASSE";
	public static final String BESONDERHEITEN = "BESONDERHEITEN";
	public static final String INFOS = "INFOS";
	public static final String STANDORT = "STANDORT";
	public static final String MESSUNG_DSC = "MESSUNG_DSC";
	public static final String MESSUNG_PULVER = "MESSUNG_PULVER";
	public static final String MESSUNG_IR = "MESSUNG_IR";
	public static final String BEMERKUNGEN_ZUR_MESSUNG = "BEMERKUNGEN_ZUR_MESSUNG";
	public static final String VERTRAG_VORHANDEN = "VERTRAG_VORHANDEN";
	public static final String VERTRAG_VORHANDEN_DATUM = "VERTRAG_VORHANDEN_DATUM";
	public static final String VERTRAG_UNTERZEICHNET = "VERTRAG_UNTERZEICHNET";
	public static final String VERTRAG_UNTERZEICHNET_DATUM = "VERTRAG_UNTERZEICHNET_DATUM";
	public static final String VERTRAG_VERSCHICKT = "VERTRAG_VERSCHICKT";
	public static final String VERTRAG_VERSCHICKT_DATUM = "VERTRAG_VERSCHICKT_DATUM";
	public static final String VERTRAG_ABGERECHNET = "VERTRAG_ABGERECHNET";
	public static final String VERTRAG_ABGERECHNET_DATUM = "VERTRAG_ABGERECHNET_DATUM";
	public static final String VERTRAG_BEZAHLT = "VERTRAG_BEZAHLT";
	public static final String VERTRAG_BEZAHLT_DATUM = "VERTRAG_BEZAHLT_DATUM";
	public static final String BEMERKUNGEN = "BEMERKUNGEN";
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("TEST SERVLET: doPost()");
    	
    	Enumeration<String> attributeNames = request.getAttributeNames();
    	
    	System.out.println("start - attribute names:");
    	
    	String attributeName;
    	if(attributeNames.hasMoreElements()) attributeName = attributeNames.nextElement();
    	
    	while(attributeNames.hasMoreElements()){
    		
    		System.out.println("attribute names:");
    		attributeName = attributeNames.nextElement();
    		if(attributeName == null) continue;
//    		String attribute = (String)request.getAttribute(attributeName);
//    		System.out.println(attributeName + " : " + attribute);
    		System.out.println(attributeName);
    	}
    	
    	System.out.println("end - attribute names:");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("TEST SERVLET: doGet()");
    	System.out.println("TEST SERVLET: doGet() nothing implemented yet");
    }
}