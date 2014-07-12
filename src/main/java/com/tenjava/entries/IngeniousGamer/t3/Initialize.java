package com.tenjava.entries.IngeniousGamer.t3;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.tenjava.entries.IngeniousGamer.util.RandomCollection;

public class Initialize {
	public static ItemStack melon = new ItemStack(Material.MELON_BLOCK, 2, (short)0);
	public static ItemStack chestpiece = new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1, (short)0);
	public static ItemStack diamond = new ItemStack(Material.DIAMOND, 1, (short)0);
	public static ItemStack reward4 = new ItemStack(Material.GLASS, 1, (short)0);
	public static ItemStack reward5 = new ItemStack(Material.GLASS, 1, (short)0);

	public static RandomCollection<String> items = new RandomCollection<String>();
	public static void initEverything(){
		items.add(1.0, melon);
		items.add(0.1, diamond);
		items.add(0.4, chestpiece);
	}
}
