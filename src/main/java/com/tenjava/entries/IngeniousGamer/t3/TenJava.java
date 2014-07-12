package com.tenjava.entries.IngeniousGamer.t3;


import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.tenjava.entries.IngeniousGamer.events.EventTracker;

public class TenJava extends JavaPlugin {
	public static Plugin plugin;

	public void onEnable(){
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new EventTracker(), this);
		plugin =  this;
		Initialize.initEverything();

	}
	public void onDisable(){
		
	}
}