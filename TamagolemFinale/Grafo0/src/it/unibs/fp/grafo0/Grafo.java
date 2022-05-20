package it.unibs.fp.grafo0;

import java.util.*;

/**
 * Classe Grafo.
 * @author Fabio Danesi, Davide Branchetti, Brilant Gashi.
 *
 */
public class Grafo {
	  private static Random rd= new Random();
	  
	  public ArrayList<Nodo> lista;
	  public int[][] matAd;
	 
	  public Grafo(ArrayList<Nodo> l) {
	 
	    lista = l;
	    matAd = new int[l.size()][l.size()];
	  }
	  
	public ArrayList<Nodo> getLista() {
		return lista;
	}
	  
	public void setLista(ArrayList<Nodo> lista) {
		this.lista = lista;
	}

	public int[][] getMatAd() {
		return matAd;
	}

	public void setMatAd(int[][] matAd) {
		this.matAd = matAd;
	}

	/**
	 * Metodo per gestire i semi problematici.
	 * @return
	 */
	public int randomizzatore() {
		  int r=rd.nextInt(51);
		  if(r==1||r==10||r==28||r==35||r==43||r==49) {
			  r=0;
		  }
		  return r;
	  }
	  
		/**
		 * Metodo per creare il grafo.
		 * @return
		 */
	  public int riempimatricecolonna(int maxR, int maxC) {
		  int somma=0;
		  
		  rd.setSeed(randomizzatore());
		  for(int k=0;k<maxC;k++) {

			  somma=0;
			  for(int v=k; v<maxR; v++) {
				  if(k==v) {
						matAd[k][v]=0;
						continue;
					}
					 do {
					 matAd[k][v]= rd.nextInt(21)-10;
					 matAd[v][k]=(-1)*matAd[k][v];
					 }while(matAd[k][v]==0);
					 
					 //System.out.println("Valore della colonna "+k+" e nella riga "+v+ ": "+matAd[k][v]);
			  }
			  
			  for(int v=0; v<5; v++) {
					  somma=matAd[k][v]+somma;
			  }
			  //System.out.println("Somma: "+somma);
			  
			  
			  if(somma!=0) {
				  k--;
			  }
			}
		  return somma;
		  
	  }
	  
	}

