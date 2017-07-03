


public class ClogErstellen {

	public static Clog erstellen() {

		Clog datei = new Clog();

		Konsole.print("Vorname: ");
		datei.add(ReadValue());
		Konsole.print("Nachname: ");
		datei.add(ReadValue());
		Konsole.print("Wohnort: ");
		datei.add(ReadValue());
		Konsole.print("Datum: ");
		datei.add(ReadValue());
		Konsole.print("Titel: ");
		datei.add(ReadValue());
		Konsole.print("Text: ");
		datei.add(ReadValue());
		Konsole.print("Schlagwörter: ");

		String suche;

		do {
			suche = ReadValue().toLowerCase();	//Großbuchstaben werden ignoriert
			datei.add(suche);
		}
		while (!suche.equals("exit"));
		datei.entfernen();
		return datei;

	}

	private static String ReadValue() {

		return Konsole.konsolelesen();

	}

}
