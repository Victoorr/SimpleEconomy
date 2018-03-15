package br.dev.victor696.simpleeconomy.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import br.dev.victor696.simpleeconomy.SimpleEconomy;
import br.dev.victor696.simpleeconomy.commands.subcommands.MoneySubAdd;
import br.dev.victor696.simpleeconomy.commands.subcommands.MoneySubConvert;
import br.dev.victor696.simpleeconomy.commands.subcommands.MoneySubHelp;
import br.dev.victor696.simpleeconomy.commands.subcommands.MoneySubPay;
import br.dev.victor696.simpleeconomy.commands.subcommands.MoneySubRemove;
import br.dev.victor696.simpleeconomy.commands.subcommands.MoneySubSet;
import br.dev.victor696.simpleeconomy.commands.subcommands.MoneySubToggle;
import br.dev.victor696.simpleeconomy.commands.subcommands.MoneySubTop;
import br.dev.victor696.simpleeconomy.object.Money;
import br.dev.victor696.simpleeconomy.utils.Methods;

public class MoneyCommand implements CommandExecutor, TabCompleter {
	private List<EconomyCommand> commands;

	public MoneyCommand() {
		this.commands = new ArrayList<EconomyCommand>();
		this.newCommand(new MoneySubAdd());
		this.newCommand(new MoneySubConvert());
		this.newCommand(new MoneySubHelp());
		this.newCommand(new MoneySubPay());
		this.newCommand(new MoneySubRemove());
		this.newCommand(new MoneySubSet());
		this.newCommand(new MoneySubToggle());
		this.newCommand(new MoneySubTop());
	}

	public List<EconomyCommand> getCommands() {
		return this.commands;
	}

	public boolean newCommand(EconomyCommand blackCommand) {
		if (!this.commands.contains(blackCommand)) {
			this.commands.add(blackCommand);
		}
		return this.commands.contains(blackCommand);
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length <= 0) {
				Money m = SimpleEconomy.getInstance().account.get(p.getName());
				if (m == null) {
					try {
						Methods.createAccount(p.getName());
					} catch (Exception e) {
					}
				}

				p.sendMessage(SimpleEconomy.getInstance().getConfig().getString("Mensagens.SaldoVer")
						.replaceAll("&", "§").replace("{valor}", Methods.pegarMoney(m.getMoney())));
				return false;
			}
			if (args.length >= 1) {
				for (EconomyCommand blackCommand : getCommands()) {
					if (args[0].equalsIgnoreCase(blackCommand.getCommand())) {
						if (p.hasPermission(blackCommand.getPermission())) {
							blackCommand.onCommand(p, args);
						} else {
							p.sendMessage(SimpleEconomy.getInstance().getConfig()
									.getString("Mensagens.SemPermissao").replaceAll("&", "§"));
						}
						return false;
					}
					String[] aliases;
					for (int length = (aliases = blackCommand.getAliases()).length, i = 0; i < length; ++i) {
						String sub = aliases[i];
						if (args[0].equalsIgnoreCase(sub)) {
							if (p.hasPermission(blackCommand.getPermission())) {
								blackCommand.onCommand(p, args);
							} else {
								p.sendMessage(SimpleEconomy.getInstance().getConfig()
										.getString("Mensagens.SemPermissao").replaceAll("&", "§"));
							}
							return false;
						}
					}
				}
				Player pp = Bukkit.getPlayer(args[0]);
				Money m = SimpleEconomy.getInstance().account.get(pp.getName());
				if (m == null) {
					if (!pp.isOnline()) {
						p.sendMessage(SimpleEconomy.getInstance().getConfig()
								.getString("Mensagens.JogadorNaoEncontrado").replaceAll("&", "§"));
						return false;
					} else {
						try {
							Methods.createAccount(pp.getName());
						} catch (Exception e) {
						}
					}
				}
				p.sendMessage(SimpleEconomy.getInstance().getConfig().getString("Mensagens.SaldoVerOutroJogador")
						.replaceAll("&", "§").replace("{valor}", Methods.pegarMoney(m.getMoney()))
						.replace("{player}", pp.getName()));
			}
		}
		return false;
	}

	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		List<String> tabCompleter = new ArrayList<String>();
		if (args.length == 1) {
			for (EconomyCommand blackCommand : getCommands()) {
				if (sender.hasPermission(blackCommand.getPermission())) {
					tabCompleter.add(blackCommand.getCommand());
				}
			}
		}
		return tabCompleter;
	}
}