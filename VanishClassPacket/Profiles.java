package com.me.OyunTarayici.AtalanteCraftPlugin.Utils;

import java.util.HashMap;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Profiles {

	public static HashMap<UUID, Profiles> ProfileHashMap=new HashMap<UUID, Profiles>();
	
	private String name;
	private int vanish=0;
	private Boolean autoVanish=false;
	
	public Profiles(int vanish, Boolean autoVanish) {
		this.vanish=vanish;;
		this.autoVanish=autoVanish;
	}
	
}
