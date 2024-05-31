package pf.cnam.npf121.bataillenavale.models;

public class Cellule {
	private String position;
	private Coordonnee coordonnee;

	public Cellule(String position, Coordonnee coordonnee) {
		this.position = position;
		this.coordonnee = coordonnee;
	}
	
	public String getPosition() {
		return position;
	}
	
	public Coordonnee getCoordonnee() {
		return coordonnee;
	}

}
