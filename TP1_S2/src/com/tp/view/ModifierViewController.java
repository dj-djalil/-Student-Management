package com.tp.view;

import java.time.LocalDate;

import com.tp.db.Connection;
import com.tp.model.Etudiant;
import com.tp.model.Specialite;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ModifierViewController {
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

	static Etudiant selectedEtudiant;

	public void initialize() {
		specialite.setItems(PrincipaleViewController.listDesSpecialite);
	}

	public void initInfo(Etudiant etudiant) {
		selectedEtudiant = etudiant;
		nom.setText(etudiant.getNomE());
		prenom.setText(etudiant.getPrenomE());
		date.setValue(LocalDate.parse(etudiant.getDateN()));
		PrincipaleViewController.listDesSpecialite.forEach(s -> {
			if (s.getNumS() == etudiant.getNumS())
				specialite.getSelectionModel().select(s);
		});
		moyenne.setText(String.valueOf(etudiant.getMoyenneE()));
	}

	// this methode use another way to use object from PrincipaleViewController
	// withount instantiate
	@FXML
	public void exit() {
		PrincipaleViewController.editStage.close();
	}

	@FXML
	public void edit() {
		Alert erreur = new Alert(AlertType.ERROR);
		Alert info = new Alert(AlertType.INFORMATION);
		try {
			String n = nom.getText();
			String p = prenom.getText();
			String d = date.getValue().toString();
			int s = specialite.getSelectionModel().getSelectedItem().getNumS();
			float m = Float.valueOf(moyenne.getText());
			if (n == null || n.equals("") || p == null || p.equals("")) {
				erreur.setHeaderText("Tous les champs doivent être remplis");
				erreur.showAndWait();
			} else {
				PrincipaleViewController.listDesEtudiant.forEach(e -> {
					if (e.equals(selectedEtudiant)) {
						e.setNomE(n);
						e.setPrenomE(p);
						e.setDateN(d);
						e.setMoyenneE(m);
						e.setNumS(s);
					}
				});
				// set Observation
				PrincipaleViewController.listDesSpecialite.forEach(PrincipaleViewController::setObservation);
				// modifier l'etudiant dans la base de donnee 
				Connection.modifier(selectedEtudiant);

				PrincipaleViewController.editStage.close();
				info.setHeaderText("etudiant bien modifier ");
				info.showAndWait();
				 
			}

		} catch (Exception e) {

			 if (e.getClass().getName().equals("java.lang.NullPointerException"))
			 erreur.setHeaderText("Tous les champs doivent être remplis");
			 else
			 erreur.setHeaderText("Entrer une moyenne valide svp !");
			
			 erreur.showAndWait();
			System.out.println(e);
		}

	}
}
