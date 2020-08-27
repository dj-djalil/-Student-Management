/*
 * @Auther : Louhichi AbdELdjalil / Madache Ismail 
 */

package com.tp.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MyApp extends Application {

	private static Stage stage;
	public static Stage getStage() {
		return stage;
	}
	public static void setStage(Stage stage) {
		MyApp.stage = stage;
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("PrincipaleView.fxml"));
		this.stage = primaryStage;

		Parent root = loader.load();
		Scene scene = new Scene(root);
		stage.setTitle("Home");
		stage.setScene(scene);
		stage.show();

	}

}
