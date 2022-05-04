package it.unibs.arnaldo.planetario;

import java.util.*;

/*
 * La Classe Stella è una Classe figlia della Classe padre CorpoCeleste ed eredita i suoi rispettivi attributi e metodi.
 * 
 * 
 */
public class Stella extends CorpoCeleste{
	/*
	 * Alla classe Stella è associato un ArrayList di tipo Pianeta, ovvero la Classe stella punta ad un ArrayList contenente tutti 
	 * i pianeti del sistema plantario.
	 * Inoltre, i metodi Getters and Setters permettono di associare/richiamare un valore dell'ArrayListPianeta.
	 * 
	 */
	private ArrayList<Pianeta> pianeta;

	public ArrayList<Pianeta> getPianeta() {
		return pianeta;
	}

	public void setPianeta(ArrayList<Pianeta> pianeta) {
		this.pianeta = pianeta;
	}

	/*
	 *  nome: Assegna un oggetto di tipo String alla variabile Nome.
	 *  codiceUnivoco: Assegna un oggetto di tipo String alla variabile codiceUnivoco.
	 *  massa: Assegna un oggetto di tipo double alla variabile massa.
	 *  coordinate: Assegna un oggetto di tipo Coordinate alla variabile coordinate.
	 *  pianeta: è un ArrayList contenente oggetti di tipo Pianeta.
	 */
	public Stella(String nome, String codiceUnivoco, double massa, Coordinate coordinate, ArrayList<Pianeta> pianeta) {
		super(nome, codiceUnivoco, massa, coordinate);
		this.pianeta = pianeta;
	}
	
	/*
	 * Con il metodo aggiungiPianeta si può aggiungere un pianeta all'ArrayListPianeta nella posizione inserita dall'utente.
	 * posizione: rappresenta la posizione nell'ArrayListPianeta su cui opera il metodo aggiungiPianeta.
	 * aggiungiP: rappresenta l'oggetto pianeta da aggiungere al relativo ArrayList.
	 */
	public void aggiungiPianeta(int posizione, Pianeta aggiungiP) {
		pianeta.add(posizione, aggiungiP);
	}
	
	/*
	 * Con il metodo rimuoviPianeta si può rimuovere un pianeta all'ArrayListPianeta nella posizione inserita dall'utente.
	 * index: rappresenta la posizione nell'ArrayListPianeta su cui opera il metodo rimuoviPianeta.
	 */
	public void rimuoviPianeta(int index) {
		pianeta.remove(index);
	}

	@Override
	public String toString() {
		return "Stella [ getNome()=" + getNome()
				+ ", getCodiceUnivoco()=" + getCodiceUnivoco() + ", getMassa()=" + getMassa() + ", getCoordinate()="
				+ getCoordinate() + ", Pianeta="+ pianeta.toString() + "]";
	}

	public String mostraStella() {
        return "\nSole : Nome=" + getNome() + ", CodiceUnivoco=" + getCodiceUnivoco() + ", Massa=" + getMassa() + ", Coordinate="
                + getCoordinate() + "\n";
    }
	
}
