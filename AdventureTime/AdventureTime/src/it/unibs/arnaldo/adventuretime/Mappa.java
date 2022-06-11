package it.unibs.arnaldo.adventuretime;

import java.awt.Point;

/**
 * Classe Mappa
 * @author Fabio Danesi
 *
 */
public class Mappa {

	private static final int _0 = 0;
	private static final String E = "E";
	private static final String CHEST_APERTA = "Chest aperta!";
	private static final String STR = "\n";
	private static final String O = "O ";
	private static final String STRING3 = " ";
	private static final String PRINCIPESSA_KIBO_SALVATA = "Principessa Kibo salvata!";
	private static final String K = "K";
	private static final String IL_MALVAGIO_DIJKSTRA_FÀ_LA_SUA_COMPARSA = "Il malvagio Dijkstra fà la sua comparsa!";
	private static final String IL_MOSTRO_ATTACCA_IL_GIOCATORE_VITA_GIOCATORE = "Il mostro attacca il giocatore. Vita giocatore ";
	private static final String IL_GIOCATORE_ATTACCA_IL_MOSTRO_VITA_MOSTRO = "Il giocatore attacca il mostro. Vita mostro ";
	private static final String STRING2 = ": ";
	private static final String W = "W";
	private static final String A = "A";
	private static final String S2 = "S";
	private static final String D = "D";
	private static final String M = "M";
	private static final String DOT = ".";
	private static final String C = "C";
	private static final String STRING = "#";
	private String[][] mappa;
	private Giocatore giocatore;
	
	private String calpestato;//ricorda cosa si trova sotto i piedi del giocatore
	private Point posGiocatore;

	public int maxY() {
		return mappa.length-1;
	}
	
	public int maxX() {
		return mappa[_0].length-1;
	}
	
	public Giocatore getGiocatore() {
		return giocatore;
	}

	public void setGiocatore(Giocatore giocatore) {
		this.giocatore = giocatore;
	}

	public String[][] getMappa() {
		return mappa;
	}
	
	public void setMappa(String[][] mappa) {
		this.mappa = mappa;
	}
	
	public String getCalpestato() {
		return calpestato;
	}
	
	public void setCalpestato(String calpestato) {
		this.calpestato = calpestato;
	}
	
	public Point getPosGiocatore() {
		return posGiocatore;
	}
	
	public void setPosGiocatore(Point posGiocatore) {
		this.posGiocatore = posGiocatore;
	}
	
	public Point setPosGiocatoreG() {
		return this.posGiocatore = getGiocatore().getCoordinate();
	}
	
	public String setCalpG() {
		Point posGiocatore = getGiocatore().getCoordinate();
		String s=mappa[(int) posGiocatore.getX()][(int)posGiocatore.getY()];
		
		return s;
	}
	
	/**
	 * Costruttore 1
	 * @param mappa
	 * @param giocatore
	 */
	public Mappa(String[][] mappa, Giocatore giocatore) {
		super();
		this.mappa = mappa;
		this.giocatore=giocatore;
		this.posGiocatore=setPosGiocatoreG();
		this.calpestato = setCalpG();
	}
	
	/**
	 * COstruttore 2
	 * @param giocatore
	 */
	public Mappa(Giocatore giocatore) {
		super();
		this.mappa = generaMappa();
		
		this.giocatore=giocatore;
		coordinateIniziali();
		
		this.posGiocatore=setPosGiocatoreG();
		this.calpestato = setCalpG();
	}
	
	/**
	 * Costruttore 3
	 */
	public Mappa() {
		super();
		this.mappa = generaMappa();
		this.giocatore=null;
		this.posGiocatore=null;
		this.calpestato = null;
	}
	
	/**
	 * Coordinate iniziali del giocatore
	 */
	public void coordinateIniziali() {
		Point coordinateIniziali= new Point(maxY(), (int)Math.ceil(maxX()/2));
		this.getGiocatore().setCoordinate(coordinateIniziali);
	}
	
	/**
	 * Metodo per generare la mappa di gioco
	 * @return
	 */
	public String[][] generaMappa(){
		
		//prova
		String[][] mappaProva= {
				{STRING, STRING, DOT, DOT, DOT, C, DOT, DOT, STRING, STRING},
				{STRING, C, DOT, M, DOT, D, DOT, DOT, C, STRING},
				{DOT, DOT, DOT, DOT, DOT, DOT, DOT, DOT, DOT, DOT},
				{DOT, DOT, DOT, DOT, DOT, DOT, DOT, DOT, DOT, DOT},
				{DOT, DOT, DOT, DOT, STRING, STRING, DOT, DOT, M, DOT},
				{DOT, DOT, DOT, DOT, STRING, STRING, M, DOT, DOT, DOT},
				{DOT, M, DOT, DOT, DOT, DOT, DOT, DOT, DOT, DOT},
				{DOT, DOT, DOT, DOT, DOT, DOT, DOT, DOT, DOT, DOT},
				{STRING, C, DOT, DOT, DOT, DOT, DOT, DOT, C, STRING},
				{STRING, STRING, DOT, DOT, DOT, DOT, DOT, DOT, STRING, STRING}};
		
		return mappaProva;
	}
	
