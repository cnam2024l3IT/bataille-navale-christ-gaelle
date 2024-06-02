package pf.cnam.npf121.bataillenavale.models.enumerations;

import java.util.HashMap;
import java.util.Map;

public enum Orientation {
	VERTICAL(1),
	HORIZONTAL(2);
	
	public final int ordre;
	
    private static final Map<Integer, Orientation> BY_ORDRE = new HashMap<>();
    
    static {
        for (Orientation o: values()) {
        	BY_ORDRE.put(o.ordre, o);
        }
    }

	Orientation(int ordre) {
		this.ordre = ordre;
	}
	
	public static Orientation valueOfOrdre(int ordre) {
        return BY_ORDRE.get(ordre);
    }
}
