
package it.unibs.arnaldo.planetario;

import java.util.ArrayList;

public class Planetario {
	private static final int _999999999 = 999999999; //valore massimo che puoi assegnare alla stella
	private static final int _0 = 0;
	private static final String IL_PLANETARIO_NON_È_STATO_ANCORA_CREATO = "\nIl planetario non è stato ancora creato!";
	private static final double VALORE_MAX_MASSA_STELLA = 150.0;
	private static final double VALORE_MAX_MASSA_PIANETA = 100.0;
	private static final double _0_0 = 0.0;
	
	//private static Stella s; //oggetto di tipo stella contente il planetario
	private static ArrayList<Coordinate> cor = new ArrayList<Coordinate>(); //arraylistcoordinate per verificare se due pianeti si trovano nella stessa posizione
	
	public static int numP=_0;
	public static int numL=_0;
	
	private Stella s;
	
	public Planetario() {
		super();
		this.s=null;
	}
	
	public Stella getS() {
		return s;
	}

	public void setS(Stella s) {
		this.s = s;
	}

	public void menuCrea() {
		String []elenco= {"Crea planetario", "Visualizza planetario", "Aggiungi pianeta con relative lune", "Aggiungi una luna ad un pianeta", "Rimuovi pianeta con relative lune", "Rimuovi una luna ad un pianeta", "Cerca un pianeta o una luna tramite il codice univoco", "Mostra percorso da luna", "Centro di Massa"};
		MyMenu menu= new MyMenu("\tPlanetario", elenco);
		int scelta=_0;
		do {
			scelta=menu.scegli();			
			switch(scelta) {
			case 1:
				if(s==null) {
				s=creaPlanetario();
				stampa(s);
				}else {
					System.out.println("Il planetario è già stato creato!");
				}
				
				break;
				
			case 2:
				
				if(s!=null) {
					stampa(s);
				}else {
					System.out.println(IL_PLANETARIO_NON_È_STATO_ANCORA_CREATO);
				}
				
				break;
				
			case 3:
				//il caso 3 aggiunge un pianeta con relative lune
				if(s!=null) {
					int pos;
					Pianeta nuovoPianeta;
					ArrayList<Luna> lulu= new ArrayList<Luna>();
					int lung=s.getPianeta().size()-1;
					
					if(s.getPianeta().size()>0) {
						pos=InputDati.leggiIntero("Definisci la posizione in cui inserire il nuovo pianeta (MAX tra 0 e " + lung +"): ", _0, lung);
						nuovoPianeta=s.getPianeta().get(pos).creaPianeta(s.getCodiceUnivoco(),cor);
						s.aggiungiPianeta(pos, nuovoPianeta);
					}else /*if(s.getPianeta().size()==0)*/ {
						Coordinate c= new Coordinate(0,0);
						Luna nuovaLuna=new Luna(null, null, 0, c);
						lulu.add(nuovaLuna);
						nuovoPianeta =new Pianeta(null, null, 0, c, lulu);
						s.aggiungiPianeta(0, nuovoPianeta);
						nuovoPianeta=s.getPianeta().get(0).creaPianeta((s.getCodiceUnivoco()),cor);
						s.aggiungiPianeta(0, nuovoPianeta);
						s.rimuoviPianeta(1);
					}
					
					stampa(s);
				}else {
					System.out.println(IL_PLANETARIO_NON_È_STATO_ANCORA_CREATO);
				}
				
				break;
				
			case 4:
				// aggiungi una luna 
				if(s!=null) {
					int pos;
					int lung=s.getPianeta().size()-1;
					Luna nuovaLuna;
					
					if(s.getPianeta().size()>0) {
						pos=InputDati.leggiIntero("Definisci il pianeta a cui assegnare una nuova luna (MAX tra 0 e " + lung +"): ", _0, lung);
						if(s.getPianeta().get(pos).getLuna().size()>0) {
							nuovaLuna=s.getPianeta().get(pos).getLuna().get(0).creaLuna(s.getPianeta().get(pos).getCodiceUnivoco(), cor);
							s.getPianeta().get(pos).aggiungiLuna(nuovaLuna);
							stampa(s);
						}else {
							Coordinate c= new Coordinate(0,0);
							nuovaLuna=new Luna(null, null, 0, c);
							s.getPianeta().get(pos).getLuna().add(nuovaLuna);
							nuovaLuna=s.getPianeta().get(pos).getLuna().set(0,s.getPianeta().get(pos).getLuna().get(0).creaLuna(s.getPianeta().get(pos).getCodiceUnivoco(), cor));
							
							s.getPianeta().get(pos).aggiungiLuna(nuovaLuna);
							s.getPianeta().get(pos).rimuoviLuna(1);
							stampa(s);
							
						}
						
					}else if(s.getPianeta().size()==0) {
						System.out.println("Non ci sono pianeti a cui aggiungere lune!");
					}
					
				}else {
					System.out.println(IL_PLANETARIO_NON_È_STATO_ANCORA_CREATO);
				}
				
				break;
				
			case 5:
				// si rimuove un pianeta specifico, il controllo dell'if è per vedere se esistono realmente pianeti del sistema planetario o meno
				if(s!=null) {
					int pos;
					int lung=s.getPianeta().size()-1;
					if(s.getPianeta().size()>0) {
						pos=InputDati.leggiIntero("Definisci l'indice del pianeta da rimuovere (MAX tra 0 e " + lung +"): ", _0, lung);
						s.rimuoviPianeta(pos);
						stampa(s);
						
					}
					else
						System.out.println("Non esistono pianeti nel planetario");
				}
				
				break;
				
			case 6:
				// si rimuove una luna  specifica, il controllo dei 2 if annidati è per vedere se esiste realmente la Luna
				if(s!=null) {
					int pos, pos2;
					int lung=s.getPianeta().size()-1, lung2;
					if(s.getPianeta().size()>0) {
						pos=InputDati.leggiIntero("Definisci l'indice del pianeta da rimuovere una nuova luna (MAX tra 0 e " + lung +"): ", _0, lung);
						if(s.getPianeta().get(pos).getLuna().size()>0) {
							lung2=s.getPianeta().get(pos).getLuna().size()-1;
							pos2=InputDati.leggiIntero("Definisci l'indice della luna da rimuovere (MAX tra 0 e " + lung2 +"): ", _0, lung2);
							s.getPianeta().get(pos).rimuoviLuna(pos2);
							stampa(s);
						}
							else if(s.getPianeta().get(pos).getLuna().size()==0) {
								System.out.println("Non ci sono Lune appartenenti al pianeta :"+ s.getPianeta().get(pos).getNome() );
							}
					}
					else {
						System.out.println("Non esistono pianeti nel planetario");
					
						}	
				}
				
				
				break;
				
			case 7:
				//cerca un pianeta e una luna tramite un codice univoco ( il codice univoco del pianeta ha 18 cifre, mentre quello della Luna 24)
				if(s!=null) {
					String cerca, ricerca;
					boolean trova=false;
					
					cerca=InputDati.leggiStringaNonVuota("Inserire il codice univoco del/la Pianeta/Luna da cercare: ");
					
					trova=cercaPianetaLuna(s, cerca);
					if(trova == true && cerca.length()==18) {
						
						ArrayList<Pianeta> pian=new ArrayList<Pianeta>();
						Pianeta pi;
						pian=(s.getPianeta());
						int i=_0;
						for(i=_0; s.getPianeta().size()>i; i++) {
							pi=pian.get(i);
							if(pi.getCodiceUnivoco().equals(cerca)) {
								System.out.println("\nIl suo pianeta cercato è:\n\t" + pi.mostraPianeta() + "\n");
							}
						}
						
						System.out.println("\nIl pianeta cercato fa parte del sistema!");
						
					}else if(trova == true && cerca.length()==24) {
						
						ricerca=cerca.substring(_0, 18);
						ArrayList<Pianeta> pian=new ArrayList<Pianeta>();

						Pianeta pi;
						Luna lu;
						pian=(s.getPianeta());
						int i=_0, j=_0;
						for(i=_0; s.getPianeta().size()>i; i++) {
							pi=pian.get(i);
							if(pi.getCodiceUnivoco().equals(ricerca)) {
								System.out.println("\nIl suo pianeta di riferimento è:\n\t" + pi.mostraPianeta() + "\n");
							}
							for(j=_0; pi.getLuna().size()>j; j++) {
								lu=(pi.getLuna()).get(j);
								if(lu.getCodiceUnivoco().equals(cerca)) {
									System.out.println("La luna cercata è:\n " + lu.mostraLuna() + "\n");
								}
							}
						}
						
					}else if(trova == false) {
						System.out.println("\nPianeta o luna non trovato/a!");
					}
				}else {
					System.out.println(IL_PLANETARIO_NON_È_STATO_ANCORA_CREATO);
				}
				
				break;
				
			case 8:
				//Mostra il percorso della luna, che è uguale al metodo cerca luna ma con l'output modificato
				if(s!=null) {
					
					String cerca, ricerca;
					boolean trova=false;
					
					cerca=InputDati.leggiStringaNonVuota("Inserire il codice univoco della luna da cui vedere il percorso: ");
					
					trova=cercaPianetaLuna(s, cerca);
					
					 if(trova == true && cerca.length()==24) {
						
						ricerca=cerca.substring(_0, 18);
						Luna tLuna=null;
						Pianeta tPianeta=null;
						
						ArrayList<Pianeta> pian=new ArrayList<Pianeta>();
						
						Pianeta pi;
						Luna lu;
						pian=(s.getPianeta());
						int i=_0, j=_0;
						for(i=_0; s.getPianeta().size()>i; i++) {
							pi=pian.get(i);
							if(pi.getCodiceUnivoco().equals(ricerca)) {
								tPianeta=pi;
								
							}
							for(j=_0; pi.getLuna().size()>j; j++) {
								lu=(pi.getLuna()).get(j);
								if(lu.getCodiceUnivoco().equals(cerca)) {
									tLuna=lu;
									
								}
								
							}
						}
						System.out.println(s.getNome() + ">" + tPianeta.getNome() + ">" + tLuna.getNome());					

					 }else if(trova == false) {
							System.out.println("\nPianeta o luna non trovato/a!");
					}
				}else {
					System.out.println(IL_PLANETARIO_NON_È_STATO_ANCORA_CREATO);
				}
				
				break;
				
			case 9:
				//centro di massa calcolato appositamente
				if(s!=null) {
					Coordinate centroFinale=centroDiMassa(s);
					System.out.println("Centro di Massa= "+ centroFinale.toString());
				}else {
					System.out.println(IL_PLANETARIO_NON_È_STATO_ANCORA_CREATO);
				}
				
				break;
			}
			
		}while(scelta!=_0);
		
		System.out.println("GAME OVER!");
	}
	
