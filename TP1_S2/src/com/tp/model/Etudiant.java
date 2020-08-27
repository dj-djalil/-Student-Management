package com.tp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "etudiant")
public class Etudiant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NumE")
	private int numE;
	@Column(name = "NomE")
	private String nomE;
	@Column(name = "PrenomE")
	private String prenomE;
	@Column(name = "Moyenne")
	private float moyenneE;
	@Column(name = "DateN")
	private String dateN;
	@Column(name = "NumS")
	private int numS;

	@Column(name = "Observation")
	private String observation;

	public Etudiant() {

	}

	public Etudiant(String nomE, String prenomE, float moyenneE, String dateN, int numS) {
		this.nomE = nomE;
		this.prenomE = prenomE;
		this.moyenneE = moyenneE;
		this.dateN = dateN;
		this.numS = numS;
	}

	public int getNumE() {
		return numE;
	}

	public void setNumE(int numE) {
		this.numE = numE;
	}

	public String getNomE() {
		return nomE;
	}

	public void setNomE(String nomE) {
		this.nomE = nomE;
	}

	public String getPrenomE() {
		return prenomE;
	}

	public void setPrenomE(String prenomE) {
		this.prenomE = prenomE;
	}

	public float getMoyenneE() {
		return moyenneE;
	}

	public void setMoyenneE(float moyenneE) {
		this.moyenneE = moyenneE;
	}

	public String getDateN() {
		return dateN;
	}

	public void setDateN(String dateN) {
		this.dateN = dateN;
	}

	public int getNumS() {
		return numS;
	}

	public void setNumS(int numS) {
		this.numS = numS;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	@Override
	public String toString() {
		return "Etudiant [numE=" + numE + ", nomE=" + nomE + ", prenomE=" + prenomE + ", moyenneE=" + moyenneE
				+ ", dateN=" + dateN + ", numS=" + numS + ", observation=" + observation + "]";
	}

 

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Etudiant other = (Etudiant) obj;
		if (dateN == null) {
			if (other.dateN != null)
				return false;
		} else if (!dateN.equals(other.dateN))
			return false;
		if (Float.floatToIntBits(moyenneE) != Float.floatToIntBits(other.moyenneE))
			return false;
		if (nomE == null) {
			if (other.nomE != null)
				return false;
		} else if (!nomE.equals(other.nomE))
			return false;
		if (numE != other.numE)
			return false;
		if (numS != other.numS)
			return false;
		if (observation == null) {
			if (other.observation != null)
				return false;
		} else if (!observation.equals(other.observation))
			return false;
		if (prenomE == null) {
			if (other.prenomE != null)
				return false;
		} else if (!prenomE.equals(other.prenomE))
			return false;
		return true;
	}

	
}
