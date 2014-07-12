package com.tenjava.entries.IngeniousGamer.commands;

import org.bukkit.entity.Player;

public interface SubCommand {

    public boolean onCommand(Player player, String[] args);

    public String help(Player p);
    
}