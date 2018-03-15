package br.dev.victor696.simpleeconomy.commands.subcommands;

import org.bukkit.entity.Player;

import br.dev.victor696.simpleeconomy.SimpleEconomy;
import br.dev.victor696.simpleeconomy.commands.EconomyCommand;

public class MoneySubHelp extends EconomyCommand {

	public MoneySubHelp() {
		super("help", "veconomy.cmd.help", new String[] { "ajuda" });
	}

	@Override
	public void onCommand(Player p, String[] args) {
		if (args.length > 0) {
			if (args[0].equalsIgnoreCase("help")) {
				
				for (String msg : SimpleEconomy.getInstance().getConfig().getStringList("Mensagens.ListaDeAjuda")) {
					p.sendMessage(msg.replaceAll("&", "§"));
				}
				
			}
		}
	}
}
