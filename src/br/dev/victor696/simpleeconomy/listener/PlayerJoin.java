package br.dev.victor696.simpleeconomy.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import br.dev.victor696.simpleeconomy.SimpleEconomy;
import br.dev.victor696.simpleeconomy.utils.Methods;

public class PlayerJoin implements Listener {

	@EventHandler
	public void PlayerJoinE(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (SimpleEconomy.getInstance().account.get(p.getName()) == null) {
			try {
				Methods.createAccount(p.getName());
				SimpleEconomy.getInstance().info("Jogador " + p.getName() + " teve sua conta criada.");
			} catch (Exception e2) {
				SimpleEconomy.getInstance().error("Erro ao criar a conta do jogador " + p.getName() + ".");
				e2.printStackTrace();
			}
		}
	}

}
