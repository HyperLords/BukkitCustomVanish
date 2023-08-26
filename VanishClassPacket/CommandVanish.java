package com.me.OyunTarayici.AtalanteCraftPlugin.Commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.me.OyunTarayici.AtalanteCraftPlugin.AtalanteCraftClass;
import com.me.OyunTarayici.AtalanteCraftPlugin.API.MessageTranslater;
import com.me.OyunTarayici.AtalanteCraftPlugin.API.PluginAPI;
import com.me.OyunTarayici.AtalanteCraftPlugin.API.MessageTranslater.Message;
import com.me.OyunTarayici.AtalanteCraftPlugin.Utils.Profiles;
import com.me.OyunTarayici.AtalanteCraftPlugin.VanishControl.VanishManager;

public class CommandAtalanteClassVanish implements CommandExecutor, TabCompleter{
	
	MessageTranslater messageTranslater=new MessageTranslater();
	
	public CommandVanish(JavaPlugin plugin) {
		plugin.getCommand("avanish").setTabCompleter(this);
	}
	
	@SuppressWarnings({ "deprecation", "unused" })
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender.hasPermission("atalantecraft.vanish.other")) {
			if (sender instanceof Player) {
				Player player=(Player)sender;

				if (args.length==0) {
					/*
					 * open the vanish gui
					 * */
				return true;}
				
				if (args[0].equalsIgnoreCase("hide")) {
					if (args.length==1) {
						for (Player allPlayer:Bukkit.getOnlinePlayers()) {
							new VanishManager().playerHide(player.getUniqueId(), allPlayer.getUniqueId());
						}
					return true;} //Hide yourself
					
					OfflinePlayer targetOfflinePlayer=Bukkit.getOfflinePlayer(args[1]);
					if (!targetOfflinePlayer.isOnline()) {
						player.sendMessage(PluginAPI.getColor(messageTranslater.message(Message.PLAYER_NOT_ONLINE).replace("<PLAYER_NAME>", targetOfflinePlayer.getName())));
					return true;}else if (targetOfflinePlayer==null) {return true;}else {
						for (Player allPlayer:Bukkit.getOnlinePlayers()) {
							new VanishManager().playerHide(player.getUniqueId(), allPlayer.getUniqueId());
						}
					return true;} //Hide player name arguments
				} //Hide argument
				
				if (args[0].equalsIgnoreCase("everytime")) {
					if (args.length==1) {
						Profiles profiles=Profiles.ProfileHashMap.get(player.getUniqueId());
						if (profiles.getAutoVanish()==true) {
							AtalanteCraftClass.profile.setAutoVanish(profiles, false);
							AtalanteCraftClass.profile.saveProfile(player);
							AtalanteCraftClass.profile.createProfile(player);
							player.sendMessage(PluginAPI.getColor(messageTranslater.message(Message.PLAYER_UNVANISHED)));
							player.performCommand("avanish unvanish");
						return true;}else {
							AtalanteCraftClass.profile.setAutoVanish(profiles, true);
							AtalanteCraftClass.profile.saveProfile(player);
							AtalanteCraftClass.profile.createProfile(player);
							player.sendMessage(PluginAPI.getColor(messageTranslater.message(Message.EVERYONE_VANISHED)));
						}
					return true;}
					
					OfflinePlayer targetOfflinePlayer=Bukkit.getOfflinePlayer(args[1]);
					if (!targetOfflinePlayer.isOnline()) {
						player.sendMessage(PluginAPI.getColor(messageTranslater.message(Message.PLAYER_NOT_ONLINE).replace("<PLAYER_NAME>", targetOfflinePlayer.getName())));
					return true;}else if (targetOfflinePlayer==null) {return true;}else {
						for (Player allPlayer:Bukkit.getOnlinePlayers()) {
							Profiles profiles=Profiles.ProfileHashMap.get(targetOfflinePlayer.getUniqueId());
							if (profiles.getAutoVanish()==true) {
								AtalanteCraftClass.profile.setAutoVanish(profiles, false);
								AtalanteCraftClass.profile.saveProfile(player);
								AtalanteCraftClass.profile.createProfile(player);
								player.sendMessage(PluginAPI.getColor(messageTranslater.message(Message.PLAYER_UNVANISHED)));
								player.performCommand("avanish unvanish "+targetOfflinePlayer.getName());
							return true;}else {
								AtalanteCraftClass.profile.setAutoVanish(profiles, true);
								AtalanteCraftClass.profile.saveProfile(player);
								AtalanteCraftClass.profile.createProfile(player);
								player.sendMessage(PluginAPI.getColor(messageTranslater.message(Message.EVERYONE_VANISHED)));
							}
						}
					return true;}
				} //Argument everytime
				
				if (args[0].equalsIgnoreCase("unhide")) {
					if (args.length==1) {
						for (Player allPlayer:Bukkit.getOnlinePlayers()) {
							new VanishManager().playerShow(player.getUniqueId(), allPlayer.getUniqueId());
						}
					return true;} //Unhide yourself
					
					OfflinePlayer targetOfflinePlayer=Bukkit.getOfflinePlayer(args[1]);
					if (!targetOfflinePlayer.isOnline()) {
						player.sendMessage(PluginAPI.getColor(messageTranslater.message(Message.PLAYER_NOT_ONLINE).replace("<PLAYER_NAME>", targetOfflinePlayer.getName())));
					return true;}else if (targetOfflinePlayer==null) {return true;}else {
						for (Player allPlayer:Bukkit.getOnlinePlayers()) {
							new VanishManager().playerShow(player.getUniqueId(), allPlayer.getUniqueId());
						}
					return true;} //Unhide player name
				}
				
				/*
				 * 
				 * Testing example debug commands 
				 * 
				 * @Authors: OyunTarayici(ByEZberCime)
				 * 
				 * */
				
				if (player.hasPermission("atalantecraft.debug.*")) {
					if (args[0].equalsIgnoreCase("debug")) {
						
						if (args[1].equalsIgnoreCase("auto_vanish_unicode_19853289")) {
							Profiles profiles=Profiles.ProfileHashMap.get(player.getUniqueId());
							if (profiles.getAutoVanish()==true) {
								AtalanteCraftClass.profile.setAutoVanish(profiles, false);
								AtalanteCraftClass.profile.saveProfile(player);
								AtalanteCraftClass.profile.createProfile(player);
//								player.sendMessage("success false");
							return true;}else {
								AtalanteCraftClass.profile.setAutoVanish(profiles, true);
								AtalanteCraftClass.profile.saveProfile(player);
								AtalanteCraftClass.profile.createProfile(player);
//								player.sendMessage("success true");
							}
						return true;}
						
						/*
						 * 
						 * player hide method debug
						 * 
						 * */
						
						if (args[1].equalsIgnoreCase("player_vanish_unicode_12231234")) {
							for (Player players:Bukkit.getOnlinePlayers()) {
								new VanishManager().playerHide(player.getUniqueId(), players.getUniqueId());
							}
						return true;}
						
						/*
						 * 
						 * player show method debug
						 * 
						 * */
						
						if (args[1].equalsIgnoreCase("player_unvanish_unicode_12231234")) {
							for (Player players:Bukkit.getOnlinePlayers()) {
								new VanishManager().playerShow(player.getUniqueId(), players.getUniqueId());
							}
						return true;}
						
						if (args[1].equalsIgnoreCase("control_v_unicode_26421578")) {
							Profiles profiles=Profiles.ProfileHashMap.get(player.getUniqueId());
							if (profiles.getVanish()==1) {
								player.sendMessage("vanish false");
							return true;}else {
								player.sendMessage("vanish true");
							}
						return true;}
						
						if (args[1].equalsIgnoreCase("vanish_add_unicode_19309678")) {
							Profiles profiles=Profiles.ProfileHashMap.get(player.getUniqueId());
							int number=Integer.parseInt(args[2]);
							AtalanteCraftClass.profile.setVanish(profiles, number);
							AtalanteCraftClass.profile.saveProfile(player);
							AtalanteCraftClass.profile.createProfile(player);
							return true;}
						return true;} //Debug vanish profile
				return true;}else player.sendMessage(PluginAPI.getColor(messageTranslater.message(Message.NOT_PERMISSION)));
				
				
			}else return true;
		}else sender.sendMessage(PluginAPI.getColor(messageTranslater.message(Message.NOT_PERMISSION)));
	return true;}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("avanish")) {
			if (args.length==7) {
				return new ArrayList<>(); //Empty command argument not list player name
			} //Tab Argument 6
			else if (args.length==6) {
				return new ArrayList<>(); //Empty command argument not list player name
			} //Tab argument 5
			else if (args.length==5) {
				return new ArrayList<>(); //Empty command argument not list player name
			} //Tab argument 4
			else if (args.length==4) {
				return new ArrayList<>(); //Empty command argument not list player name
			} //Tab argument 3
			else if (args.length==3) {
				return new ArrayList<>(); //Empty command argument not list player name
			} //Tab argument 2
			else if (args.length==2) {
				if (args[0].equalsIgnoreCase("hide")||args[0].equalsIgnoreCase("unhide")||args[0].equalsIgnoreCase("everytime")) {
					ArrayList<String> onlinePlayers=new ArrayList<>();
					for (Player players:Bukkit.getOnlinePlayers()) {
						if (!onlinePlayers.contains(players.getName())) {
							onlinePlayers.add(players.getName());
						} //Online players list detect player name
					} //Online players for loop
					return onlinePlayers;
				} //Hide or unhide list online players
			} //Tab argument 1
			else if (args.length==1) {
				return Arrays.asList("hide","unhide","everytime");
			} //Tab argument 0
		}
		return null;
	}

}
