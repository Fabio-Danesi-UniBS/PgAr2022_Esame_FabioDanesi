package it.unibs.arnaldo.adventuretime;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Classe Giocatore
 * @author Fabio Danesi
 *
 */
public class Giocatore extends Creatura{

	private static final int _5 = 5;
	private static final int _20 = 20;
	private static final int _0 = 0;
	private static final String GIOCATORE = "Giocatore: ";
	private ArrayList<Oggetto> inventario;
	private double vitaMax;
	private Point coordinate;
	
	/**
	 * Costruttore
	 * @param nome
	 * @param vita
	 * @param oggettoEquip
	 * @param attacco
	 * @param difesa
	 * @param coordinate
	 * @param inventario
	 */
	public Giocatore(String nome, double vita, Oggetto oggettoEquip, int attacco, int difesa, Point coordinate,
			ArrayList<Oggetto> inventario) {
		super(nome, vita, oggettoEquip, attacco, difesa);
		this.coordinate=coordinate;
		this.inventario = inventario;
		this.vitaMax=super.getVita();
	}
	
	/**
	 * Costruttore alternativo
	 * @param nome
	 * @param inventario
	 * @param oggetto
	 */
	public Giocatore(String nome, ArrayList<Oggetto> inventario, Oggetto oggetto) {
		super(nome, _20+oggetto.getDifesa(), oggetto, _5, _5);
		
		//test
		//super(nome, 100, Oggetto.SPADA_FORTISSIMA, 100+Oggetto.SPADA_FORTISSIMA.getAttacco(), 100, new Point(0,0));
		this.coordinate=new Point(_0,_0);
		this.inventario = inventario;
		this.vitaMax=super.getVita();
	}
	
	public Point getCoordinate() {
		return coordinate;
	}
	
	public void setCoordinate(Point coordinate) {
		this.coordinate = coordinate;
	}
	
	public ArrayList<Oggetto> getInventario() {
		return inventario;
	}

	public double getVitaMax() {
		return vitaMax;
	}

	public void setVitaMax(double vitaMax) {
		this.vitaMax = vitaMax;
	}

	public void setInventario(ArrayList<Oggetto> inventario) {
		this.inventario = inventario;
	}

	@Override
	public String toString() {
		return GIOCATORE + super.toString();
	}

	
}
