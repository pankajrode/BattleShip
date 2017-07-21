package com.thoughtworks.battleship;

import java.util.ArrayList;
import java.util.List;

public class BattleShipWar implements BattleShip {
    private ShipField warShip;
    
    public BattleShipWar(ShipField warShip) {
    	this.warShip = warShip;
    }

	@Override
	public void startWar(Player player1, Player player2) {
		warShip.startWar(player1, player2);

	}

}
