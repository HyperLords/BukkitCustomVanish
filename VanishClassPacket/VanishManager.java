package com.me.OyunTarayici.AtalanteCraftPlugin.VanishControl;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

import com.me.OyunTarayici.AtalanteCraftPlugin.AtalanteCraftClass;
import com.me.OyunTarayici.AtalanteCraftPlugin.API.MessageTranslater;
import com.me.OyunTarayici.AtalanteCraftPlugin.API.PluginAPI;
import com.me.OyunTarayici.AtalanteCraftPlugin.API.MessageTranslater.Message;
import com.me.OyunTarayici.AtalanteCraftPlugin.Utils.Profiles;

public class VanishManager {

	MessageTranslater messageTranslater=new MessageTranslater();
	
	public static ArrayList<UUID> vanishedList=new ArrayList<>();
	
	public void playerHide(UUID who,UUID fromWho) {
		Player whoPlayer=Bukkit.getPlayer(who);
		Profiles profiles=Profiles.ProfileHashMap.get(who);
		if (profiles.getVanish()==0) {
			if (!vanishedList.contains(who)) {
				PluginAPI.hidePlayer(who, fromWho);
				AtalanteCraftClass.profile.setVanish(profiles, 1);
				AtalanteCraftClass.profile.saveProfile(whoPlayer);
				AtalanteCraftClass.profile.createProfile(whoPlayer);
				vanishedList.add(who);
				
				whoPlayer.sendMessage(PluginAPI.getColor(messageTranslater.message(/*This enum call message.yml*/ Message.PLAYER_VANISHED_NOW_HIDE)));
				whoPlayer.sendMessage(PluginAPI.getColor(messageTranslater.message(/*This enum call message.yml*/ Message.PLAYER_VANISHED_NOBODY)));
			}else return;
		return;}
	} //Player hide method
	
	public void playerShow(UUID who,UUID fromWho) {
		Player whoPlayer=Bukkit.getPlayer(who);
		Profiles profiles=Profiles.ProfileHashMap.get(who);
		if (profiles.getVanish()==1) {
			if (vanishedList.contains(who)) {
				PluginAPI.showPlayer(who, fromWho);
				AtalanteCraftClass.profile.setVanish(profiles, 0);
				AtalanteCraftClass.profile.saveProfile(whoPlayer);
				AtalanteCraftClass.profile.createProfile(whoPlayer);
				vanishedList.remove(who);
				whoPlayer.sendMessage(PluginAPI.getColor(messageTranslater.message(/*This enum call message.yml*/ Message.PLAYER_UNVANISHED)));
			return;}
		return;}
	} //Player unhide packet
	
	public void reloadVanish(Player whoPlayer) {
		try {
			Profiles profiles=Profiles.ProfileHashMap.get(whoPlayer.getUniqueId());
			AtalanteCraftClass.profile.setVanish(profiles, 0);
			AtalanteCraftClass.profile.saveProfile(whoPlayer);
			AtalanteCraftClass.profile.createProfile(whoPlayer);
			vanishedList.remove(whoPlayer.getUniqueId());
		} catch (NoClassDefFoundError e) {
			return;
		}
	} //Player left the game reload profile data
	
	public void automaticVanish(PlayerJoinEvent e,Player player) {
		try {
			Profiles profiles=Profiles.ProfileHashMap.get(player.getUniqueId());
			if (player.hasPermission("atalantecraft.vanish.mode")) {
				if (profiles.getAutoVanish()==true) {
					for (Player players:Bukkit.getOnlinePlayers()) {
						playerHide(player.getUniqueId(), players.getUniqueId());
					}
					e.setJoinMessage(null); // Allright player join automaticly cancel join message
				return;} // Auto vanish allright false player out and connect hide player
				else {
					for (Player players:Bukkit.getOnlinePlayers()) {
						playerShow(player.getUniqueId(), players.getUniqueId());
					}
					e.setJoinMessage(null);
				} // Auto vanish allright false player out and connect not hide
			return;}
		} catch (NoClassDefFoundError e2) {
			return;
		}
	return;}
	
	public void onHidePlayer(Player player) {
		try {
			if (player.hasPermission("atalantecraft.vanish.mode")) {
				for (UUID allVanishedPlayer:vanishedList) {
					PluginAPI.showPlayer(allVanishedPlayer, player.getUniqueId());
				}
			return;} // if permission add all officer team not hide player
			else {
				for (UUID allVanishedPlayer:vanishedList) {
					PluginAPI.hidePlayer(allVanishedPlayer, player.getUniqueId());
				}
			}
		} catch (NoClassDefFoundError e) {
			return;
		}
	return;} //Player join all player hide yourself
	
}
