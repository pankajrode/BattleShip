package com.thoughtworks.battleship;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MissileLauncher {
	
	private ConcurrentMap<String,AtomicInteger> counterMap = new ConcurrentHashMap<>();
	
	private boolean isRemoveShip(Player target, String targetShipPosition) {
		boolean isRemoveShip = true;
		boolean positionfound = false;
		Map<String,Ship> ships = target.getShips();
		Ship targetShip =  null;
		for(String key: ships.keySet()) {
			Ship ship = ships.get(key);
			List<Position> positions  = ship.getPositions();
			for(Position pos : positions) {
				if(pos.getPosition().equals(targetShipPosition)) {
					targetShip = ship;
					positionfound = true;
					break;
				}
			}
			if(positionfound) {
				break;
			}
		}
		
		   if(null != targetShip) {
			   if(ShipType.TYPE_Q.getShipType().equals(targetShip.getType().getShipType())) {
				   AtomicInteger counter = counterMap.get(targetShipPosition);
				   if(counter != null ) {
					   int currentCounter = counter.decrementAndGet();
					   if(currentCounter == 0 ) { 
					   		counterMap.clear();
					   		return true;
					   }
					   counterMap.put(targetShipPosition, counter);
				   } else {
					   counterMap.put(targetShipPosition, new AtomicInteger(1));
				   }
				   isRemoveShip = false;
			   }
		   }

		return isRemoveShip;
	}


	public boolean launchMissile(Player source, Player target, String targetShip) {
		boolean targetHitSuccessfully = false;
		Map<String,Ship> ships = target.getShips();
		Ship ship = null;
		List<Position> positions;
		for(String key : ships.keySet()) {
			ship = ships.get(key);
			positions = ship.getPositions();
			for(Position pos : positions) {
				String x_y = pos.getPosition();
			targetHitSuccessfully = x_y.equals(targetShip)  ? true: false;
			if(targetHitSuccessfully) break;
			}
			if(targetHitSuccessfully) break;
		}
		
		if(null == targetShip) {
			System.out.println(source.getName()+" has no more missiles left to launch ");
		} else if(targetHitSuccessfully) {
			System.out.println(source.getName()+" fires a missile with target "+targetShip+" which got hit");
			if(isRemoveShip(target,targetShip)) { removeShipPosition(target,targetShip); }
		} else {
			System.out.println(source.getName()+" fires a missile with target "+targetShip+" which got miss");
		}
		
		return targetHitSuccessfully;
	}
	
	private void removeShipPosition(Player target, String targetShip) {
		Map<String, Ship> ships = target.getShips();
		Ship ship = null;
		List<Position> positions;
		boolean found = false;
		Position position = null;
		for (String key : ships.keySet()) {
			ship = ships.get(key);
			positions = ship.getPositions();
			for (Position pos : positions) {
				String x_y = pos.getPosition();
				if (x_y.equals(targetShip)) {
					found = true;
					position = pos;
				}
			}
			if(found) {
				positions.remove(position);
				break;
			}
		}
	}
}
