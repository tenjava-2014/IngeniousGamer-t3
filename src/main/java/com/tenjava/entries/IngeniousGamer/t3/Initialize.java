package com.tenjava.entries.IngeniousGamer.t3;

import java.lang.reflect.Method;
import java.util.Random;








import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
			public void run(){
				int chance = rand.nextInt((100 - (0)) + 1) + (0);
				if(chance >=96){
					spawnComets();
				}				

			}
		}, 0, 200);
	}
	public static BukkitTask cometTask;
	public static void spawnComets(){
		Bukkit.broadcastMessage(ChatColor.BLUE+"["+ChatColor.WHITE+"Weather.com"+ChatColor.BLUE+"] "+ChatColor.RED+"Looks like an meteor shower is coming!");
		cometTask = Bukkit.getScheduler().runTaskTimer(TenJava.plugin, new Runnable(){
			Random rand = new Random();
			boolean check=true;
			int loops = 15;
			int count = 1;
			public void run(){
				if(check){
					loops = rand.nextInt((15 - (0)) + 1) + (0);
					check=false;
				}
				Player player = Bukkit.getOnlinePlayers()[rand.nextInt((Bukkit.getOnlinePlayers().length - (0))) + (0)];
				Location cometSpawn = player.getLocation();
				CraftWorld handle = (CraftWorld) cometSpawn.getWorld();
				int randomZ = rand.nextInt((60 - (-60)) + 1) + (-60);
				int randomX = rand.nextInt((60 - (-60)) + 1) + (-60);
				int randomVelX = rand.nextInt((1 - (-1)) + 1) + (-1);
				int randomVelZ = rand.nextInt((1 - (-1)) + 1) + (-1);
				
				for(int i=0;i<7;i++){
					int scatterX = rand.nextInt((5 - (-5)) + 1) + (-5);
					int scatterZ = rand.nextInt((5 - (-5)) + 1) + (-5);
				EntityComet fr= new EntityComet(handle.getHandle(),
						(cometSpawn.getX()+randomX)+scatterX,
						(cometSpawn.getY()+60),
						(cometSpawn.getZ()+randomZ)+scatterZ,
						173,0);
				handle.getHandle().addEntity(fr, SpawnReason.CUSTOM);
				fr.ticksLived=1;
				fr.motX=randomVelX;
				//fr.motY=1.2;
				fr.motZ=-(randomVelZ);
			    fr.positionChanged=true;
			    fr.velocityChanged=true;
				}
				count++;
				if(count>=loops){
					cometTask.cancel();
				}
			}
		}, 0, 60);
		
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
