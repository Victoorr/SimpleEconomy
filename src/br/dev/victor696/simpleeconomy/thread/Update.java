package br.dev.victor696.simpleeconomy.thread;

import org.bukkit.scheduler.BukkitRunnable;

import br.dev.victor696.simpleeconomy.SimpleEconomy;
import br.dev.victor696.simpleeconomy.utils.Methods;

public class Update {
	
	public void start() {
		
		int update = SimpleEconomy.getInstance().getConfig().getInt("Geral.AtualiarTop");
		new BukkitRunnable() {
			
			@Override
			public void run() {
				Methods.loadTop();
			}
		}.runTaskTimer(SimpleEconomy.getInstance(), 20 * update, 20 * update);
		
	}

}
