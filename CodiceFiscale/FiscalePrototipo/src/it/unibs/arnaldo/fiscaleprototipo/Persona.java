package it.unibs.arnaldo.fiscaleprototipo;

import java.util.ArrayList;
/**
 * La classe persona intercetta i dati estratti da inputPersone
 *
 */
public class Persona {
	/**
	 * 
	 */
	private static final String[] mesi ={"A", "B", "C", "D", "E", "H", "L", "M", "P", "R", "S", "T"};
	private static int[] giorni ={31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private static final String[] cPari ={"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	
	
	private String nome;
	private String cognome;
	private String sesso;
	private String comuneDiNascita;
	private String dataDiNascita;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getSesso() {
		return sesso;
	}
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}
	public String getComuneDiNascita() {
		return comuneDiNascita;
	}
	public void setComuneDiNascita(String comuneDiNascita) {
		this.comuneDiNascita = comuneDiNascita;
	}
	public String getDataDiNascita() {
		return dataDiNascita;
	}
	public void setDataDiNascita(String dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}
	
	public Persona(String nome, String cognome, String sesso, String comuneDiNascita, String dataDiNascita) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.comuneDiNascita = comuneDiNascita;
		this.dataDiNascita = dataDiNascita;
	}
	/**
	 * In questo metodo vengono passati i dati di una persona  e dà come return il codice fiscale finale corretto
	 */
		//vengono passati i dati di una persona
		public String generaCodiceFiscale(ArrayList<Comune> comuniIta) {
			String cognome, nome, data, luogo, controllo, stringaParziale;
			
			cognome=generaCognome(getCognome());
			nome=generaNome(getNome());
			data=generaData();
			luogo=generaLuogo(comuniIta);
			
			stringaParziale=(cognome + nome + data + luogo).trim();//rappresenta il codice fiscale  senza il codice di controllo
			
			controllo=generaControllo(stringaParziale);//viene generato il codice controllo
			return (stringaParziale+controllo).trim();//il return ritorna il codice fiscale finale
			
		}
		/**
		 * Questo metodo genera una stringa cognome ( di 3 lettere) prendendo come parametro un cognome
		 */
		//cognome 1 2 3 e poi vocali
			public String generaCognome(String NomeOCognome)
			{
				int conta = 0;
				char [] stringa = new char [3];
				
				for (int i=0; i < NomeOCognome.length(); i++)
				{	
					char lettera = NomeOCognome.charAt(i);
					
					if(lettera < 64 || lettera > 90 || lettera==65 || lettera ==69 || lettera==73 || lettera == 79 || lettera==85)
                    {
                        continue;
                    }
						stringa[conta] = lettera;
						conta++;
					
					if(conta==3)
					{
						return (""+stringa[0]+stringa[1]+stringa[2]).trim();
					}
				}
				
				for (int i=0; i < NomeOCognome.length() && conta <3; i++)
				{
					char _cognome = NomeOCognome.charAt(i);
					
					if(_cognome < 64 || _cognome > 90 ||  _cognome==65 || _cognome ==69 || _cognome ==73 || _cognome == 79 || _cognome==85)
					{
						stringa[conta] = _cognome;
						conta++;
					}
					
					if(conta==3)
					{
						return (""+stringa[0]+stringa[1]+stringa[2]).trim();
					}
				}
				
				while(conta < 3)
				{
					
					stringa[conta] = 'X';
					conta++;
					
				}
				return (""+stringa[0]+stringa[1]+stringa[2]).trim();
			}
			
