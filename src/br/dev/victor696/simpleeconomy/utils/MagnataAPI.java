package br.dev.victor696.simpleeconomy.utils;

import org.bukkit.entity.Player;

public class MagnataAPI {
	
	public String magnata;
	
	public String getMagnata() {
		return this.magnata;
	}
	
	public boolean isMagnata(Player p) {
		if (p.getName().equals(this.magnata)) {
			return true;
		}
		return false;
	}

}
