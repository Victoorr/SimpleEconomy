package br.dev.victor696.simpleeconomy.object;

import java.sql.ResultSet;

import org.bukkit.Bukkit;

import br.dev.victor696.simpleeconomy.SimpleEconomy;
import br.dev.victor696.simpleeconomy.storage.Sql;

public class Money {

	private String p;
	private double money;
	boolean pay;

	public Money(String p) {
		this.p = p;
		this.money = 0;
		pay = true;
	}

	public String getPlayer() {
		return this.p;
	}

	public double getMoney() {
		return this.money;
	}

	public void setMoney(double valor) {
		this.money = valor;
	}

	public boolean getPay() {
		return this.pay;
	}

	public void setPay(boolean pay) {
		this.pay = pay;
	}
	
	public void loadAccount() {
		try {
			ResultSet rs = Sql.prepareStatement("SELECT * FROM SimpleEconomy WHERE Player = '" + this.p + "'").executeQuery();
			while (rs.next()) {
				money = Double.parseDouble(rs.getString("Money"));
			}
		} catch (Exception e) {
			SimpleEconomy.getInstance().error("Carregar account");
			Bukkit.getConsoleSender().sendMessage("" + e);
		}
	}

	public void saveAccount() {
		try {
			Sql.executarUpdate("UPDATE SimpleEconomy SET Money = '" + this.money + "' WHERE Player = '" + this.p + "'");
		} catch (Exception e) {
			SimpleEconomy.getInstance().error("Salvar account");
			Bukkit.getConsoleSender().sendMessage("" + e);
		}
	}

}
