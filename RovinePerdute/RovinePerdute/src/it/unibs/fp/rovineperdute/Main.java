package it.unibs.fp.rovineperdute;

import javax.xml.stream.XMLStreamException;

public class Main {
    public static void main(String args[]){

        try {
			Interfaccia interfaccia=new Interfaccia();
		} catch (XMLStreamException e) {
			System.out.println("Lettura o scrittura file non riuscita!\n"+e.getMessage());
			e.printStackTrace();
		}
    }
}