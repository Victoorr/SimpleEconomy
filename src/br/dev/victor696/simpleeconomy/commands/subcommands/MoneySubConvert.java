package br.dev.victor696.simpleeconomy.commands.subcommands;

import org.bukkit.entity.Player;

import br.dev.victor696.simpleeconomy.commands.EconomyCommand;
import br.dev.victor696.simpleeconomy.convert.ConvertBlackEconomy;

public class MoneySubConvert extends EconomyCommand {

	public MoneySubConvert() {
		super("convert", "veconomy.cmd.convert", new String[] { "converter" });
	}

	@Override
	public void onCommand(Player p, String[] args) {
		if (args.length > 0) {
			if (args[0].equalsIgnoreCase("convert")) {
				if (args.length == 1) {
					p.sendMessage("§cUse '/money convert <BlackEconomy>' para converter o money.");
					return;
				}
				if (args[1].equalsIgnoreCase("blackeconomy")) {
					ConvertBlackEconomy.start();
					
				} else {
					p.sendMessage("§cUse '/money convert <BlackEconomy>' para converter o money.");
					return;
				}
			}
		}
	}
}
