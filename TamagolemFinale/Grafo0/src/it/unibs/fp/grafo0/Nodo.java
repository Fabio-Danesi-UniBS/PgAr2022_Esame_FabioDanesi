package it.unibs.fp.grafo0;

/**
 * Classe Nodo.
 * @author Fabio Danesi, Davide Branchetti, Brilant Gashi.
 *
 */
public class Nodo {

	public String info;

	public Nodo(String i) {
	info = i;
	}

	/**
	 * Metodo toString() personalizzato e fondamentale per l'eleborazione!
	 */
	@Override
	public String toString() {
		return info;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	public String toStringAlter() {
		return "Nodo: "+info;
	}
	
	

}
