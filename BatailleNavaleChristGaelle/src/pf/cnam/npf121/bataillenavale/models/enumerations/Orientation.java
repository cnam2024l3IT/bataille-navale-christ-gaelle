package pf.cnam.npf121.bataillenavale.models.enumerations;

import java.util.HashMap;
import java.util.Map;

import pf.cnam.npf121.bataillenavale.interfaces.AffichageMenu;
import pf.cnam.npf121.bataillenavale.models.exceptions.NonTrouveException;

public enum Orientation implements AffichageMenu {
	UP(1),
	RIGHT(2),
	DOWN(3),
	LEFT(4);
	
	public final int ordre;
	
    private static final Map<Integer, Orientation> BY_ORDRE = new HashMap<>();
    
    static {
        for (Orientation o: values())
        	BY_ORDRE.put(o.ordre, o);
    }

	Orientation(int ordre) {
		this.ordre = ordre;
	}
	
	public static Orientation valueOfOrdre(int ordre) throws NonTrouveException {
		Orientation orientation = BY_ORDRE.get(ordre);
		
		if(orientation == null) 
			throw new NonTrouveException("Aucune orientation n'a été trouvée avec la valeur " + ordre);
		
        return orientation;
    }

	@Override
	public void afficher() {
		System.out.println(ordre + " - " + name());
		
	}
}
