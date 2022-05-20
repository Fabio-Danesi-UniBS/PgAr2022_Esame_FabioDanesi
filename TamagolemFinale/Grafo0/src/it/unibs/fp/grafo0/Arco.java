package it.unibs.fp.grafo0;

/**
 * Classe Arco.
 * @author Fabio Danesi, Davide Branchetti, Brilant Gashi.
 *
 */
public class Arco {

	private String elemento;
	private int valoreDanno;
	
	public String getElemento() {
		return elemento;
	}
	public void setElemento(String elemento) {
		this.elemento = elemento;
	}
	public int getValoreDanno() {
		return valoreDanno;
	}
	public void setValoreDanno(int valoreDanno) {
		this.valoreDanno = valoreDanno;
	}
	
	/**
	 * Costruttore della classe Arco con parametri formali (String elemento, int valoreDanno).
	 * @param elemento
	 * @param valoreDanno
	 */
	public Arco(String elemento, int valoreDanno) {
		//super();
		this.elemento = elemento;
		this.valoreDanno = valoreDanno;
	}
	
	@Override
	public String toString() {
		return "Arco: [Elemento: " + elemento + ", Danno: " + valoreDanno + "]";
	}
}
