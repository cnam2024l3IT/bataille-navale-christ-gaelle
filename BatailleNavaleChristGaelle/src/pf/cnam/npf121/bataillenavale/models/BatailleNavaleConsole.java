package pf.cnam.npf121.bataillenavale.models;

import java.util.Scanner;
import java.util.Set;

import pf.cnam.npf121.bataillenavale.interfaces.AffichageConsole;
import pf.cnam.npf121.bataillenavale.models.enumerations.Orientation;
import pf.cnam.npf121.bataillenavale.models.exceptions.NonTrouveException;

public class BatailleNavaleConsole {
	private AffichageConsole console = s -> System.out.println(s);
	private GrilleManager grilleManager;

	public BatailleNavaleConsole() {
		grilleManager = GrilleManager.getInstance();
	}
	
	public void afficher(String message) {
		console.afficher(message);
	}
	
	public void afficherGrille(Joueur joueur) {
		console.afficher("Grille : " + joueur.getNom());
		afficherStatus(status(joueur.getGrilleNavire()));
	}
	
	public void afficherGrilleAdversaire(Joueur joueur) {
		console.afficher("Grille de votre adversaire");
		afficherStatus(status(joueur.getGrilleAdversaire()));
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
	
	public Cellule obtenirCellule(Joueur joueur) {
		Cellule cellule = null;
		do {
			try {
				cellule = GrilleManager.getInstance().recupererCellule(demanderPosition(joueur));
			} catch (NonTrouveException e) {
				console.afficher(e.getMessage());
			}
		} while(cellule == null);
		return cellule;
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
	
	private void afficherStatus(String[] status) {
		for(int i = 0; i < status.length; i++)
			console.afficher(status[i]);
	}
	
	private int demanderNumero(Joueur joueur, Set<Navire> navires) {
		console.afficher(joueur.getNom() + ", choisissez le navire à placer : (saisir le numéro)");		
		afficherListeNavires(navires);
		return recevoirInt();
	}
	
	private void afficherListeNavires(Set<Navire> navires) {
		navires.stream().sorted((n1, n2) -> n1.getNumero() - n2.getNumero()).forEach(n -> console.afficher(n + ""));
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
	
	private int demanderOrientation(Joueur joueur) {
		console.afficher(joueur.getNom() + ", veuillez saisir une orientation :");
		afficherListeOrientations();
		return recevoirInt();		
	}
	
	private void afficherListeOrientations() {
		for(int i = 0; i < Orientation.values().length; i++)
			console.afficher(Orientation.values()[i] + "");
	}
	
	private String[] status(Grille grille) {
		String[] lignes = grilleManager.getLignes();
		String[] colonnes = grilleManager.getColonnes();
		String[] status = new String[lignes.length + 1];
		status[0] = grilleManager.getRowColonnes();
		String txt;
		for(int i = 0; i < lignes.length; i++) {
			txt = String.format("%2s", lignes[i]) + "|";
			for(int j = 0; j < lignes.length; j++) {
				String position = colonnes[j] + lignes[i];
				switch(grille.getCelluleStatus(position)) {
				case ADJACENTE: txt += "A|";
					break;
				case DETRUITE: txt += "X|";
					break;
				case OCCUPEE: txt += "N|";
					break;
				case RATEE: txt += "R|";
					break;
				case TOUCHEE: txt += "T|";
					break;
				case VIDE: txt += "_|";
					break;
				default:
					break;
				
				}
			}
			status[i + 1] = txt;
		}
		return status;
	}

}