	//questo metodo grazie a 2 cicli for annidati calcola man mano la somma per poi trovare il centro di massa
	public Coordinate centroDiMassa(Stella s) {
		Coordinate centroMassa = new Coordinate(_0_0,_0_0);
		double massa=s.getMassa();
		ArrayList<Pianeta> pian=new ArrayList<Pianeta>();
		ArrayList<Luna> lun=new ArrayList<Luna>();
		Pianeta pi;
		Luna lu;
		pian=(s.getPianeta());
		int i=_0, j=_0;
		double x = _0,y = _0;
		for(i=_0; s.getPianeta().size()>i; i++) {
			pi=pian.get(i);
			massa=pi.getMassa()+massa;
			x=(pi.getCoordinate().getX())*pi.getMassa()+x;
			y=(pi.getCoordinate().getY())*pi.getMassa()+y;
				//j=0;
			for(j=_0; pi.getLuna().size()>j; j++) {
				lu=(pi.getLuna()).get(j);
				lun.add(j, lu);
				massa=massa+lu.getMassa();
				x=((lu.getCoordinate().getX())*lu.getMassa())+x;
				y=(lu.getCoordinate().getY())*lu.getMassa()+y;
			}
		}
		
		centroMassa.setX(x/massa);
		centroMassa.setY(y/massa);
		return centroMassa;
	}
	
