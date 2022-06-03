package it.unibs.fp.rovineperdute;

import java.util.*;

public  class Mappa {
    int matricePercorsi[][];
    HashMap<Integer, Citta> listaCitta;

    public Mappa(HashMap<Integer, Citta> listaCitta) {
        this.listaCitta = listaCitta;
        this.matricePercorsi = new int[listaCitta.size()][listaCitta.size()];
    }
    /**
     * Questo metodo crea la mappa basandosi solamente sull'altitudine; verifica se esiste il collegamento tra le città,
     * calcola la relativa distanza tra di esse e la imposta.
     * 
     */
 
    public void percorsoAltitudine(Mappa mappaDaEsaminare){
        int altezza=0;
        for (int i=0; i < mappaDaEsaminare.listaCitta.size(); i++){
            int indice=0;
            for (int j=0; j < mappaDaEsaminare.listaCitta.size(); j++){
               
                if (mappaDaEsaminare.matricePercorsi[i][j] == 1) {
                   
                    altezza = (Math.abs(mappaDaEsaminare.listaCitta.get(i).getAltitudine()
                            - mappaDaEsaminare.listaCitta.get(j).getAltitudine()));
                   
                    this.listaCitta.get(i).getCittaCollegate().get(indice).setPeso(altezza);
                    indice++;
                }
            }
        }
    }
    /**
     * Questo metodo crea la mappa per la squadra che considera solo le distanze cartesiane, poi verifica se esiste un collegamento tra due citta
     * ,e calcola la distanza tra le due citta tra due citta e la imposta.
     * @param mappaDaEsaminare
     */
 
    public void percorsoPlanare(Mappa mappaDaEsaminare){
        int distanza=0, x, y;

        for (int i=0; i < mappaDaEsaminare.listaCitta.size(); i++){
            x = mappaDaEsaminare.listaCitta.get(i).getX();
            y = mappaDaEsaminare.listaCitta.get(i).getY();
            int indice = 0;
            for (int j=0; j < mappaDaEsaminare.listaCitta.size(); j++){
                
                if (mappaDaEsaminare.matricePercorsi[i][j] == 1) {
                   
                    distanza = (int) Math.sqrt(Math.pow((x - listaCitta.get(j).getX()), 2)
                            + Math.pow((y - listaCitta.get(j).getY()), 2));
                    
                    this.listaCitta.get(i).getCittaCollegate().get(indice).setPeso(distanza);
                    indice++;
                }
            }
        }
    }
    /**
     * Questo metodo seleziona righe matrice ciascuna corrispondente a una localita,controlla quante destinazioni sono raggiungibili partendo dalla localita' selezionata
     * e infine imposta il percorso come "esistente" all'interno della matricePercorsi
     */
    public void matriceDistanze() {
        for (int i=0; i < this.listaCitta.size(); i++){
            for (int j = 0; j < this.listaCitta.get(i).getCittaCollegate().size(); j++){
                this.matricePercorsi[i][this.listaCitta.get(i).getCittaCollegate().get(j).getCittaArrivo().getId()] = 1;
            }
        }
    }
    /**
     * Restituisce la citta' presente a quel determinato indice della listaCitta
     */
    private static Citta trovaCitta(int idCitta, HashMap<Integer, Citta> listaCitta){
        return listaCitta.get(idCitta);
    }

    /**Questo metodo trova il percorso migliore per raggiungere le Rovine Perdute,Se la lista di elementi da controllare e' vuota il metodo si interrompe,
     * Seleziona la citta da analizzare, controlla quanti collegamenti,individua la citta collegata e ne recupera le informazioni,Se la citta e' gia' stata controllata viene ignorata
     * Calcola la lunghezza del percorso attraverso il nodoAttuale,Se il percorso attraverso il nodoAttuale risulta essere migliore viene salvato,Rimuove la citta per sostituirla
     * Aggiorna gli attributi,Salva nuovamente la citta nella lista.
     * 
     */
    public void percorsoCorto (Citta cittaDiProvenienza){

        cittaDiProvenienza.setDistanza(0);
        PriorityQueue<Citta> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(cittaDiProvenienza);
        cittaDiProvenienza.setVisitato(true);
        int idCercato;

        while (!priorityQueue.isEmpty()){
           
            Citta nodoAttuale = priorityQueue.poll();
            for ( Archi arco  : nodoAttuale.getCittaCollegate()){

               
                idCercato = arco.getCittaArrivo().getId();
                Citta cit = trovaCitta(idCercato, this.listaCitta);

               
                if (!cit.isVisitato()){
                
                    int newDistance = nodoAttuale.getDistanza() + arco.getPeso();
                   
                    if (newDistance < cit.getDistanza()) {
                        
                        priorityQueue.remove(cit);
                        cit.setDistanza(newDistance);
                        cit.setCittaAttuale(nodoAttuale);      
                        priorityQueue.add(cit);
                    }

                }
            }
                
                nodoAttuale.setVisitato(true);
        }

    }
    /**
     * Le tappe del percorso vengono salvate in un'apposita lista,La lista viene invertita in modo che la partenza sia a indice 0 e restituisce il percorso
     */
    public List<Citta> restituisciPercorsoPiuCorto (Citta cittaTarget){
        List<Citta> percorso = new ArrayList<>();
        for(Citta nodoPredecessore = cittaTarget; nodoPredecessore!= null; nodoPredecessore = nodoPredecessore.getCittaAttuale()){
            percorso.add(nodoPredecessore);
        }
        Collections.reverse(percorso);
        return percorso;
    }

}