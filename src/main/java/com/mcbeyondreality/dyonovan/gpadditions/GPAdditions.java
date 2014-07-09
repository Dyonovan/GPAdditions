package com.mcbeyondreality.dyonovan.gpadditions;

import java.util.ArrayList;
import java.util.List;

import me.ryanhamshire.GriefPrevention.GriefPrevention;
import me.ryanhamshire.GriefPrevention.Configuration.WorldConfig;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class GPAdditions extends JavaPlugin implements Listener {

	List<String> leftClick = new ArrayList<String>();
	List<String> rightClick = new ArrayList<String>();
	
	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		leftClick = GPAdditions.this.getConfig().getStringList("LeftClick");
		rightClick = GPAdditions.this.getConfig().getStringList("RightClick");
		getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public void onDisable() {

	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerInteract(PlayerInteractEvent event) {

		if (event.getAction() == Action.LEFT_CLICK_BLOCK) {

			for (Object o : leftClick) {

				String splitConfig[] = o.toString().split(":");

				if (splitConfig[1].toString().equalsIgnoreCase("*")) {

					if (event.getClickedBlock().getType().toString().equalsIgnoreCase(splitConfig[0])) {

						WorldConfig wc = GriefPrevention.instance.getWorldCfg(event.getPlayer().getWorld());

						if (wc.getContainersRules().Allowed(event.getClickedBlock().getLocation(), event.getPlayer(), true).Denied()) {
							event.setCancelled(true);
						}

					} 
				} else if (event.getClickedBlock().getState().getData().toString().equalsIgnoreCase((splitConfig[0] + "(" + splitConfig[1] + ")"))) {

					WorldConfig wc = GriefPrevention.instance.getWorldCfg(event.getPlayer().getWorld());
					if (wc.getContainersRules().Allowed(event.getClickedBlock().getLocation(), event.getPlayer(), true).Denied()) {
						event.setCancelled(true);
					}

				}
			}
		} else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			
			for (Object o : rightClick) {

				String splitConfig[] = o.toString().split(":");

				if (splitConfig[1].toString().equalsIgnoreCase("*")) {

					if (event.getClickedBlock().getType().toString().equalsIgnoreCase(splitConfig[0])) {

						WorldConfig wc = GriefPrevention.instance.getWorldCfg(event.getPlayer().getWorld());

						if (wc.getContainersRules().Allowed(event.getClickedBlock().getLocation(), event.getPlayer(), true).Denied()) {
							event.setCancelled(true);
						}

					} 
				} else if (event.getClickedBlock().getState().getData().toString().equalsIgnoreCase((splitConfig[0] + "(" + splitConfig[1] + ")"))) {

					WorldConfig wc = GriefPrevention.instance.getWorldCfg(event.getPlayer().getWorld());
					if (wc.getContainersRules().Allowed(event.getClickedBlock().getLocation(), event.getPlayer(), true).Denied()) {
						event.setCancelled(true);
					}

				}
			}
			
		} else if (event.getAction() == Action.PHYSICAL) {
			//event.getPlayer().sendMessage("Pressure Plate Activated");
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (cmd.getName().equalsIgnoreCase("gpareload")) {
			this.reloadConfig();
			leftClick = GPAdditions.this.getConfig().getStringList("LeftClick");
			rightClick = GPAdditions.this.getConfig().getStringList("RightClick");
			sender.sendMessage("Config Reload...");
			return true;
		}

		return false;
	}

}
