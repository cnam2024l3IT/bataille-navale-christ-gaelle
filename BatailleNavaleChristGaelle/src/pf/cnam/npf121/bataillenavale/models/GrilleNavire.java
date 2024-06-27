package pf.cnam.npf121.bataillenavale.models;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import pf.cnam.npf121.bataillenavale.models.enumerations.Orientation;
import pf.cnam.npf121.bataillenavale.models.exceptions.InvalidePositionException;
import pf.cnam.npf121.bataillenavale.models.exceptions.NonTrouveException;

public class GrilleNavire extends Grille {
	private Set<Navire> navires = new HashSet<>();
	protected Set<Cellule> cellules = new HashSet<>();
	private Set<Cellule> cellulesDetruites = new HashSet<>();

	public GrilleNavire() {
		super();
		cellules.addAll(allCellules);
	}
	
	public String[] getStatus() {
		String[] status = new String[lignes.length + 1];
		String txt = String.format("%3s", "");
		for(int i = 0; i < colonnes.length; i++)
			txt += colonnes[i] + " ";
		status[0] = txt;
		for(int i = 0; i < lignes.length; i++) {
			txt = String.format("%2s", lignes[i]) + "|";
			for(int j = 0; j < colonnes.length; j++) {
				String position = colonnes[j] + lignes[i];
				
				if(celluleOccupee(position))
					txt += "N|";
				else if(celluleLibre(position))
					txt += "_|";
				else if(celluleDetruite(position))
					txt += "X|"; 
				else
					txt += "A|";
			}
			status[i + 1] = txt;
		}
		return status;
	}
	
	public void placerNavire(Navire navire, Cellule cellule, Orientation orientation) 
			throws InvalidePositionException {
		Set<Cellule> cellulesNavire = recupererCellulesNavire(cellule, navire.getNombreCellule(), orientation);
		
		if(cellulesNavire.size() < navire.getNombreCellule())
			throw new InvalidePositionException("Position invalide");
		
		navire.setCellules(cellulesNavire);
		removeCellules(cellulesNavire);
		cellulesNavire.stream().forEach(c -> retirerCellulesAdjacentes(c));
		navires.add(navire);
	}
	
	public void removeNavire(Navire navire) {
		navires.remove(navire);
	}
	
	private boolean celluleOccupee(String position) {
		try {
			return recupererNavireParCellule(position) != null;
		} catch (NonTrouveException e) {
			return false;
		}
	}
	
	private boolean celluleLibre(String position) {
		return cellules.stream()
				.map(cellule -> cellule.getPosition()).filter(p -> p.equals(position)).findAny().isPresent();
	}
	
	private boolean celluleDetruite(String position) {
		return cellulesDetruites.stream()
				.map(cellule -> cellule.getPosition()).filter(p -> p.equals(position)).findAny().isPresent();
	}

	private Set<Cellule> recupererCellulesNavire(Cellule cellule, int nombreCellules, Orientation orientation) {
		Predicate<Cellule> predicateCellule = c -> {
			int offset = nombreCellules - 1;
			switch(orientation) {
			case UP: return c.getCoordonnee().getX() == cellule.getCoordonnee().getX() 
					&& c.getCoordonnee().getY() <= cellule.getCoordonnee().getY() 
					&& c.getCoordonnee().getY() >= cellule.getCoordonnee().getY() - offset;
			case DOWN: return c.getCoordonnee().getX() == cellule.getCoordonnee().getX() 
					&& c.getCoordonnee().getY() >= cellule.getCoordonnee().getY() 
					&& c.getCoordonnee().getY() <= cellule.getCoordonnee().getY() + offset;
			case LEFT: return c.getCoordonnee().getY() == cellule.getCoordonnee().getY() 
					&& c.getCoordonnee().getX() <= cellule.getCoordonnee().getX() 
					&& c.getCoordonnee().getX() >= cellule.getCoordonnee().getX() - offset;
			case RIGHT: return c.getCoordonnee().getY() == cellule.getCoordonnee().getY() 
					&& c.getCoordonnee().getX() >= cellule.getCoordonnee().getX() 
					&& c.getCoordonnee().getX() <= cellule.getCoordonnee().getX() + offset;
			default: return false;
			}
		};
		return cellules.stream().filter(predicateCellule).collect(Collectors.toSet());
	}
	
	private void removeCellules(Set<Cellule> cellules) {
		this.cellules.removeAll(cellules);
	}
	
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
	
	public Navire recupererNavireParCellule(String position) throws NonTrouveException {
		return navires.stream().filter(navire -> navire.celluleExisteParPosition(position))
				.findFirst().orElseThrow(() -> 
					new NonTrouveException("Aucun navire n'a été trouvé à la position "  + position));
	}
	
	public void addCelluleDetruite(Cellule cellule) {
		cellulesDetruites.add(cellule);
	}
	
	public void addNavire(Navire navire) {
		navires.add(navire);
	}
	
	public boolean estVide() {
		return navires.size() == 0;
	}
	
}
