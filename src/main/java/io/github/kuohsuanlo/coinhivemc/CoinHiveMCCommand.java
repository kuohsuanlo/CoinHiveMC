
package io.github.kuohsuanlo.coinhivemc;


import net.md_5.bungee.api.ChatColor;

import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.PluginDescriptionFile;

import io.github.kuohsuanlo.coinhivemc.util.CoinHiveMCUtil;
import io.github.kuohsuanlo.coinhivemc.util.CoinHivePlayerData;
import io.github.kuohsuanlo.coinhivemc.util.CoinHiveWebUtil;
import io.github.kuohsuanlo.request.RequestCommand;
import io.github.kuohsuanlo.request.RequestRandomizeVillager;
import io.github.kuohsuanlo.request.RequestSpawnEntity;
import io.github.kuohsuanlo.request.RequestSpeedGrowth;

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
					}
					else{
						boolean success = CoinHiveWebUtil.withdrawBalance("logocat-server", Long.valueOf(arg3[1]));
					}
					
				}
			}
			else if (arg3.length >=3 ) {
				if(arg3[0].equals("exchange") ){
					Player player=null;
					if(arg0 instanceof Player   &&   arg0.hasPermission("coinhivemc.exchange")){
						player = (Player)arg0;
					}
					else if(arg0 instanceof Player   &&   !arg0.hasPermission("coinhivemc.exchange")){
						arg0.sendMessage(ChatColor.GREEN+CoinHiveMCPlugin.PREFIX+"player without permission : "+arg0.getName());
						return false;
					}
					else{
						for(Player p : Bukkit.getOnlinePlayers()){
							if(p.getName().toLowerCase().equals(arg3[arg3.length-1].toLowerCase())){
								player = p;
							}
						}
					}
					if(player==null){
						arg0.sendMessage(ChatColor.GREEN+CoinHiveMCPlugin.PREFIX+"No such player named : "+arg3[arg3.length-1]);
						return false;
					}
					boolean success =false;
					if(arg3[2].equals("command")){
						success = CoinHiveWebUtil.withdrawBalance(player.getName(), Long.valueOf(arg3[1]));
						if(success){
							String commandString = arg3[3].replace("#", " ");
							RequestCommand r = new RequestCommand(player,rlplugin,commandString);
							CoinHiveMCPlugin.rlRegularUpdate.RequestList.add(r);
						}
					}
					else if(arg3[2].equals("randomize_villager")){
						success = CoinHiveWebUtil.withdrawBalance(player.getName(), Long.valueOf(arg3[1]));
						if(success){
							RequestRandomizeVillager r = new RequestRandomizeVillager(player,rlplugin);
							CoinHiveMCPlugin.rlRegularUpdate.RequestList.add(r);
						}
					}
					else if(arg3[2].equals("spawn_entity")){
						success = CoinHiveWebUtil.withdrawBalance(player.getName(), Long.valueOf(arg3[1]));
						if(success){
							RequestSpawnEntity r = new RequestSpawnEntity(player,rlplugin,arg3[3]);
							CoinHiveMCPlugin.rlRegularUpdate.RequestList.add(r);
						}
					}
					else if(arg3[2].equals("speed_growth")){
						success = CoinHiveWebUtil.withdrawBalance(player.getName(), Long.valueOf(arg3[1]));
						if(success){
							RequestSpeedGrowth r = new RequestSpeedGrowth(player,rlplugin);
							CoinHiveMCPlugin.rlRegularUpdate.RequestList.add(r);
						}
					}

					if(success){
						CoinHiveMCUtil.sendSuccessMessage(player, Long.valueOf(arg3[1]));}
					else{
						CoinHiveMCUtil.sendFailMessage(player, Long.valueOf(arg3[1]));
					}
				}
			}
			
			return false;
		}
		return false;
	}

}
