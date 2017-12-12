package io.github.kuohsuanlo.request;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;

import io.github.kuohsuanlo.coinhivemc.CoinHiveMCPlugin;
import io.github.kuohsuanlo.coinhivemc.util.CoinHiveMCUtil;
import net.md_5.bungee.api.ChatColor;

public class RequestRandomizeVillager extends RequestCoinHive{	
	public static Profession[] professions = {
			Profession.BLACKSMITH,
			Profession.BUTCHER,
			Profession.FARMER,
			Profession.LIBRARIAN,
			Profession.PRIEST
	};
	public static int pMax = professions.length;
	public static double locationDiffMax = 10;
	public static Random rng = new Random();
	public static int xDiff[] = {-1,0,1};
	public static int zDiff[] = {-1,0,1};
	public RequestRandomizeVillager(Player p, CoinHiveMCPlugin pg) {
		super(p,pg);
	}
	@Override
	public void executeRequest() {
		super.executeRequest();
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable(){
		       public void run(){
		    	   	Chunk chunkCenter = player.getLocation().getChunk();
					for(int i=0;i<3;i++){
						for(int j=0;j<3;j++){
							Chunk chunk = player.getWorld().getChunkAt(chunkCenter.getX()+xDiff[i], chunkCenter.getZ()+zDiff[j]);
							randomizeChunkVillager(chunk);
							
						}
					}
		       }
		     }, 0L);
	}
	private void randomizeChunkVillager(Chunk chunk){
		Entity[] entitiesRandomized = chunk.getEntities();
		for(int e=0;e<entitiesRandomized.length;e++){
			if(entitiesRandomized[e].getLocation().distance(player.getLocation())>=locationDiffMax) continue;
			

			if(entitiesRandomized[e].getType().equals(EntityType.VILLAGER)){
				Villager oldVillager = (Villager) entitiesRandomized[e];
				boolean isAdult = oldVillager.isAdult();
				boolean canBreed = oldVillager.canBreed();
				int age = oldVillager.getAge();
				String customName = oldVillager.getCustomName();
				Location oldLocation = oldVillager.getLocation().clone();
				oldVillager.remove();
				
				Villager newVillager = (Villager) oldLocation.getWorld().spawnEntity(oldLocation, EntityType.VILLAGER);
				int maxTry = 5;
				int currentTry=0;
				while(newVillager.getProfession().equals(oldVillager.getProfession())  ||
						newVillager.getProfession().equals(Profession.NITWIT)){
					
					if(currentTry>=maxTry) break;
					currentTry++;
					newVillager.remove();
					newVillager = (Villager) oldVillager.getWorld().spawnEntity(oldVillager.getLocation(), EntityType.VILLAGER);
				
				}
				if(isAdult) newVillager.setAdult();
				else newVillager.setBaby();
				newVillager.setBreed(canBreed);
				newVillager.setAge(age);
				newVillager.setCustomName(customName);
				
				CoinHiveMCUtil.playNormalEffect(newVillager.getLocation());
				
				
			}
		}
	}
}
