package com.explore.domain;

public enum Region {
	north_africa("Moroco"), south_africa("South Africa"), 
	west_africa("Nigeria"), east_africa("Kenya"), central_africa("Congo");
	
	private String label;
	
	private Region(String label) { this.label = label; }
	
	public static Region findByLabel (String byLabel) {
		for (Region r : Region.values()) {
			if (r.label.equalsIgnoreCase(byLabel)) {
				return r;
			}			
		}
		return null;
	}
}
