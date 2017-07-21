package com.thoughtworks.battleship;

import java.util.List;

public class ShipTest {
	
	public static void main(String[] args) {
		Size size = new Size(3, 3);
		Position position = new Position("D", "4");
		ShipType type = ShipType.TYPE_P;
		
		Ship ship = new Ship(size,position,type);
		List<Position> positions = ship.getPositions();
		
		for(Position pos : positions) {
			System.out.println("Positions : "+pos.getPosition());
		}
		
	}

}
