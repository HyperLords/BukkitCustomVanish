package com.me.OyunTarayici.AtalanteCraftPlugin.Utils.ProfileConsumer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.me.OyunTarayici.AtalanteCraftPlugin.Utils.Profiles;

public interface IProfile {

	public void createProfile(Player player);
	
	public void saveProfile(Player player);
	
	public void setVanish(Profiles profiles,int point);
	
	public void setAutoVanish(Profiles profiles,Boolean autoVanish);
	
	default void autoBackup() {
		Bukkit.getOnlinePlayers().forEach(players->{
			createProfile(players);
		});
	}
	
	default void autoReloadAccount() {
		Bukkit.getOnlinePlayers().forEach(players->{
			saveProfile(players);
		});
	}
	
}
