package com.tenjava.entries.IngeniousGamer.t3;

import java.lang.reflect.Method;
import java.util.Random;







import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import com.tenjava.entries.IngeniousGamer.entities.EntityComet;
import com.tenjava.entries.IngeniousGamer.util.RandomCollection;

public class Initialize {
	public static ItemStack melon = new ItemStack(Material.MELON_BLOCK, 2, (short)0);
	public static ItemStack chestpiece = new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1, (short)0);
	public static ItemStack diamond = new ItemStack(Material.DIAMOND, 1, (short)0);
	public static ItemStack gold = new ItemStack(Material.GOLD_INGOT, 3, (short)0);
	public static ItemStack iron = new ItemStack(Material.IRON_INGOT, 2, (short)0);

	private static BukkitTask randomTask;
	public static RandomCollection<String> items = new RandomCollection<String>();
	public static void initEverything(){
		items.add(1.0, melon);
		items.add(0.1, diamond);
		items.add(0.2, chestpiece);
		items.add(0.4, iron);
		items.add(0.6, iron);
		patch();
		
		
		randomTask = Bukkit.getScheduler().runTaskTimer(TenJava.plugin, new Runnable(){
			Random rand = new Random();
			boolean check=true;
			Player player = Bukkit.getOnlinePlayers()[rand.nextInt((Bukkit.getOnlinePlayers().length - (0)) + 1) + (0)];
			public void run(){
				if(check){
					//cometMap.put(fr, tid); Add cometMap for tracking tasks when implemented
					check=false;
				}
				Location cometSpawn = player.getLocation();
				CraftWorld handle = (CraftWorld) cometSpawn.getWorld();
				EntityComet fr= new EntityComet(handle.getHandle(),
						cometSpawn.getX(),
						cometSpawn.getY()+60,
						cometSpawn.getZ(),
						173,0);
				handle.getHandle().addEntity(fr, SpawnReason.CUSTOM);
				fr.ticksLived=1;
				int randomZ = rand.nextInt((60 - (-60)) + 1) + (-60);
				int randomY = rand.nextInt((0 - (0)) + 1) + (0);
				int randomX = rand.nextInt((60 - (-60)) + 1) + (-60);
				int dir;
				if(randomX==1){dir = 1;}
				else{dir = -1;}
				//fr.setPosition(cometSpawn.getX()+(-60*dir), cometSpawn.getY()+10, cometSpawn.getZ()+randomZ);
				//fr.motX=3.4*dir;
				//fr.motY=1.2;
				//fr.motZ=-(randomZ/5.8)*dir;
			    fr.positionChanged=true;
			    fr.velocityChanged=true;
			}
		}, 0, 200);
	}
	public static void patch() {
		   try {
		      Method a = net.minecraft.server.v1_7_R3.EntityTypes.class.getDeclaredMethod("a", Class.class, String.class, int.class);
		      a.setAccessible(true);
		      a.invoke(a, EntityComet.class, "Comet", 504);
		   } catch (Exception ignored) {
			  //Already Registered.
		   }
	}
}
