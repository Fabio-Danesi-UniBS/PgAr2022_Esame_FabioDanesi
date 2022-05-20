package it.unibs.fp.grafo0;

import java.util.*;

/**
 * Classe Equilibrio.
 * @author Fabio Danesi, Davide Branchetti, Brilant Gashi.
 *
 */
public class Equilibrio {

	private HashMap<Nodo, ArrayList<Arco>> adiacenze;
		
		public Equilibrio() {
			super();
			this.adiacenze = creaEquilibrio();
		}
		
		public HashMap<Nodo, ArrayList<Arco>> getAdiacenze() {
			return adiacenze;
		}

		public void setAdiacenze(HashMap<Nodo, ArrayList<Arco>> adiacenze) {
			this.adiacenze = adiacenze;
		}

		/**
		 * Crea un nuovo equilibrio.
		 * @return HashMap<Nodo, ArrayList<Arco>>
		 */
		public HashMap<Nodo, ArrayList<Arco>> creaEquilibrio(){
			
		    ArrayList<Nodo> l = new ArrayList<Nodo>();
		    adiacenze = new HashMap<Nodo, ArrayList<Arco>>();
		    
		    for(Elementi ele : Elementi.values()) {
				l.add(new Nodo(ele.toString()));
			}
		 
		    Grafo g = new Grafo(l);
		    
		    int f=0;
		    for(Elementi ele : Elementi.values()) {
				f++;
			}
		 
		    g.riempimatricecolonna(f, f);
		 
		    //System.out.println();
		    
		    int i=0, j;
		    ArrayList<Arco> y2=new ArrayList<Arco>();
		    
		    for(Elementi ele : Elementi.values()) {
		    	Nodo x1=g.getLista().get(i);
				//System.out.print(x1+":\n");
				j=0;
				y2=new ArrayList<Arco>();
				for(Elementi ele1 : Elementi.values()) {
					
					String y1=ele1.toString();
					//System.out.print("\t"+y1+": ");

					//System.out.print("\t"+g.matAd[i][j]);
					y2.add(new Arco(y1, g.matAd[i][j]));

					j++;
				}
				adiacenze.put(x1, y2);
				//System.out.println();
				i++;
			}
		    
		    //visualizzaTutti();
		    return adiacenze;
		}
		
		/**
		 * Metodo FONDAMENTALE per l'estrazione dei valori dalla hashmap!
		 * @param elemento1
		 * @param elemento2
		 * @return valore di elemento1-->elemento2
		 */
		public int visualizzaValori(String elemento1, String elemento2) {
					
					ArrayList<Arco> m=null;
					
					Iterator it = adiacenze.entrySet().iterator();
				    while(it.hasNext()) {
				    	Map.Entry entry = (Map.Entry)it.next();
				    	
				    	//System.out.println(entry.getKey());
				    	String f=entry.getKey().toString();
				    	
				    	if(f.equals(elemento1)) {
				    		//System.out.println(entry.getValue());
				    		m=(ArrayList<Arco>) entry.getValue();
				    		
				    		for(int i=0; i<m.size(); i++) {
				    			String g= m.get(i).getElemento();
				    			if(g.equals(elemento2)) {

				    				return m.get(i).getValoreDanno();

				    			}
				    		}
				    	}
				    }
				    return 0;
				}
		
		/**
		 * Classe per la visualizzazione dei Nodi con relativi Archi.
		 */
		public void visualizzaTutti2() {
			
			ArrayList<Arco> m=null;
			
			Iterator it = adiacenze.entrySet().iterator();
		    while(it.hasNext()) {
		    	Map.Entry entry = (Map.Entry)it.next();
		    	
		    	
		    	String f=entry.getKey().toString();
		    	
		    	System.out.println("\nNodo: "+f+"\n\tArco: ");
		    	System.out.print("\t");
		    	
		    		m=(ArrayList<Arco>) entry.getValue();
		    		
		    		for(int i=0; i<m.size(); i++) {
		    			String g= m.get(i).getElemento();
		    			int d= m.get(i).getValoreDanno();
		    			
		    			System.out.print("\t"+g.toString()+": "+d+"\t");
		    		}
		    }
		}
		
		
		
		/**
		 * Visualizzazione di controllo.
		 */
		public void visualizzaTutti() {
		
		Iterator it = adiacenze.entrySet().iterator();
	    while(it.hasNext()) {
	    	Map.Entry entry = (Map.Entry)it.next();
	    	
	    	System.out.println("\n" + entry.getKey());
	        System.out.println("\t" + entry.getValue());
	    }
	}
}
