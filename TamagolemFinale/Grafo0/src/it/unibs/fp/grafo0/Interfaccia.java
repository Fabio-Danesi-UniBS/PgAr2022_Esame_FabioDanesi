package it.unibs.fp.grafo0;

import java.util.*;

/**
 * Classe Interfaccia.
 * @author Fabio Danesi, Davide Branchetti, Brilant Gashi.
 *
 */
public class Interfaccia {

	/**
	 * Metodo per il controllo di tamagolem con le stesse pietre nelle medesime disposizioni.
	 * @param p1
	 * @param p2
	 * @param pietreNum
	 * @return
	 */
	public boolean controlloPietreUguali(Player p1, Player p2, int pietreNum) {
		boolean flag=false;
		int conta=0;
		
		if(p1.getTama().get(0).getPietre().size()>0 || p2.getTama().get(0).getPietre().size()>0) {
		for(int i=0; i<pietreNum; i++) {
			String x=p1.getTama().get(0).getPietre().get(i);
			String y=p2.getTama().get(0).getPietre().get(i);
			
			if(x.equals(y)) {
				conta++;
			}
		}
		}else {
			System.out.println("Errore");
		}
		
		if(conta==3) {
			flag=true;
		}
		
		return flag;
	}
	
	/**
	 * Classe principale!
	 */
	public void menu() {
		
		int tamaNum=Tamagolem.tamaNum();
		Equilibrio equi=new Equilibrio();
		Pietra p=new Pietra(tamaNum);
		int numPietreT=Pietra.numPietrePerGolem(tamaNum);

		Player player1=null;
		Player player2=null;
		
		int fl=0;

		do {
			
			if(fl!=0) {
				System.out.println("\nPietre elementari uguali in posizioni uguali. PAREGGIO! Ricreare entrambe i giocatori con relativi tamagolem!\n");
				rimettiPietre(player1, tamaNum, p);
				rimettiPietre(player2, tamaNum, p);
				//player1.getTama().remove(0);
				//player2.getTama().remove(0);
				player1=new Player();
				player2=new Player();
			}
			fl=0;
			
			System.out.println("\n----------------------------------------------------\n\tDati giocatore 1\n----------------------------------------------------\n");
			System.out.println("Innanzitutto scegli "+numPietreT+" pietre elementari tra quelle disponibili in elenco:\n"+p.toStringAlter());
			player1=creaPlayer(tamaNum, p);
			//player1=new Player(scegliPietre(tamaNum,p), tamaNum);
			
			System.out.println("\n----------------------------------------------------\n\tDati giocatore 2\n----------------------------------------------------\n");
			System.out.println("Innanzitutto scegli "+numPietreT+" pietre elementari tra quelle disponibili in elenco:\n"+p.toStringAlter());
			player2=creaPlayer(tamaNum, p);
			//player2=new Player(scegliPietre(tamaNum,p), tamaNum);
			
			
			if(controlloPietreUguali(player1, player2, numPietreT)!=false) {
				fl=-1;
			}
			
		}while(fl!=0);

		do {
			int f=1;
			do {
				player1.getTama().get(0).combatti(player2.getTama().get(0), equi);
				System.out.println("\n-- Turno "+f+" --");
				System.out.println("Tama "+player1.getTama().get(0).getNome()+": "+player1.getTama().get(0).getVita());
				System.out.println("Tama "+player2.getTama().get(0).getNome()+": "+player2.getTama().get(0).getVita()+"\n");
				
				f++;
				
			}while(player1.getTama().get(0).sconfitta()!=true && player2.getTama().get(0).sconfitta()!=true);
			
			if(player1.getTama().get(0).sconfitta()==true) {
				System.out.println(player1.getTama().get(0).getNome()+ " è morto!");

				if(player1.fine()==false) {
					
					fl=0;
					
					do {
						
						rimettiPietre(player1,tamaNum,p);
						
						if(fl!=0) {
							System.out.println("\nPietre elementari uguali in posizioni uguali. Ricreare entrambe i giocatori con relativi tamagolem!\n");
							//rimettiPietre(player1, tamaNum,p);
							//player1.getTama().remove(0);
						}
						fl=0;
						
					
					System.out.println("\n----------------------------------------------------\n\tDati giocatore 1 ("+player1.getNome()+")\n----------------------------------------------------\n");
					System.out.println("Innanzitutto scegli "+numPietreT+" pietre elementari tra quelle disponibili in elenco:\n"+p.toStringAlter());
					player1.cambiaTamagolem(scegliPietre(tamaNum,p));
					//player1.creaNuovoTamagolem(scegliPietre(tamaNum,p));
					
					if(controlloPietreUguali(player1, player2, numPietreT)!=false) {
						fl=-1;
					}
					}while(fl!=0);
					
					player1.setNumeroDiTamagolem(player1.getNumeroDiTamagolem()-1);
				}

			}else if(player2.getTama().get(0).sconfitta()==true) {
				System.out.println(player2.getTama().get(0).getNome()+ " è morto!");

				if(player2.fine()==false) {
					
					fl=0;
					
					do {
						
						rimettiPietre(player2,tamaNum,p);
						
						if(fl!=0) {
							System.out.println("\nPietre elementari uguali in posizioni uguali. Ricreare entrambe i giocatori con relativi tamagolem!\n");
							//rimettiPietre(player2, tamaNum,p);
							//player2.getTama().remove(0);
						}
						fl=0;
						
					
					System.out.println("\n----------------------------------------------------\n\tDati giocatore 2 ("+player2.getNome()+")\n----------------------------------------------------\n");
					System.out.println("Innanzitutto scegli "+numPietreT+" pietre elementari tra quelle disponibili in elenco:\n"+p.toStringAlter());
					player2.cambiaTamagolem(scegliPietre(tamaNum,p));
					//player2.creaNuovoTamagolem(scegliPietre(tamaNum,p));
					
					if(controlloPietreUguali(player1, player2, numPietreT)!=false) {
						fl=-1;
					}
				}while(fl!=0);
					
					player2.setNumeroDiTamagolem(player2.getNumeroDiTamagolem()-1);
			}
		}
			
		}while(player1.fine()!=true && player2.fine()!=true);
		
		if(player1.getTama().get(0).sconfitta()!=true) {
			System.out.println("\nVince il giocatore: "+ player1.getNome());
		}else if(player2.getTama().get(0).sconfitta()!=true) {
			System.out.println("\nVince il giocatore: "+ player2.getNome());
		}
		
		System.out.println("\nGAME OVER!");
		
		System.out.println("\nL'equilibrio durante la partita intercorsa era:");
		equi.visualizzaTutti2();
		
	}
	
