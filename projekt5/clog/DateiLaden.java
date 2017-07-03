

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class DateiLaden {

	public static Clog ladedatei(Clog oldClog) {

		final Path pfad = Paths.get("datei.txt");
		List<String> array = new ArrayList<>();
		try {
			array = Files.readAllLines(pfad);
		} catch (IOException e) {
		Konsole.println("Fehler!");
		Konsole.konsolelesen();
		return null;
		}
        
		Clog datei = new Clog();
        
		for (String s : array) {
			datei.add(s);
		}
		Konsole.print("Datei geladen");
		Konsole.konsolelesen();
		return datei;

	}
}
