package game;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;

public class Item_list {
	public static File[] data;
	private static ListView<String> myListView;
	protected static List<String> myList = new ArrayList<>();
	protected static ListProperty<String> listProperty = new SimpleListProperty<>();

	public static void liste_adde(URL location, ResourceBundle resources) {
		data = Read_items.Einlesen1();
		int i = 0;
		System.out.println(data[0]);
		for (i = 0; i < data.length - 1; i++) {
			myList.add(data[i].toString());
		}
		myListView.itemsProperty().bind(listProperty);
		listProperty.set(FXCollections.observableArrayList(myList));

	}
}
