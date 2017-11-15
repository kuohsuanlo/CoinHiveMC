package io.github.kuohsuanlo.coinhivemc;


import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import io.github.kuohsuanlo.coinhivemc.util.CoinHiveMCUtil;
import io.github.kuohsuanlo.coinhivemc.util.CoinHivePlayerData;
import net.md_5.bungee.api.ChatColor;

public class CoinHiveMCListener implements Listener {
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		Player player = event.getPlayer();
		if(!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
		if(!player.getInventory().getItemInMainHand().getType().equals(Material.MONSTER_EGG)) return;
		
		if(!player.hasPermission("coinhivemc.exempt.place")  &&  CoinHiveMCPlugin.SpawnerEggNeedPermission){
			if(!CoinHiveMCUtil.getAllTrustUUID(event.getClickedBlock().getLocation()).contains(player.getUniqueId().toString())){
				player.sendMessage(ChatColor.RED+CoinHiveMCPlugin.PREFIX+CoinHiveMCPlugin.NO_BUILD_PERMISSION);
				event.setCancelled(true);
				return;
			}
			if(CoinHiveMCUtil.isPublicClaim(CoinHiveMCUtil.getClaimFromLocation(event.getClickedBlock().getLocation()))){
				player.sendMessage(ChatColor.RED+CoinHiveMCPlugin.PREFIX+CoinHiveMCPlugin.IS_PUBLIC);
				event.setCancelled(true);
				return;
			}
		}
		
	}
	
  
}
