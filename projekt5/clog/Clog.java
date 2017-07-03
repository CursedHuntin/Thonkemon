import java.util.ArrayList;


public class Clog{

	ArrayList<String> datei;

	public Clog() {

		datei = new ArrayList<String>();

	}

	public int size() {

		return datei.size();

	}

	public String entfernen() {

		int letzter = datei.size()-1;
		return datei.remove(letzter);

	}

	public boolean add(String wert) {

		return datei.add(wert);

	}

	public String get(int i) {

		return datei.get(i);

	}

	public int leer(String wert) {

		int anzahl=-1;

		for (int zaehler=(datei.size()-1);zaehler>5;zaehler--) {

			String valueList = this.get(zaehler);
			anzahl = gleich(wert,valueList,anzahl,zaehler);
		}
		return anzahl;
	}

	public static int gleich(String value, String valueList,int anzahl,int zaehler){

		if(value.equals(valueList))return zaehler;
		return anzahl;
	}
}
