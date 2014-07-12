package com.tenjava.entries.IngeniousGamer.triggered;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;

public class ChestSpawn {
	public static void spawnTreasure(Block b){
		b.setType(Material.CHEST);
		Chest chest = (Chest)b;
		//TODO ADD RANDOM REWARDS
		//chest.getInventory().addItem(null);
	}
}
