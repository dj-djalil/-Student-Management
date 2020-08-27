package com.tp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "domaine")
public class Domaine {
	@Id
	@Column(name = "NumD")
	private int numD;
	@Column(name = "NomD")
	private String nomD;

 

	public int getNumD() {
		return numD;
	}

	public void setNumD(int numD) {
		this.numD = numD;
	}

	public String getNomD() {
		return nomD;
	}

	public void setNomD(String nomD) {
		this.nomD = nomD;
	}

//	@Override
//	public String toString() {
//		return "Domaine [numD=" + numD + ", nomD=" + nomD + "]";
//	}
	
	
	@Override
	public String toString() {
		return  nomD ;
	}

}
