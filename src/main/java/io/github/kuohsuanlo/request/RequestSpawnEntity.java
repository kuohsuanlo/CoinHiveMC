package io.github.kuohsuanlo.request;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import io.github.kuohsuanlo.coinhivemc.CoinHiveMCPlugin;
import io.github.kuohsuanlo.coinhivemc.util.CoinHiveMCUtil;
import net.md_5.bungee.api.ChatColor;

public class RequestSpawnEntity extends RequestCoinHive{
	public EntityType type;
	public String typeString;
	public RequestSpawnEntity(Player p, CoinHiveMCPlugin pg, String eString) {
		super(p,pg);
		typeString = eString;
		type = EntityType.valueOf(eString);
	}
	@Override
	public void executeRequest() {
		super.executeRequest();
		if(type==null){
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED+CoinHiveMCPlugin.PREFIX+" error, no type called "+typeString);
		}
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable(){
		       public void run(){
		    	   player.getWorld().spawnEntity(player.getLocation(), type);
		    	   CoinHiveMCUtil.playNormalEffect(player.getLocation());
		       }
		     }, 0L);
		
	}

}
