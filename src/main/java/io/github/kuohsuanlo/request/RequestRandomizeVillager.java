package io.github.kuohsuanlo.request;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;

public class RequestRandomizeVillager extends RequestCoinHive{
	public static int pMax = Profession.values().length;
	public static double locationDiffMax = 20;
	public static Profession[] professions ;
	public static Random rng = new Random();
	public static int xDiff[] = {-1,0,1};
	public static int zDiff[] = {-1,0,1};
	public RequestRandomizeVillager(Player p) {
		super(p);
	}
	@Override
	public void executeRequest() {
		Chunk chunkCenter = player.getLocation().getChunk();
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				Chunk chunk = player.getWorld().getChunkAt(chunkCenter.getX()+xDiff[i], chunkCenter.getZ()+zDiff[j]);
				
				Entity[] entitiesRandomized = chunk.getEntities();
				for(int e=0;e<entitiesRandomized.length;e++){
					if(entitiesRandomized[e].getLocation().distance(player.getLocation())>=locationDiffMax) continue;
					
					if(entitiesRandomized[e].getType().equals(EntityType.VILLAGER)){
						Villager villager = (Villager) entitiesRandomized[e];
						villager.setProfession(professions[rng.nextInt(pMax)]);
					}
				}
			}
		}
		
	}
}
