package com.tenjava.entries.IngeniousGamer.commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;



import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;


public class CommandExec implements CommandExecutor
{
	private Plugin plugin;
	private HashMap<String, SubCommand> commands;
	//Plugin Prefix**
	public static String pluginPrefix = ChatColor.DARK_GRAY+"["+ChatColor.DARK_GREEN+"World"+ChatColor.GREEN+"Events"+ChatColor.DARK_GRAY+"] "+ChatColor.GRAY;
	public void help(Player p){
		p.sendMessage("/worldevent <command> <args>");
		for(SubCommand v: commands.values()){
			p.sendMessage(ChatColor.AQUA +v.help(p));
		}
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd1, String commandLabel, String[] args){
		String cmd = cmd1.getName();
		//PluginDescriptionFile pdfFile = plugin.getDescription();

		Player player = null;
		if (sender instanceof Player) {
			player = (Player) sender;
		}
		else{
			System.out.println(pluginPrefix+"Use commands in game please!");
			return true;
		}

		if(cmd.equalsIgnoreCase("worldevent")){ 
			if(args == null || args.length < 1){
				player.sendMessage(ChatColor.DARK_GREEN +""+ ChatColor.BOLD +"World"+ChatColor.GREEN+""+ ChatColor.BOLD +"Events"+ChatColor.RESET);
				player.sendMessage(ChatColor.GOLD +"Type "+ChatColor.DARK_RED+"/worldevent help"+ChatColor.GOLD+" for help. Redundancy is redundant" );

				return true;
			}
			if(args[0].equalsIgnoreCase("help")){
				help(player);
				return true;
			}
			String sub = args[0];

			Vector<String> l  = new Vector<String>();
			l.addAll(Arrays.asList(args));
			l.remove(0);
			args = (String[]) l.toArray(new String[0]);
			if(!commands.containsKey(sub)){
				player.sendMessage(pluginPrefix+ChatColor.RED+"Command dosent exist.");
				player.sendMessage(ChatColor.DARK_GREEN +"Type "+ChatColor.DARK_RED+"/worldevent help"+ChatColor.DARK_GREEN+" for help.");
				return true;
			}
			try{

				commands.get(sub).onCommand( player,  args);
			}catch(Exception e){e.printStackTrace(); player.sendMessage(pluginPrefix+ChatColor.RED+"Error!");                
			player.sendMessage(ChatColor.DARK_GREEN +"Type "+ChatColor.DARK_RED+"/worldevent help"+ChatColor.DARK_GREEN+" for help.");
			}
			return true;
		}
		return false;
	}
	public CommandExec(Plugin plugin)
	{
		this.plugin = plugin;
		commands = new HashMap<String, SubCommand>();
		loadCommands();
	}

	private void loadCommands()
	{
		commands.put("force", new Force());
	}
	
	
}
