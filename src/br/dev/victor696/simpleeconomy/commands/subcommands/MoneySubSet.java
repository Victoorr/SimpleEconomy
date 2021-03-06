package br.dev.victor696.simpleeconomy.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import br.dev.victor696.simpleeconomy.SimpleEconomy;
import br.dev.victor696.simpleeconomy.commands.EconomyCommand;
import br.dev.victor696.simpleeconomy.object.Money;
import br.dev.victor696.simpleeconomy.utils.Methods;

public class MoneySubSet extends EconomyCommand {

	public MoneySubSet() {
		super("set", "veconomy.cmd.set", new String[] { "setar" });
	}

	@Override
	public void onCommand(Player p, String[] args) {
		if (args.length > 0) {
			if (args.length == 1 || args.length == 2) {
				p.sendMessage(SimpleEconomy.getInstance().getConfig().getString("Mensagens.SetArgumento").replaceAll("&", "�"));
				return;
			}
			
			Player pp = Bukkit.getPlayer(args[1]);
			if (pp == null) {
				p.sendMessage(SimpleEconomy.getInstance().getConfig().getString("Mensagens.JogadorNaoEncontrado").replaceAll("&", "�"));
				return;
			}
			
			double value = 0.0;
            try {
                value = Double.valueOf(args[2]);
            }
            catch (Exception e) {
				p.sendMessage(SimpleEconomy.getInstance().getConfig().getString("Mensagens.ValorPrecisaSerNumero").replaceAll("&", "�"));
                return;
            }
            
            if (value <= 0) {
				p.sendMessage(SimpleEconomy.getInstance().getConfig().getString("Mensagens.ValorPrecisaSerMaiorQueZero").replaceAll("&", "�"));
				return;
			}
            
            Money m = SimpleEconomy.getInstance().account.get(pp.getName());
            if (m == null) {
           	 try {
               	 Methods.createAccount(pp.getName());
				} catch (Exception e) {
				}
            }
            
            m.setMoney(value);
			p.sendMessage(SimpleEconomy.getInstance().getConfig().getString("Mensagens.SaldoSetado").replaceAll("&", "�")
					.replace("{valor}", Methods.pegarMoney(value))
					.replace("{player}", pp.getName()));
		}
	}

}