	// si va a cercare il pianeta cercando mediante il codice univoco
	public boolean cercaPianetaLuna(Stella s, String daCercare) {
		int i=_0,j=_0;
		Pianeta pi;
		Luna lu;
		int tipoCorpo=daCercare.length();
		
		ArrayList<Pianeta> pian=new ArrayList<Pianeta>();
		pian=(s.getPianeta());
		
		if(tipoCorpo==18) {
			for(i=_0; s.getPianeta().size()>i; i++) {
				pi=pian.get(i);
				if(pi.getCodiceUnivoco().contains(daCercare)) {
					return true;
				}
			}
		}else if(tipoCorpo==24) {
			for(i=_0; s.getPianeta().size()>i; i++) {
				pi=pian.get(i);
				for(j=_0; pi.getLuna().size()>j; j++) {
					lu=(pi.getLuna()).get(j);
					if(lu.getCodiceUnivoco().contains(daCercare)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	//con questo metodo stampiamo il planetario con 2 cicli for annidati
	public void stampa(Stella s) {
		System.out.println(s.mostraStella());	
		ArrayList<Pianeta> pian=new ArrayList<Pianeta>();
		ArrayList<Luna> lun=new ArrayList<Luna>();
		Pianeta pi;
		Luna lu;
		pian=(s.getPianeta());
		int i=_0, j=_0;
		for(i=_0; s.getPianeta().size()>i; i++) {
			pi=pian.get(i);
			System.out.println(pi.mostraPianeta() + ", Indice pianeta=" + i + "\n");
			for(j=_0; pi.getLuna().size()>j; j++) {
				lu=(pi.getLuna()).get(j);
				System.out.println(lu.mostraLuna() + ", Indice luna=" + j + "\n");
				lun.add(j, lu);
			}
		}
	}
	

	public Stella creaPlanetario() {
		Coordinate coordiante=new Coordinate(_0,_0); 
		String nome;
		double massa;
		String codiceUnivoco;
		
		int i, cont=_0;
		int codice=_0;
		Stella s;
		
		ArrayList<Pianeta> pianeta= new ArrayList<Pianeta>(); 
		Pianeta p, pl=new Pianeta(null, null, 0,coordiante, null);
		StringBuffer r= new StringBuffer("ST");
		
		nome=InputDati.leggiStringaNonVuota("Nome stella principale: ");
		int z=_0;
		
		do {
			
			if(z==1) {  //if per dare messaggio di errore se il valore della massa è fuori dal range ( max=150(costante), min=valore massimo di massa del pianeta(100))
				System.out.println("Inserisci minore di "+ VALORE_MAX_MASSA_STELLA);
			}
			
			massa=InputDati.leggiDoubleConMinimo("Inserisci massa: ", VALORE_MAX_MASSA_PIANETA);
			z++;
		}while(massa>VALORE_MAX_MASSA_STELLA);
		
		codice= InputDati.leggiIntero("Inserisci il codice (numerico) di " + nome + " : ", _0, _999999999);
		//con questo ciclo if annidato facciamo in modo che il codice univoco della stella abbia sempre la lunghezza pari a 11
		if((codice)<10) {
			r.append("00000000");
		}else if((codice)>=10 && (codice)<100) {
			r.append("0000000");
		}else if((codice)>=100 && (codice)<1000) {
			r.append("000000");
		}else if((codice)>=1000 && (codice)<10000) {
			r.append("00000");
		}else if((codice)>=10000 && (codice)<100000) {
			r.append("0000");
		}else if((codice)>=100000 && (codice)<1000000) {
			r.append("000");
		}else if((codice)>=1000000 && (codice)<10000000) {
			r.append("00");
		}else if((codice)>=10000000 && (codice)<100000000) {
			r.append("0");
		}
		r.append(codice);
		
		codiceUnivoco= r.toString(); 
		
		coordiante.setX(_0_0); //impostiamo le coordinate della stella a 0,0 per comodità
		coordiante.setY(_0_0);
		cor.add(coordiante);
		
		cont=InputDati.leggiInteroNonNegativo("Quanti pianeti?");
		//numP=0;
		
		for(i=_0; i<cont && i<26000; i++) { //con questo ciclo for andiamo a riempire l'arraylist di pianeti
			int j=i+1;

			System.out.println("Pianeta "+ j + " della stella " + nome + ": ");
			p=pl.creaPianeta(codiceUnivoco,cor);
			pianeta.add(i, p);
		}
		
		s=new Stella(nome, codiceUnivoco, massa, coordiante, pianeta);
		return s;
	}
	
}
