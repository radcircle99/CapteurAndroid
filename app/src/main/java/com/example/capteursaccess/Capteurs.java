package com.example.capteursaccess;

import  java.io.Serializable;



public class Capteurs implements Serializable {
	
	private int idCapteur;
	private String nomCapteur;
	private String placeCapteur;
	private double valeur;
	
	
	
	public Capteurs() {
		
		this.idCapteur=0;
		this.nomCapteur="unknown";
		this.placeCapteur="unknown";
		this.valeur=0;
		
		
	}
	public Capteurs(int idCapteur, String nomCapteur, String placeCapteur, double valeur) {
		
		this.idCapteur = idCapteur;
		this.nomCapteur = nomCapteur;
		this.placeCapteur = placeCapteur;
		this.valeur = valeur;
	}
	public int getIdCapteur() {
		return idCapteur;
	}
	public void setIdCapteur(int idCapteur) {
		this.idCapteur = idCapteur;
	}
	public String getNomCapteur() {
		return nomCapteur;
	}
	public void setNomCapteur(String nomCapteur) {
		this.nomCapteur = nomCapteur;
	}
	public String getPlaceCapteur() {
		return placeCapteur;
	}
	public void setPlaceCapteur(String placeCapteur) {
		this.placeCapteur = placeCapteur;
	}
	public double getValeur() {
		return valeur;
	}
	public void setValeur(double valeur) {
		this.valeur = valeur;
	}
	@Override
	public String toString() {
		return "Capteurs [idCapteur=" + idCapteur + ", nomCapteur=" + nomCapteur + ", placeCapteur=" + placeCapteur
				+ ", valeur=" + valeur + "]";
	}


	
	
	

}
