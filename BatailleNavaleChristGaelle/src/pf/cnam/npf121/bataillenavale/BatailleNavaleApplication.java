package pf.cnam.npf121.bataillenavale;

import pf.cnam.npf121.bataillenavale.models.BatailleNavale;

public class BatailleNavaleApplication {

	public static void main(String[] args) {
		BatailleNavale batailleNavale = new BatailleNavale();
		batailleNavale.placerNavires();
		batailleNavale.attaquer();
	}

}
