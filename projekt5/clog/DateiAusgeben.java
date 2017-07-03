


public class DateiAusgeben {

	public static void ausgeben(Clog datei) {

		if (datei==null) {

			Konsole.println("Die Datei ist leer"); // Falls keine Clog vorhanden ist..für den User hilfreich
			Konsole.println("");
			Konsole.konsolelesen();
			return;
		}

		Konsole.println("Gebe das Schlagwort ein: ");
		String suche = Konsole.konsolelesen();
		suche=suche.toLowerCase();  // Groß/kleinschreibung wird nicht beachtet

		int in=datei.leer(suche);

		if (in==-1) {

			Konsole.println("Clog nicht gefunden");  // Falls das Schlagwort nicht existiert.
			Konsole.println("");
			Konsole.konsolelesen();
			return;
		}


		Konsole.println("Vorname: " + datei.get(0));
		Konsole.println("Nachname: " + datei.get(1));
		Konsole.println("Wohnort: " + datei.get(2));
		Konsole.println("Datum: " + datei.get(3));
		Konsole.println("Titel: " + datei.get(4));
		Konsole.println("Text: " + datei.get(5));
		Konsole.println("Schlagworte: ");
		String s;
		for(int i = 6; i < datei.size();i++){
			s=datei.get(i);
			Konsole.println(s);

		}
		Konsole.println("");
		Konsole.println("Bitte Entertaste bedienen");
		Konsole.konsolelesen();

	}

}
