


public class HauptWahl {

	public static void wahl(Clog datei) {

		Konsole.clear();
		Hauptmenue.haupt();

		String selected = Konsole.konsolelesen();

		switch (selected) {
			case "1":
				Konsole.clear();
				datei = ClogErstellen.erstellen();
				wahl(datei);
				break;
			case "2":
				Konsole.clear();
				DateiAusgeben.ausgeben(datei);
				wahl(datei);
				break;
			case "3":
				Konsole.clear();
				datei = DateiLaden.ladedatei(datei);
				wahl(datei);
				break;
			case "4":
				Konsole.clear();
				DateiSpeichern.speicheredatei(datei);
				wahl(datei);
				break;
			case "5":
				Konsole.clear();
				break;
			default:
				wahl(datei);
				break;

		}

	}

}
