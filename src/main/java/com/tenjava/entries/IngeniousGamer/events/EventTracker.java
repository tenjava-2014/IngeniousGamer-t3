package com.tenjava.entries.IngeniousGamer.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_7_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitTask;

import com.tenjava.entries.IngeniousGamer.entities.EntityComet;
import com.tenjava.entries.IngeniousGamer.entities.EntityQuickSand;
import com.tenjava.entries.IngeniousGamer.t3.TenJava;
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
			if(percentChance>19){//18
				try{
					Particles.sendParticle(Particles.FIREWORKS_SPARK, e.getBlock().getLocation().add(0.5,0.5,0.5),(float) .2,(float) .2,(float) .2,(float) 0.1, 25);
				}catch(Exception ex){ex.printStackTrace();}
				e.getBlock().getWorld().playSound(e.getBlock().getLocation(), Sound.CHEST_CLOSE, 1 , (float)1);
				ChestSpawn.spawnTreasure(e.getBlock());	
				e.getPlayer().sendMessage(ChatColor.GREEN+"You've found a buried chest!");
			}
		}
	}
	public static BukkitTask fallingSand;
	public static Map<Location, BukkitTask> quickSandMap = new HashMap<Location, BukkitTask>();
	public static void spawnQuickSand(final Player p){
		final ArrayList<Location> quicksand = new ArrayList<Location>();
		final Location origin = p.getLocation().getBlock().getLocation().add(0,-1,0);
		quicksand.add(origin);
		quicksand.add(origin.clone().add(1,0,0));
		quicksand.add(origin.clone().add(-1,0,0));
		quicksand.add(origin.clone().add(0,0,1));
		quicksand.add(origin.clone().add(0,0,-1));
		quicksand.add(origin.clone().add(1,0,1));
		quicksand.add(origin.clone().add(-1,0,-1));
		quicksand.add(origin.clone().add(-1,0,1));
		quicksand.add(origin.clone().add(1,0,-1));
		final ArrayList<EntityQuickSand> entityQuicksand = new ArrayList<EntityQuickSand>();
		for(Location l : quicksand){
			l.getBlock().setType(Material.WEB);
			l.clone().add(0,-1,0).getBlock().setType(Material.WEB);
			CraftWorld handle = (CraftWorld) l.getWorld();
			EntityQuickSand qs= new EntityQuickSand(handle.getHandle(),
					l.getX()+.5,
					l.getY()+.5,
					l.getZ()+.5,
					12,0);
			handle.getHandle().addEntity(qs, SpawnReason.CUSTOM);
			entityQuicksand.add(qs);
			
		}
		fallingSand = Bukkit.getScheduler().runTaskTimer(TenJava.plugin, new Runnable(){
			boolean check=true;
			public void run(){
				if(check){
					quickSandMap.put(origin, fallingSand);
					check=false;
				}
				if(p.getLocation().distance(origin)>=2){
					for(Location l : quicksand){
						l.getBlock().setType(Material.SAND);
						l.clone().add(0,-1,0).getBlock().setType(Material.SAND);
						for(EntityQuickSand eqs : entityQuicksand){
							eqs.die();
						}
					}
					quickSandMap.get(origin).cancel();
				}
				
			}
		}, 0, 20);
	}
	@EventHandler
	public void onPlayerMove(final PlayerMoveEvent e){
		if(e.getPlayer().getLocation().add(0,-1,0).getBlock().getType().equals(Material.SAND)){
			int percent = rand.nextInt((1000 - (0)) + 1) + (0);
			if(percent >= 999){//90
				spawnQuickSand(e.getPlayer());
			}
		}
	
	}
}