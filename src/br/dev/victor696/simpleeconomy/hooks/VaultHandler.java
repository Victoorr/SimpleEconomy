package br.dev.victor696.simpleeconomy.hooks;

import java.sql.SQLException;
import java.util.List;

import org.bukkit.OfflinePlayer;

import br.dev.victor696.simpleeconomy.SimpleEconomy;
import br.dev.victor696.simpleeconomy.object.Money;
import br.dev.victor696.simpleeconomy.utils.Methods;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.economy.EconomyResponse.ResponseType;

public class VaultHandler implements Economy {

	@Override
	public EconomyResponse bankBalance(String p) {
		return new EconomyResponse(0.0, 0.0, ResponseType.NOT_IMPLEMENTED,
				"Este plugin não possui suporte para este tipo de acão.");
	}

	@Override
	public EconomyResponse bankDeposit(String p, double valor) {
		return new EconomyResponse(0.0, 0.0, ResponseType.NOT_IMPLEMENTED,
				"Este plugin não possui suporte para este tipo de acão.");
	}

	@Override
	public EconomyResponse bankHas(String p, double valor) {
		return new EconomyResponse(0.0, 0.0, ResponseType.NOT_IMPLEMENTED,
				"Este plugin não possui suporte para este tipo de acão.");
	}

	@Override
	public EconomyResponse bankWithdraw(String p, double valor) {
		return new EconomyResponse(0.0, 0.0, ResponseType.NOT_IMPLEMENTED,
				"Este plugin não possui suporte para este tipo de acão.");
	}

	@Override
	public EconomyResponse createBank(String p, String arg1) {
		return new EconomyResponse(0.0, 0.0, ResponseType.NOT_IMPLEMENTED,
				"Este plugin não possui suporte para este tipo de acão.");
	}

	@Override
	public EconomyResponse createBank(String p, OfflinePlayer arg1) {
		return new EconomyResponse(0.0, 0.0, ResponseType.NOT_IMPLEMENTED,
				"Este plugin não possui suporte para este tipo de acão.");
	}