	/**
	 * Metodo per il movimento del giocatore sulla mappa e gestione dei relativi incontri malevoli
	 * @param s
	 */
	public void muovi(String s) {
		s=s.trim().toUpperCase();
		
		Point p=new Point();
		
		if(s.equals(W)) {//su
			
			p.x=Math.max(_0, (int)getGiocatore().getCoordinate().getX()-1);
			p.y=(int)getGiocatore().getCoordinate().getY();

		}else if(s.equals(A)) {//sinistra
			
			p.x=(int)getGiocatore().getCoordinate().getX();
			p.y=Math.min(maxY(), (int)getGiocatore().getCoordinate().getY()-1);

		}else if(s.equals(S2)) {//basso
			
			p.x=Math.min(maxX(), (int)getGiocatore().getCoordinate().getX()+1);
			p.y=(int)getGiocatore().getCoordinate().getY();
			
		}else if(s.equals(D)) {//destra
			
			
			p.x=(int)getGiocatore().getCoordinate().getX();
			p.y=Math.max(_0, (int)getGiocatore().getCoordinate().getY()+1);

		}
		
		if(mappa[p.x][p.y]!=STRING) {
			getGiocatore().setCoordinate(p);
		}
		
		if(mappa[p.x][p.y].equals(M)) {
			Mostro m= new Mostro();
			boolean flag= false;
			
			do {
				getGiocatore().combatti(m, false);
				System.out.println(IL_GIOCATORE_ATTACCA_IL_MOSTRO_VITA_MOSTRO+m.getNome()+STRING2+m.getVita());
				if(m.isMorto()==true) {
					flag=true;
				}else {
					m.combatti(getGiocatore(), true);
					System.out.println(IL_MOSTRO_ATTACCA_IL_GIOCATORE_VITA_GIOCATORE+getGiocatore().getNome()+STRING2+getGiocatore().getVita());
				}
				
				if(getGiocatore().isMorto()==true) {
					flag=true;
				}
				
			}while(flag!=true);
			
			if(m.isMorto()==true) {
				mappa[p.x][p.y]=DOT;
			}
		}
		
		if(mappa[p.x][p.y].equals(D)) {
			
			System.out.println(IL_MALVAGIO_DIJKSTRA_FÀ_LA_SUA_COMPARSA);
			Mostro m= Mostro.diQualcosa();
			boolean flag= false;
			
			do {
				getGiocatore().combatti(m, false);
				System.out.println(IL_GIOCATORE_ATTACCA_IL_MOSTRO_VITA_MOSTRO+m.getNome()+STRING2+m.getVita());
				if(m.isMorto()==true) {
					flag=true;
				}else {
					m.combatti(getGiocatore(), true);
					System.out.println(IL_MOSTRO_ATTACCA_IL_GIOCATORE_VITA_GIOCATORE+getGiocatore().getNome()+STRING2+getGiocatore().getVita());
				}
				
				if(getGiocatore().isMorto()==true) {
					flag=true;
				}
				
			}while(flag!=true);
			
			if(m.isMorto()==true) {
				mappa[p.x][p.y]=K;
			}
		}
	}
	
	/**
	 * Metodo per determinare la fine della partita con la vittoria del giocatore
	 * @param p
	 * @return
	 */
	public boolean fine(Point p) {
		if(mappa[p.x][p.y].equals(K)) {
			System.out.println(PRINCIPESSA_KIBO_SALVATA);
			return true;
		}
		return false;
	}

	/**
	 * toString modificato
	 */
	@Override
	public String toString() {
		StringBuffer s=new StringBuffer();
		
		for(int i=_0; i<mappa.length; i++) {
			for(int j=_0; j<mappa[i].length; j++) {
				if(i==getGiocatore().getCoordinate().getX() && j==getGiocatore().getCoordinate().getY()) {
					s.append(O);
				}else {
					s.append(mappa[i][j]+STRING3);
				}
				
			}
			s.append(STR);
		}
		
		return s.toString();
	}

	/**
	 * Metodo per aprire le chest
	 * @param apri
	 */
	public void apri(String apri) {
		
		apri=apri.trim().toUpperCase();
		
		Point p=new Point();
		
		p.x=(int)getGiocatore().getCoordinate().getX();
		p.y=(int)getGiocatore().getCoordinate().getY();
		
		if(apri.equals(E) && mappa[p.x][p.y].equals(C)) {
			Chest c= new Chest();
			System.out.println(CHEST_APERTA);
			System.out.println(c.toString());
			getGiocatore().getInventario().add(c.getOggetto());
		}
		mappa[p.x][p.y]=DOT;
		
	}
	
	
	
	
}
