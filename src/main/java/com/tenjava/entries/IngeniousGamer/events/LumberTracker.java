package com.tenjava.entries.IngeniousGamer.events;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class LumberTracker implements Listener {
	public static Random rand =new Random();
	@EventHandler
	public void onWoodBreak(BlockBreakEvent e){
		int percentChance = rand.nextInt((20 - (0)) + 1) + (0);
		if(percentChance>18){
			//TODO
		}
	}
}