	@Override
	public boolean createPlayerAccount(String p) {
		try {
			Methods.createAccount(p);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean createPlayerAccount(OfflinePlayer p) {
		return createPlayerAccount(p.getName());
	}

	@Override
	public boolean createPlayerAccount(String p, String arg1) {
		return createPlayerAccount(p);
	}

	@Override
	public boolean createPlayerAccount(OfflinePlayer p, String arg1) {
		return createPlayerAccount(p.getName());
	}

	@Override
	public String currencyNamePlural() {
		return "Coins";
	}

	@Override
	public String currencyNameSingular() {
		return "Coin";
	}

	@Override
	public EconomyResponse deleteBank(String p) {
		return new EconomyResponse(0.0, 0.0, ResponseType.NOT_IMPLEMENTED,
				"Este plugin não possui suporte para este tipo de acão.");
	}

	@Override
	public EconomyResponse depositPlayer(String p, double valor) {
		if (valor > 0) {
			Money m = SimpleEconomy.getInstance().account.get(p);
			if (m == null) {
				return new EconomyResponse(0, 0, ResponseType.FAILURE, "Conta inexistente");
			}
			m.setMoney(m.getMoney() + valor);
			return new EconomyResponse(0, m.getMoney(), ResponseType.SUCCESS, "");
		} else {
			return new EconomyResponse(0, 0, ResponseType.FAILURE, "Valor negativo");
		}
	}

	@Override
	public EconomyResponse depositPlayer(OfflinePlayer p, double valor) {
		return depositPlayer(p.getName(), valor);
	}

	@Override
	public EconomyResponse depositPlayer(String p, String arg1, double valor) {
		return depositPlayer(p, valor);
	}

	@Override
	public EconomyResponse depositPlayer(OfflinePlayer p, String arg1, double valor) {
		return depositPlayer(p.getName(), valor);
	}

	@Override
	public String format(double arg0) {
		return null;
	}

	@Override
	public int fractionalDigits() {
		return 0;
	}

	@Override
	public double getBalance(String p) {
		if (SimpleEconomy.getInstance().account.get(p) != null) {
			return SimpleEconomy.getInstance().account.get(p).getMoney();
		} else {
			return 0.0;
		}
	}

	@Override
	public double getBalance(OfflinePlayer p) {
		return getBalance(p.getName());
	}

	@Override
	public double getBalance(String p, String arg1) {
		return getBalance(p);
	}

	@Override
	public double getBalance(OfflinePlayer p, String arg1) {
		return getBalance(p.getName());
	}

	@Override
	public List<String> getBanks() {
		return null;
	}

	@Override
	public String getName() {
		return "VEconomy";
	}

	@Override
	public boolean has(String p, double valor) {
		
		Money m = SimpleEconomy.getInstance().account.get(p);
		if (m != null) {
			if (valor >= 0){
				return m.getMoney() >= valor;
			} else {
				return false;
			} 
		} else {
			return false;
		}
		
	}

	@Override
	public boolean has(OfflinePlayer p, double valor) {
		return has(p.getName(), valor);
	}

	@Override
	public boolean has(String p, String arg1, double valor) {
		return has(p, valor);
	}

	@Override
	public boolean has(OfflinePlayer p, String arg1, double valor) {
		return has(p.getName(), valor);
	}

	@Override
	public boolean hasAccount(String p) {
		return SimpleEconomy.getInstance().account.get(p) != null;
	}

	@Override
	public boolean hasAccount(OfflinePlayer p) {
		return hasAccount(p.getName());
	}

	@Override
	public boolean hasAccount(String p, String arg1) {
		return hasAccount(p);
	}

	@Override
	public boolean hasAccount(OfflinePlayer p, String arg1) {
		return hasAccount(p.getName());
	}

	@Override
	public boolean hasBankSupport() {
		return false;
	}

	@Override
	public EconomyResponse isBankMember(String p, String arg1) {
		return new EconomyResponse(0.0, 0.0, ResponseType.NOT_IMPLEMENTED,
				"Este plugin não possui suporte para este tipo de acão.");
	}

	@Override
	public EconomyResponse isBankMember(String p, OfflinePlayer arg1) {
		return new EconomyResponse(0.0, 0.0, ResponseType.NOT_IMPLEMENTED,
				"Este plugin não possui suporte para este tipo de acão.");
	}

	@Override
	public EconomyResponse isBankOwner(String p, String arg1) {
		return new EconomyResponse(0.0, 0.0, ResponseType.NOT_IMPLEMENTED,
				"Este plugin não possui suporte para este tipo de acão.");
	}

	@Override
	public EconomyResponse isBankOwner(String p, OfflinePlayer arg1) {
		return new EconomyResponse(0.0, 0.0, ResponseType.NOT_IMPLEMENTED,
				"Este plugin não possui suporte para este tipo de acão.");
	}

	@Override
	public boolean isEnabled() {
		return SimpleEconomy.getInstance().isEnabled();
	}

	@Override
	public EconomyResponse withdrawPlayer(String p, double valor) {
		if (valor < 0) {
			return new EconomyResponse(0, 0, ResponseType.FAILURE, "Valor negativo");
		}
		Money m = SimpleEconomy.getInstance().account.get(p);
		if (m == null) {
			return new EconomyResponse(0, 0, ResponseType.FAILURE, "Conta não existe");
		}
		m.setMoney(m.getMoney() - valor);
		return new EconomyResponse(0, m.getMoney(), ResponseType.SUCCESS, "");
	}

	@Override
	public EconomyResponse withdrawPlayer(OfflinePlayer p, double valor) {
		return withdrawPlayer(p.getName(), valor);
	}

	@Override
	public EconomyResponse withdrawPlayer(String p, String arg1, double valor) {
		return withdrawPlayer(p, valor);
	}

	@Override
	public EconomyResponse withdrawPlayer(OfflinePlayer p, String arg1, double valor) {
		return withdrawPlayer(p.getName(), valor);
	}

}
