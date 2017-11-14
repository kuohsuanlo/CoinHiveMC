
package io.github.kuohsuanlo.coinhivemc;


import net.md_5.bungee.api.ChatColor;

import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

import io.github.kuohsuanlo.coinhivemc.util.CoinHiveMCUtil;
import io.github.kuohsuanlo.coinhivemc.util.CoinHivePlayerData;
import io.github.kuohsuanlo.coinhivemc.util.CoinHiveWebUtil;

/**
 * Handler for the /pos sample command.
 * @author SpaceManiac
 */
public class CoinHiveMCCommand implements CommandExecutor {
    public CoinHiveMCPlugin rlplugin;
	public CoinHiveMCCommand(CoinHiveMCPlugin plugin){
		rlplugin = plugin;
    }
	
	
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		if (arg2.equalsIgnoreCase("coinhivemc")) {
			
			if (arg3.length ==1 ) {
				if(arg3[0].equals("reload")  &&  arg0.hasPermission("coinhivemc.reload")){
					rlplugin.onReload();
					arg0.sendMessage(ChatColor.RED+CoinHiveMCPlugin.PREFIX+" reloaded.");
			        
					return true;
				}
				else if(arg3[0].equals("list")  &&  arg0.hasPermission("coinhivemc.list")){
				    for(Entry<String, CoinHivePlayerData> e : CoinHiveMCPlugin.playerData.entrySet()) {
				    	arg0.sendMessage(ChatColor.GREEN+e.toString());
				    }
				}
			}
			else if (arg3.length ==2 ) {
				if(arg3[0].equals("withdraw") ){
					if(arg0 instanceof Player   &&   arg0.hasPermission("coinhivemc.withdraw")){
						Player player = (Player)arg0;
						boolean success = CoinHiveWebUtil.withdrawBalance(player.getName(), Long.valueOf(arg3[1]));
						if(success){
							
						}
						
					}
					else{
						boolean success = CoinHiveWebUtil.withdrawBalance("logocat-server", Long.valueOf(arg3[1]));
						if(success){
						
						}
					
					}
					
				}
			}
			
			return false;
		}
		return false;
	}

}
