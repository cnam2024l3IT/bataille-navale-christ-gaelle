package pf.cnam.npf121.bataillenavale;

import pf.cnam.npf121.bataillenavale.models.BatailleNavale;

public class BatailleNavaleApplication {

	public static void main(String[] args) {
		BatailleNavale batailleNavale = new BatailleNavale();
		System.out.println("Début de la partie");
		batailleNavale.placerNavires();
		batailleNavale.attaquer();
		System.out.println("Fin de la partie");
	}

}
