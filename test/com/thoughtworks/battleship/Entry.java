package com.thoughtworks.battleship;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class Entry {
	private static InputStream in = Entry.class.getClassLoader().getResourceAsStream("input.txt");
	public static void main(String[] args) throws Exception {
		File f = new File("input.txt");
		
		Scanner scanner = new Scanner(in);
		String line = scanner.nextLine();
		String[] height_width = line.split("\\s");
		
		
		//Creating BattleField
		BattleField battleField = new BattleField(Integer.valueOf(height_width[0]),height_width[1]);
		
		// read no of BattleShips each player having
		int no_of_battle_fields = Integer.valueOf(scanner.nextLine());
		
		// creating players
		Player player1 = new Player("Player-1",2);
		Player player2 = new Player("Player-2",2);
		
		// Read battleship type, dimensions of ships, positions, 
		String line3 = scanner.nextLine();
		String[] tokens_line3 = line3.split("\\s");
		
		// Ship Type
		ShipType type = "P".equals(tokens_line3[0]) ? ShipType.TYPE_P : ShipType.TYPE_Q;
		
		Size size1 = new Size(Integer.valueOf(tokens_line3[1]), Integer.valueOf(tokens_line3[2]));
		
		// Player 1 position
		Position position1_player1 = new Position(String.valueOf(tokens_line3[3].charAt(0)),String.valueOf(tokens_line3[3].charAt(1)));  // TODO: redesign Position class to take string
																																		// implement internally logic to split Y and X
		
		// Player 1 ship1
		Ship player1_ship1 = new Ship(size1,position1_player1,type);
		
		Map<String,Ship> player1_ships = new HashMap<>();   // TODO: remove Hashmap instead use set
		Map<String,Ship> player2_ships = new HashMap<>();
		
		// Player 2 position
		Position position1_player2 = new Position(String.valueOf(tokens_line3[4].charAt(0)),String.valueOf(tokens_line3[4].charAt(1)));  // TODO: resdesign 
		
		// player2 ship2
		Ship player2_ship1 = new Ship(size1,position1_player2,type);
		
		// read next  line battleship type, dimensions of ships and positions
		String line4 = scanner.nextLine();
		
		String[] tokens_line4 = line4.split("\\s");
		
		// Ship Type
		ShipType type2 = "P".equals(tokens_line4[0]) ? ShipType.TYPE_P : ShipType.TYPE_Q;
		
		Size size2 = new Size(Integer.valueOf(tokens_line4[1]), Integer.valueOf(tokens_line4[2]));
		
		// Player 1 position
		Position position2_player1 = new Position(String.valueOf(tokens_line4[3].charAt(0)),String.valueOf(tokens_line4[3].charAt(1)));  // TODO: redesign Position class to take string
																																		// implement internally logic to split Y and X
		
		// Player 1 ship1
		Ship player1_ship2 = new Ship(size2,position2_player1,type2);
		 
		player1_ships.put(position2_player1.getPosition(), player1_ship2);
		
		// Player 2 position
		Position position2_player2 = new Position(String.valueOf(tokens_line3[4].charAt(0)),String.valueOf(tokens_line3[4].charAt(1)));  // TODO: resdesign 
		
		// player2 ship2
		Ship player2_ship2 = new Ship(size1,position2_player2,type);
		
		player1_ships.put(position1_player1.getPosition(), player1_ship1);
		player1_ships.put(position2_player1.getPosition(), player1_ship2);
		
		player2_ships.put(position1_player2.getPosition(), player2_ship1);
		player2_ships.put(position2_player2.getPosition(), player2_ship2);
		
		player1.setShips(player1_ships);
		player2.setShips(player2_ships);
		
		// Missiles target for player1
		String line5 = scanner.nextLine();
		String[] player1_missiles = line5.split("\\s");
		Queue<String> missiles_player1 = new ArrayBlockingQueue<>(100);
		for(String key: player1_missiles) {
			missiles_player1.add(key);
		}
		
		player1.setTargetMissiles(missiles_player1);
		
		// Missiles target for player2
		String line6 = scanner.nextLine();
		String[] player2_missiles = line6.split("\\s");

		Queue<String> missiles_player2 = new ArrayBlockingQueue<>(100);
		for(String key: player2_missiles) {
			missiles_player2.add(key);
		}
		
		player2.setTargetMissiles(missiles_player2);

		// start Battle
		
		BattleShipWar shipWar = new BattleShipWar(new WarShipField(new MissileLauncher()));
	
		shipWar.startWar(player1, player2);

	}
}
