package com.tenjava.entries.IngeniousGamer.events;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.tenjava.entries.IngeniousGamer.triggered.ChestSpawn;
import com.tenjava.entries.IngeniousGamer.triggered.TreeFire;
import com.tenjava.entries.IngeniousGamer.util.Particles;

public class EventTracker implements Listener {
	public static Random rand =new Random();
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e){
		if(e.getBlock().getType().equals(Material.LOG)||e.getBlock().getType().equals(Material.LOG_2)){
			int percentChance = rand.nextInt((20 - (0)) + 1) + (0);
			if(percentChance>18){//18
				try{
					Particles.sendParticle(Particles.FIREWORKS_SPARK, e.getBlock().getLocation().add(0.5,0.5,0.5),(float) .5,(float) .5,(float) .5,(float) 0.1, 50);
				}catch(Exception ex){ex.printStackTrace();}
				e.getBlock().getWorld().playSound(e.getBlock().getLocation(), Sound.BLAZE_DEATH, 1 , (float)0.5);
				TreeFire.setOnFire(e.getBlock());	
				e.getPlayer().sendMessage(ChatColor.RED+"Oh no! It seems as if the tree was too dry!");
			}
		}
		else if(e.getBlock().getType().equals(Material.SAND)){
			int percentChance = rand.nextInt((20 - (0)) + 1) + (0);
			if(percentChance>0){//18
				try{
					Particles.sendParticle(Particles.FIREWORKS_SPARK, e.getBlock().getLocation().add(0.5,0.5,0.5),(float) .2,(float) .2,(float) .2,(float) 0.1, 25);
				}catch(Exception ex){ex.printStackTrace();}
				e.getBlock().getWorld().playSound(e.getBlock().getLocation(), Sound.CHEST_CLOSE, 1 , (float)1);
				ChestSpawn.spawnTreasure(e.getBlock());	
				e.getPlayer().sendMessage(ChatColor.GREEN+"You've found a buried chest!");
			}
		}
	}
}
