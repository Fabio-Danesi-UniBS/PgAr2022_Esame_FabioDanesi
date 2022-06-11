package it.unibs.fp.mylib;

public class ControllaStringa {

	public static boolean soloMaiuscole(String daControllare) {
		if(daControllare.matches("[A-Z]+")) {
			return true;
		}
		return false;
	}
	
	public static boolean soloMinuscole(String daControllare) {
		if(daControllare.matches("[a-z]+")) {
			return true;
		}
		return false;
	}
	
	public static boolean soloLettereDiUnTipo(String daControllare) {
		if(daControllare.matches("[A-Z]+") || daControllare.matches("[a-z]+")) {
			return true;
		}
		return false;
	}
	
	public static boolean soloLettereDiOgniTipo(String daControllare) {
		if(daControllare.matches("[a-zA-Z]+")) {
			return true;
		}
		return false;
	}
	
	public static boolean soloNumeri(String daControllare) {
		if(daControllare.matches("[0-9]+")) {
			return true;
		}
		return false;
	}
	
	public static boolean noSpecialiNoSpazi(String daControllare) {
		if(daControllare.matches("[0-9a-zA-Z]+")) {
			return true;
		}
		return false;
	}
	
	public static boolean cercaParolaNellaStringa(String daControllare, String parolaDaCercare) {
		StringBuffer parola= new StringBuffer();
		
		parola.append("(.*)"+parolaDaCercare+"(.*)");
		String daCercare=parola.toString();
		
		if(daControllare.matches(daCercare)) {
			return true;
		}
		return false;
	}
	
}
