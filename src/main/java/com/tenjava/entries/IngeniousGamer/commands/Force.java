package com.tenjava.entries.IngeniousGamer.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;

import com.tenjava.entries.IngeniousGamer.events.EventTracker;
import com.tenjava.entries.IngeniousGamer.t3.Initialize;


public class Force implements SubCommand {
	@Override
	public boolean onCommand(Player player, String[] args) {
		if(args[0].equalsIgnoreCase("meteor")){
			Initialize.spawnComets();
		}
		else if(args[0].equalsIgnoreCase("quicksand")){
			EventTracker.spawnQuickSand(player);
		}
		else{
			player.sendMessage(ChatColor.RED+"That is not an event! Try 'meteor' or 'quicksand'");
		}
		return true;
	}

	@Override
	public String help(Player p) {
		return ChatColor.GOLD + "/worldevent force <event> " + ChatColor.DARK_RED + " - " + ChatColor.YELLOW + "force an event";
	}





}