package io.github.kuohsuanlo.request;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.meowj.langutils.lang.LanguageHelper;

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
		    	   Entity spawned = player.getWorld().spawnEntity(player.getLocation(), type);
		    	   String localized_name = LanguageHelper.getEntityName(spawned.getType(), "zh_tw");
		    	   spawned.setCustomName(localized_name);
		    	   ((LivingEntity) spawned).setRemoveWhenFarAway(false);
		    	   CoinHiveMCUtil.playNormalEffect(player.getLocation());
		       }
		     }, 0L);
		
	}

}
