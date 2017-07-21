package com.thoughtworks.battleship;

public enum ShipType {
		
	TYPE_P("P"),
	TYPE_Q("Q");
	
	private final String type;
	
	ShipType(String type) {
		this.type = type;
	}
	
	public String getShipType() {
		return this.type;
	}
}
