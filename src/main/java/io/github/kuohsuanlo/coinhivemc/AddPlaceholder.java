package io.github.kuohsuanlo.coinhivemc;

import java.text.DecimalFormat;

import javax.swing.text.NumberFormatter;

import org.bukkit.entity.Player;

import io.github.kuohsuanlo.coinhivemc.util.CoinHiveMCUtil;
import io.github.kuohsuanlo.coinhivemc.util.CoinHivePlayerData;
import me.clip.placeholderapi.external.EZPlaceholderHook;

public class AddPlaceholder extends EZPlaceholderHook{

	private CoinHiveMCPlugin fplugin;
	
	public AddPlaceholder(CoinHiveMCPlugin instance) {
		super(instance, "coinhivemc");
		// TODO Auto-generated constructor stub
		fplugin = instance;
	}

	@Override
	public String onPlaceholderRequest(Player p, String identifier) {
		String player_name = p.getName().toLowerCase();
		if (identifier.equals("hashes_per_coin")) {
			return CoinHiveMCPlugin.HashesPerCoin+"";
	    }
		else if (identifier.equals("player_total")) {
			CoinHivePlayerData data = CoinHiveMCPlugin.playerData.get(player_name);
			if(data == null) return "0";
			else return CoinHiveMCUtil.getCoinNumber(data.total);
	    }
		else if (identifier.equals("player_withdrawn")) {
			CoinHivePlayerData data = CoinHiveMCPlugin.playerData.get(player_name);
			if(data == null) return "0";
			else return CoinHiveMCUtil.getCoinNumber(data.withdrawn);
	    }
		else if (identifier.equals("player_balance")) {
			CoinHivePlayerData data = CoinHiveMCPlugin.playerData.get(player_name);
			if(data == null) return "0";
			else return CoinHiveMCUtil.getCoinNumber(data.balance);
	    }
		if (identifier.equals("team_total")) {
			CoinHivePlayerData data = CoinHiveMCPlugin.playerData.get(player_name+"-team");
			if(data == null) return CoinHiveMCPlugin.NOT_TEAM_LEADER;
			else return CoinHiveMCUtil.getCoinNumber(data.total);
	    }
		else if (identifier.equals("team_withdrawn")) {
			CoinHivePlayerData data = CoinHiveMCPlugin.playerData.get(player_name+"-team");
			if(data == null) return CoinHiveMCPlugin.NOT_TEAM_LEADER;
			else return CoinHiveMCUtil.getCoinNumber(data.withdrawn);
	    }
		else if (identifier.equals("team_balance")) {
			CoinHivePlayerData data = CoinHiveMCPlugin.playerData.get(player_name+"-team");
			if(data == null) return CoinHiveMCPlugin.NOT_TEAM_LEADER;
			else return CoinHiveMCUtil.getCoinNumber(data.balance);
	    }
		return "";
	}


}