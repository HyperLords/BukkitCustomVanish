package com.me.OyunTarayici.AtalanteCraftPlugin.API;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import com.me.OyunTarayici.AtalanteCraftPlugin.AtalanteCraftClass;
import net.md_5.bungee.api.ChatColor;

public class PluginAPI {

	public static void consoleMessage(String message) {
		Bukkit.getConsoleSender().sendMessage(getColor(message));
	return;}
	
	@SuppressWarnings("deprecation")
	public static void hidePlayer(UUID who,UUID fromWho) {
		Player playerFromWho=Bukkit.getPlayer(fromWho);
		Player Who=Bukkit.getPlayer(who);
		playerFromWho.hidePlayer(Who);
	return;}
	
	@SuppressWarnings("deprecation")
	public static void showPlayer(UUID who,UUID fromWho) {
		Player playerFromWho=Bukkit.getPlayer(fromWho);
		Player Who=Bukkit.getPlayer(who);
		playerFromWho.showPlayer(Who);
	return;}
	
}
