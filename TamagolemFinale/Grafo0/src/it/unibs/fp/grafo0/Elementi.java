package it.unibs.fp.grafo0;

/**
 * Classe Elementi
 * @author Fabio Danesi, Davide Branchetti, Brilant Gashi.
 *
 */
public enum Elementi {
	FUOCO, ACQUA, TERRA, VENTO, FULMINE;
	
	/**
	 * Costruttore di Elementi.
	 */
	Elementi(){
	}

	/**
	 * Metodo per contare gli elementi.
	 * @return
	 */
	public static int contaElementi() {
		int n=0;
	    for(Elementi ele : Elementi.values()) {
			n++;
		}
	    return n;
	}
}
