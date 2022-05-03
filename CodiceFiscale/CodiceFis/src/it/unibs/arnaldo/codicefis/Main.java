package it.unibs.arnaldo.codicefis;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Main {
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException {
		// TODO Auto-generated method stub
		ProcessazioneDati processo=new ProcessazioneDati();
		processo.esegui();
		//altro...
	}
	
}
