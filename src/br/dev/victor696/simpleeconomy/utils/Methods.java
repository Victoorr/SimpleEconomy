package br.dev.victor696.simpleeconomy.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;

import br.dev.victor696.simpleeconomy.SimpleEconomy;
import br.dev.victor696.simpleeconomy.object.Money;
import br.dev.victor696.simpleeconomy.storage.Sql;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Methods {

	public static ArrayList<String> top;

	public static List<String> getListTop() {
		return top;
	}

	public static void createAccount(String p) throws SQLException {
		tabelaSetarJogador(p);
		Money m = new Money(p);
		m.setMoney(SimpleEconomy.getInstance().getConfig().getInt("Geral.ComecarCom"));
		m.saveAccount();
		SimpleEconomy.getInstance().account.put(p, m);
	}

	public static void tabelaSetarJogador(String p) {
		PreparedStatement stm = null;
		try {
			stm = Sql.conexao.prepareStatement("INSERT INTO SimpleEconomy (Player, Money, Pay) VALUES (?,?,?)");
			stm.setString(1, p);
			stm.setDouble(2, 0.0);
			stm.setBoolean(3, true);
			stm.executeUpdate();
		} catch (SQLException e) {

			SimpleEconomy.getInstance().error("Inserir um novo jogador (" + p + ")");
			Bukkit.getConsoleSender().sendMessage("" + e);
		}
	}

	public static List<String> loadTop() {
		try {
			top.clear();
			int size = SimpleEconomy.getInstance().getConfig().getInt("Geral.TopTamanho");
			ResultSet rs = Sql.prepareStatement("SELECT * FROM SimpleEconomy ORDER BY Money DESC LIMIT " + size + ";").executeQuery();
			int i = 0;
			while (rs.next()) {
				i++;
				String player = rs.getString("Player");
				double money = rs.getInt("Money");
				String prefixo = "";
				if (SimpleEconomy.getInstance().pex) {
					prefixo = PermissionsEx.getUser(player).getPrefix().replaceAll("&", "§");
				}

				String magnata = "";
				if (i == 1) {
					magnata = SimpleEconomy.getInstance().getConfig().getString("Geral.TopMagnataTag").replaceAll("&", "§");
					SimpleEconomy.getInstance().getMagnataAPI().magnata = player;
				}

				String formato = SimpleEconomy.getInstance().getConfig().getString("Mensagens.TopFormato").replaceAll("&", "§").replace(
						"{posicao}", Integer.toString(i)).replace("{magnata}", magnata).replace("{preffixo}", prefixo)
								.replace("{player}", player).replace("{valor}", pegarMoney(money));

				top.add(formato);
			}

			SimpleEconomy.getInstance().info("Top carregado com sucesso!");

		} catch (SQLException e) {
			SimpleEconomy.getInstance().error("Carregar top");
			Bukkit.getConsoleSender().sendMessage("" + e);
		}
		return top;
	}
	public static String pegarMoney(double money) {
		DecimalFormat numberFormat = new DecimalFormat("###,###.###");
		String d = numberFormat.format(money);
		if (d.equalsIgnoreCase(".00")) {
			d = "0.0";
		}
		return d;
	}

}
