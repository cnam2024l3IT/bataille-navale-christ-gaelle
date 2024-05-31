package pf.cnam.npf121.bataillenavale.models;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import pf.cnam.npf121.bataillenavale.models.enumerations.Orientation;
import pf.cnam.npf121.bataillenavale.models.exceptions.InvalidePositionException;

public class Joueur {
	private String nom;
	private GrilleNavire grilleNavire = new GrilleNavire();
	private Set<Navire> navires = new HashSet<>();
//	Scanner sc = new Scanner(System.in);

	public Joueur(String nom) {
		this.nom = nom;
		initializeNavires();
	}
	
	private void initializeNavires() {
		navires.add(new ContreTorpilleur());
		navires.add(new Croiseur());
		navires.add(new PorteAvion());
		navires.add(new SousMarin());
		navires.add(new Torpilleur());
	}
	
	public void placerNavires(int count) {
		do {
			try {
				count++;
				placerNavire();
			} catch (InvalidePositionException e) {
				System.out.println(e.getMessage());
			}
		} while(grilleNavire.getNavires().size() < navires.size() && count < 7);
	}
	
	private void placerNavire() throws InvalidePositionException {
		Scanner sc = new Scanner(System.in);
		String str = "";
		int orientationInt = 0;
		System.out.println("Veuillez saisir une position :");
		if(sc.hasNextLine())
			str = sc.nextLine();
		else sc.next();
		System.out.println("Veuillez saisir une orientation : (0 = vertical | 1 = horizontal)");
		if(sc.hasNextInt())
			orientationInt = sc.nextInt();
		else sc.next();
		
		grilleNavire.placerNavire(new ContreTorpilleur(), str, orientationInt == 0 ? Orientation.VERTICAL : Orientation.HORIZONTAL);
	}

}
