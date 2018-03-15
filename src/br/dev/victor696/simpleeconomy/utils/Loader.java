package br.dev.victor696.simpleeconomy.utils;

import java.sql.ResultSet;

import org.bukkit.Bukkit;

import br.dev.victor696.simpleeconomy.SimpleEconomy;
import br.dev.victor696.simpleeconomy.object.Money;
import br.dev.victor696.simpleeconomy.storage.Sql;

public class Loader {

	public static void loadPlayers() {
		try {
			ResultSet rs = null;
			rs = Sql.conexao.prepareStatement("SELECT * FROM SimpleEconomy").executeQuery();
			int i = 0;

			while (rs.next()) {
				String p = rs.getString("Player");
				Money m = new Money(p);
				m.loadAccount();
				SimpleEconomy.getInstance().account.put(p, m);
				i++;
			}

			if (i > 0) {
				SimpleEconomy.getInstance().info("Foram carregados " + i + " jogadores da tabela!");
			} else {
				SimpleEconomy.getInstance().info("Nao ha nenhum jogador na tabela!");
			}
		} catch (Exception e) {
			SimpleEconomy.getInstance().error("Carregar jogador da tabela");
			Bukkit.getConsoleSender().sendMessage("" + e);
		}
	}

	public static void savePlayers() {
		try {
			int i = 0;
			for (String ps : SimpleEconomy.getInstance().account.keySet()) {
				Money m = SimpleEconomy.getInstance().account.get(ps);
				if (m != null) {
					m.saveAccount();
					i++;
				}
			}

			if (i > 0) {
				SimpleEconomy.getInstance().info("Foram salvos " + i + " jogadores na tabela!");
			} else {
				SimpleEconomy.getInstance().info("Nao ha nenhum jogador para salvar na tabela!");
			}
		} catch (Exception e) {
			SimpleEconomy.getInstance().error("Salvar jogador na tabela");
			Bukkit.getConsoleSender().sendMessage("" + e);
		}
	}
}
