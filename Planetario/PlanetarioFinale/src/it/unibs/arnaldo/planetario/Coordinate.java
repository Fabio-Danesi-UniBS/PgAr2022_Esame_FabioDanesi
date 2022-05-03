package it.unibs.arnaldo.planetario;

/*
 * La classe Coordinate creerà un una variabile contente 2 attributi che avranno come valore rispettivamente la coordinata x
 * e la coordinata y del corpo celeste a cui è associato.
 */
public class Coordinate {
	/*
	 * I due attributi x e y conterrano le coordinate e non sono modificabili.
	 */
	private double x;
	private double y;
	
	/*
	 * Il metodo Coordinate ci permetterà di istanziare this.x e this.y.
	 * 
	 * 
	 */
	public Coordinate(double x, double y) {
		//super();
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
		
	}
	@Override
	public String toString() {
		return "[x=" + x + ", y=" + y + "]";
	}
	
}
