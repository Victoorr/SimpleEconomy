package br.dev.victor696.simpleeconomy.convert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import br.dev.victor696.simpleeconomy.SimpleEconomy;
import br.dev.victor696.simpleeconomy.object.Money;

public class ConvertBlackEconomy {

	public static void start() {
		new BukkitRunnable() {

			@SuppressWarnings("unused")
			@Override
			public void run() {
				try {
					String host = SimpleEconomy.instance.getConfig().getString("Convert.Host");
					int porta = SimpleEconomy.instance.getConfig().getInt("Convert.Port");
					String usuario = SimpleEconomy.instance.getConfig().getString("Convert.User");
					String senha = SimpleEconomy.instance.getConfig().getString("Convert.Password");
					String db = SimpleEconomy.instance.getConfig().getString("Convert.DB");
					String tabela = SimpleEconomy.instance.getConfig().getString("Convert.Table");

					Connection c = DriverManager.getConnection("jdbc:mysql://" + host + ":" + porta + "/" + db,
							usuario, senha);

					ResultSet rs = null;
					rs = c.prepareStatement("SELECT * FROM " + tabela).executeQuery();
					while (rs.next()) {

						String player = rs.getString("name");
						double value = rs.getDouble("value");

						//

						Money m = new Money(player);
						if (m != null) {
							m.setMoney(m.getMoney() + value);
						} else
							m.setMoney(value);
						m.saveAccount();

						//

						SimpleEconomy.getInstance().info("Player '" + player + "' convertido para VEconomy!");
					}

				} catch (Exception e) {
					SimpleEconomy.getInstance().error("Converter jogadores");
					Bukkit.getConsoleSender().sendMessage("" + e);
				}
			}
		}.runTaskAsynchronously(SimpleEconomy.getInstance());
	}
}
