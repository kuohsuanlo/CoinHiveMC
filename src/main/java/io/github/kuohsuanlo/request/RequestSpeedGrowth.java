package io.github.kuohsuanlo.request;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;

public class RequestSpeedGrowth  extends RequestCoinHive{
	public static int pMax = Profession.values().length;
	public static Profession[] professions ;
	public static Random rng = new Random();
	public static int xDiff[] = {-1,0,1};
	public static int zDiff[] = {-1,0,1};
	public RequestSpeedGrowth(Player p) {
		super(p);
	}
	@Override
	public void executeRequest() {
		Chunk chunkCenter = player.getLocation().getChunk();
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				Chunk chunk = player.getWorld().getChunkAt(chunkCenter.getX()+xDiff[i], chunkCenter.getZ()+zDiff[j]);
				
				
			}
		}
		
	}

}
