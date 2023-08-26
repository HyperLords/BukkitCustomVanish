package com.me.OyunTarayici.AtalanteCraftPlugin.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import com.me.OyunTarayici.AtalanteCraftPlugin.AtalanteCraftClass;
import com.me.OyunTarayici.AtalanteCraftPlugin.VanishControl.VanishManager;

public class JoinEvent implements Listener {

	@EventHandler
	public void event(PlayerJoinEvent e) {
		AtalanteCraftClass.profile.createProfile(e.getPlayer());
		AtalanteCraftClass.profile.saveProfile(e.getPlayer());
		AtalanteCraftClass.profile.createProfile(e.getPlayer());
		
		new VanishManager().onHidePlayer(e.getPlayer());
		new VanishManager().automaticVanish(e,e.getPlayer());
		
		e.setJoinMessage(null);
	}
	
	@EventHandler
	public void event(PlayerQuitEvent e) {
		AtalanteCraftClass.profile.saveProfile(e.getPlayer());
		AtalanteCraftClass.profile.createProfile(e.getPlayer());
		
		new VanishManager().reloadVanish(e.getPlayer());
		e.setQuitMessage(null);
	}
	
}
