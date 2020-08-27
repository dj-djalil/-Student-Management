package com.tp.view;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.tp.db.Connection;
import com.tp.model.Domaine;
import com.tp.model.Etudiant;
import com.tp.model.Filiere;
import com.tp.model.Specialite;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class PrincipaleViewController {

	// comboboxes
	@FXML
	ComboBox<Domaine> domaine;
	@FXML
	ComboBox<Filiere> filiere;
	@FXML
	ComboBox<Specialite> specialite;

	// table view
	@FXML
	TableView table;
	@FXML
	TableColumn<Etudiant, Integer> num;
	@FXML
	TableColumn<Etudiant, String> nom;
	@FXML
	TableColumn<Etudiant, String> prenom;
	@FXML
	TableColumn<Etudiant, Float> moyenne;
	@FXML
	TableColumn<Etudiant, String> date;
	@FXML
	TableColumn<Etudiant, String> observation;

	// TextFiled
	@FXML
	TextField rechercher;

	// buttons
	@FXML
	Button tous;
	// observable lists contain all data
	static ObservableList<Etudiant> listDesEtudiant;
	ObservableList<Domaine> listDesDomaine;
	ObservableList<Filiere> listDesFiliere;
	static ObservableList<Specialite> listDesSpecialite;

	// student list after filtring -> by specialite
	ObservableList<Etudiant> EtudiantMatched;
	//
	static int k;
	//
	ChangeListener<String> myChangeListner = this::changedImplimentation;

	public PrincipaleViewController() {

	}

	public void initialize() {
		listDesEtudiant = FXCollections.observableArrayList(Connection.getListEtudiant());
		listDesDomaine = FXCollections.observableArrayList(Connection.getListDomaine());
		listDesFiliere = FXCollections.observableArrayList(Connection.getListFiliere());

		listDesSpecialite = FXCollections.observableArrayList(Connection.getListSpecialite());

		// set observation -> pour chaque specialite les 6 premiere etudiants sont admis
		// et l'age <= 24 ans
		// pour passer le concours
		// utilisation des stream Api (java 8)
		// pour chaque specialite -> s

		listDesSpecialite.forEach(PrincipaleViewController::setObservation);

		// add items to domaine ComboBox
		domaine.setItems(listDesDomaine);
		// domaine listner
		domaine.getSelectionModel().selectedItemProperty().addListener((v, o, n) -> {

			// recuperation de la filiere qui correspond au domaine selectionner (utilise
			// les stream )
			ObservableList<Filiere> filierMatched = FXCollections.observableArrayList(
					listDesFiliere.stream().filter(f -> f.getNumD() == n.getNumD()).collect(Collectors.toList()));
			specialite.getItems().clear();
			filiere.getItems().clear();
			filiere.setItems(filierMatched);
		});

		// filiere listner
		filiere.getSelectionModel().selectedItemProperty().addListener((v, o, n) -> {

			if (n != null) {
				// recuperation de la specialite qui correspond au filiere selectionner (utilise
				// les stream )
				ObservableList<Specialite> specialiteMatched = FXCollections.observableArrayList(listDesSpecialite
						.stream().filter(s -> s.getNumF() == n.getNumF()).collect(Collectors.toList()));
				specialite.setItems(specialiteMatched);
			}
		});

		// specialite listner

		specialite.getSelectionModel().selectedItemProperty().addListener((v, o, n) -> {

			// on va recuperer la list des etudiant correspond aux item selectionner
			if (n != null) {

				table.setItems(null);

				EtudiantMatched = FXCollections.observableArrayList(
						listDesEtudiant.stream().filter(e -> e.getNumS() == n.getNumS()).collect(Collectors.toList()));
				rechercher.textProperty().removeListener(myChangeListner);
				// EtudiantMatched.stream().map(e->{e.setObservation("admis");return e;});
				if (!EtudiantMatched.isEmpty()) {
					num.setCellValueFactory(new PropertyValueFactory<Etudiant, Integer>("numE"));
					nom.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("nomE"));
					prenom.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("prenomE"));
					date.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("dateN"));
					moyenne.setCellValueFactory(new PropertyValueFactory<Etudiant, Float>("moyenneE"));
					observation.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("observation"));
					table.setItems(EtudiantMatched);
					if (EtudiantMatched != null)
						rechercher.textProperty().addListener(myChangeListner);
				}

			}

		});

	}

	// Tous les Etudiant

	@FXML
	public void DisplayAllStudents() {
		specialite.getSelectionModel().select(null);
		num.setCellValueFactory(new PropertyValueFactory<Etudiant, Integer>("numE"));
		nom.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("nomE"));
		prenom.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("prenomE"));
		date.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("dateN"));
		moyenne.setCellValueFactory(new PropertyValueFactory<Etudiant, Float>("moyenneE"));
		observation.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("observation"));
		table.setItems(listDesEtudiant);
		rechercher.textProperty().addListener(myChangeListner);

	}

	public void changedImplimentation(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		FilteredList<Etudiant> filterData = new FilteredList<>(listDesEtudiant, p -> true);
		filterData.setPredicate(etud -> {

			if (newValue == null || newValue.isEmpty()) {
				return true;
			}
			String lowerCaseFilter = newValue.toLowerCase();

			if (etud.getNomE().toLowerCase().contains(lowerCaseFilter)) {
				return true;
			} else if (etud.getPrenomE().toLowerCase().contains(lowerCaseFilter)) {
				return true;
			} else if (String.valueOf(etud.getNumE()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true;
			} else
				return false;

		});

		SortedList<Etudiant> sortedData = new SortedList<>(filterData);
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		nom.setCellValueFactory(new PropertyValueFactory<>("numE"));
		nom.setCellValueFactory(new PropertyValueFactory<>("nomE"));
		prenom.setCellValueFactory(new PropertyValueFactory("prenomE"));
		moyenne.setCellValueFactory(new PropertyValueFactory<>("moyenneE"));
		date.setCellValueFactory(new PropertyValueFactory("dateN"));
		date.setCellValueFactory(new PropertyValueFactory("observation"));
		table.setItems(sortedData);

	}

	// supprimer un etudiant
	@FXML
	public void supprimer() {

		if (table.getSelectionModel().getSelectedItem() == null) {
			Alert warnning = new Alert(AlertType.ERROR);
			warnning.setHeaderText("Attention !");
			warnning.setContentText("Aucun Etudaint Selectionner");
			warnning.showAndWait();
		} else {
			Etudiant e = (Etudiant) table.getSelectionModel().getSelectedItem();
			listDesEtudiant.remove(e);
			Connection.supprimerEtudiant(e);
			if (EtudiantMatched != null && EtudiantMatched.contains(e)) {
				EtudiantMatched.remove(e);
			}

			Alert info = new Alert(AlertType.INFORMATION);
			info.setHeaderText("l'etudiant [" + e.getNomE() + "]  a etait bien supprimer ");
			info.show();
		}

	}

	static Stage addStage = new Stage();

	@FXML
	public void ajouter() throws IOException {
		// System.out.println("clicked");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("AjouterView.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);

		addStage.setTitle("Add Student");
		addStage.setScene(scene);
		addStage.showAndWait();
	}

	static Stage editStage = new Stage();

	@FXML
	public void modifier() throws IOException {
		System.out.println("Modifier clicked ");
		if (table.getSelectionModel().getSelectedIndex() != -1) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("ModifierView.fxml"));
			Parent root = loader.load();
			ModifierViewController controller = loader.getController();
			controller.initInfo((Etudiant) table.getSelectionModel().getSelectedItem());
			Scene scene = new Scene(root);

			editStage.setTitle("Edit Student");
			editStage.setScene(scene);
			editStage.show();
		} else {
			Alert warnning = new Alert(AlertType.ERROR);
			warnning.setHeaderText("Attention !");
			warnning.setContentText("Aucun Etudaint Selectionner");
			warnning.showAndWait();
		}

	}

	public static void setObservation(Specialite s) {
		k = 0;
		// pour chaque etudiant -> e : si l'etudaint e apartient à -> s faire le
		// traitment ci dessous
		listDesEtudiant.forEach(e -> {
			if (s.getNumS() == e.getNumS()) {

				int currentYear = LocalDate.now().getYear();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
				String date = e.getDateN();
				int yearOfBirth = LocalDate.parse(date, formatter).getYear();
				if (k < 6) {
					if (currentYear - yearOfBirth <= 24) {
						e.setObservation("Admis pour passer le concours");
						k++;
					} else {
						e.setObservation("Probléme d'age ");
					}
				} else {
					e.setObservation("Non admis ");
				}

			}
		});
	}

	@FXML
	public void imprimer() {
		// dialogue window to choose file name
		FileChooser choose = new FileChooser();
		choose.getExtensionFilters().addAll(new ExtensionFilter("All PDF Files", "*.pdf"));
		Document document = new Document();

		FileOutputStream file;

		try {
			file = new FileOutputStream(choose.showSaveDialog(MyApp.getStage()));
		

		if (file != null) {
			PdfWriter.getInstance(document, file);
			document.open();
			document.add(new Paragraph("\n\n"));
			PdfPTable t = new PdfPTable(6);
			Font f = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL);
			Font f1 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

			PdfPCell num = new PdfPCell(new Paragraph("Num", f1));
			PdfPCell nom = new PdfPCell(new Paragraph("Nom", f1));
			PdfPCell prenom = new PdfPCell(new Paragraph("Prenom", f1));
			PdfPCell date = new PdfPCell(new Paragraph("Date Naissance", f1));
			PdfPCell moyenne = new PdfPCell(new Paragraph("Moyenne", f1));
			PdfPCell observation = new PdfPCell(new Paragraph("Observation", f1));
			t.addCell(num);
			t.addCell(nom);
			t.addCell(prenom);
			t.addCell(moyenne);
			t.addCell(date);
			t.addCell(observation);
			final PdfPTable finaleTable = new PdfPTable(t);
			List<Etudiant> etudaintImprimer = table.getItems();
			etudaintImprimer.forEach(e -> {
				PdfPCell lnum = new PdfPCell(new Paragraph(String.valueOf(e.getNumE()), f));
				PdfPCell lnom = new PdfPCell(new Paragraph(e.getNomE(), f));
				PdfPCell lprenom = new PdfPCell(new Paragraph(e.getPrenomE(), f));
				PdfPCell ldate = new PdfPCell(new Paragraph(e.getDateN(), f));
				PdfPCell lmoyenne = new PdfPCell(new Paragraph(String.valueOf(e.getMoyenneE()), f));
				PdfPCell lobservation = new PdfPCell(new Paragraph(e.getObservation(), f));
				finaleTable.addCell(lnum);
				finaleTable.addCell(lnom);
				finaleTable.addCell(lprenom);
				finaleTable.addCell(lmoyenne);
				finaleTable.addCell(ldate);
				finaleTable.addCell(lobservation);

			});
			document.add(finaleTable);
			document.close();

			Alert info = new Alert(AlertType.INFORMATION);
			info.setHeaderText("le fichier est bien enregistrer");
			info.show();
		}
	}catch ( Exception e1) {
		 Alert warnning = new Alert(AlertType.WARNING);
		 warnning.setHeaderText("erreur");
		 warnning.show();
	}

	}

}
