import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;


public class Konsole {

	static PrintStream Konsole = System.out;

	public static void print(Object txt) {

		Konsole.print(txt);
	}

	public static void println(Object txt) {

	Konsole.println(txt);

	}

	public static void println(int nummer) {

	Konsole.println(nummer);

	}

	public static String konsolelesen() {

		BufferedReader eingabe = new BufferedReader(new InputStreamReader(System.in));
		try {
			String input = eingabe.readLine();
			return input;
		} catch (IOException e) {
			Konsole.println("FEHLER!");
		}
		return "";
	}

	public static void clear() {

		Konsole.print("\033[H\033[2J");
		Konsole.flush();
		Konsole.print("");

	}


}
