package br.dev.victor696.simpleeconomy.commands.subcommands;

import java.util.List;

import org.bukkit.entity.Player;

import br.dev.victor696.simpleeconomy.commands.EconomyCommand;
import br.dev.victor696.simpleeconomy.utils.CenterMessage;
import br.dev.victor696.simpleeconomy.utils.Methods;

public class MoneySubTop extends EconomyCommand {

	public MoneySubTop() {
		super("top", "veconomy.cmd.top", new String[] { "victor" });
	}

	@Override
	public void onCommand(Player p, String[] args) {
		if (args.length > 0) {
			if (args[0].equalsIgnoreCase("top")) {

				p.sendMessage("");
				CenterMessage.sendCenteredMessage(p, "§2§lTOP 10 MAIS RICO DO SERVIDOR");
				CenterMessage.sendCenteredMessage(p, "§7(Atualizado a cada 5 minutos)");
				p.sendMessage("");
				
				List<String> s = Methods.getListTop();
				
				for (int i = 0; i < 10; i++) {
					if ((s.size()-1) >= i) {
						p.sendMessage("   " + s.get(i));
					}
				}
				
			}
		}
	}
}
