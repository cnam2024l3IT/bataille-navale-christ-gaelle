package pf.cnam.npf121.bataillenavale.models;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import pf.cnam.npf121.bataillenavale.models.enumerations.Orientation;
import pf.cnam.npf121.bataillenavale.models.exceptions.InvalidePositionException;

public class GrilleNavire extends Grille {
	private Set<Navire> navires = new HashSet<>();
	private Set<Cellule> cellulesDetruites = new HashSet<>();

	public GrilleNavire() {
		super();
	}
	
	public Set<Navire> getNavires() {
		return navires;
	}

	@Override
	public void afficher() {
		String txt = String.format("%3s", "");
		for(int i = 0; i < colonnes.length; i++) {
			txt += colonnes[i] + " ";
		}
		System.out.println(txt);
		for(int i = 0; i < lignes.length; i++) {
			txt = String.format("%2s", lignes[i]) + "|";
			for(int j = 0; j < colonnes.length; j++) {
				String position = colonnes[j] + lignes[i];
				
				if(celluleOccupee(position)) {
					txt += "N|";
				} else if(celluleLibre(position)) {
					txt += "_|";
				} else if(celluleDetruite(position)) {
					txt += "X|";
				} else {
					txt += "A|";
				}
			}
			System.out.println(txt);
		}
	}
	
	public boolean celluleOccupee(String position) {
		return trouverNavireParPosition(position).isPresent();
	}
	
	private boolean celluleLibre(String position) {
		return cellules.stream()
				.map(cellule -> cellule.getPosition()).filter(p -> p.equals(position)).findAny().isPresent();
	}
	
	private boolean celluleDetruite(String position) {
		return cellulesDetruites.stream()
				.map(cellule -> cellule.getPosition()).filter(p -> p.equals(position)).findAny().isPresent();
	}
	
	public void placerNavire(Navire navire, String position, Orientation orientation) 
			throws InvalidePositionException {
		Optional<Cellule> optionalCellule = recupererCellule(position);
		
		if(optionalCellule.isEmpty())
			throw new InvalidePositionException("Position non valide");
		
		Set<Cellule> cellulesNavire = recupererCellulesNavire(optionalCellule.get(), navire.getNombreCellule(), 
				orientation);
		
		if(cellulesNavire.size() < navire.getNombreCellule())
			throw new InvalidePositionException("Position non valide");
		
		navire.setCellules(cellulesNavire);
		removeCellules(cellulesNavire);
		cellulesNavire.stream().forEach(c -> retirerCellulesAdjacentes(c));
		navires.add(navire);
	}

	private Set<Cellule> recupererCellulesNavire(Cellule cellule, int nombreCellules, Orientation orientation) {
		Predicate<Cellule> predicateCellule = c -> orientation == Orientation.VERTICAL ? 
				c.getCoordonnee().getX() == cellule.getCoordonnee().getX() 
					&& c.getCoordonnee().getY() >= cellule.getCoordonnee().getY() 
					&& c.getCoordonnee().getY() <= (cellule.getCoordonnee().getY() + nombreCellules - 1)
				: c.getCoordonnee().getY() == cellule.getCoordonnee().getY() 
						&& c.getCoordonnee().getX() >= cellule.getCoordonnee().getX() 
						&& c.getCoordonnee().getX() <= (cellule.getCoordonnee().getX() + nombreCellules - 1);
		return cellules.stream().filter(predicateCellule).collect(Collectors.toSet());
	}
	
	private void removeCellules(Set<Cellule> cellules) {
		this.cellules.removeAll(cellules);
	}
	// Corriger pour retirer les cellules adjacentes en diagonale
	private void retirerCellulesAdjacentes(Cellule cellule) {
		Predicate<Cellule> predicateCellule = c -> 
			(c.getCoordonnee().getX() == cellule.getCoordonnee().getX() 
				&& (c.getCoordonnee().getY() == cellule.getCoordonnee().getY() - 1 
					|| c.getCoordonnee().getY() == cellule.getCoordonnee().getY() + 1)) 
			|| (c.getCoordonnee().getY() == cellule.getCoordonnee().getY() 
				&& (c.getCoordonnee().getX() == cellule.getCoordonnee().getX() - 1 
					|| c.getCoordonnee().getX() == cellule.getCoordonnee().getX() + 1));
		removeCellules(cellules.stream().filter(predicateCellule).collect(Collectors.toSet()));
	}
	
	public void retirerNavireCellule(String position) {
		Navire navire = trouverNavireParPosition(position).get();
		navires.remove(navire);
		Cellule cellule = navire.getCelluleParPosition(position);
		navire.retirerCellule(cellule);
		cellulesDetruites.add(cellule);
		System.out.println("Touché");
		
		if(navire.getCellules().size() > 0) {
			navires.add(navire);
		} else {
			System.out.println("Coulé");
		}
	}
	
	private Optional<Navire> trouverNavireParPosition(String position) {
		return navires.stream().filter(navire -> navire.celluleExisteParPosition(position)).findAny();
	}
	
	public String recupererToutesLesPositions() {
		String positions = "";
		for(int i = 0; i < colonnes.length; i++) {
			for(int j = 0; j < lignes.length; j++) {
				positions += colonnes[i] + lignes[j] + "|";
			}
		}
		return positions;
	}
	
	public boolean positionExiste(String position) {
		Set<Cellule> allCellules = initialiser();
		return allCellules.stream()
				.map(c -> c.getPosition()).filter(p -> p.equals(position)).findAny().isPresent();
	}

}
