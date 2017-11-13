
package io.github.kuohsuanlo.coinhivemc;


import net.md_5.bungee.api.ChatColor;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

import io.github.kuohsuanlo.coinhivemc.util.CoinHiveMCUtil;

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
			
			if (arg3.length >=1 ) {
				if(arg3[0].equals("reload")  &&  arg0.hasPermission("coinhivemc.reload")){
					rlplugin.onReload();
					arg0.sendMessage(ChatColor.RED+CoinHiveMCPlugin.PREFIX+"ENABLED_WORLD : "+CoinHiveMCPlugin.enabledWorld.toString());
					arg0.sendMessage(ChatColor.RED+CoinHiveMCPlugin.PREFIX+" reloaded.");
			        
					return true;
				}
			}
			return false;
		}
		return false;
	}

}
