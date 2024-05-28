package pf.cnam.npf121.bataillenavale.models;

public class Pion {
	private String couleur;
	private int nombreDeCases = 1;

	public Pion() {
		
	}
	
	public String getCouleur() {
		return couleur;
	}
	
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	
	public int getNombreDeCases() {
		return nombreDeCases;
	}
	
	public void setNombreDeCases (int nombreDeCases) {
		this.nombreDeCases = nombreDeCases;
	}

}
