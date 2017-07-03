

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class DateiSpeichern {


	public static void speicheredatei(Clog datei) {

		if (datei == null){
			Konsole.println("Keine datei zum speichern");
			Konsole.konsolelesen();
			return;
		}

		final Path pfad = Paths.get("datei.txt");
		final List<String> array = new ArrayList<String>();
		int groesse = datei.size();
		for (int i=0;i<groesse;i++) {
			array.add(datei.get(i));
		}

		try {
			Files.write(pfad,array);
			Konsole.println("");
		} catch (IOException e) {
			Konsole.println("Daten konnten nicht geladen werden.");
			Konsole.konsolelesen();
		}
		Konsole.print("Datei gespeichert");
		Konsole.konsolelesen();
    }

}
