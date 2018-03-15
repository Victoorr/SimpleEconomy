package br.dev.victor696.simpleeconomy.commands.subcommands;

import org.bukkit.entity.Player;

import br.dev.victor696.simpleeconomy.SimpleEconomy;
import br.dev.victor696.simpleeconomy.commands.EconomyCommand;
import br.dev.victor696.simpleeconomy.object.Money;
import br.dev.victor696.simpleeconomy.utils.Methods;

public class MoneySubToggle extends EconomyCommand {

	public MoneySubToggle() {
		super("toggle", "veconomy.cmd.toggle", new String[] { "pref", "preferencias" });
	}

	@Override
	public void onCommand(Player p, String[] args) {
		if (args.length > 0) {
			if (args[0].equalsIgnoreCase("toggle")) {

				Money m = SimpleEconomy.getInstance().account.get(p.getName());
				if (m == null) {
					try {
						Methods.createAccount(p.getName());
					} catch (Exception e) {
					}
				}
				
				if (m.getPay()) {
					m.setPay(false);
					p.sendMessage(SimpleEconomy.getInstance().getConfig().getString("Mensagens.ToggleDesativado").replaceAll("&", "§"));
				} else {
					m.setPay(true);
					p.sendMessage(SimpleEconomy.getInstance().getConfig().getString("Mensagens.ToggleAtivado").replaceAll("&", "§"));
				}
				
			}
		}
	}
}
