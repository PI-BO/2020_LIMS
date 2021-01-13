package view;

import java.io.IOException;

import config.Config;

public class SubstanzHTML implements HTMLPage{

	private final String REQUEST_PARAMETER = Config.getValue("html.requestParameter.ProjektPage");
	
	public SubstanzHTML() {

		
	}
	
	@Override
	public void show() throws IOException {
		// TODO Auto-generated method stub
		
	}

}
