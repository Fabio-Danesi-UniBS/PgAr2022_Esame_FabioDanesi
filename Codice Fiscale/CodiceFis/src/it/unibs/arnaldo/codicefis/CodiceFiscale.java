package it.unibs.arnaldo.codicefis;

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
	
	public boolean takeAControl() {
		if(toString1().length()==16) {
			if((controllo(getCognome())==true) && (controllo(getNome())==true) && (controlloAnno(getAnno())==true) && (controlloMese(getMese())==true) && (controlloGiorno(getGiorno(), getMese())==true) && (controlloLuogo(getLuogo())==true) && (controlloControllo(getControllo())==true)) {
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
	
	public static boolean controllo(String cognome) {
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
	
	public static void bisestile(String anno) {
		int a=Integer.parseInt(anno);
	    if ( ( ( a % 4 == 0 ) && ( a % 100 != 0 ) ) || ( a % 400 == 0 ) ) {
	        //se bisestile aggiorna Febbraio a 29 giorni!
	        giorni[1]=29;
	     }else {
	    	 giorni[1]=28;
	     }
	  }
	
	
	public static boolean controlloControllo(String controllo) {
		char a;
		boolean ok=false;
		a=controllo.charAt(0);
		
			if((a>=65 && a<=90)) {
				ok=true;
			}
		return ok;
	}
	
	public static boolean controlloLuogo(String luogo) {
		char a;
		int b;
		boolean ok=false;
		a=luogo.charAt(0);
		b=Integer.parseInt(luogo.substring(1, 4));
		
			if((a>=65 && a<=90) && (b>=0 && b<999)) {
				ok=true;
			}
		return ok;
	}
	
	public static boolean controlloGiorno(String giorno, String mese) {
		boolean ok=false;
		int p=0, a;
		a=Integer.parseInt(giorno);

		for(int i=0; i<mesi.length; i++) {
			if(mese.equals(mesi[i])) {
				p=i;
			}
		}
		
		for(int i=0; i<giorni[p]; i++) {
			if(a==(i+1)) {
				ok=true;
			}
		}
		
		return ok;
	}
	
	public static boolean controlloMese(String mese) {
		boolean ok=false;

		for(int i=0; i<mesi.length; i++) {
			if(mese.equals(mesi[i])) {
				ok=true;
			}
			
		}
		return ok;
	}
	
	public static boolean controlloAnno(String anno) {
		int a;
		boolean ok=false;
		StringBuffer verificaAnno=new StringBuffer();
		String annoMod;
		
		if(anno.matches("[0-9]+")) {
			verificaAnno.append("19"+anno);
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
		return "CodiceFiscale: " + (cognome + nome + anno + mese + giorno + luogo + controllo).trim();
	}
	
	public String toString1() {
		return (cognome + nome + anno + mese + giorno + luogo + controllo).trim();
	}
	
}
