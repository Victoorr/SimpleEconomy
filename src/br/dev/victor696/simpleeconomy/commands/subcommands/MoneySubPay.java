package br.dev.victor696.simpleeconomy.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import br.dev.victor696.simpleeconomy.SimpleEconomy;
import br.dev.victor696.simpleeconomy.commands.EconomyCommand;
import br.dev.victor696.simpleeconomy.object.Money;
import br.dev.victor696.simpleeconomy.utils.Methods;

public class MoneySubPay extends EconomyCommand {

	public MoneySubPay() {
		super("pay", "veconomy.cmd.pay", new String[] { "pagar", "enviar" });
	}

	@Override
	public void onCommand(Player p, String[] args) {
		if (args.length > 0) {
			if (args.length == 1 || args.length == 2) {
				p.sendMessage(SimpleEconomy.getInstance().getConfig().getString("Mensagens.PayArgumento").replaceAll("&", "§"));
				return;
			}

			Money mm = SimpleEconomy.getInstance().account.get(p.getName());
			if (mm == null) {
				try {
					Methods.createAccount(p.getName());
				} catch (Exception e) {
				}
			}
			
			Player pp = Bukkit.getPlayer(args[1]);
			if (pp == null) {
				p.sendMessage(SimpleEconomy.getInstance().getConfig().getString("Mensagens.JogadorNaoEncontrado").replaceAll("&", "§"));
				return;
			}
			
			if (pp.getName().toLowerCase().equals(p.getName().toLowerCase())) {
				p.sendMessage(SimpleEconomy.getInstance().getConfig().getString("Mensagens.EsteJogadorEVoce").replaceAll("&", "§"));
				return;
			}
			
			Money m = SimpleEconomy.getInstance().account.get(pp.getName());
			if (m == null) {
				try {
					Methods.createAccount(pp.getName());
				} catch (Exception e) {
				}
			}
			
			if (m.getPay() == false) {
				p.sendMessage(SimpleEconomy.getInstance().getConfig().getString("Mensagens.ValorPrecisaSerNumero").replaceAll("&", "§")
						.replace("{player}", pp.getName()));
				return;
			}
			
			double value = 0.0;
			try {
				value = Double.valueOf(args[2]);
			} catch (Exception e) {
				p.sendMessage(SimpleEconomy.getInstance().getConfig().getString("Mensagens.ValorPrecisaSerNumero").replaceAll("&", "§"));
				return;
			}

			if (value <= 0) {
				p.sendMessage(SimpleEconomy.getInstance().getConfig().getString("Mensagens.ValorPrecisaSerMaiorQueZero").replaceAll("&", "§"));
				return;
			}

			if (mm.getMoney() < value) {
				p.sendMessage(SimpleEconomy.getInstance().getConfig().getString("Mensagens.SaldoInsuficiente").replaceAll("&", "§"));
				return;
			}

			mm.setMoney(mm.getMoney() - value);
			m.setMoney(m.getMoney() + value);

			p.sendMessage(SimpleEconomy.getInstance().getConfig().getString("Mensagens.SaldoEnviado").replaceAll("&", "§")
					.replace("{valor}", Methods.pegarMoney(value))
					.replace("{player}", pp.getName()));
			p.sendMessage(SimpleEconomy.getInstance().getConfig().getString("Mensagens.SaldoRecebido").replaceAll("&", "§")
					.replace("{valor}", Methods.pegarMoney(value))
					.replace("{player}", p.getName()));
		}
 	}

}
