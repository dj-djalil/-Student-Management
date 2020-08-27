package com.tp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="filiere")
public class Filiere {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="NumF")
	private int numF;
	@Column(name="NomF")
	private String nomF;
	@Column(name="NumD")
	private int numD;
	
	 
	
	
	
	public int getNumF() {
		return numF;
	}
	public void setNumF(int numF) {
		this.numF = numF;
	}
	public String getNomF() {
		return nomF;
	}
	public void setNomF(String nomF) {
		this.nomF = nomF;
	}
	
	public int getNumD() {
		return numD;
	}
	public void setNumD(int numD) {
		this.numD = numD;
	}
//	@Override
//	public String toString() {
//		return "Filiere [numF=" + numF + ", nomF=" + nomF + ", numD=" + numD + "]";
//	}
	 
	@Override
	public String toString() {
		return  nomF ;
	}

	
	
}
