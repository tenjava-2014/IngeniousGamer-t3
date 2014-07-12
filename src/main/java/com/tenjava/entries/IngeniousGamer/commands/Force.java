package com.tenjava.entries.IngeniousGamer.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;


public class Force implements SubCommand {
	@Override
	public boolean onCommand(Player player, String[] args) {
		//TODO FORCE EVENTS
		return true;
	}

	@Override
	public String help(Player p) {
		return ChatColor.GOLD + "/worldevent force <event> " + ChatColor.DARK_RED + " - " + ChatColor.YELLOW + "force an event";
	}





}