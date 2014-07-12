package com.tenjava.entries.IngeniousGamer.triggered;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;

import com.tenjava.entries.IngeniousGamer.t3.Initialize;
import com.tenjava.entries.IngeniousGamer.t3.TenJava;


public class ChestSpawn {

	public static void spawnTreasure(final Block b){
		Bukkit.getScheduler().runTaskLater(TenJava.plugin,new Runnable(){
			public void run(){
				b.setType(Material.CHEST);
				Chest chest = (Chest)b.getState();
				for(int i=0;i<5;i++){
				chest.getInventory().addItem(Initialize.items.next());
				}
			}
		},1);
	}
}
