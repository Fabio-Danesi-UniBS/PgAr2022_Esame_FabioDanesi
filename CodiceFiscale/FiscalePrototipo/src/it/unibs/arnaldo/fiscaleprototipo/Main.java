package it.unibs.arnaldo.fiscaleprototipo;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class Main {
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, TransformerException {
		// TODO Auto-generated method stub
		ProcessazioneDati processo=new ProcessazioneDati();
		processo.esegui();
		//altro...
	}
	
}
