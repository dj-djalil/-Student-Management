package com.tp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "specialite")
public class Specialite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NumS")
	private int numS;
	@Column(name = "NomS")
	private String nomS;
	@Column(name = "NumF")
	private int numF;

	public int getNumS() {
		return numS;
	}

	public void setNumS(int numS) {
		this.numS = numS;
	}

	public String getNomS() {
		return nomS;
	}

	public void setNomS(String nomS) {
		this.nomS = nomS;
	}

	
	public int getNumF() {
		return numF;
	}

	public void setNumF(int numF) {
		this.numF = numF;
	}

//	@Override
//	public String toString() {
//		return "Specialite [numS=" + numS + ", nomS=" + nomS + "]";
//	}
	
	@Override
	public String toString() {
		return nomS ;
	}

}
