package it.unibs.arnaldo.codicefis;

public class Codice {

	private String codice;

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public Codice(String codice) {
		super();
		this.codice = codice;
	}

	@Override
	public String toString() {
		return (codice).trim();
	}
	
	public String toString1() {
		return (codice).trim()+"\n";
	}
}
