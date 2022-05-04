package it.unibs.arnaldo.planetario;

/*
 * La classe Corpo Celeste crea un oggetto che sarà una Classe padre da cui  le rispettive classi Stella,
 * Pianeta, Luna ereditreanno gli attributi e i metodi (Classe Padre= Corpo Celeste, Classe figlie= Stella,Pianeta,Luna). 
 */
public class CorpoCeleste {
	private String nome;
	private String codiceUnivoco;
	private double massa;
	private Coordinate coordinate;
	
	/*
	 * Il Corpo Celeste ha come attributi il nome, il codiceUnivoco, la massa e le coordinate, non modificabili.
	 * nome: Assegna un oggetto di tipo String alla variabile Nome.
	 * codiceUnivoco: Assegna un oggetto di tipo String alla variabile codiceUnivoco.
	 * massa: Assegna un oggetto di tipo double alla variabile massa.
	 * coordinate: Assegna un oggetto di tipo Coordinate alla variabile coordinate.
	 */
	public CorpoCeleste(String nome, String codiceUnivoco, double massa, Coordinate coordinate) {
		super();
		this.nome = nome;
		this.codiceUnivoco = codiceUnivoco;
		this.massa = massa;
		this.coordinate = coordinate;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCodiceUnivoco() {
		return codiceUnivoco;
	}
	public void setCodiceUnivoco(String codiceUnivoco) {
		this.codiceUnivoco = codiceUnivoco;
	}
	public double getMassa() {
		return massa;
	}
	public void setMassa(double massa) {
		this.massa = massa;
	}
	public Coordinate getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
	
	@Override
    public String toString() {
        return "CorpoCeleste [nome=" + nome + ", codiceUnivoco=" + codiceUnivoco + ", massa=" + massa + ", coordinate=" + coordinate.toString() +"]";
    }
	
}
