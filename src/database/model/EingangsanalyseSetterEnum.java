package database.model;

public enum EingangsanalyseSetterEnum {

	NO_ID {
		@Override
		public void set(Eingangsanalyse eingangsanalyse, String value) {
			eingangsanalyse.setNoId(value);
		}
	},
	SCREENING_NO {
		@Override
		public void set(Eingangsanalyse eingangsanalyse, String value) {
			eingangsanalyse.setScreeningNo(value);			
		}
	},
	PLANUNG_ERFOLGT_DURCH {
		@Override
		public void set(Eingangsanalyse eingangsanalyse, String value) {
			eingangsanalyse.setPlanungErfolgtDurch(value);
		}
	},
	SUBSTANZ {
		@Override
		public void set(Eingangsanalyse eingangsanalyse, String value) {
			eingangsanalyse.setSubstanz(value);		
		}
	},
	API_STARTMATERIAL_REF_CODE {
		@Override
		public void set(Eingangsanalyse eingangsanalyse, String value) {
			eingangsanalyse.setApiStartMeterialRefCode(value);			
		}
	},
	PROJEKTLEITERNOTIZ {
		@Override
		public void set(Eingangsanalyse eingangsanalyse, String value) {
			eingangsanalyse.setProjektleiterNotiz(value);		}
	},
	VERWEIS {
		@Override
		public void set(Eingangsanalyse eingangsanalyse, String value) {
			eingangsanalyse.setVerweis(value);			
		}
	},
	STARTFREIGABE {
		@Override
		public void set(Eingangsanalyse eingangsanalyse, String value) {
			eingangsanalyse.setStartFreigabe(value);			
		}
	},
	ERLEDIGT_BIS_SOLL {
		@Override
		public void set(Eingangsanalyse eingangsanalyse, String value) {
			eingangsanalyse.setErledigtBisSoll(value);			
		}
	},
	HINWEIS {
		@Override
		public void set(Eingangsanalyse eingangsanalyse, String value) {
			eingangsanalyse.setHinweis(value);			
		}
	},
	PLANUNG_ABGESCHLOSSEN {
		@Override
		public void set(Eingangsanalyse eingangsanalyse, String value) {
			eingangsanalyse.setPlanungAbgeschlossen(value);			
		}
	},
	SICHERHEITSHINWEIS {
		@Override
		public void set(Eingangsanalyse eingangsanalyse, String value) {
			eingangsanalyse.setSicherheitshinweis(value);			
		}
	},
	METHODE {
		@Override
		public void set(Eingangsanalyse eingangsanalyse, String value) {
			eingangsanalyse.addMethode(value);			
		}
	},
	AUSWAHL {
		@Override
		public void set(Eingangsanalyse eingangsanalyse, String value) {
			eingangsanalyse.addAuswahl(value);			
		}
	},
	OPERATOR {
		@Override
		public void set(Eingangsanalyse eingangsanalyse, String value) {
			eingangsanalyse.addOperator(value);			
		}
	},
	PARAMETER {
		@Override
		public void set(Eingangsanalyse eingangsanalyse, String value) {
			eingangsanalyse.addParameter(value);			
		}
	},
	MESSFILE {
		@Override
		public void set(Eingangsanalyse eingangsanalyse, String value) {
			eingangsanalyse.addMessfile(value);			
		}
	},
	STATUS {
		@Override
		public void set(Eingangsanalyse eingangsanalyse, String value) {
			eingangsanalyse.addStatus(value);			
		}
	};
	
	public abstract void set(Eingangsanalyse eingangsanalyse, String value);
}
