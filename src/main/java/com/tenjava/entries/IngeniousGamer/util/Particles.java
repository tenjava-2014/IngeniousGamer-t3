package com.tenjava.entries.IngeniousGamer.util;

import net.minecraft.server.v1_7_R3.PacketPlayOutWorldParticles;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Particles {
	//temp particle method until I add enum list
	public static void sendParticle(String effect, Location loc, float offsetX, float offsetY, float offsetZ, float speed, int count){
        for (Player player : Bukkit.getOnlinePlayers()) {
		PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(effect,offsetX,offsetY,offsetZ,(float)loc.getX(),(float)loc.getY(),(float)loc.getZ(),speed,count);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
        }
	}
}
