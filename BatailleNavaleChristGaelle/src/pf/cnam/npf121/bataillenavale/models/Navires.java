package pf.cnam.npf121.bataillenavale.models;

import java.util.HashSet;
import java.util.Set;

public class Navires implements Cloneable {
	private Set<Navire> navires = new HashSet<>();

	public Navires() {
		navires.add(new ContreTorpilleur());
		navires.add(new Croiseur());
		navires.add(new PorteAvion());
		navires.add(new SousMarin());
		navires.add(new Torpilleur());
	}

	public Navires(Set<Navire> navires) {
		this.navires = navires;
	}
	
	@Override
	public Navires clone() throws CloneNotSupportedException {
		Set<Navire> navs = new HashSet<>();
		navires.forEach(n -> navs.add(n));
		return new Navires(navs);
	}
	
	public Set<Navire> getNavires() {
		return navires;
	}

}
