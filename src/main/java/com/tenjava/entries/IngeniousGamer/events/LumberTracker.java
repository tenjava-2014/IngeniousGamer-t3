package com.tenjava.entries.IngeniousGamer.events;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.tenjava.entries.IngeniousGamer.util.Particles;

public class LumberTracker implements Listener {
	public static Random rand =new Random();
	@EventHandler
	public void onWoodBreak(BlockBreakEvent e){
		int percentChance = rand.nextInt((20 - (0)) + 1) + (0);
		if(percentChance>0){//18
			Particles.sendParticle("fireworksSpark", e.getBlock().getLocation().add(0.5,0.5,0.5),(float) .5,(float) .5,(float) .5,(float) 0, 25);
		}
	}
}
