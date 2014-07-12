package com.tenjava.entries.IngeniousGamer.events;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
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
			e.getBlock().setType(Material.AIR);
			try{
			Particles.sendParticle(Particles.FIREWORKS_SPARK, e.getPlayer().getLocation().add(0.5,0.5,0.5),(float) .5,(float) .5,(float) .5,(float) 0, 25);
			}catch(Exception ex){ex.printStackTrace();}
		}
	}
}
