package io.github.kuohsuanlo.coinhivemc;

import org.bukkit.entity.Player;

public class PlayerWithdrawRequest {
	public final long countDown;
	public final Player player;
	public PlayerWithdrawRequest(Player p, long cd){
		countDown = cd;
		player= p;
	}
}
