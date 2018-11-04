package com.rickasheye.main;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.EventListener;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class supercomputer extends JavaPlugin implements Listener{
	
	float ver = 1.0f;
	Material replaceableMaterial = Material.STONE;
	int length = 5;
	boolean enablePillar;
	
	public void onEnable() {
		// TODO insert logic right here lol!
		getLogger().info("Pillars " + ver + " is disabled!");
		getLogger().info("registering events!");
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	
	public void onDisable() {
		// TODO insert logic when the plugin is disabled!
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("pillars") && player.hasPermission("pillars.identify")) {
			sender.sendMessage(ChatColor.YELLOW + "Running Pillars " + ver);
			return true;
		}else if(cmd.getName().equalsIgnoreCase("pillarBlock") && player.hasPermission("pillars.changeBlock")) {
			//Change the material of the pillar
			if (!args[0].equalsIgnoreCase(null)) {
				replaceableMaterial = Material.getMaterial(args[0]);
				sender.sendMessage(ChatColor.GREEN + "The Material of the pillar has changed to: " + args[0]);
			}else {
				sender.sendMessage("You need to enter args!");
			}
		}else if(cmd.getName().equalsIgnoreCase("pillarLength") && player.hasPermission("pillars.changeLength")) {
			//Change the length of the pillar
			if(!args[0].equalsIgnoreCase(null)) {
				length = Integer.parseInt(args[0]);
				sender.sendMessage(ChatColor.GREEN + "Pillar Height has changed to: " + length);
			}
		}else if(cmd.getName().equalsIgnoreCase("enablePillar") && player.hasPermission("pillars.enable")) {
			//enable or disable the pillar
			if(enablePillar == false) {
				sender.sendMessage(ChatColor.GREEN + "Pillar is enabled!");
				enablePillar = true;
			}else if(enablePillar == true) {
				sender.sendMessage(ChatColor.RED + "Pillar is disabled!");
				enablePillar = false;
			}
		}
		return false; 
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
	    if (enablePillar) {
			for (int i = 0; i < length; i++) {
				Location loc = event.getPlayer().getLocation();
				loc.setY(loc.getY() + 5 + i);
				Block b = loc.getBlock();
				b.setType(replaceableMaterial);
			} 
		}
	}
}
