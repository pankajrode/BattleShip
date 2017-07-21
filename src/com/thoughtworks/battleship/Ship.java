package com.thoughtworks.battleship;

import java.util.ArrayList;
import java.util.List;

public class Ship {
	private Size size;
	private Position position;
	private ShipType type;
	List<Position> positions = new ArrayList<>();	
	
	public Ship(Size size, Position pos, ShipType type) {
		this.size = size;
		this.position = pos;
		this.type = type;
		positions.add(this.position);
		calculatePositions();
	}
	
	private void calculatePositions() {
		int width = size.WIDTH;
		int height = size.HEIGHT;
		int X = Integer.valueOf(position.getX());
		int Y = (int)position.getY().charAt(0);
		
		for(int i=1; i< width; i++) {
			X = X + 1;
			if(X > 9) {
				throw new RuntimeException("X Axix have value > 9");
			}
			positions.add(new Position(position.getY(),String.valueOf(X)));
		}

		X = Integer.valueOf(position.getX());
		Y = (int)position.getY().charAt(0);

		for(int j=1;j<height;j++) {
			Y = Y + 1;
			char ch;
			if(Y <= 122) {
				ch = (char)Y;
			} else {
				throw new RuntimeException("Not a Valid Y Axis Values");
			}
			positions.add(new Position(String.valueOf(ch),String.valueOf(X)));

		}
	}

	public ShipType getType() {
		return type;
	}

	public List<Position> getPositions() {
		return positions;
	}
	
	
	
}
