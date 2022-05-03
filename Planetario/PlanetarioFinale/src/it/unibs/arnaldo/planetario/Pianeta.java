package it.unibs.arnaldo.planetario;

import java.util.*;

/*
 * La Classe Pianeta è una Classe figlia della Classe padre CorpoCeleste ed eredita i suoi rispettivi attributi e metodi.
 * 
 * 
 */
public class Pianeta extends CorpoCeleste{

	private static final int _0 = 0;
	private static final double VALORE_MAX_MASSA_PIANETA = 100.0;
	private static final double VALORE_MAX_MASSA_LUNA = 50.0;
	public static int numP=_0;
	public static int numL=_0;
	/*
	 * Alla classe Pianeta è associato un ArrayList di tipo Luna, ovvero la Classe Pianeta punta ad un ArrayList contenente tutte e
	 * sole le sue rispettive Lune.
	 * Inoltre, i metodi Getters and Setters permettono di associare/richiamare un valore dell'ArrayListLuna.
	 */
    private ArrayList<Luna> luna;

    public ArrayList<Luna> getLuna() {
		return luna;
	}

	public void setLuna(ArrayList<Luna> luna) {
		this.luna = luna;
	}

	/*
	 * 
	 *  nome: Assegna un oggetto di tipo String alla variabile Nome.
	 *  codiceUnivoco: Assegna un oggetto di tipo String alla variabile codiceUnivoco.
	 *  massa: Assegna un oggetto di tipo double alla variabile massa.
	 *  coordinate: Assegna un oggetto di tipo Coordinate alla variabile coordinate.
	 *  luna: è un ArrayList contenente oggetti di tipo Luna.
	 */
	public Pianeta(String nome, String codiceUnivoco, double massa, Coordinate coordinate, ArrayList<Luna> luna) {
        super(nome, codiceUnivoco, massa, coordinate);
        this.luna = luna;
    }
	
	/*
	 * Con il metodo aggiungiLuna si può aggiungere un pianeta all'ArrayListLuna nella posizione inserita dall'utente.
	 *  aggiungiL: rappresenta l'oggetto luna da aggiungere al relativo ArrayList.
	 */
	public void aggiungiLuna(Luna aggiungiL) {
		luna.add(aggiungiL);
	}
	
	/*
	 * Con il metodo rimuoviLuna si può rimuovere un pianeta all'ArrayListLuna nella posizione inserita dall'utente.
	 * @param index rappresenta la posizione nell'ArrayListLuna su cui opera il metodo rimuoviLuna.
	 *
	 */
	public void rimuoviLuna(int index) {
		luna.remove(index);
	}

	@Override
	public String toString() {
		return "Pianeta ["+ " getNome()=" + getNome()
				+ ", getCodiceUnivoco()=" + getCodiceUnivoco() + ", getMassa()=" + getMassa() + ", getCoordinate()="
				+ getCoordinate() + "Luna=" + luna.toString()+"]";
	}
	
	public String mostraPianeta() {
        return "\tPianeta : Nome=" + getNome() + ", CodiceUnivoco=" + getCodiceUnivoco() + ", Massa=" + getMassa() + ", Coordinate="
                + getCoordinate();
    }
	
	public Pianeta creaPianeta(String codiceUnivocoStella, ArrayList<Coordinate> cord) { 
		Coordinate coordiante=new Coordinate(_0,_0);
		String nome;
		double massa, x, y;
		boolean prova;
		String codiceUnivoco;
		StringBuffer r= new StringBuffer("PI");
		
		int i, cont=_0;

		ArrayList<Luna> luna= new ArrayList<Luna>(); //ciascun oggetto pianeta dell'ArrayListPianeta avrà a sua volta un ArrayListLuna di Lune
		Luna l, lu=new Luna(null, null, 0,coordiante);
		Pianeta p;
				
		nome=InputDati.leggiStringaNonVuota("Nome pianeta: ");
		// con questi if facciamo in modo che il codice univoco sia di 7 cifre(per convenzione) che andrà a sommarsi a quello della stella
		if((numP)<10) {
			r.append("0000");
		}else if((numP)>=10 && (numP)<100) {
			r.append("000");
		}else if((numP)>=100 && (numP)<1000) {
			r.append("00");
		}else if((numP)>=1000 && (numP)<10000) {
			r.append("0");
		}
		r.append(numP);
		
		codiceUnivoco=codiceUnivocoStella + r;//11+7=18
		numP++;
		int m=_0;		
		// il ciclo do while esiste per verificare che la massa del pianeta sia compresa tra ( minimo=50, max=100)
		do {
			
			if(m==1) {
				System.out.println("Inserisci minore di "+ VALORE_MAX_MASSA_PIANETA);
			}
			
			massa=InputDati.leggiDoubleConMinimo("Inserisci massa: ", VALORE_MAX_MASSA_LUNA);
			m++;
		}while(massa>VALORE_MAX_MASSA_PIANETA);
		
		do {
			x=InputDati.leggiDouble("Inserisci coordinata X: ");
			y=InputDati.leggiDouble("Inserisci coordinata Y: ");
			prova=true;
			
			for (int j=_0; (j<=cord.size()-1)&&(prova==true); j++) {
				if(cord.get(j).getX()==x && cord.get(j).getY()==y) {
					prova=false;
					System.out.println("Queste coordinate sono già presenti!");
				}
			}
		}while(prova==false);
		
		coordiante.setX(x);
		coordiante.setY(y);
		cord.add(coordiante);
				
		cont=InputDati.leggiInteroNonNegativo("Quante lune?");
		numL=_0;
		// con questo ciclo for andiamo a riempire l'arrayListLuna dell'oggetto pianeta in modo uguale a come si è fatto con il Pianeta
		for(i=_0; i<cont && i<5000; i++) {
			int j=i+1;

			System.out.println("Luna "+ j + " del pianeta " + nome + ": ");
			l=lu.creaLuna(codiceUnivoco,cord);
			luna.add(i, l);
		}
				
		p= new Pianeta(nome, codiceUnivoco, massa, coordiante, luna);
				
		return p;
	}
	
	public Pianeta trovaPianeta(Stella s, String daCercare) { 
		int i=_0;
		Pianeta pi, pianeta=null;
		
		ArrayList<Pianeta> pian=new ArrayList<Pianeta>();
		pian=(s.getPianeta());
		//mediante un setaccio di pianeti, si fanno scorrere uno a uno e si verifica se il codice univoco inserito in input esiste
			//for(i=_0; s.getPianeta().size()>i; i++) {
				pi=pian.get(i);
				if(pi.getCodiceUnivoco().contains(daCercare)) {
					pianeta=pi;
				}
			//}
			return pianeta;
	}

}