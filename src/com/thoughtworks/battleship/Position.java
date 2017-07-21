package com.thoughtworks.battleship;

public final class Position {
	public final String X;
	public final String Y;
	
	public Position(String y, String x) {
		this.X = x;
		this.Y = y;
	}

	public String getX() {
		return X;
	}

	public  String getY() {
		return Y;
	}
	
	public String getPosition() {
		return Y + X;
	}
}
