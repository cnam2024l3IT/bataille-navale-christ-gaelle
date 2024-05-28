package pf.cnam.npf121.bataillenavale.models;

public class CustomCase {
	private int x;
	private int y;
	private boolean occupee = false;

	public CustomCase(int x, int y) {
		
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
