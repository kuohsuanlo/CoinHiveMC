package io.github.kuohsuanlo.request;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import io.github.kuohsuanlo.coinhivemc.CoinHiveMCPlugin;
import io.github.kuohsuanlo.coinhivemc.util.CoinHiveMCUtil;
import net.md_5.bungee.api.ChatColor;

public abstract class RequestCoinHive {
	public final Player player;
	public final CoinHiveMCPlugin plugin;
	public RequestCoinHive(Player p, CoinHiveMCPlugin pg){
		player = p;
		plugin = pg;
	}
	public void executeRequest(){
		CoinHiveMCUtil.playSuccessSoundEffect(player.getLocation());
		if(CoinHiveMCPlugin.Verbosity>=1){
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN+CoinHiveMCPlugin.PREFIX+"request executed by"+player.getPlayerListName());
		}
	}
}
