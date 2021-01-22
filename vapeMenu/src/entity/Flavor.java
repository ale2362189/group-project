package entity;

public class Flavor {
	private int flavorId;
	private String flavor;
	
	public Flavor(int flavorId, String flavor) {
		this.flavorId = flavorId;
		this.flavor = flavor;
		
	}

	public int getFlavorId() {
		return flavorId;
	}

	public void setFlavorId(int flavorId) {
		this.flavorId = flavorId;
	}

	public String getFlavor() {
		return flavor;
	}

	public void setFlavor(String flavor) {
		this.flavor = flavor;
	} 
}
