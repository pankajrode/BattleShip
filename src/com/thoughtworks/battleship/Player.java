package com.thoughtworks.battleship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Player {
	private String name;
	private int number_ships;
	private Map<String,Ship> ships;
	private Queue<String> missiles_target;

	public Player(String name, int ships){
		this.name = name;
		this.number_ships = ships;
		this.ships = new HashMap<>();
		missiles_target = new ArrayBlockingQueue<>(100);
	}
	
	public void setShips(Map<String,Ship> ships) {
		this.ships = ships;
	}
	
	public Map<String,Ship> getShips() {
		return this.ships;
	}
	
	public void setTargetMissiles(Queue<String> missiles) {
		this.missiles_target = missiles;
	}
	
	public Queue<String> getTargetMissiles() {
		return missiles_target;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
