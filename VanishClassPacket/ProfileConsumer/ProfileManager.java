package com.me.OyunTarayici.AtalanteCraftPlugin.Utils.ProfileConsumer;

import java.io.IOException;
import org.bukkit.entity.Player;
import com.me.OyunTarayici.AtalanteCraftPlugin.Files.FileProfile;
import com.me.OyunTarayici.AtalanteCraftPlugin.Utils.Profiles;

public class ProfileManager implements IProfile{

	public void createProfile(Player player) {
		if (!player.hasPlayedBefore()) {
			Profiles.ProfileHashMap.put(player.getUniqueId(), new Profiles(0,false));
			FileProfile.profileConfig.set("profiles."+player.getUniqueId()+".vanish", 0);
			FileProfile.profileConfig.set("profiles."+player.getUniqueId()+".auto_vanish", false);
			try {
				FileProfile.profileConfig.save(FileProfile.profileFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		return;}else {
			int level=FileProfile.profileConfig.getInt("profiles."+player.getUniqueId()+".vanish");
			Boolean autoVanish=FileProfile.profileConfig.getBoolean("profiles."+player.getUniqueId()+".auto_vanish");
			Profiles.ProfileHashMap.put(player.getUniqueId(), new Profiles(level,autoVanish));
		}
	}

	public void saveProfile(Player player) {
		Profiles profiles=Profiles.ProfileHashMap.get(player.getUniqueId());
		if (Profiles.ProfileHashMap.containsKey(player.getUniqueId())) {
			FileProfile.profileConfig.set("profiles."+player.getUniqueId()+".vanish", profiles.getVanish());
			FileProfile.profileConfig.set("profiles."+player.getUniqueId()+".auto_vanish", profiles.getAutoVanish());
			try {
				FileProfile.profileConfig.save(FileProfile.profileFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Profiles.ProfileHashMap.remove(player.getUniqueId());
		return;}
	}

	public void setVanish(Profiles profiles, int point) {
		profiles.setVanish(point);
	}
	
	@Override
	public void setAutoVanish(Profiles profiles, Boolean autoVanish) {
		profiles.setAutoVanish(autoVanish);
	}

}
