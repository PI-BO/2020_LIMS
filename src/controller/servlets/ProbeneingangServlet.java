package controller.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.Probeneingang;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;

@WebServlet(ProbeneingangServlet.ROUTE)
@MultipartConfig
public class ProbeneingangServlet extends HttpServlet {

	private static final Logger LOGGER = LogManager.getLogger(ProbeneingangServlet.class.getSimpleName());

	private static final long serialVersionUID = 7322122506656092712L;
	public static final String ROUTE = "/probeneingang";
		
	public static final String INTERNE_VERGABENUMMER = "INTERNE_VERGABENUMMER";
	public static final String WIRKSTOFF = "WIRKSTOFF";
	public static final String AUFTRAGGEBER = "AUFTRAGGEBER_ID";
	public static final String PROBEN_NR = "PROBEN_NR";
	public static final String PROJEKTVERTRAGNUMMER= "PROJEKTVERTRAGNUMMER";
	public static final String ANLAGENNUMMER = "ANLAGENNUMMER";
	public static final String SUMMENFORMEL = "SUMMENFORMEL";
	public static final String BEZEICHNUNG = "BEZEICHNUNG";
	public static final String ORIGINATOR = "ORIGINATOR";
	public static final String PROBENEINGANG = "PROBENEINGANG_DATUM";
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
	public static final String LITERATUR = "LITERATUR";
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException{
    	
    	System.out.println(this.getClass() + ": doPost()");
    	LOGGER.debug("doPost()");
    	
		try {
//			printTestLog(request);
			Probeneingang probeneingang = createProbeneingang(request);
			probeneingang.saveToDatabasePlaceholderMethod();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private Probeneingang createProbeneingang(HttpServletRequest request) throws IOException, ServletException {
    	
    	Probeneingang probeneingang = new Probeneingang();
    	
    	Enumeration<String> parameterNames = request.getParameterNames();
    	
    	String parameterName;
    	if(parameterNames.hasMoreElements()) parameterName = parameterNames.nextElement();
    	
    	while(parameterNames.hasMoreElements()){
    		
    		parameterName = parameterNames.nextElement();
    		
    		if(parameterName == null) continue;
    		
    		String parameter = (String)request.getParameter(parameterName);
    		LOGGER.debug(parameterName + ": " + parameter);
    		
    		if(parameterName == ANLAGENNUMMER)                     		{ probeneingang.setAnlagennummer(parameter); 			 continue; }
    		if(parameterName == AUFTRAGGEBER)                          	{ probeneingang.setAuftraggeber(parameter);              continue; }
    		if(parameterName == BEMERKUNGEN)                           	{ probeneingang.setBemerkungen(parameter);               continue; }
    		if(parameterName == BEMERKUNGEN_ZUR_MESSUNG)               	{ probeneingang.setBemerkungenZurMessung(parameter);     continue; }
    		if(parameterName == BESONDERHEITEN)                        	{ probeneingang.setBesonderheiten(parameter);            continue; }
    		if(parameterName == BEZEICHNUNG)                           	{ probeneingang.setBezeichnung(parameter);               continue; }
    		if(parameterName == INFOS)                                 	{ probeneingang.setInfos(parameter);                     continue; }
    		if(parameterName == INTERNE_VERGABENUMMER)                 	{ probeneingang.setInterneVergabenummer(parameter);      continue; }
    		if(parameterName == LITERATUR)                             	{ probeneingang.setLiteratur(parameter);                 continue; }
    		if(parameterName == MESSUNG_DSC)                           	{ probeneingang.setMessungDSC(parameter);                continue; }
    		if(parameterName == MESSUNG_IR)                            	{ probeneingang.setMessungIR(parameter);                 continue; }
    		if(parameterName == MESSUNG_PULVER)                        	{ probeneingang.setMessungPulver(parameter);             continue; }
    		if(parameterName == ORIGINATOR)                            	{ probeneingang.setOriginator(parameter);                continue; }
    		if(parameterName == PROBEN_NR)                             	{ probeneingang.setProbenNr(parameter);                  continue; }
    		if(parameterName == PROBENEINGANG)                         	{ probeneingang.setProbeneingangDatum(parameter);        continue; }
    		if(parameterName == PROBENMASSE)                           	{ probeneingang.setProbenmasse(parameter);               continue; }
    		if(parameterName == PROJEKTVERTRAGNUMMER)                  	{ probeneingang.setProjektvertragnummer(parameter);      continue; }
    		if(parameterName == STANDORT)                              	{ probeneingang.setStandort(parameter);                  continue; }
    		if(parameterName == SUMMENFORMEL)                          	{ probeneingang.setSummenformel(parameter);              continue; }
    		if(parameterName == VERTRAG_ABGERECHNET)                   	{ probeneingang.setVertragAbgerechnet(true);             continue; }
    		if(parameterName == VERTRAG_ABGERECHNET_DATUM)             	{ probeneingang.setVertragAbgerechnetDatum(parameter);   continue; }
    		if(parameterName == VERTRAG_BEZAHLT)                       	{ probeneingang.setVertragBezahlt(true);                 continue; }
    		if(parameterName == VERTRAG_BEZAHLT_DATUM)                 	{ probeneingang.setVertragBezahltDatum(parameter);       continue; }
    		if(parameterName == VERTRAG_UNTERZEICHNET)                 	{ probeneingang.setVertragUnterzeichnet(true);           continue; }
    		if(parameterName == VERTRAG_UNTERZEICHNET_DATUM)           	{ probeneingang.setVertragUnterzeichnetDatum(parameter); continue; }
    		if(parameterName == VERTRAG_VERSCHICKT)                    	{ probeneingang.setVertragVerschickt(true);              continue; }
    		if(parameterName == VERTRAG_VERSCHICKT_DATUM)              	{ probeneingang.setVertragVerschicktDatum(parameter);    continue; }
    		if(parameterName == VERTRAG_VORHANDEN)                     	{ probeneingang.setVertragVorhanden(true);               continue; }
    		if(parameterName == VERTRAG_VORHANDEN_DATUM)               	{ probeneingang.setVertragVorhandenDatum(parameter);     continue; }
    		if(parameterName == WIRKSTOFF)                             	{ probeneingang.setWirkstoff(parameter);                 continue; }
    	}
    	
    	Collection<Part> parts = request.getParts();
		
		for(Part part : parts){
			
			if(part.getSubmittedFileName() == null) continue;
			
			LOGGER.debug("imagefileName: " + part.getSubmittedFileName());
			
			probeneingang.addBild(part.getInputStream());
		}
		
		return probeneingang;
	}

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.debug("doPost()");
    }

	private void printTestLog(HttpServletRequest request) throws IOException, ServletException {
    	
    	Enumeration<String> parameterNames = request.getParameterNames();
    	
    	System.out.println("\n----------PARAMETER----------\n");
    	
    	String parameterName;
    	if(parameterNames.hasMoreElements()) parameterName = parameterNames.nextElement();
    	
    	while(parameterNames.hasMoreElements()){
    		
    		parameterName = parameterNames.nextElement();
    		if(parameterName == null) continue;
    		String parameter = (String)request.getParameter(parameterName);
    		System.out.println(parameterName + " : " + parameter);
    	}
    	
    	System.out.println("\n----------PARTS----------\n");
    	
    	Collection<Part> parts = request.getParts();
		
		for(Part p : parts){
			System.out.println("fileName: " + p.getSubmittedFileName());
		}
	}
}