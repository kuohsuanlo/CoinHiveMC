package io.github.kuohsuanlo.request;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class RequestSpawnEntity extends RequestCoinHive{
	public EntityType type;
	public RequestSpawnEntity(Player p, EntityType e) {
		super(p);
		type = e;
	}
	@Override
	public void executeRequest() {
		player.getWorld().spawnEntity(player.getLocation(), type);
	}

}
