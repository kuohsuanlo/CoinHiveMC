package io.github.kuohsuanlo.request;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import io.github.kuohsuanlo.coinhivemc.CoinHiveMCPlugin;
import net.md_5.bungee.api.ChatColor;

public class RequestCommand extends RequestCoinHive{
	public String command;
	public RequestCommand(Player p, CoinHiveMCPlugin pg, String c) {
		super(p,pg);
		command = c;
	}
	@Override
	public void executeRequest() {
		super.executeRequest();
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable(){
		       public void run(){
		    	   command = command.replace("{player}", player.getName());
		   		//Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), command);
		    	if(!player.isOp()){
		    		player.setOp(true);
		    		player.performCommand(command);
			   		player.setOp(false);
		    	}
		    	else{
		    		player.performCommand(command);
		    	}
		   		
		       }
		     }, 0L);
		
		
	}
}
