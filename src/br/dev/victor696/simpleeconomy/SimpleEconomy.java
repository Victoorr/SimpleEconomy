package br.dev.victor696.simpleeconomy;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import br.dev.victor696.simpleeconomy.commands.MoneyCommand;
import br.dev.victor696.simpleeconomy.hooks.VaultHandler;
import br.dev.victor696.simpleeconomy.listener.MagnataListener;
import br.dev.victor696.simpleeconomy.listener.PlayerJoin;
import br.dev.victor696.simpleeconomy.object.Money;
import br.dev.victor696.simpleeconomy.storage.Sql;
import br.dev.victor696.simpleeconomy.thread.Update;
import br.dev.victor696.simpleeconomy.utils.Loader;
import br.dev.victor696.simpleeconomy.utils.MagnataAPI;
import br.dev.victor696.simpleeconomy.utils.Methods;
import net.milkbowl.vault.economy.Economy;

public class SimpleEconomy extends JavaPlugin {

	public static SimpleEconomy instance;
	public Economy vault;
	public HashMap<String, Money> account;
	public Sql sql;
	public Update update;
	public MagnataAPI magnataapi;
	public boolean mysql = false;
	public boolean pex = false;
	public boolean magnata = false;
	public boolean lc = false;

	public static SimpleEconomy getInstance() {
		return instance;
	}
	
	public Economy getEconomy(){
		return vault;
	}

	public Sql getSql() {
		return sql;
	}
	
	public Update getUpdate() {
		return update;
	}
	
	public MagnataAPI getMagnataAPI() {
		return magnataapi;
	}

	public SimpleEconomy() {
		instance = this;
		account = new HashMap<>();
		Methods.top = new ArrayList<>();
		sql = new Sql();
		update = new Update();
		magnataapi = new MagnataAPI();
	}

	@Override
	public void onEnable() {

		System.out.println("SimpleEconomy [v1.0] - Author: Victor696");
		info("Iniciando...");
		
		getMagnataAPI().magnata = "Ninguém";
		saveDefaultConfig();

		if (getServer().getPluginManager().getPlugin("PermissionsEx") !=null) {
			pex = true;
			info("PermissionsEx hookado com sucesso!");
		}
		
		if (getConfig().getBoolean("Geral.Magnata")) {
			magnata = true;
		}
		
		if (magnata){
			if (getServer().getPluginManager().getPlugin("Legendchat") !=null) {
				lc = true;
				info("LegendChat hookado com sucesso!");
			}
		}

		Sql.abrirConexao();
		Sql.criarTabelas();
		
		getUpdate().start();

        getCommand("money").setExecutor(new MoneyCommand());
        getCommand("money").setTabCompleter(new MoneyCommand());
        info("Comando registrado com sucesso!");
        
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
        Bukkit.getPluginManager().registerEvents(new MagnataListener(), this);
        info("Listener registrado com sucesso!");

		setupVault();
		new BukkitRunnable() {
			
			@Override
			public void run() {
				Methods.loadTop();
			}
		}.runTaskLater(this, 20l * 5);
		
		Loader.loadPlayers();
		
		mysql = true;
		info("Iniciado com sucesso!");
	}

	@Override
	public void onDisable() {
		if (mysql) {
			Loader.savePlayers();
			Sql.fecharConexao();
		}
	}

	public void info(String msg) {
		Bukkit.getConsoleSender().sendMessage("§e[SimpleEconomy] " + msg);
	}

	public void error(String msg) {
		Bukkit.getConsoleSender().sendMessage("§c[SimpleEconomy ERROR] " + msg);
	}
	
	public void setupVault(){
		vault = new VaultHandler();
		getServer().getServicesManager().register(Economy.class, vault, this, ServicePriority.Highest);
		info("Vault hookado com sucesso!");
	}

}
