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

	public GrilleNavire() {
		super();
	}
	
	public Set<Navire> getNavires() {
		return navires;
	}
	
	public void placerNavire(Navire navire, String position, Orientation orientation) throws InvalidePositionException {
		Optional<Cellule> optionalCellule = retrieveCellule(position);
		
		if(optionalCellule.isEmpty()) {
			throw new InvalidePositionException("Position non valide");
		}
		
		Set<Cellule> cellulesNavire = retrieveCellulesNavire(optionalCellule.get(), navire.getNombreCellule(), 
				orientation);
		
		if(cellulesNavire.size() < navire.getNombreCellule()) {
			throw new InvalidePositionException("Position non valide");
		}
		
		navire.setCellules(cellulesNavire);
		removeCellules(cellulesNavire);
		cellulesNavire.stream().forEach(c -> removeCellulesAdjacentes(c));
		navires.add(navire);
	}
	
	private Set<Cellule> retrieveCellulesNavire(Cellule cellule, int nombreCellules, Orientation orientation) {
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
	
	private void removeCellulesAdjacentes(Cellule cellule) {
		Predicate<Cellule> predicateCellule = c -> 
			(c.getCoordonnee().getX() == cellule.getCoordonnee().getX() 
				&& (c.getCoordonnee().getY() == cellule.getCoordonnee().getY() - 1 
					|| c.getCoordonnee().getY() == cellule.getCoordonnee().getY() + 1)) 
			|| (c.getCoordonnee().getY() == cellule.getCoordonnee().getY() 
				&& (c.getCoordonnee().getX() == cellule.getCoordonnee().getX() - 1 
					|| c.getCoordonnee().getX() == cellule.getCoordonnee().getX() + 1));
		removeCellules(cellules.stream().filter(predicateCellule).collect(Collectors.toSet()));
	}

}
