package br.dev.victor696.simpleeconomy.hooks;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

import net.milkbowl.vault.economy.Economy;

public class VaultEconomy {

	private Economy economy;

	public VaultEconomy() {

	}

	public boolean setup() {
		try {
			RegisteredServiceProvider<Economy> service = Bukkit.getServicesManager().getRegistration(Economy.class);
			if (service != null) {
				this.economy = service.getProvider();
			}
		} catch (Exception exception) {
		}
		return this.economy != null;
	}

	public Economy getEconomy() {
		return economy;
	}

}