			//IN PROVA
			/**
			 * Questo metodo verifica se il parametro in ingresso è una vocale o un carattere speciale
			 * @lettera rappresenta una singola lettera 
			 */
			public boolean saltaGiro(char lettera)
			{
				
				if(lettera < 64 || lettera > 90 || lettera==65 || lettera ==69 || lettera==73 || lettera == 79 || lettera==85)//questo if controlla se la lettera è un carattere speciale o vocale(codice ascii)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			/**
			 * questo metodo conta il numero di consonanti nella stringa passata come parametro
			 * @elemento rappresenta una stringa ( un nome o un cognome)
			 */
			public int contaConsonante(String elemento)
			{
				char lettera;
				int consonante = 0;
				for(int i=0; i < elemento.length(); i++)
				{
					lettera = elemento.charAt(i);
					if((lettera > 64 && lettera < 90) && lettera!=65 && lettera !=69 && lettera!=73 && lettera != 79 && lettera!=85)//nella condizione if si verifica se il char non appartiene ai caratteri speciale e non è una vocale( codice ascii)
					{
						consonante++; 
					}
				}
				return consonante;
			}
			/**
			 * questo metodo restituisce una stringa di 3 caratteri, che rappresenterà il nome da inserire nel codice fiscale
			 * 
			 */
			public String generaNome(String nome)
			{
				int conta = 0; 
				int altro = 0;
				char [] stringa = new char [3];
				int contatore_consonanti = contaConsonante(nome); //conta il numero di consonanti presenti nel nome passato come parametro
				
				for (int i=0; i < nome.length(); i++)
				{	
					char lettera = nome.charAt(i);					
					
					if(saltaGiro(lettera) == true) //se si individua una vocale o un carattere speciale, si passa alla prossima lettera dell'array nome
					{
						continue;
					}
					
					stringa[conta] = lettera; //si assoccia la lettera corrente alla stringa in posizione conta
					conta++;//è il contatore relativo alla posizione dell' array stringa
					altro++;//è un contatore che permette di entrare nel if seguente
					
					if(altro==2 && contatore_consonanti > 3) //questo if decresce il contatore conta di 1 valore, per fare in modo di sovrascrivere la terza consonante nella posizione della seconda consonante(solo nel caso in cui il nome abbia 4 o più consonanti)
					{
						conta--;
					}
					
					if(conta==3)//se conta ==3 significa che l'array stringa contiene 3 lettere e perciò il nome generato viene restituito al codice fiscale finale 
					{
						return (""+stringa[0]+stringa[1]+stringa[2]).trim();
					}
				}
				
				for (int i=0; i < nome.length() && conta <3; i++) //nel caso in cui l'array stringa contiene meno di 3 lettere per insufficienza di consonanti, si procede all'inserimento delle vocali
				{
					char _nome = nome.charAt(i);
					
					if(_nome < 64 || _nome > 90 ||  _nome==65 || _nome ==69 || _nome ==73 || _nome == 79 || _nome==85) //questo if controlla se la lettera corrente è una vocale
					{
						stringa[conta] = _nome; //se la condizione è rispettata si inserisce la lettera nell'arraystringa in posizione conta
						conta++;
					}
					
					if(conta==3)//se conta ==3 significa che l'array stringa contiene 3 lettere e perciò il nome generato viene restituito al codice fiscale finale
					{
						return (""+stringa[0]+stringa[1]+stringa[2]).trim();
					}
				}
				
				while(conta < 3)//se le vocali e le consonanti sono insufficienti per riempire l'array stringa, si procede all'inserimento della lettera "X" fino a riempirlo(3 lettere fianli)
				{
					
					stringa[conta] = 'X';
					conta++;
					
				}
				return (""+stringa[0]+stringa[1]+stringa[2]).trim();
			}
			/**
			 * il metodo restituisce l'anno, mese e il giorno sottoforma di stringa che verrà inserita nel codice fiscale
			 */
			public String generaData()
			{
				String annoCompletoString, annoString, meseString, giornoString;
				
				annoString = getDataDiNascita().substring(2, 4);//annoString prende le ultime 2 cifre dell'anno
				annoCompletoString=getDataDiNascita().substring(0, 4);
				
				meseString = getDataDiNascita().substring(5, 7);
				giornoString = getDataDiNascita().substring(8, 10);
				//Importante controllo!!!
				bisestile1(annoCompletoString);//viene passato l'anno completo al metodo bisestile
				
				int mese, giorno;
				
				mese = Integer.parseInt(meseString); //mese viene convertito in int
				giorno = Integer.parseInt(giornoString);//giorno viene convertito in int
				
				boolean esci=false;
				
				for(int i=0; i < mesi.length && esci!=true; i++)//questo ciclo for permette di associare al mese la sua lettera corrispondente
				{
					if((mese-1) == i)
					{
						meseString = mesi[i];
						esci=true;
					}
				}
				
				if(getSesso().equals("F"))//nel caso di una femmina il giorno avrà un valore diverso 
				{
					giorno = giorno + 40;
					giornoString = giorno + "";
				}
				
				return (annoString + meseString + giornoString).trim();//trim toglie gli spazi inutili tra le stringhe
				
			}
			/**
			 * il metodo genera il luogo confrontandolo con l'arraylist dei comuni ( controllo dell'esistenza già eseguito)
			 */
			public String generaLuogo(ArrayList<Comune> comuni)
			{
				String codiceCatastale = new String();
				
				for(int i=0; i < comuni.size(); i++)
				{
					if(getComuneDiNascita().equals(comuni.get(i).getNome()) == true)
					{
						codiceCatastale = comuni.get(i).getCodice();
					}
				}
				return codiceCatastale ;
			}
			/**
			 *  il metodo considera i caratteri della stringa del codice fiscale nella posizione pari e assoccia ad ogni carattere un valore
			 */
			private int confrontaPari(String caratterePari) {
				int pos=0;
				
				if(caratterePari.matches("[0-9]+")) {
					return Integer.parseInt(caratterePari);
				}else if(caratterePari.matches("[A-Z]+")){
					for(int i=0; i<cPari.length; i++) {
						if(caratterePari.equals(cPari[i])) {
							pos=i;
						}
					}
				}
				return pos;
			}
			/**
			 * il metodo considera i caratteri della stringa del codice fiscale nella posizione dispari e assoccia ad ogni carattere un valore
			 */
			private int confrontaDispari(String carattereDispari) {
				int pos=0;
				
				if(carattereDispari.matches("[0-9]+")) {//controlla che la stringa contenga caratteri numerici e  restiutisce un valore specifico per numero
					switch(Integer.parseInt(carattereDispari)) {
					case 0:
						return 1;
					case 1:
						return 0;
					case 2:
						return 5;
					case 3:
						return 7;
					case 4:
						return 9;
					case 5:
						return 13;
					case 6:
						return 15;
					case 7:
						return 17;
					case 8:
						return 19;
					case 9:
						return 21;
					}
				}else if(carattereDispari.matches("[A-Z]+")){//controlla che la stringa contenga caratteri alfabetici e restiutisce un valore specifico per carattere
					
					switch(carattereDispari) {
					case "A":
						pos= 1;
						break;
					case "B":
						pos= 0;
						break;
					case "C":
						pos= 5;
						break;
					case "D":
						pos= 7;
						break;
					case "E":
						pos= 9;
						break;
					case "F":
						pos= 13;
						break;
					case "G":
						pos= 15;
						break;
					case "H":
						pos= 17;
						break;
					case "I":
						pos= 19;
						break;
					case "J":
						pos= 21;
						break;
					case "K":
						pos= 2;
						break;
					case "L":
						pos= 4;
						break;
					case "M":
						pos= 18;
						break;
					case "N":
						pos= 20;
						break;
					case "O":
						pos= 11;
						break;
					case "P":
						pos= 3;
						break;
					case "Q":
						pos= 6;
						break;
					case "R":
						pos= 8;
						break;
					case "S":
						pos= 12;
						break;
					case "T":
						pos= 14;
						break;
					case "U":
						pos= 16;
						break;
					case "V":
						pos= 10;
						break;
					case "W":
						pos= 22;
						break;
					case "X":
						pos= 25;
						break;
					case "Y":
						pos= 24;
						break;
					case "Z":
						pos= 23;
						break;
						
					}
				}
				return pos;
			}
			/**
			 * il metodo genera l'ultima cifra del codice fiscale
			 */
			public String generaControllo(String stringaParziale) {
				String parte;
				int sommaPari=0, sommaDispari=0, somma=0, resto=0, posizione=-1;
				boolean flag=false;
				
				for(int i=1; i<stringaParziale.length(); i=i+2) {//pari
					parte= stringaParziale.substring(i, i+1);
					sommaPari= sommaPari + confrontaPari(parte);
				}
				
				for(int i=0; i<stringaParziale.length(); i=i+2) {//dispari
					parte= stringaParziale.substring(i, i+1);
					sommaDispari= sommaDispari + confrontaDispari(parte);
				}
				
				somma=sommaPari + sommaDispari;
				resto=somma%26;
				
				for(int i=0; i<cPari.length && flag!=true; i++) {
					if(resto==i) {
						posizione=i;
						flag=true;
					}
				}
				
				return cPari[posizione];
			}
			/**
			 * modifica l'array giorni se l'anno è bisestile o no
			 */
			public void bisestile1(String anno) {
				int a=Integer.parseInt(anno);
			    if ( ( ( a % 4 == 0 ) && ( a % 100 != 0 ) ) || ( a % 400 == 0 ) ) {
			        //se bisestile aggiorna Febbraio a 29 giorni!
			        giorni[1]=29;
			     }else {
			    	 giorni[1]=28;
			     }
			  }
	
			@Override
			public String toString() {
				return cognome +", "+ nome +", "+ dataDiNascita +", "+ comuneDiNascita+"\n";
			}	
			
	public String toString(String controllo) {
		return (cognome + nome + dataDiNascita + comuneDiNascita + controllo).trim();
	}
	
	
	
}
