package com.tp.view;

import java.util.Collections;
import java.util.Comparator;

import com.tp.db.Connection;
import com.tp.model.Etudiant;
import com.tp.model.Specialite;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;

public class AjouterViewController {
	@FXML
	TextField nom;
	@FXML
	TextField prenom;
	@FXML
	DatePicker date;
	@FXML
	TextField moyenne;
	@FXML
	ChoiceBox<Specialite> specialite;

	public void initialize() {
		specialite.getItems().addAll(PrincipaleViewController.listDesSpecialite);

	}

	public void save() {
		Alert erreur = new Alert(AlertType.ERROR);
		try {
			String n = nom.getText();
			String p = prenom.getText();
			String d = date.getValue().toString();
			float m = Float.valueOf(moyenne.getText());
			int numS = specialite.getSelectionModel().getSelectedItem().getNumS();
			Etudiant e = new Etudiant(n, p, m, d, numS);
			if(n!=null && p!= null) {
			// ajouter l'etudiant a la list des etudiant ()
			PrincipaleViewController.listDesEtudiant.add(e);
			
			 
			// retrier la list aprés l'ajoute d'un nouveau etudiant
			Collections.sort(PrincipaleViewController.listDesEtudiant, new Comparator<Etudiant>() {

				@Override
				public int compare(Etudiant o1, Etudiant o2) {
					if (o1.getMoyenneE() > o2.getMoyenneE())
						return -1;
					else if (o1.getMoyenneE() < o2.getMoyenneE())
						return 1;
					else
						return 0;

				}

			});
			// set l'observation
			PrincipaleViewController.listDesSpecialite.forEach(PrincipaleViewController::setObservation);
			Connection.ajouterEtudiant(e);
			// vider tous les champs 
			nom.setText(null);
			prenom.setText(null);
			date.setValue(null);
			moyenne.setText(null);
			specialite.getSelectionModel().select(-1);
			
			Alert valide = new Alert(AlertType.INFORMATION);
			valide.setHeaderText("Etudiant " + e.getNomE() + " a etait bien ajouter");
			valide.show();
			}else {
				
				if (e.getClass().getName().toString().equals("java.lang.NullPointerException"))
					erreur.setHeaderText("Tous les champs doivent être remplis");
			}
		} catch (Exception e) {
			if (e.getClass().getName().toString().equals("java.lang.NullPointerException"))
				erreur.setHeaderText("Tous les champs doivent être remplis");
			else
				erreur.setHeaderText("Entrer une moyenne valide svp !");
			erreur.showAndWait();
		}
	}

	@FXML
	public void exit() {
		PrincipaleViewController.addStage.close();
	}
}
