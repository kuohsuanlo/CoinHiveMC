package io.github.kuohsuanlo.request;

import org.bukkit.entity.Player;

public abstract class RequestCoinHive {
	public Player player;
	public RequestCoinHive(Player p){
		player = p;
	}
	public abstract void executeRequest();
}
