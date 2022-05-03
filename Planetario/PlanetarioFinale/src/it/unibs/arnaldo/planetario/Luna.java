package it.unibs.arnaldo.planetario;

import java.util.ArrayList;

/*
 * La Classe Luna è una Classe figlia della Classe padre CorpoCeleste ed eredita i suoi rispettivi attributi e metodi.
 * 
 */
public class Luna extends CorpoCeleste{
	private static final int _0 = 0;
	private static final double VALORE_MAX_MASSA_LUNA = 50.0;
	private static final double _1_0 = 1.0;

	
	public static int numP=_0;
	public static int numL=_0;
	/*
	 * 
	 *  nome: Assegna un oggetto di tipo String alla variabile Nome.
	 *  codiceUnivoco: Assegna un oggetto di tipo String alla variabile codiceUnivoco.
	 *  massa: Assegna un oggetto di tipo double alla variabile massa.
	 *  coordinate: Assegna un oggetto di tipo Coordinate alla variabile coordinate.
	 */
	
    public Luna(String nome, String codiceUnivoco, double massa, Coordinate coordinate) {
        super(nome, codiceUnivoco, massa, coordinate);
    }

	@Override
	public String toString() {
		return "Luna [getNome()=" + getNome() + ", getCodiceUnivoco()=" + getCodiceUnivoco() + ", getMassa()="
				+ getMassa() + ", getCoordinate()=" + getCoordinate() + "]";
	}

	public String mostraLuna() {
        return "\t\tLuna : Nome=" + getNome() + ", CodiceUnivoco=" + getCodiceUnivoco() + ", Massa=" + getMassa() + ", Coordinate="
                + getCoordinate();
    }
	
	public Luna creaLuna(String codiceUnivocoPianeta, ArrayList<Coordinate> cord) {// crea luna funziona in modo analogo a crea pianeta
		String nome;
		double massa, x, y;
		boolean prova;
		Coordinate coordiante=new Coordinate(_0,_0);
		String codiceUnivoco;
		StringBuffer r= new StringBuffer("LU");
		
		nome=InputDati.leggiStringaNonVuota("Nome luna: ");
		// facciamo in modo che il codice univoco abbia 6 cifre, che sommato al codice unvico del pianeta(18 cifre) dà un codice di 24 cifre
		if((numL)<10) {
			r.append("000");
		}else if((numL)>=10 && (numL)<100) {
			r.append("00");
		}else if((numL)>=100 && (numL)<1000) {
			r.append("0");
		}
		r.append(numL);
		
		codiceUnivoco=codiceUnivocoPianeta + r;//18+6=24
		numL++;
		int p=_0;
		
		do {
			
			if(p==1) {
				System.out.println("Inserisci minore di "+ VALORE_MAX_MASSA_LUNA);
			}
			
			massa=InputDati.leggiDoubleConMinimo("Inserisci massa: ", _1_0);
			p++;
		}while(massa>VALORE_MAX_MASSA_LUNA);
		
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
		
		Luna l= new Luna(nome, codiceUnivoco, massa, coordiante);
		
		return l;
	}
	
	public Luna trovaLuna(Stella s, String daCercare) {
		int i=_0,j=_0;
		Pianeta pi;
		Luna lu, luna=null;
		
		ArrayList<Pianeta> pian=new ArrayList<Pianeta>();
		pian=(s.getPianeta());

			for(i=_0; s.getPianeta().size()>i; i++) {
				pi=pian.get(i);
				for(j=_0; pi.getLuna().size()>j; j++) {
					lu=(pi.getLuna()).get(j);
					if(lu.getCodiceUnivoco().contains(daCercare)) {
						luna=lu;
						
					}
				}
			}
			return luna;
	}

}