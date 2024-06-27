package pf.cnam.npf121.bataillenavale.models;

import java.util.Scanner;
import java.util.Set;

import pf.cnam.npf121.bataillenavale.interfaces.AffichageConsole;
import pf.cnam.npf121.bataillenavale.models.enumerations.Orientation;
import pf.cnam.npf121.bataillenavale.models.exceptions.NonTrouveException;

public class BatailleNavaleConsole {
	private AffichageConsole console = s -> System.out.println(s);

	public BatailleNavaleConsole() {
		// TODO Auto-generated constructor stub
	}
	
	public void afficher(String message) {
		console.afficher(message);
	}
	
	public void afficherGrille(Joueur joueur) {
		console.afficher("Grille : " + joueur.getNom());
		afficherStatus(joueur.getStatusGrille());
	}
	
	public void afficherGrilleAdversaire(Joueur joueur) {
		console.afficher("Grille de votre adversaire");
		afficherStatus(joueur.getStatusGrilleAdversaire());
	}
	
	private void afficherStatus(String[] status) {
		for(int i = 0; i < status.length; i++) {
			console.afficher(status[i]);
		}
	}

	public Navire obtenirNavire(Joueur joueur, Set<Navire> navires) {
		Navire navire = null;
		do {
			try {
				navire = trouverNavireParNumero(demanderNumero(joueur, navires), navires);
			} catch (NonTrouveException e) {
				console.afficher(e.getMessage());
			}
		} while(navire == null);
		return navire;
	}
	
	private int demanderNumero(Joueur joueur, Set<Navire> navires) {
		console.afficher(joueur.getNom() + ", choisissez le navire à placer : (saisir le numéro)");		
		afficherListeNavires(navires);
		return recevoirInt();
	}
	
	private void afficherListeNavires(Set<Navire> navires) {
		navires.stream().sorted((n1, n2) -> n1.getNumero() - n2.getNumero()).forEach(n -> afficherStatus(n.status()));
	}
	
	@SuppressWarnings("resource")
	private int recevoirInt() {
		Scanner scanner = new Scanner(System.in);
		
		if(scanner.hasNextInt())
			return scanner.nextInt();
		
		return 0;
	}
	
	private Navire trouverNavireParNumero(int numero, Set<Navire> navires) throws NonTrouveException {
		return navires.stream()
				.filter(navire -> navire.getNumero() == numero).findFirst()
				.orElseThrow(() -> new NonTrouveException("Aucun navire n'a été trouvé avec le numéro " + numero));
	}
	
	public Cellule obtenirCellule(Joueur joueur) {
		Cellule cellule = null;
		do {
			
			try {
				cellule = joueur.recupererCellule(demanderPosition(joueur));
			} catch (NonTrouveException e) {
				console.afficher(e.getMessage());
			}
		} while(cellule == null);
		return cellule;
	}
	
	private String demanderPosition(Joueur joueur) {
		console.afficher(joueur.getNom() + ", veuillez saisir une position : ");
		return recevoirLine();
	}
	
	@SuppressWarnings("resource")
	private String recevoirLine() {
		Scanner scanner = new Scanner(System.in);
		
		if(scanner.hasNextLine())
			return scanner.nextLine();
		
		return "";
	}
	
	public Orientation obtenirOrientation(Joueur joueur) {
		Orientation orientation = null;
		do {
			try {
				orientation = Orientation.valueOfOrdre(demanderOrientation(joueur));
			} catch (NonTrouveException e) {
				System.out.println(e.getMessage());
			}
		} while(orientation == null);
		return orientation;
	}
	
	private int demanderOrientation(Joueur joueur) {
		console.afficher(joueur.getNom() + ", veuillez saisir une orientation :");
		afficherListeOrientations();
		return recevoirInt();		
	}
	
	private void afficherListeOrientations() {
		for(int i = 0; i < Orientation.values().length; i++)
			afficherStatus(Orientation.values()[i].status());
	}

}
