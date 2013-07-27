package net.milkbowl.vault.economy.plugins;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.Plugin;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

public class WarPoints implements Economy{

	private Plugin plugin;
	public final String name="WarPoints";
	
	public WarPoints(Plugin p){
		plugin = p;
		if (!isEnabled()){
			System.out.println(String.format("[%s][Economy] %s hooked.", plugin.getDescription().getName(), name));
		}
		Bukkit.getServer().getPluginManager().registerEvents(new EconomyServerListener(this), plugin);
	}
	
	@Override
	public boolean isEnabled() {
		return plugin.getServer().getPluginManager().getPlugin("RCWars") != null;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean hasBankSupport() {
		return false;
	}

	@Override
	public int fractionalDigits() {
		return 0;
	}

	@Override
	public String format(double amount) {
		return String.valueOf(amount);
	}

	@Override
	public String currencyNamePlural() {
		return "WarPoints";
	}

	@Override
	public String currencyNameSingular() {
		return "WarPoint";
	}

	@Override
	public boolean hasAccount(String playerName) {
		return me.SgtMjrME.Object.WarPoints.isLoaded(playerName);
	}

	@Override
	public boolean hasAccount(String playerName, String worldName) {
		return hasAccount(playerName);
	}

	@Override
	public double getBalance(String playerName) {
		return me.SgtMjrME.Object.WarPoints.getWarPoints(playerName);
	}

	@Override
	public double getBalance(String playerName, String world) {
		return getBalance(playerName);
	}

	@Override
	public boolean has(String playerName, double amount) {
		return me.SgtMjrME.Object.WarPoints.has(playerName, amount);
	}

	@Override
	public boolean has(String playerName, String worldName, double amount) {
		return has(playerName, amount);
	}

	@Override
	public EconomyResponse withdrawPlayer(String playerName, double amount) {
		double wp = getBalance(playerName);
		if (!me.SgtMjrME.Object.WarPoints.spendWarPoints(Bukkit.getPlayer(playerName), (int) amount)){
			return new EconomyResponse(amount, wp, EconomyResponse.ResponseType.FAILURE, "You do not have enough WarPoints");
		}
		return new EconomyResponse(amount, wp, EconomyResponse.ResponseType.SUCCESS, null);
	}

	@Override
	public EconomyResponse withdrawPlayer(String playerName, String worldName,
			double amount) {
		return withdrawPlayer(playerName, amount);
	}

	@Override
	public EconomyResponse depositPlayer(String playerName, double amount) {
		me.SgtMjrME.Object.WarPoints.giveWarPoints(Bukkit.getPlayer(playerName), (int) amount);
		return new EconomyResponse(0,0,EconomyResponse.ResponseType.SUCCESS,null);
	}

	@Override
	public EconomyResponse depositPlayer(String playerName, String worldName,
			double amount) {
		return depositPlayer(playerName, amount);
	}

	@Override
	public EconomyResponse createBank(String name, String player) {
		return new EconomyResponse(0,0,EconomyResponse.ResponseType.NOT_IMPLEMENTED,null);
	}

	@Override
	public EconomyResponse deleteBank(String name) {
		return new EconomyResponse(0,0,EconomyResponse.ResponseType.NOT_IMPLEMENTED,null);
	}

	@Override
	public EconomyResponse bankBalance(String name) {
		return new EconomyResponse(0,0,EconomyResponse.ResponseType.NOT_IMPLEMENTED,null);
	}

	@Override
	public EconomyResponse bankHas(String name, double amount) {
		return new EconomyResponse(0,0,EconomyResponse.ResponseType.NOT_IMPLEMENTED,null);
	}

	@Override
	public EconomyResponse bankWithdraw(String name, double amount) {
		return new EconomyResponse(0,0,EconomyResponse.ResponseType.NOT_IMPLEMENTED,null);
	}

	@Override
	public EconomyResponse bankDeposit(String name, double amount) {
		return new EconomyResponse(0,0,EconomyResponse.ResponseType.NOT_IMPLEMENTED,null);
	}

	@Override
	public EconomyResponse isBankOwner(String name, String playerName) {
		return new EconomyResponse(0,0,EconomyResponse.ResponseType.NOT_IMPLEMENTED,null);
	}

	@Override
	public EconomyResponse isBankMember(String name, String playerName) {
		return new EconomyResponse(0,0,EconomyResponse.ResponseType.NOT_IMPLEMENTED,null);
	}

	@Override
	public List<String> getBanks() {
		return new ArrayList<String>();
	}

	@Override
	public boolean createPlayerAccount(String playerName) {
		return me.SgtMjrME.Object.WarPoints.isLoaded(playerName);
	}

	@Override
	public boolean createPlayerAccount(String playerName, String worldName) {
		return createPlayerAccount(playerName);
	}
	
	public class EconomyServerListener implements Listener {

        public EconomyServerListener(WarPoints warPoints) {
            //nothing (?!?!?!?!)
        }

        @EventHandler(priority = EventPriority.MONITOR)
        public void onPluginEnable(PluginEnableEvent event) {
           //nothing
        }

        @EventHandler(priority = EventPriority.MONITOR)
        public void onPluginDisable(PluginDisableEvent event) {
            //nothing
        }
    }

}
