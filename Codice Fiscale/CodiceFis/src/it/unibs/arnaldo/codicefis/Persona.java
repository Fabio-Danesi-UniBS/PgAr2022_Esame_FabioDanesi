package it.unibs.arnaldo.codicefis;

public class Persona {
	private String nome;
	private String cognome;
	private String sesso;
	private String comuneDiNascita;
	private String dataDiNascita;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getSesso() {
		return sesso;
	}
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}
	public String getComuneDiNascita() {
		return comuneDiNascita;
	}
	public void setComuneDiNascita(String comuneDiNascita) {
		this.comuneDiNascita = comuneDiNascita;
	}
	public String getDataDiNascita() {
		return dataDiNascita;
	}
	public void setDataDiNascita(String dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}
	
	public Persona(String nome, String cognome, String sesso, String comuneDiNascita, String dataDiNascita) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.comuneDiNascita = comuneDiNascita;
		this.dataDiNascita = dataDiNascita;
	}
	
	@Override
	public String toString() {
		return "Persona: nome=" + nome + ", cognome=" + cognome + ", sesso=" + sesso + ", comuneDiNascita="
				+ comuneDiNascita + ", dataDiNascita=" + dataDiNascita +"\n";
	}
	
	
	
}
