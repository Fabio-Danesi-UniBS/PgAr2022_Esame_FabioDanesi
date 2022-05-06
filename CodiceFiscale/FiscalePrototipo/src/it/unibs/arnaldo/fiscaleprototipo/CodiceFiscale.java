package it.unibs.arnaldo.fiscaleprototipo;

import java.util.*;

public class CodiceFiscale {

	private static final String[] mesi ={"A", "B", "C", "D", "E", "H", "L", "M", "P", "R", "S", "T"};
	private static int[] giorni ={31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	private String cognome;
	private String nome;
	private String anno;
	private String mese;
	private String giorno;
	private String luogo;
	private String controllo;
	
	
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getAnno() {
		return anno;
	}
	public void setAnno(String anno) {
		this.anno = anno;
	}
	public String getMese() {
		return mese;
	}
	public void setMese(String mese) {
		this.mese = mese;
	}
	public String getGiorno() {
		return giorno;
	}
	public void setGiorno(String giorno) {
		this.giorno = giorno;
	}
	public String getLuogo() {
		return luogo;
	}
	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}
	public String getControllo() {
		return controllo;
	}
	public void setControllo(String controllo) {
		this.controllo = controllo;
	}
	
	public CodiceFiscale(String cognome, String nome, String anno, String mese, String giorno, String luogo,
			String controllo) {
		super();
		this.cognome = cognome;
		this.nome = nome;
		this.anno = anno;
		this.mese = mese;
		this.giorno = giorno;
		this.luogo = luogo;
		this.controllo = controllo;
	}
	
	public CodiceFiscale(String stringaCodice) {
		//super();
		if(stringaCodice.length()==16) {
			this.cognome = stringaCodice.substring(0, 3);
			this.nome = stringaCodice.substring(3, 6);
			this.anno = stringaCodice.substring(6, 8);
			this.mese = stringaCodice.substring(8, 9);
			this.giorno = stringaCodice.substring(9, 11);
			this.luogo = stringaCodice.substring(11, 15);
			this.controllo = stringaCodice.substring(15, 16);
		}else if(stringaCodice.length()>16){
			this.cognome = stringaCodice.substring(0, 3);
			this.nome = stringaCodice.substring(3, 6);
			this.anno = stringaCodice.substring(6, 8);
			this.mese = stringaCodice.substring(8, 9);
			this.giorno = stringaCodice.substring(9, 11);
			this.luogo = stringaCodice.substring(11, 15);
			this.controllo = stringaCodice.substring(15);
		}else if(stringaCodice.length()<16) {
			if(stringaCodice.length()==0) {
				
				this.cognome = "";
				this.nome = "";
				this.anno = "";
				this.mese = "";
				this.giorno = "";
				this.luogo = "";
				this.controllo = "";
				
			}else if(stringaCodice.length()>0 && stringaCodice.length()<3) {
				this.cognome = stringaCodice;
				
				this.nome = "";
				this.anno = "";
				this.mese = "";
				this.giorno = "";
				this.luogo = "";
				this.controllo = "";
				
			}else if(stringaCodice.length()>=3 && stringaCodice.length()<6) {
				this.cognome = stringaCodice.substring(0, 3);
				this.nome = stringaCodice.substring(3);
				
				this.anno = "";
				this.mese = "";
				this.giorno = "";
				this.luogo = "";
				this.controllo = "";
				
			}else if(stringaCodice.length()>=6 && stringaCodice.length()<8) {
				this.cognome = stringaCodice.substring(0, 3);
				this.nome = stringaCodice.substring(3, 6);
				this.anno = stringaCodice.substring(6);

				this.mese = "";
				this.giorno = "";
				this.luogo = "";
				this.controllo = "";
				
			}else if(stringaCodice.length()>=8 && stringaCodice.length()<9) {
				this.cognome = stringaCodice.substring(0, 3);
				this.nome = stringaCodice.substring(3, 6);
				this.anno = stringaCodice.substring(6, 8);
				this.mese = stringaCodice.substring(8);
				
				this.giorno = "";
				this.luogo = "";
				this.controllo = "";
				
			}else if(stringaCodice.length()>=9 && stringaCodice.length()<11) {
				this.cognome = stringaCodice.substring(0, 3);
				this.nome = stringaCodice.substring(3, 6);
				this.anno = stringaCodice.substring(6, 8);
				this.mese = stringaCodice.substring(8, 9);
				this.giorno = stringaCodice.substring(9);

				this.luogo = "";
				this.controllo = "";
				
			}else if((stringaCodice.length()>=11 && stringaCodice.length()<15) || (stringaCodice.length()==15)) {
				this.cognome = stringaCodice.substring(0, 3);
				this.nome = stringaCodice.substring(3, 6);
				this.anno = stringaCodice.substring(6, 8);
				this.mese = stringaCodice.substring(8, 9);
				this.giorno = stringaCodice.substring(9, 11);
				this.luogo = stringaCodice.substring(11);
				
				this.controllo = "";
				
			}
			
			/*Funziona per:
			 *   <codice></codice>
				  <codice>R</codice>
				  <codice>RR</codice>
				  <codice>RRA</codice>
				  <codice>RRAM</codice>
				  <codice>RRAMH</codice>
				  <codice>RRAMHL</codice>
				  <codice>RRAMHL2</codice>
				  <codice>RRAMHL24</codice>
				  <codice>RRAMHL24M</codice>
				  <codice>RRAMHL24M3</codice>
				  <codice>RRAMHL24M31</codice>
				  <codice>RRAMHL24M31L</codice>
				  <codice>RRAMHL24M31L5</codice>
				  <codice>RRAMHL24M31L58</codice>
				  <codice>RRAMHL24M31L584</codice>
			 */
		}
		
	}
	
