package pf.cnam.npf121.bataillenavale.models;

public class Case {
	private int x;
	private int y;
	private boolean occupee = false;

	public Case(int x, int y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean getOccupee() {
		return occupee;
	}
	
	public void setOccupee(boolean occupee) {
		this.occupee = occupee;
	}

}
