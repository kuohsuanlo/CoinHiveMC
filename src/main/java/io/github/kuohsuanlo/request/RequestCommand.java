package io.github.kuohsuanlo.request;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class RequestCommand extends RequestCoinHive{
	public String command;
	public RequestCommand(Player p, String c) {
		super(p);
		command = c;
	}
	@Override
	public void executeRequest() {
		command = command.replace("{player}", player.getName());
		Bukkit.getServer().dispatchCommand(player, command);
	}
}
