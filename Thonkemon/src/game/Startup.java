package game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Startup extends Application {
	static Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		try {
			Startup.primaryStage = primaryStage;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Anfang.fxml"));
			Parent root = (Parent) loader.load();
			Selection con = (Selection) loader.getController();
			con.setStage(primaryStage);
			Scene scene = new Scene(root, 1000, 600);
			con.setScene(scene);
			String sheet = getClass().getResource("/application.css").toExternalForm();
			scene.getStylesheets().add(sheet);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Thonkemon");
			primaryStage.centerOnScreen();
			primaryStage.show();
			Player p1 = new Player();
			Player p2 = new Player();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}

// public class Startup {
// public static void main(String[] args) {
// new Fight(new Player());
// Player p1 = new Player();
// Player p2 = new Player();
// new Selection(p1, p2);
// Kappas
// }
// }//
