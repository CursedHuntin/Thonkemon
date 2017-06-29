package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Read_items {
	public static File[] Einlesen1() {

		// Das auszulesende Verzeichnis
		File verzeichnis = new File("C:/Users/Sebastian/Desktop/informatik/eclipse/Musikspieler/src/musik");

		// Inhalt von "verzeichnis" in das Array "dateien" einlesen
		File[] dateien = new File[200];
		File[] help;
		help = verzeichnis.listFiles();
		// Controller.laenge=help.length;
		dateien = help;
		return dateien;

	}

	public static File[] Einlesen2(String s) throws FileNotFoundException {
		int i = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/List/" + s + ".txt"));
			String line = br.readLine();

			while (line != null) {
				line = br.readLine();
				i++;

			}
		} catch (IOException e) {
			// Controller.sucess=false;
			System.out.println("Beim erstllen deiner Playlist gab es einen Fehler: \n"
					+ "Entweder existiert die Liste nicht oder eine Musikdatei fehlt");
		}
		// Controller.laenge=i;
		Selection.leng = i;
		File[] dateien = new File[200];
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/List/" + s + ".txt"));
			String line = br.readLine();
			i = -1;
			while (line != null) {
				i++;
				dateien[i] = new File("src/musik/" + line);
				line = br.readLine();
			}
		} catch (IOException e) {
			// Controller.sucess=false;
			System.out.println("Beim erstllen deiner Playlist gab es einen Fehler: \n"
					+ "Entweder existiert die Liste nicht oder eine Musikdatei fehlt");
		}

		return dateien;
	}
}
