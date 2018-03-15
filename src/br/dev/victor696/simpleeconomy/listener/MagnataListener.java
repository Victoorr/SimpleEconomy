package br.dev.victor696.simpleeconomy.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import br.dev.victor696.simpleeconomy.SimpleEconomy;

public class MagnataListener implements Listener {

	@EventHandler
	public void PlayerJoinE(PlayerJoinEvent e) {
		if (SimpleEconomy.getInstance().getConfig().getBoolean("Magnata.Config.Entrou") && e.getPlayer().getName().equals(SimpleEconomy.getInstance().getMagnataAPI().getMagnata())) {
			Bukkit.broadcastMessage(
					SimpleEconomy.getInstance().getConfig().getString("Magnata.Mensagens.Entrou").replaceAll("&", "§").replace("{magnata}", SimpleEconomy.getInstance().getMagnataAPI().getMagnata()));
		}
	}

	@EventHandler
	public void PlayerQuitE(PlayerQuitEvent e) {
		if (SimpleEconomy.getInstance().getConfig().getBoolean("Magnata.Config.Saiu") && e.getPlayer().getName().equals(SimpleEconomy.getInstance().getMagnataAPI().getMagnata())) {
			Bukkit.broadcastMessage(
					SimpleEconomy.getInstance().getConfig().getString("Magnata.Mensagens.Saiu").replaceAll("&", "§").replace("{magnata}", SimpleEconomy.getInstance().getMagnataAPI().getMagnata()));
		}
	}

	@EventHandler
	public void PlayerCommandPreprocessE(PlayerCommandPreprocessEvent e) {
		if (SimpleEconomy.getInstance().magnata && e.getMessage().equalsIgnoreCase("/magnata")) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(SimpleEconomy.getInstance().getConfig().getString("Magnata.Mensagens.Magnata")
					.replaceAll("&", "§").replace("{magnata}", SimpleEconomy.getInstance().getMagnataAPI().magnata));
			return;
		}
	}
	
	@EventHandler
	public void ChatMessageEvent(ChatMessageEvent e) {
		if (SimpleEconomy.getInstance().lc) {
			if (SimpleEconomy.getInstance().getMagnataAPI().getMagnata().equals(e.getSender().getName())) {
				e.setTagValue("magnata", SimpleEconomy.getInstance().getConfig().getString("Magnata.Config.Tag").replaceAll("&", "§"));
			}
		}
	}

}
