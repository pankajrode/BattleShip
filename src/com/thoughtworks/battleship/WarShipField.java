package com.thoughtworks.battleship;

import java.util.Queue;

public class WarShipField implements ShipField {
	
	private MissileLauncher launcher;
	
	public WarShipField(MissileLauncher launcher) {
		this.launcher = launcher;
	}
	
	public void startWar(Player player1, Player player2) {
	   Queue<String> player1_missiles = player1.getTargetMissiles();
	   Queue<String> player2_missiles = player2.getTargetMissiles();
	   
	   while(!player1_missiles.isEmpty() || !player2_missiles.isEmpty()) {
		   
		   // launch from player1 to player2
		   String missileTargetOnPlayer2Ship = player1_missiles.poll();
		   while(launcher.launchMissile(player1,player2, missileTargetOnPlayer2Ship)) {
			   missileTargetOnPlayer2Ship = player1_missiles.poll();
		   }
		   
		   // laucnh from player2 to player1
		   missileTargetOnPlayer2Ship = player2_missiles.poll();
		   while(launcher.launchMissile(player2, player1, missileTargetOnPlayer2Ship)) {
			   missileTargetOnPlayer2Ship = player2_missiles.poll();
		   }
	   }
	   
		if(player1.getTargetMissiles().isEmpty()) {
			System.out.println(player2.getName()+" Won the Battle");
		} else if(player2.getTargetMissiles().isEmpty()) {
			System.out.println(player1.getName()+" Won the Battle");
		} else {
			System.out.println("Both the players declars the peace");
		}

	}

}