	public boolean takeAControl(ArrayList<Comune> controlloComune) {
		if(toString().length()==16) {
			if((controllo(getCognome())==true) && (controllo(getNome())==true) && (controlloAnno(getAnno())==true) && (controlloMese(getMese())==true) && (controlloGiorno(getGiorno(), getMese())==true) && (controlloLuogo(getLuogo(), controlloComune)==true) && (controlloControllo(getControllo())==true)) {
				System.out.println(toString()+" è un codice fiscale!");
				return true;
			}else {
				System.out.println(toString()+" non è un codice fiscale!");
			}
		}else {
			System.out.println(toString()+" non è corretto!");
		}
		return false;
	}
	
	public boolean controllo(String cognome) {
		char a,b,c;
		boolean ok=false;
		a=cognome.charAt(0);
		b=cognome.charAt(1);
		c=cognome.charAt(2);
	
			if((a>=65 && a<=90) && (b>=65 && b<=90) && (c>=65 && c<=90)) {
				ok=true;
			}
		return ok;
	}
	
	public void bisestile(String anno) {
		int a=Integer.parseInt(anno);
	    if ( ( ( a % 4 == 0 ) && ( a % 100 != 0 ) ) || ( a % 400 == 0 ) ) {
	        //se bisestile aggiorna Febbraio a 29 giorni!
	        giorni[1]=29;
	     }else {
	    	 giorni[1]=28;
	     }
	  }
	
	public boolean controlloControllo(String controllo) {
		char a;
		boolean ok=false;
		a=controllo.charAt(0);
		
			if((a>=65 && a<=90)) {
				ok=true;
			}
		return ok;
	}
	
	public boolean controlloLuogo(String luogo, ArrayList<Comune> comuni) {
		boolean ok=false;
		
				for(int i=0; i<comuni.size(); i++) {
					if(luogo.equalsIgnoreCase(comuni.get(i).getCodice())) {
						ok=true;
					}
				}
		return ok;
	}
	
	public boolean controlloGiorno(String giorno, String mese) {
		boolean ok=false;
		int p=0, a;
		a=Integer.parseInt(giorno);

		for(int i=0; i<mesi.length; i++) {
			if(mese.equals(mesi[i])) {
				p=i;
			}
		}
		
		for(int i=0; i<giorni[p]; i++) {
			if(a==(i+1)||a==(i+1+40)) {
				ok=true;
			}
		}
		
		return ok;
	}
	
	public boolean controlloMese(String mese) {
		boolean ok=false;

		for(int i=0; i<mesi.length; i++) {
			if(mese.equals(mesi[i])) {
				ok=true;
			}
			
		}
		return ok;
	}
	
	public boolean controlloAnno(String anno) {
		int a;
		boolean ok=false;
		StringBuffer verificaAnno=new StringBuffer();
		String annoMod;
		
		if(anno.matches("[0-9]+")) {
			//verificaAnno.append("19"+anno);
			verificaAnno.append("20"+anno);
			annoMod=verificaAnno.toString();
			bisestile(annoMod);
			
			a=Integer.parseInt(anno);
			
			if(a>=0 && a<99) {
				ok=true;
			}
		}
		return ok;
	}
	
	@Override
	public String toString() {
		return (cognome + nome + anno + mese + giorno + luogo + controllo).trim();
	}
	
}