	/**
	 * Metodo per creare un player.
	 * @param tamaNum
	 * @param p
	 * @return
	 */
	public Player creaPlayer(int tamaNum, Pietra p) {
		Player player=new Player(scegliPietre(tamaNum,p), tamaNum);
		return player;
	}
	
	/**
	 * Metodo per togliere le pietre assegnate ad un tamagolem.
	 * @param player
	 * @param tamaNum
	 * @param p
	 */
	public void rimettiPietre(Player player, int tamaNum, Pietra p ) {
		for(int i=0;i<Pietra.numPietrePerGolem(tamaNum);i++) {
			String elementoPietra=player.getTama().get(0).getPietre().get(i);
			Elementi elemento=null;
			switch(elementoPietra) {
			case "ACQUA":
				elemento=elemento.ACQUA;
				break;
			case "FUOCO":
				elemento=elemento.FUOCO;
				break;
			case "TERRA":
				elemento=elemento.TERRA;
				break;
			case "VENTO":
				elemento=elemento.VENTO;
				break;
			case "FULMINE":
				elemento=elemento.FULMINE;
				break;
			default:
				elementoPietra="NON TROVATO";
			}
			p.rimetti(elemento);
		}
	}
	
	/**
	 * Metodo per scegliare le pietre da assegnare ad un tamagolem.
	 * @param tamaNum
	 * @param p
	 * @return
	 */
	public ArrayList<String> scegliPietre( int tamaNum, Pietra p){
		Elementi elemento;
		ArrayList<String> s= new ArrayList<String>();
		//in questo for andiamo ad assocciare a un singolo golem il tipo di pietre (inserito dall'utente)
		for(int i=0;i<Pietra.numPietrePerGolem(tamaNum);i++) {
			String elementoPietra;
			elemento=null;
			do {
			elementoPietra = InputDati.leggiStringaNonVuota("Di che elemento vuoi la pietra numero " + (i+1) + " : ");
			elementoPietra=elementoPietra.trim().toUpperCase();
			switch(elementoPietra) {
			case "ACQUA":
				elemento=elemento.ACQUA;
				break;
			case "FUOCO":
				elemento=elemento.FUOCO;
				break;
			case "TERRA":
				elemento=elemento.TERRA;
				break;
			case "VENTO":
				elemento=elemento.VENTO;
				break;
			case "FULMINE":
				elemento=elemento.FULMINE;
				break;
			default:
				elementoPietra="NON TROVATO";
				System.out.println("\nElemento non esistente! RIPROVA!\n");
			}
			}while(elementoPietra.contains("NON TROVATO"));
			if(p.getScortaPietre().contains(elemento)) {
				s.add(p.estrai(elemento));
				
			}
			else {
				System.out.println("\nLa pietra dell'elemento scelto è esaurita! RIPROVA!\n");
				i--;
			}
			
		}
	return s;
	}
}

