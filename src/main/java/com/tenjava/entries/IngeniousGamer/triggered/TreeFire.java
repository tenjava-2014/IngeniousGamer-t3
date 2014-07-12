package com.tenjava.entries.IngeniousGamer.triggered;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class TreeFire {
	public static void setOnFire(Block b){
		for(int i=0;i<3;i++){
			Location origin = b.getLocation().add(0,i,0);
			origin.clone().add(1,0,0).getBlock().setType(Material.FIRE);
			origin.clone().add(0,0,1).getBlock().setType(Material.FIRE);
			origin.clone().add(-1,0,0).getBlock().setType(Material.FIRE);
			origin.clone().add(0,0,-1).getBlock().setType(Material.FIRE);
		}
	}
}
