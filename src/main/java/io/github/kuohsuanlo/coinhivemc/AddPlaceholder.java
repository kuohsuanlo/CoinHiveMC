package io.github.kuohsuanlo.coinhivemc;

import org.bukkit.entity.Player;

import io.github.kuohsuanlo.coinhivemc.util.CoinHiveMCUtil;
import me.clip.placeholderapi.external.EZPlaceholderHook;

public class AddPlaceholder extends EZPlaceholderHook{

	private CoinHiveMCPlugin fplugin;
	
	public AddPlaceholder(CoinHiveMCPlugin instance) {
		super(instance, "coinhive");
		// TODO Auto-generated constructor stub
		fplugin = instance;
	}

	@Override
	public String onPlaceholderRequest(Player p, String identifier) {
		if (identifier.equals("player_total")) {
			return "";
	    }
		return null;
	}


}